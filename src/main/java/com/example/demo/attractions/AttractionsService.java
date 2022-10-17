package com.example.demo.attractions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AttractionsService {
    private final AttractionsRepository attractionsRepository;

    @Autowired
    public AttractionsService(AttractionsRepository attractionsRepository) {
        this.attractionsRepository = attractionsRepository;
    }

    public List<Attractions> getAttractions() {
        return attractionsRepository.findAll();
    }

    public void addNewAttraction(Attractions attractions){
        attractionsRepository.save(attractions);
        System.out.println(attractions);
    }

    public void deleteAttractions(Long attractionID){
        boolean exists = attractionsRepository.existsById(attractionID);
        if (!exists){
            throw new IllegalStateException("Attraction with id " + attractionID + " does not exists");

        }
        attractionsRepository.deleteById(attractionID);
    }

}
