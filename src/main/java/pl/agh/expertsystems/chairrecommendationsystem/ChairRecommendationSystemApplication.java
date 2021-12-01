package pl.agh.expertsystems.chairrecommendationsystem;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChairRecommendationSystemApplication {

    public static void main(String[] args) {
        Query q1 =
                new Query(
                        "consult",
                        new Term[] {new Atom("src/main/prolog/chair-recommendation-system.pl")}
                );
        System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
        SpringApplication.run(ChairRecommendationSystemApplication.class, args);
    }

}
