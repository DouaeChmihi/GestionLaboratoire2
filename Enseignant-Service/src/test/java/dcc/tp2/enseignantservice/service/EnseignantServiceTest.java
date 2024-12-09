package dcc.tp2.enseignantservice.service;

import dcc.tp2.enseignantservice.client.ChercheurRestFeign;
import dcc.tp2.enseignantservice.client.ProjetRestFeign;
import dcc.tp2.enseignantservice.entities.Enseignant;
import dcc.tp2.enseignantservice.repository.EnseignantRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class) //poru creer mocks
class EnseignantServiceTest {

    //  Mock repo et mock rest client : endo gae les methodes d repo w d rest client mais comportement null

    @Mock
    private EnseignantRepository enseignantRepository;
    @Mock
    private ChercheurRestFeign chercheurRestFeign;
    @Mock
    private ProjetRestFeign projetRestFeign;

    @InjectMocks   //katssmt3ml des mocks machi des vrais chercheurs..
    private EnseignantService enseignantService;
    @Test
    void create_Enseignant() {
        Enseignant enseignant = new Enseignant(null, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant");
        Enseignant enseignantSaved = new Enseignant(1L, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant");

        //action
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignantSaved);

        //tester
        Enseignant result = enseignantService.Create_Enseignant(enseignant);

        //verification
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantSaved);

    }

    @Test
    void getAll_Enseignant() {

        List<Enseignant> enseignantList = List.of(
            new Enseignant(1L, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant"),
            new Enseignant(2L, "najlae", "bk", "p3213", "bk@gmail.com", "123", "info", "Enseignant")
        );
            Mockito.when(enseignantRepository.findAll()).thenReturn(enseignantList);
            List<Enseignant> result = enseignantService.GetAll_Enseignant();
            AssertionsForClassTypes.assertThat(result).isNotNull();
            AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantList);

    }

    @Test
    void get_EnseignantByID() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant");
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));


        Enseignant result = enseignantService.Get_EnseignantByID(id);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);

    }

    @Test
    void Not_get_EnseignantByID() {
        Long id = 100L;
        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.empty());
        Enseignant result = enseignantService.Get_EnseignantByID(id);
        AssertionsForClassTypes.assertThat(result).isNull();

    }

    @Test
    void findByEmail() {

        String email= "douae@gmail.com";
        Enseignant enseignant = new Enseignant(1L, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant");
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(enseignant);


        Enseignant result = enseignantService.FindByEmail(email);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignant);
    }

    @Test
    void Not_findByEmail() {

        String email= "la@gmail.com";
        Mockito.when(enseignantRepository.findEnseignantByEmail(email)).thenReturn(null);
        Enseignant result = enseignantService.FindByEmail(email);
        AssertionsForClassTypes.assertThat(result).isNull();
    }

    @Test
    void update_Enseignant() {
        Long id = 1L;
        Enseignant enseignant = new Enseignant(1L, "douae", "chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant");
        Enseignant enseignantModified = new Enseignant(1L, "douae", "dh-chmihi", "p3213", "douae@gmail.com", "123", "info", "Enseignant");

        Mockito.when(enseignantRepository.findById(id)).thenReturn(Optional.of(enseignant));
        Mockito.when(enseignantRepository.save(enseignant)).thenReturn(enseignantModified);

        Enseignant result = enseignantService.Update_Enseignant(enseignantModified, id);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(enseignantModified);


    }

    @Test
    void delete_Enseignant() {
        Long id = 1L;
        enseignantService.Delete_Enseignant(id);
    }

    @Test
    void statistique() {
        Long id = 1L;

        Map<String, Long> Statistiques = new HashMap<>();
        Statistiques.put("nombre de projet",2L);
        Statistiques.put("nombre de chercheur",2L);

        Mockito.when(chercheurRestFeign.nb_chercheur_Enseignant(id)).thenReturn(2L);
        Mockito.when(projetRestFeign.nb_Projet_Enseignant(id)).thenReturn(2L);

        Map<String, Long> result =enseignantService.statistique(id);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(Statistiques);



    }
}