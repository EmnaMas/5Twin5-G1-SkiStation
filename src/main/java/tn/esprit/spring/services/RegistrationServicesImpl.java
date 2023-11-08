package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImpl implements  IRegistrationServices{

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;


    @Override
    public Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        Course course = courseRepository.findById(numCourse).orElse(null);
        if (registration != null && course != null) {
            registration.setCourse(course);
            return registrationRepository.save(registration);
        } else {
            return null;
        }
    }


    @Transactional
    @Override
    public Registration addRegistrationAndAssignToSkierAndCourse(Registration registration, Long numSkieur, Long numCours) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Course course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null) {
            return null;
        }

        if (isAlreadyRegistered(registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse())) {
            log.info("Sorry, you're already registered for this course of the week: " + registration.getNumWeek());
            return null;
        }

        int ageSkieur = Period.between(skier.getDateOfBirth(), LocalDate.now()).getYears();
        log.info("Age " + ageSkieur);

        switch (course.getTypeCourse()) {
            case INDIVIDUAL:
                log.info("add without tests");
                return assignRegistration(registration, skier, course);

            case COLLECTIVE_CHILDREN:
                if (isChildEligible(ageSkieur) && canRegisterForCourse(course, registration.getNumWeek())) {
                    log.info("Ok CHILD!");
                    return assignRegistration(registration, skier, course);
                } else {
                    log.info("Sorry, your age doesn't allow you to register for this course! \n Try to Register for a Collective Adult Course...");
                }
                break;

            default:
                if (isAdultEligible(ageSkieur) && canRegisterForCourse(course, registration.getNumWeek())) {
                    log.info("Ok ADULT!");
                    return assignRegistration(registration, skier, course);
                } else {
                    log.info("Sorry, your age doesn't allow you to register for this course! \n Try to Register for a Collective Child Course...");
                }
        }
        return registration;
    }

    private boolean isAlreadyRegistered(int numWeek, Long skierId, Long courseId) {
        return registrationRepository.countDistinctByNumWeekAndSkierNumSkierAndCourseNumCourse(numWeek, skierId, courseId) >= 1;
    }

    private boolean isChildEligible(int age) {
        return age < 16;
    }

    private boolean isAdultEligible(int age) {
        return age >= 16;
    }

    private boolean canRegisterForCourse(Course course, int numWeek) {
        Long count = registrationRepository.countByCourseAndNumWeek(course, numWeek);
        if (count < 6) {
            log.info("Course successfully added!");
            return true;
        } else {
            log.info("Full Course! Please choose another week to register!");
            return false;
        }
    }




    private Registration assignRegistration (Registration registration, Skier skier, Course course){
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }

}
