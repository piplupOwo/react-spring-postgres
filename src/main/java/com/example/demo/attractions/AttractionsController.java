package com.example.demo.attractions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping()
    public void addAttractions(@RequestBody Attractions attractions){
        attractionsService.addNewAttraction(attractions);
    }

    @GetMapping(path = "{attractionID}")
    public Optional<Attractions> getAttractionByID(@PathVariable("attractionID") Integer attractionID){
        return attractionsService.getAttractionById(attractionID);
    }

    @PutMapping(path="{attractionID}")
    public void updateAttraction(
            @PathVariable("attractionID") Integer attractionID,
            @RequestBody Attractions attraction
    ){
        attractionsService.updateAttraction(attractionID,attraction);
    }

    @DeleteMapping(path = "{attractionID}")
    public void deleteAttractions(@PathVariable("attractionID") Integer attractionID){
        attractionsService.deleteAttractions(attractionID);
    }

}
