package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SkierServicesMockTest {

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private IPisteRepository pisteRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SkierServicesImpl skierServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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




    @Test
    void testRetrieveAllSkiers() {
        // Mocking behavior
        when(skierRepository.findAll()).thenReturn(Arrays.asList(new Skier(), new Skier()));

        // Test
        List<Skier> skiers = skierServices.retrieveAllSkiers();

        // Verify
        assertEquals(2, skiers.size());
    }
}
