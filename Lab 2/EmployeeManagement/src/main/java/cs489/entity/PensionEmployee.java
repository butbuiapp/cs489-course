package cs489.entity;

import java.time.LocalDate;

public class PensionEmployee extends Employee {

    private PensionPlan pensionPlan;

    public PensionEmployee(Employee employee) {
        super(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmploymentDate(), employee.getYearlySalary());
    }

    public PensionPlan getPensionPlan() {
        return pensionPlan;
    }

    public void setPensionPlan(PensionPlan pensionPlan) {
        this.pensionPlan = pensionPlan;
    }
}
