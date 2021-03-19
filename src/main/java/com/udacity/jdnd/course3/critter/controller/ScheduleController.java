package com.udacity.jdnd.course3.critter.controller;


import com.udacity.jdnd.course3.critter.model.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.utils.EntityUtils.convertFromDTOToEntity;
import static com.udacity.jdnd.course3.critter.utils.EntityUtils.createScheduleDTO;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;


    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertFromDTOToEntity(scheduleDTO, new Schedule());
        return createScheduleDTO(
                this.scheduleService.saveSchedule(schedule, scheduleDTO.getPetIds(), scheduleDTO.getEmployeeIds()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return this.scheduleService
                .findAllSchedules()
                .stream()
                .map(schedule -> createScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return this.scheduleService
                .findScheduleByPetId(petId)
                .stream()
                .map(schedule -> createScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return this.scheduleService
                .findScheduleByEmployeeId(employeeId)
                .stream()
                .map(schedule -> createScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return this.scheduleService
                .findScheduleByCustomerId(customerId)
                .stream()
                .map(schedule -> createScheduleDTO(schedule))
                .collect(Collectors.toList());
    }
}
