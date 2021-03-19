package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.utils.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetService petService;

    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        if (petIds != null) {
            List<Pet> pets = petIds.stream().map(petId -> this.petService.findPetById(petId)).collect(Collectors.toList());
            customer.setPets(pets);
        }
        return this.customerRepository.save(customer);
    }

    public Employee saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }


    public Employee findEmployeeById(Long employeeId) {
        return this.employeeRepository.findById(employeeId).orElseThrow(() -> new ItemNotFoundException("couldn't find employee with id " + employeeId));
    }

    public Customer findCustomerById(Long customerId) {
        return this.customerRepository
                .findById(customerId)
                .orElseThrow(() -> new ItemNotFoundException("couldn't find customer with id " + customerId));
    }

    public List<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    public void addPetToCustomer(Customer customer, Pet pet) {
        customer.setPets( Arrays.asList(pet) );
        this.customerRepository.save(customer);
    }

    public Employee setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ItemNotFoundException("couldn't find employee with id " + employeeId));
        employee.setDaysAvailable(daysAvailable);
        return this.employeeRepository.save(employee);
    }


    public List<Employee> findEmployeesAvailable(Set<EmployeeSkill> skills, LocalDate date) {
        return this.employeeRepository
                .findAllByDaysAvailable(date.getDayOfWeek())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

    public Customer findOwnerByPet(long petId) {
        Pet pet = this.petService.findPetById(petId);
        return this.customerRepository.findOneByPetsIn(Arrays.asList(pet));
    }
}
