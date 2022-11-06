package com.example.demo.attractions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/attractions")
public class AttractionsController {
    private final AttractionsService attractionsService;

    @Autowired
    public AttractionsController(AttractionsService attractionsService){
        this.attractionsService = attractionsService;
    }

    @GetMapping
    public List<Attractions> getUsers(){
        return attractionsService.getAttractions();
    }

    @PostMapping("/save")
    public void addAttractions(@RequestBody Attractions attractions){
        attractionsService.addNewAttraction(attractions);
    }

    @DeleteMapping(path = "{attractionID}")
    public void deleteAttractions(@PathVariable("attractionID") Long attractionID){
        attractionsService.deleteAttractions(attractionID);
    }

}
