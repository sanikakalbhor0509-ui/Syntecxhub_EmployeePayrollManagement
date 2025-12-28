package connection;

import java.sql.Date;

public class Payroll {
    private int empId;
    private double allowance;
    private double deduction;
    private double netSalary;
    private Date payDate;

    public double calculateNetSalary(double basic) {
        return basic + allowance - deduction;
    }

    // getters and setters
}
