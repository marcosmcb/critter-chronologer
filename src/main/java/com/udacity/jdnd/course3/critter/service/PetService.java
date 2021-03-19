package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.entity.Customer;
import com.udacity.jdnd.course3.critter.model.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.utils.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserService userService;


    public Pet savePet(Pet pet, Long ownerId) {
        Customer owner = userService.findCustomerById(ownerId);
        pet.setOwner(owner);
        Pet savedPet = this.petRepository.save(pet);
        userService.addPetToCustomer(owner, savedPet);
        return savedPet;
    }

    public Pet findPetById(Long petId) {
        return this.petRepository.findById(petId).orElseThrow(() -> new ItemNotFoundException("couldn't find pet with id " + petId));
    }

    public List<Pet> findAllPets() {
        return this.petRepository.findAll();
    }

    public List<Pet> findPetsByOwnerId(long ownerId) {
        return this.petRepository.findAllByOwnerId(ownerId);
    }
}
