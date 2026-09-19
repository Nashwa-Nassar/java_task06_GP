package com.depi.sms.model;

public class UndergraduateStudent extends Student {

    private int yearLevel; // 1 = freshman, 2 = sophomore, etc.

    public UndergraduateStudent(int id, String name, String email, int yearLevel) {
        super(id, name, email);
        this.yearLevel = yearLevel;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    @Override
    public String getStudentType() {
        return "Undergraduate";
    }

    @Override
    public String toString() {
        return super.toString() + " (Year " + yearLevel + ")";
    }
}
