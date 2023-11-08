package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class InstructorServicesImplNoMockTest {

    @Autowired
    private InstructorServicesImpl instructorServices;

    @Autowired
    private IInstructorRepository instructorRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        // Vous pouvez effectuer des opérations de configuration préalables au test ici si nécessaire
    }

    @Test
    public void testAddInstructor() {
        // Créez un instructeur factice
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        assertNotNull(addedInstructor);
        assertNotNull(addedInstructor.getNumInstructor());
        assertEquals("John", addedInstructor.getFirstName());
        assertEquals("Doe", addedInstructor.getLastName());
        // Vérifiez que l'instructeur a bien été ajouté
        assertTrue(instructorRepository.findById(addedInstructor.getNumInstructor()).isPresent());
    }

    @Test
    public void testRetrieveAllInstructors() {
        // Ajoutez quelques instructeurs fictifs pour le test
        Instructor instructor1 = new Instructor();
        instructor1.setFirstName("Alice");
        instructor1.setLastName("Johnson");
        instructor1.setDateOfHire(LocalDate.now());

        Instructor instructor2 = new Instructor();
        instructor2.setFirstName("Bob");
        instructor2.setLastName("Smith");
        instructor2.setDateOfHire(LocalDate.now());

        instructorRepository.save(instructor1);
        instructorRepository.save(instructor2);

        // Récupérez tous les instructeurs
        Iterable<Instructor> retrievedInstructors = instructorServices.retrieveAllInstructors();

        assertNotNull(retrievedInstructors);
        // Vérifiez qu'il y a au moins deux instructeurs dans la liste
        assertTrue(retrievedInstructors.spliterator().getExactSizeIfKnown() >= 2);
    }

    @Test
    public void testUpdateInstructor() {
        // Créez un instructeur factice
        Instructor instructor = new Instructor();
        instructor.setFirstName("Jane");
        instructor.setLastName("Brown");
        instructor.setDateOfHire(LocalDate.now());

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        // Mettez à jour l'instructeur
        addedInstructor.setLastName("Black");
        Instructor updatedInstructor = instructorServices.updateInstructor(addedInstructor);

        assertNotNull(updatedInstructor);
        assertEquals("Black", updatedInstructor.getLastName());
    }

    @Test
    public void testRetrieveInstructor() {
        // Créez un instructeur factice
        Instructor instructor = new Instructor();
        instructor.setFirstName("Mark");
        instructor.setLastName("Green");
        instructor.setDateOfHire(LocalDate.now());

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        // Récupérez l'instructeur par ID
        Instructor retrievedInstructor = instructorServices.retrieveInstructor(addedInstructor.getNumInstructor());

        assertNotNull(retrievedInstructor);
        assertEquals("Mark", retrievedInstructor.getFirstName());
        assertEquals("Green", retrievedInstructor.getLastName());
    }

    @Test
    public void testAddInstructorAndAssignToCourse() {
        // Créez un instructeur factice
        Instructor instructor = new Instructor();
        instructor.setFirstName("Sara");
        instructor.setLastName("Taylor");
        instructor.setDateOfHire(LocalDate.now());

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        // Créez un cours factice
        Course course = new Course();
        course.setLevel(1); // Niveau du cours
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN); // Type du cours (personnalisez selon vos besoins)
        course.setSupport(Support.SNOWBOARD); // Support du cours (personnalisez selon vos besoins)
        course.setPrice(100.0F); // Prix du cours (personnalisez selon vos besoins)
        course.setTimeSlot(2); // Créez des créneaux horaires

        courseRepository.save(course);

        // Associez le cours à l'instructeur
        Instructor updatedInstructor = instructorServices.addInstructorAndAssignToCourse(addedInstructor, course.getNumCourse());

        assertNotNull(updatedInstructor);
        assertEquals(1, updatedInstructor.getCourses().size());
        // Vérifiez que le cours a les propriétés que vous attendez
        assertTrue(updatedInstructor.getCourses().stream().anyMatch(c ->
                c.getLevel() == 1 &&
                        c.getTypeCourse() == TypeCourse.COLLECTIVE_CHILDREN &&
                        c.getSupport() == Support.SNOWBOARD &&
                        c.getPrice() == 100.0F &&
                        c.getTimeSlot() == 2
        ));
    }

}