package com.gaurav.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="Payroll")
public class Payroll
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payroll_id", nullable = false,length = 3)
    private Long payroll_id;
    @Column(length = 2,nullable = false)
    @NotNull
    private int payroll_month;
    @Column(length = 2,nullable = false)
    @NotNull
    private int attendance;
    @Column(length = 3,nullable = false)
    private int daily_wage;
    @Column(length = 5,nullable = false)
    private int generated_salary;
    @Column(length = 5)
    private int deductions;
    @Column(length = 5,nullable = false)
    @NotNull
    private int net_pay;
    @Column(length = 6,nullable = false)
    @NotBlank
    private String payment_mode;
    private String date_added;
    //Foreign key from Employee
    @ManyToOne
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    public Payroll() {
    }

    public Payroll(int payroll_month, int attendance, int daily_wage, int generated_salary, int deductions, int net_pay, String payment_mode, String date_added, Employee employee) {
        this.payroll_month = payroll_month;
        this.attendance = attendance;
        this.daily_wage = daily_wage;
        this.generated_salary = generated_salary;
        this.deductions = deductions;
        this.net_pay = net_pay;
        this.payment_mode = payment_mode;
        this.date_added = date_added;
        this.employee = employee;
    }

    public Long getPayroll_id() {
        return payroll_id;
    }

    public void setPayroll_id(Long payroll_id) {
        this.payroll_id = payroll_id;
    }

    public int getPayroll_month() {
        return payroll_month;
    }

    public void setPayroll_month(int payroll_month) {
        this.payroll_month = payroll_month;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getDaily_wage() {
        return daily_wage;
    }

    public void setDaily_wage(int daily_wage) {
        this.daily_wage = daily_wage;
    }

    public int getGenerated_salary() {
        return generated_salary;
    }

    public void setGenerated_salary(int generated_salary) {
        this.generated_salary = generated_salary;
    }

    public int getDeductions() {
        return deductions;
    }

    public void setDeductions(int deductions) {
        this.deductions = deductions;
    }

    public int getNet_pay() {
        return net_pay;
    }

    public void setNet_pay(int net_pay) {
        this.net_pay = net_pay;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    @JsonBackReference
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payroll payroll = (Payroll) o;
        return Objects.equals(payroll_id, payroll.payroll_id) && Objects.equals(payroll_month, payroll.payroll_month) && Objects.equals(attendance, payroll.attendance) && Objects.equals(daily_wage, payroll.daily_wage) && Objects.equals(generated_salary, payroll.generated_salary) && Objects.equals(deductions, payroll.deductions) && Objects.equals(net_pay, payroll.net_pay) && Objects.equals(payment_mode, payroll.payment_mode) && Objects.equals(date_added, payroll.date_added) && Objects.equals(employee, payroll.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payroll_id, payroll_month, attendance, daily_wage, generated_salary, deductions, net_pay, payment_mode, date_added, employee);
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "payroll_id=" + payroll_id +
                ", payroll_month=" + payroll_month +
                ", attendance=" + attendance +
                ", daily_wage=" + daily_wage +
                ", generated_salary=" + generated_salary +
                ", deductions=" + deductions +
                ", net_pay=" + net_pay +
                ", payment_mode='" + payment_mode + '\'' +
                ", date_added=" + date_added +
                ", employee=" + employee +
                '}';
    }
}
