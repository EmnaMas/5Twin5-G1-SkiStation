package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseServiceswithmocklTest {


    @InjectMocks
    private CourseServicesImpl courseServices;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCourse() {
        Course course = new Course(); // Create a sample course
        when(courseRepository.save(course)).thenReturn(course);

        Course addedCourse = courseServices.addCourse(course);

        // Assertions and verifications
        assertNotNull(addedCourse);
        assertEquals(course, addedCourse);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testRetrieveCourse() {
        Long numCourse = 1L; // Replace with a valid course ID
        Course course = new Course();
        when(courseRepository.findById(numCourse)).thenReturn(java.util.Optional.of(course));

        Course retrievedCourse = courseServices.retrieveCourse(numCourse);

        // Assertions and verifications
        assertNotNull(retrievedCourse);
        assertEquals(course, retrievedCourse);
        verify(courseRepository, times(1)).findById(numCourse);
    }

}
