package ee.rebecca.mushrooms.repository;

import ee.rebecca.mushrooms.entity.MushroomCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MushroomCoordinatesRepository extends JpaRepository <MushroomCoordinates, Long> {

}
