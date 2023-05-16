package org.example.gradeCalculator;

import java.util.List;
public class GradeCalculator {

    // 이수한 과목들을 인스턴스 변수로 선언
//    private final List<Course> courses;
//
//    public GradeCalculator(List<Course> courses) {
//        this.courses = courses;
//    }

    // 일급컬렉션
    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    // (학점수*교과목 점수)의 합계 / 수강신청 총학점 수
    public double calculateGrade() {
        // (학점수 * 교과목 평점)의 합계
        // (1)번의 문제점 : 응집도가 약하다.
        // getter를 이용하는 방식이 아니라 메시지를 전달해서 해당 Course 객체에게 작업을 위임하는 형태로 변경
//        (1)
//        double multipliedCreditAndGrade = 0;
//        for(Course course : courses) {
//            multipliedCreditAndGrade += course.getCredit() * course.getGradeToNumber();
//        }

//        (2)
//        double multipliedCreditAndGrade = 0;
//        for(Course course : courses) {
//            multipliedCreditAndGrade += course.multipliedCreditAndGrade();
//        }

//        (3)
        double multipliedCreditAndCourseGrade = courses.multipliedCreditAndGrade();

        // 수강신청 총학점 수
//        (1,2)
//            int totalCompletedCredit = courses.stream()
//                    .mapToInt(Course::getCredit)
//                    .sum();
//
//            return multipliedCreditAndGrade / totalCompletedCredit;


//        (3)
        double totalCompletedCredit = courses.calculateTotalCompletedCredit();
        
        return multipliedCreditAndCourseGrade / totalCompletedCredit;

    }
}

