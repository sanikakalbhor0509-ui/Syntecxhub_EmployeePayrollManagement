package connection;

import java.sql.*;

public class PayrollDAO {

    public void addEmployee(Employee e) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO employee(name, department, basic_salary) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, e.getName());
            ps.setString(2, e.getDepartment());
            ps.setDouble(3, e.getBasicSalary());
            ps.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generatePayslip(int empId, double allowance, double deduction) {
        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement ps1 =
                con.prepareStatement("SELECT basic_salary FROM employee WHERE emp_id=?");
            ps1.setInt(1, empId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                double basic = rs.getDouble("basic_salary");
                double netSalary = basic + allowance - deduction;

                PreparedStatement ps2 =
                    con.prepareStatement(
                        "INSERT INTO payroll(emp_id, allowance, deduction, net_salary, pay_date) VALUES (?,?,?,?,CURDATE())"
                    );
                ps2.setInt(1, empId);
                ps2.setDouble(2, allowance);
                ps2.setDouble(3, deduction);
                ps2.setDouble(4, netSalary);
                ps2.executeUpdate();

                System.out.println("Payslip Generated");
                System.out.println("Net Salary: " + netSalary);
            } else {
                System.out.println("Employee not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
