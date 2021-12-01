package pl.agh.expertsystems.chairrecommendationsystem.controllers;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;
import org.springframework.web.bind.annotation.*;
import pl.agh.expertsystems.chairrecommendationsystem.dto.Person;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PrologDatabaseController {
    private final AtomicInteger counter = new AtomicInteger(1);

    @GetMapping("/recommend/ergonomic-chair")
    public String recommend_ergonomic_chair() {
        List<String> result = executePeopleQuery("recommend_ergonomic_chair_people");

        return result.toString();
    }

    @GetMapping("/recommend/family-discount")
    public String recommend_family_discount() {
        List<String> result = executePeopleQuery("recommend_family_discount_people");

        return result.toString();
    }

    @GetMapping("/recommend/massage-chair")
    public String recommend_massage_chair() {
        List<String> result = executePeopleQuery("recommend_massage_chair_people");

        return result.toString();
    }

    @GetMapping("/recommend/portable-chair")
    public String recommend_portable_chair() {
        List<String> result = executePeopleQuery("recommend_portable_chair_people");

        return result.toString();
    }

    @GetMapping("/recommend/leasing-chair")
    public String recommend_leasing_chair() {
        List<String> result = executePeopleQuery("recommend_leasing_chair_people");

        return result.toString();
    }

    @GetMapping("/warn-hump")
    public String hump() {
        List<String> result = executePeopleQuery("hump_people");

        return result.toString();
    }

    @GetMapping("/too_big_discount")
    public String too_big_discount() {
        List<String> result = executePeopleQuery("too_big_discount_people");

        return result.toString();
    }

    @GetMapping("/gamer_chair")
    public String gamer_chair() {
        List<String> result = executePeopleQuery("gamer_chair_people");

        return result.toString();
    }

    //9 missing in prolog?
//    @GetMapping("/chair_without_backrest")
//    public String chair_without_backrest() {
//        List<String> result = executePeopleQuery("chair_without_backrest_people");
//
//        return result.toString();
//    }

    @GetMapping("/chair_without_backrest")
    public String chair_without_backrest() {
        List<String> result = executePeopleQuery("chair_without_backrest_people");

        return result.toString();
    }

    @PostMapping("/person")
    public void add_person(@RequestBody Person person){
        String queryTemplate = "assert(person(%d, %s, %s))";

        Integer id = counter.incrementAndGet();

        Query q =
                new Query(
                        String.format(queryTemplate, id,
                                person.getName(), person.getSurname())
                );

        q.hasSolution();

        Integer budget = person.getBudget();

        if(budget !=  null) {
            queryTemplate = "assert(budget(%d, %s))";
            q = new Query(String.format(queryTemplate, id, budget));
            q.hasSolution();
        }

        Integer discount = person.getDiscount();

        if(discount !=  null) {
            queryTemplate = "assert(discount(%d, %d))";
            q = new Query(String.format(queryTemplate, id, discount));
            q.hasSolution();
        }

        Integer sittingTime = person.getSittingTime();

        if(sittingTime !=  null) {
            queryTemplate = "assert(sitting_time(%d, %d))";
            q = new Query(String.format(queryTemplate, id, sittingTime));
            q.hasSolution();
        }

        String age = person.getAge();

        if(age !=  null) {
            queryTemplate = "assert(age(%d, %d))";
            q = new Query(String.format(queryTemplate, id, age));
            q.hasSolution();
        }

        Integer height = person.getHeight();
        Integer weight = person.getWeight();

        if(weight !=  null && height !=  null) {
            queryTemplate = "assert(measure(%d, %d, %d))";
            q = new Query(String.format(queryTemplate, id, weight, height));
            q.hasSolution();
        }

        String sex = person.getSex();

        if(sex !=  null) {
            queryTemplate = "assert(sex(%d, %s))";
            q = new Query(String.format(queryTemplate, id, sex));
            q.hasSolution();
        }

        Boolean itJob = person.getItJob();

        if(itJob != null && itJob){
            queryTemplate = "assert(it_job(%d))";
            q = new Query(String.format(queryTemplate, id));
            q.hasSolution();
        }
    }

    @GetMapping("/person")
    public String people() {
        List<String> result = executePeopleQuery("person");

        return result.toString();
    }

    private List<String> executePeopleQuery(String queryName){
        Variable X = new Variable("X");
        Variable Name = new Variable("Name");
        Variable Surname = new Variable("Surname");
        Query q =
                new Query(
                        queryName,
                        new Term[] {X, Name, Surname}
                );

        List<String> result = new ArrayList<>();
        Map<String,Term>[] solutions = q.allSolutions();
        for ( int i=0 ; i < solutions.length ; i++ ) {
            result.add(solutions[i].get("X") + ": " + solutions[i].get("Name") + " " + solutions[i].get("Surname"));
        }

        return result;
    }
}
