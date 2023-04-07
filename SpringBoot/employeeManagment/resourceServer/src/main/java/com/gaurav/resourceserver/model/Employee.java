package com.gaurav.resourceserver.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Employee")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", nullable = false,length =3)
    private Long employee_id;
    @Column(length = 6,unique = true,nullable = false)
    @NotBlank
    //personal identification id
    private String pid;
    @Column(length = 20,nullable = false)
    private String name;
    @Column(length = 50,nullable = false)
    private String address;
    @Column(length = 10,nullable = false)
    @NotNull
    private Long phone_number;
    @Column(length = 15,nullable = false)
    private String designation;
    @Column(length = 3,nullable = false)
    private int daily_wage;
    //date of joining
    private Date doj;
    //last day of working
    private Date last_day;
    private Date date_added;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Payroll> payrolls;

    public Employee() {
    }

    public Employee(String pid, String name, String address, Long phone_number, String designation, int daily_wage, Date doj, Date last_day, Date date_added) {
        this.pid = pid;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.designation = designation;
        this.daily_wage = daily_wage;
        this.doj = doj;
        this.last_day = last_day;
        this.date_added = date_added;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getDaily_wage() {
        return daily_wage;
    }

    public void setDaily_wage(int daily_wage) {
        this.daily_wage = daily_wage;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public Date getLast_day() {
        return last_day;
    }

    public void setLast_day(Date last_day) {
        this.last_day = last_day;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return daily_wage == employee.daily_wage && Objects.equals(employee_id, employee.employee_id) && Objects.equals(pid, employee.pid) && Objects.equals(name, employee.name) && Objects.equals(address, employee.address) && Objects.equals(phone_number, employee.phone_number) && Objects.equals(designation, employee.designation) && Objects.equals(doj, employee.doj) && Objects.equals(last_day, employee.last_day) && Objects.equals(date_added, employee.date_added) && Objects.equals(payrolls, employee.payrolls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, pid, name, address, phone_number, designation, daily_wage, doj, last_day, date_added, payrolls);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number=" + phone_number +
                ", designation='" + designation + '\'' +
                ", daily_wage=" + daily_wage +
                ", doj=" + doj +
                ", last_day=" + last_day +
                ", date_added=" + date_added+
                '}';
    }
}
