package org.example.gradeCalculator;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
//        (1)
//        double multipliedCreditAndCourseGrade = 0;
//        for(Course course : courses) {
//            multipliedCreditAndCourseGrade += course.multipliedCreditAndCourseGrade();
//        }
//        return multipliedCreditAndCourseGrade;

//        (2)
        return courses.stream()
                .mapToDouble(Course::multipliedCreditAndCourseGrade).sum();

    }

    public int calculateTotalCompletedCredit() {
        return  courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
    }
}
