package com.depi.sms.model;

public class FullTimeInstructor extends Instructor {

    private double annualSalary;

    public FullTimeInstructor(int id, String name, String email, String department, double annualSalary) {
        super(id, name, email, department);
        this.annualSalary = annualSalary;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Override
    public String getEmploymentType() {
        return "Full-Time";
    }
}
