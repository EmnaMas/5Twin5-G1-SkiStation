package tn.esprit.spring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.junit.jupiter.api.Assertions.assertNotNull;
        import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Transactional
public class CourseServicesSAnsmockTest {

    @Autowired
    private CourseServicesImpl courseServices;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        // You can set up test data here using the entityManager if needed
    }

    @Test
    public void testAddCourse() {
        Course courseToAdd = new Course(); // Create a sample course to add

        Course addedCourse = courseServices.addCourse(courseToAdd);

        assertNotNull(addedCourse);
        assertEquals(courseToAdd, addedCourse);
    }

    @Test
    public void testRetrieveCourse() {
        Course course = new Course();
        entityManager.persist(course);
        entityManager.flush();
        Long numCourse = course.getNumCourse();

        Course retrievedCourse = courseServices.retrieveCourse(numCourse);

        assertNotNull(retrievedCourse);
        assertEquals(course, retrievedCourse);
    }

    @Test
    public void testRetrieveCourse_NotFound() {
        Long numCourse = 9999L; // Replace with an ID that does not exist in the database

        Course retrievedCourse = courseServices.retrieveCourse(numCourse);

        assertNull(retrievedCourse);
    }

    @Test
    public void testRetrieveAllCourses() {
        Course course1 = new Course();
        Course course2 = new Course();
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.flush();

        List<Course> allCourses = courseServices.retrieveAllCourses();

        assertNotNull(allCourses);
        assertEquals(2, allCourses.size());
    }


}
