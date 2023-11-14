package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

public class InstructorServicesImplTest {

    @InjectMocks
    private InstructorServicesImpl instructorServices;

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
/*
    @Test
    public void testAddInstructor() {
        // Créez un instructeur factice
        Instructor instructor = new Instructor();
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor addedInstructor = instructorServices.addInstructor(instructor);

        assertNotNull(addedInstructor);
        verify(instructorRepository, times(1)).save(instructor);
    }
*/
    @Test
    public void testRetrieveAllInstructors() {
        // Comportement simulé du repository d'instructeurs
        List<Instructor> instructorList = new ArrayList<>();
        when(instructorRepository.findAll()).thenReturn(instructorList);

        List<Instructor> retrievedInstructors = instructorServices.retrieveAllInstructors();

        assertEquals(instructorList, retrievedInstructors);
        verify(instructorRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateInstructor() {
        // Créez un instructeur factice
        Instructor instructor = new Instructor();
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor updatedInstructor = instructorServices.updateInstructor(instructor);

        assertNotNull(updatedInstructor);
        verify(instructorRepository, times(1)).save(instructor);
    }
/*
    @Test
    public void testRetrieveInstructor() {
        // Créez un instructeur factice
        Instructor instructor = new Instructor();
        when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));

        Instructor retrievedInstructor = instructorServices.retrieveInstructor(1L);

        assertNotNull(retrievedInstructor);
        verify(instructorRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddInstructorAndAssignToCourse() {
        // Créez un instructeur et un cours factices
        Instructor instructor = new Instructor();
        Course course = new Course();

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor addedInstructor = instructorServices.addInstructorAndAssignToCourse(instructor, 1L);

        assertNotNull(addedInstructor);
        verify(courseRepository, times(1)).findById(1L);
        verify(instructorRepository, times(1)).save(instructor);
    }
    */

}

