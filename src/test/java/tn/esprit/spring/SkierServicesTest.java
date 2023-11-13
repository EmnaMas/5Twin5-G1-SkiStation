package tn.esprit.spring;

import  org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SkierServicesTest {

    @Autowired
    private SkierServicesImpl skierServices;

    @MockBean
    private ISkierRepository skierRepository;

    @MockBean
    private IPisteRepository pisteRepository;

    @MockBean
    private ICourseRepository courseRepository;

    @MockBean
    private IRegistrationRepository registrationRepository;

    @MockBean
    private ISubscriptionRepository subscriptionRepository;

    @Test
    void testRetrieveAllSkiers() {
        // Mocking behavior
        when(skierRepository.findAll()).thenReturn(Arrays.asList(new Skier(), new Skier()));

        // Test
        List<Skier> skiers = skierServices.retrieveAllSkiers();

        // Verify
        assertEquals(2, skiers.size());
    }


    @Test
    void testRemoveSkier() {
        // Mocking behavior
        Skier skierToDelete = new Skier();
        skierToDelete.setNumSkier(1L);
        skierToDelete.setFirstName("John");
        skierToDelete.setLastName("Doe");

        when(skierRepository.findById(1L)).thenReturn(java.util.Optional.of(skierToDelete));

        // Test
        skierServices.removeSkier(1L);

        // Verify
        verify(skierRepository, times(1)).deleteById(1L);
    }
}
