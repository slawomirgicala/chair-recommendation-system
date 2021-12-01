package pl.agh.expertsystems.chairrecommendationsystem.controllers;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;
import org.springframework.web.bind.annotation.*;
import pl.agh.expertsystems.chairrecommendationsystem.dto.Person;

import java.util.ArrayList;
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
        Query q =
                new Query(
                        String.format(queryTemplate, counter.incrementAndGet(),
                                person.getName(), person.getSurname())
                );
        q.hasSolution();
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
