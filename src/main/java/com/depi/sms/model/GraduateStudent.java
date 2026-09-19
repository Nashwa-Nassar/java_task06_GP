package com.depi.sms.model;

public class GraduateStudent extends Student {

    private String researchArea;

    public GraduateStudent(int id, String name, String email, String researchArea) {
        super(id, name, email);
        this.researchArea = researchArea;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    @Override
    public String getStudentType() {
        return "Graduate";
    }

    @Override
    public String toString() {
        return super.toString() + " (Research: " + researchArea + ")";
    }
}
