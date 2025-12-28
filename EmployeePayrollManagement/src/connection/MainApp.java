package connection;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PayrollDAO dao = new PayrollDAO();

        while (true) {
            System.out.println("\n--- Payroll Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Generate Payslip");
            System.out.println("3. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    Employee e = new Employee();
                    System.out.print("Name: ");
                    e.setName(sc.next());
                    System.out.print("Department: ");
                    e.setDepartment(sc.next());
                    System.out.print("Basic Salary: ");
                    e.setBasicSalary(sc.nextDouble());
                    dao.addEmployee(e);
                    break;

                case 2:
                    System.out.print("Employee ID: ");
                    int id = sc.nextInt();
                    System.out.print("Allowance: ");
                    double al = sc.nextDouble();
                    System.out.print("Deduction: ");
                    double ded = sc.nextDouble();
                    dao.generatePayslip(id, al, ded);
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}
