package pl.agh.expertsystems.chairrecommendationsystem.controllers;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;
import org.springframework.web.bind.annotation.*;
import pl.agh.expertsystems.chairrecommendationsystem.dto.Person;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PrologDatabaseController {
    private final AtomicInteger counter = new AtomicInteger(1);

    @GetMapping("/chair_without_backrest")
    public String chair_without_backrest(@RequestParam(value = "name", defaultValue = "World") String name) {
        Variable X = new Variable("X");
        Query q =
                new Query(
                        "chair_without_backrest",
                        new Term[] {X}
                );

        String result = "";
        Map<String,Term>[] solutions = q.allSolutions();
        for ( int i=0 ; i < solutions.length ; i++ ) {
            result = result + solutions[i].get("X");
        }

        return result;
    }


    @PostMapping("/add_person")
    public void add_person(@RequestBody Person person){
        String queryTemplate = "assert(person(%d, %s, %s))";
        Query q =
                new Query(
                        String.format(queryTemplate, counter.incrementAndGet(),
                                person.getName(), person.getSurname())
                );
        q.hasSolution();
    }

    @GetMapping("/people")
    public String people() {
        Variable X = new Variable("X");
        Variable Name = new Variable("Name");
        Variable Surname = new Variable("Surname");
        Query q =
                new Query(
                        "person",
                        new Term[] {X, Name, Surname}
                );

        String result = "";
        Map<String,Term>[] solutions = q.allSolutions();
        for ( int i=0 ; i < solutions.length ; i++ ) {
            result = result + solutions[i].get("X") + ": " + solutions[i].get("Name") + " " + solutions[i].get("Surname") + "\n";
        }

        return result;
    }
}
