package ee.rebecca.mushrooms.controller;

import ee.rebecca.mushrooms.entity.Mushroom;
import ee.rebecca.mushrooms.entity.MushroomCoordinates;
import ee.rebecca.mushrooms.repository.MushroomCoordinatesRepository;
import ee.rebecca.mushrooms.repository.MushroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class MushroomController {
    @Autowired
    MushroomRepository mushroomRepository;
    @Autowired
    MushroomCoordinatesRepository mushroomCoordinatesRepository;

    @GetMapping("mushrooms")
    public List<Mushroom> getMushrooms() {
        return mushroomRepository.findAll();
    }

    // http://localhost:8080/add-mushrooms/59.3851&24.6204&puravikud
    @PostMapping("add-mushrooms")
    public List<Mushroom> addMushrooms(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String comment){
        MushroomCoordinates mushroomCoordinates = MushroomCoordinates.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
        mushroomCoordinatesRepository.save(mushroomCoordinates);

        Mushroom mushroom = Mushroom.builder()
                .mushroomCoordinates(mushroomCoordinates)
                .comment(comment)
                .build();
        mushroomRepository.save(mushroom);
        return mushroomRepository.findAll();
    }

    @DeleteMapping("mushrooms/{id}")
    public List<Mushroom> deleteMushroom(@PathVariable Long id){
        if (mushroomRepository.existsById(id)){
            mushroomRepository.deleteById(id);
        } else {
            throw new RuntimeException("Provided ID doesn't correspond with any entries. Provided ID: "+id);
        }
        return mushroomRepository.findAll();
    }

    //// info muutmine mõlemas entities, kas loogika õige?? miks id ei sobi?
//    @PutMapping("edit-mushroom")
//    public List<Mushroom> editMushroom(
//            @PathVariable Long id,
//            @RequestBody Mushroom mushroom,
//            @RequestBody MushroomCoordinates mushroomCoordinates){
//        Mushroom editMushroom = mushroomRepository.findAllById(id);
//        editMushroom.setComment(mushroom.getComment());
//
//        MushroomCoordinates editMushroomCoordinates = mushroomCoordinatesRepository.findAllById(id);
//        editMushroomCoordinates.setLatitude(mushroomCoordinates.getLatitude());
//        editMushroomCoordinates.setLongitude(mushroomCoordinates.getLongitude());
//
//        return mushroomRepository.findAll();
//    }
}
