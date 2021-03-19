package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.entity.Employee;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetService petService;

    @Autowired
    UserService userService;


    public Schedule saveSchedule(Schedule schedule, List<Long> petIds, List<Long> employeeIds) {
        List<Employee> employeeList = employeeIds.stream()
                .map(employeeId -> this.userService.findEmployeeById(employeeId))
                .collect(Collectors.toList());

        List<Pet> petList = petIds.stream()
                .map(petId -> this.petService.findPetById(petId))
                .collect(Collectors.toList());

        schedule.setEmployees(employeeList);
        schedule.setPets(petList);
        return this.scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllSchedules() {
        return this.scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleByPetId(long petId) {
        return this.scheduleRepository.findAllByPets(this.petService.findPetById(petId));
    }

    public List<Schedule> findScheduleByEmployeeId(long employeeId) {
        return this.scheduleRepository.findAllByEmployees(this.userService.findEmployeeById(employeeId));
    }

    public List<Schedule> findScheduleByCustomerId(long customerId) {
        List<Pet> pets = this.petService.findPetsByOwnerId(customerId);
        return this.scheduleRepository.findAllByPetsIn(pets);
    }
}
