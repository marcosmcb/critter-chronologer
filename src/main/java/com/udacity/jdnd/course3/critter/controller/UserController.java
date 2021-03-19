package com.udacity.jdnd.course3.critter.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.critter.model.Views;
import com.udacity.jdnd.course3.critter.model.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.model.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import static com.udacity.jdnd.course3.critter.utils.EntityUtils.convertFromDTOToEntity;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = convertFromDTOToEntity(customerDTO, new Customer());
        return this.userService.saveCustomer(customer, customerDTO.getPetIds());
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){
        return this.userService.findAllCustomers();
    }


    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertFromDTOToEntity(employeeDTO, new Employee());
        return this.userService.saveEmployee(employee);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable long employeeId) {
        return this.userService.findEmployeeById(employeeId);
    }

    @GetMapping("/customer/pet/{petId}")
    public Customer getOwnerByPet(@PathVariable long petId){
        return this.userService.findOwnerByPet(petId);
    }


    @PutMapping("/employee/{employeeId}")
    public Employee setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        return this.userService.setEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<Employee> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return this.userService.findEmployeesAvailable(employeeDTO.getSkills(), employeeDTO.getDate());
    }
}
