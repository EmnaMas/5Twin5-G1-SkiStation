package tn.esprit.spring.TDO;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
@Getter
@Setter
public class CourseDTO {
    private Long numCourse;
    private int level;
    private TypeCourse typeCourse;
    private Support support;
    private Float price;
    private int timeSlot;

}
