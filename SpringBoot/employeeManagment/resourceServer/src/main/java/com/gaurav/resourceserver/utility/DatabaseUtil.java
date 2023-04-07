package com.gaurav.resourceserver.utility;

import com.gaurav.resourceserver.repository.EmployeeRepository;
import com.gaurav.resourceserver.repository.PayrollRepository;
import com.gaurav.resourceserver.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUtil implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;

    public DatabaseUtil(UserRepository userRepository, EmployeeRepository employeeRepository, PayrollRepository payrollRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
//        UserRole u = new UserRole("Gaurav Sharma","test@gmail.com", 1234567890L,"test234",new Date());
//        userRepository.save(u);
//        Employee e = new Employee("ABC123","Alan Nolan"," Toronto, Ontario",1234567890L,"Developer",500,new Date(),null,new Date());
//        employeeRepository.save(e);
//        payrollRepository.save(new Payroll(2,28,500,14000,1000,13000,"NEFT",new Date(),e));
//        payrollRepository.save(new Payroll(3,28,500,14000,500,13500,"NEFT",new Date(),e));
    }
}
