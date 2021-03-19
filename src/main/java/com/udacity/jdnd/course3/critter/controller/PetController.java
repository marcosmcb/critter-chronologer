package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.model.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.udacity.jdnd.course3.critter.utils.EntityUtils.convertFromDTOToEntity;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public Pet savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertFromDTOToEntity(petDTO, new Pet());
        return petService.savePet(pet, petDTO.getOwnerId());
    }

    @GetMapping("/{petId}")
    public Pet getPet(@PathVariable long petId) {
        return this.petService.findPetById(petId);
    }

    @GetMapping
    public List<Pet> getPets(){
        return this.petService.findAllPets();
    }

    @GetMapping("/owner/{ownerId}")
    public List<Pet> getPetsByOwner(@PathVariable long ownerId) {
        return this.petService.findPetsByOwnerId(ownerId);
    }
}
