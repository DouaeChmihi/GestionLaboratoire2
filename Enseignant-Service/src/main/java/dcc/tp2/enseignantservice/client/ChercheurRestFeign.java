package dcc.tp2.enseignantservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "Chercheur-service", url = "CHERCHEUR-SERVICE")
public interface ChercheurRestFeign {

    @GetMapping("/Chercheurs/Enseignant/{id}")
    @CircuitBreaker(name = "count-chercheur",fallbackMethod ="Chercheur_fallbackMethod")
    Long nb_chercheur_Enseignant(@PathVariable Long id);

    default String Chercheur_fallbackMethod(Long id,Exception e){
        return "Chercheur Fallback response due to failure";    }

}
