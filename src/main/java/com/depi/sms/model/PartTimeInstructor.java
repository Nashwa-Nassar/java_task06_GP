package com.depi.sms.model;

public class PartTimeInstructor extends Instructor {

    private double hourlyRate;

    public PartTimeInstructor(int id, String name, String email, String department, double hourlyRate) {
        super(id, name, email, department);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String getEmploymentType() {
        return "Part-Time";
    }
}
