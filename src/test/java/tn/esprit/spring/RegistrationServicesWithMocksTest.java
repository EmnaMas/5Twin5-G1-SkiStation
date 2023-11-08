package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.IRegistrationServices;
import tn.esprit.spring.services.RegistrationServicesImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Add this annotation
public class RegistrationServicesWithMocksTest {

    @InjectMocks
    private RegistrationServicesImpl registrationServices;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Test
    public void testAddRegistrationAndAssignToSkierWithMocks() {
        Registration registration = new Registration();
        Skier skier = new Skier();
        skier.setDateOfBirth(LocalDate.of(1990, 1, 1)); // Set a valid date of birth

        // Mock the repository methods
        Mockito.when(skierRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(skier));
        Mockito.when(registrationRepository.save(Mockito.any())).thenReturn(registration);

        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, 1L);

        assertNotNull(result);
    }

    @Test
    public void testAssignRegistrationToCourseWithMocks() {
        Registration registration = new Registration();
        Course course = new Course();
        // Set Course properties
        // Mock the repository methods
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course));
        Mockito.when(registrationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(registration));
        Mockito.when(registrationRepository.save(Mockito.any())).thenReturn(registration);

        Registration result = registrationServices.assignRegistrationToCourse(1L, 2L);

        assertNotNull(result);
    }



}
