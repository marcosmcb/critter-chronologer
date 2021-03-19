package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.udacity.jdnd.course3.critter.utils.EntityUtils.convertFromDTOToEntity;
import static com.udacity.jdnd.course3.critter.utils.EntityUtils.createPetDTO;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertFromDTOToEntity(petDTO, new Pet());
        return createPetDTO(petService.savePet(pet, petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return createPetDTO(this.petService.findPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return this.petService
                .findAllPets()
                .stream()
                .map(pet -> createPetDTO(pet))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return this.petService
                .findPetsByOwnerId(ownerId)
                .stream()
                .map(pet -> createPetDTO(pet))
                .collect(Collectors.toList());
    }
}
