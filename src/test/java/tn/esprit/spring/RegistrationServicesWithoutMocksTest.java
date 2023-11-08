package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository; // Import the repository
import tn.esprit.spring.repositories.ISkierRepository; // Import the repository
import tn.esprit.spring.services.IRegistrationServices;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegistrationServicesWithoutMocksTest {

    @Autowired
    private IRegistrationServices registrationServices;

    @Autowired
    private ISkierRepository skierRepository; // Inject the Skier repository
    @Autowired
    private ICourseRepository courseRepository; // Inject the Course repository

    private Registration registration;
    private Skier skier;
    private Course course;

    @BeforeEach
    public void setUp() {
        skier = new Skier();
        // Set Skier properties
        skier.setDateOfBirth(LocalDate.of(2000, 1, 1));

        course = new Course();
        // Set Course properties
        course.setTypeCourse(TypeCourse.INDIVIDUAL);

        skier = skierRepository.save(skier);
        course = courseRepository.save(course);

        registration = new Registration();
        // Set Registration properties
        registration.setNumWeek(1);
    }

    @Test
    public void testAddRegistrationAndAssignToSkier() {
        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, skier.getNumSkier());
        assertNotNull(result);
    }

    @Test
    public void testAssignRegistrationToCourse() {
        registration = registrationServices.addRegistrationAndAssignToSkier(registration, skier.getNumSkier());
        Registration result = registrationServices.assignRegistrationToCourse(registration.getNumRegistration(), course.getNumCourse());
        assertNotNull(result);
    }

    @Test
    public void testAddRegistrationAndAssignToSkierAndCourse() {
        Registration result = registrationServices.addRegistrationAndAssignToSkierAndCourse(registration, skier.getNumSkier(), course.getNumCourse());
        assertNotNull(result);
    }
}
