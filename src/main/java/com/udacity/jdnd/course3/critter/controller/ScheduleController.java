package com.udacity.jdnd.course3.critter.controller;


import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.udacity.jdnd.course3.critter.utils.EntityUtils.convertFromDTOToEntity;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;


    @PostMapping
    public Schedule createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertFromDTOToEntity(scheduleDTO, new Schedule());
        return this.scheduleService.saveSchedule(schedule, scheduleDTO.getPetIds(), scheduleDTO.getEmployeeIds());
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return this.scheduleService.findAllSchedules();
    }

    @GetMapping("/pet/{petId}")
    public List<Schedule> getScheduleForPet(@PathVariable long petId) {
        return this.scheduleService.findScheduleByPetId(petId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Schedule> getScheduleForEmployee(@PathVariable long employeeId) {
        return this.scheduleService.findScheduleByEmployeeId(employeeId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Schedule> getScheduleForCustomer(@PathVariable long customerId) {
        return this.scheduleService.findScheduleByCustomerId(customerId);
    }
}
