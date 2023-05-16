package org.example.gradeCalculator;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multipliedCreditAndGrade() {
//        (1)
//        double multipliedCreditAndGrade = 0;
//        for(Course course : courses) {
//            multipliedCreditAndGrade += course.multipliedCreditAndGrade();
//        }
//        return multipliedCreditAndGrade;

//        (2)
        return courses.stream()
                .mapToDouble(Course::multipliedCreditAndGrade).sum();
    }

    public int calculateTotalCompletedCredit() {
        return  courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
    }
}
