package dcc.tp2.enseignantservice.repository;

import dcc.tp2.enseignantservice.entities.Enseignant;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test") //charger tout conf necessaire pour test (db
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // pour un reel bd machie creer une bd f memoire
class EnseignantRepositoryTest {

    @Autowired
    private EnseignantRepository enseignantRepository;

        //avnt de demarer test unitaire kndir chihaja (anzid chihaja f base donne)  (had methode lwla li ktcharga)
    @BeforeEach
    void SetUp(){
        enseignantRepository.save(new Enseignant(null, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant"));
        enseignantRepository.save(new Enseignant(null, "najlae", "bk", "p3213", "bk@gmail.com", "123", "info", "Enseignant"));
    }

    @Test
    void findEnseignantByEmail() {
        String email = "douae@gmail.com";
        Enseignant enseignant = new Enseignant(null, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant");
        Enseignant result = enseignantRepository.findEnseignantByEmail(email);

        //les assertins kietiwna des mothodes bach nverifiw les me

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(enseignant);


    }

    @Test
    void Not_findEnseignantByEmail() {
        String email = "tes@gmail.com";
        Enseignant result = enseignantRepository.findEnseignantByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();
    }


    }