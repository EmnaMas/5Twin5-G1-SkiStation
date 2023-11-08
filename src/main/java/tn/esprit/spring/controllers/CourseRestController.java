package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.services.ICourseServices;

import java.util.List;

@Tag(name = "\uD83D\uDCDA Course Management")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseRestController {
    
    private final ICourseServices courseServices;
    @java.lang.SuppressWarnings("squid:S2201")

    @Operation(description = "Add Course")
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return  courseServices.addCourse(course);
    }

    @Operation(description = "Retrieve all Courses")
    @java.lang.SuppressWarnings("squid:S2201")
    @GetMapping("/all")
    public List<Course> getAllCourses(){
        return courseServices.retrieveAllCourses();
    }

    @Operation(description = "Update Course ")
    @java.lang.SuppressWarnings("squid:S2201")
    @PutMapping("/update")
    public Course updateCourse(@RequestBody Course course){
        return  courseServices.updateCourse(course);
    }

    @Operation(description = "Retrieve Course by Id")
    @java.lang.SuppressWarnings("squid:S2201")
    @GetMapping("/get/{id-course}")
    public Course getById(@PathVariable("id-course") Long numCourse){
        return courseServices.retrieveCourse(numCourse);
    }

}
