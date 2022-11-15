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

    public Optional<Attractions>  getAttractionById(int attractionID){
        return attractionsRepository.findAttractionsByID(attractionID);

    }

    public void deleteAttractions(int attractionID){
        boolean exists = attractionsRepository.existsById(attractionID);
        if (!exists){
            throw new IllegalStateException("Attraction with id " + attractionID + " does not exists");

        }
        attractionsRepository.deleteById(attractionID);
    }

    @Transactional
    public void updateAttraction(int attractionID,Attractions attraction){
        Attractions selectedAttraction = attractionsRepository.findAttractionsByID(attractionID).orElseThrow(() -> new IllegalStateException(
                "Attraction with id " + attractionID + "does not exist"
        ));
        selectedAttraction.setName(attraction.getName());
        selectedAttraction.setDescription(attraction.getDescription());
        selectedAttraction.setPassType(attraction.getPassType());
        selectedAttraction.setReplacementFee(attraction.getReplacementFee());
        selectedAttraction.setEmailTemplateID(attraction.getEmailTemplateID());
        selectedAttraction.setTotalPasses(attraction.getTotalPasses());
        selectedAttraction.setStatus(attraction.getStatus());



    }

}
