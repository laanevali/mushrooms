package ee.rebecca.mushrooms.controller;

import ee.rebecca.mushrooms.entity.Mushroom;
import ee.rebecca.mushrooms.model.MushroomDTO;
import ee.rebecca.mushrooms.repository.MushroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:3000")
public class MushroomController {
    @Autowired
    MushroomRepository mushroomRepository;

    private MushroomDTO toMushroomDTO(Mushroom m) {
        MushroomDTO mushroomDTO = new MushroomDTO();
        mushroomDTO.setId(m.getId());
        mushroomDTO.setComment(m.getComment());
        mushroomDTO.setCoordinates(List.of(m.getLatitude(), m.getLongitude()));
        return mushroomDTO;
    }

    @GetMapping("mushrooms")
    public ResponseEntity<List<MushroomDTO>> getMushrooms() {
        List<MushroomDTO> mushroomDTOs = mushroomRepository.findAll().stream()
                .map(this::toMushroomDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mushroomDTOs);
    }

    @PostMapping("add-mushrooms")
    public ResponseEntity<List<MushroomDTO>> addMushrooms(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String comment) {
        Mushroom mushroom = Mushroom.builder()
                .latitude(latitude)
                .longitude(longitude)
                .comment(comment)
                .build();
        mushroomRepository.save(mushroom);

        List<MushroomDTO> mushroomDTOs = mushroomRepository.findAll().stream()
                .map(this::toMushroomDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mushroomDTOs);
    }

    @DeleteMapping("mushrooms/{id}")
    public ResponseEntity<List<MushroomDTO>> deleteMushroom(@PathVariable Long id) {
        if (mushroomRepository.existsById(id)) {
            mushroomRepository.deleteById(id);
        } else {
            throw new RuntimeException("Provided ID doesn't correspond with any entries. Provided ID: " + id);
        }

        List<MushroomDTO> mushroomDTOs = mushroomRepository.findAll().stream()
                .map(this::toMushroomDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mushroomDTOs);
    }

    @PutMapping("edit-mushroom")
    public ResponseEntity<List<MushroomDTO>> editMushroom(
            @RequestParam Long id,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String comment) {

        Optional<Mushroom> optionalMushroom = mushroomRepository.findById(id);
        if (optionalMushroom.isPresent()) {
            Mushroom mushroom = optionalMushroom.get();
            mushroom.setLatitude(latitude);
            mushroom.setLongitude(longitude);
            mushroom.setComment(comment);
            mushroomRepository.save(mushroom);
        }

        List<MushroomDTO> mushroomDTOs = mushroomRepository.findAll().stream()
                .map(this::toMushroomDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mushroomDTOs);
    }
}
