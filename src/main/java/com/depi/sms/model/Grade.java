package com.depi.sms.model;

/**
 * A single grade a Student earned in one Course.
 * Composition: a Grade has-a Student and has-a Course.
 */
public class Grade {

    private Student student;
    private Course course;
    private double score; // 0-100

    public Grade(Student student, Course course, double score) {
        this.student = student;
        this.course = course;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    /** Converts the numeric score to a letter grade. */
    public String getLetterGrade() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    /** Standard 4.0-scale grade points for this letter grade, used for GPA. */
    public double getGradePoints() {
        switch (getLetterGrade()) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }

    @Override
    public String toString() {
        return student.getName() + " - " + course.getName() + ": " + score
                + " (" + getLetterGrade() + ")";
    }
}
