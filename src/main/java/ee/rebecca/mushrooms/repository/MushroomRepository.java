package ee.rebecca.mushrooms.repository;

import ee.rebecca.mushrooms.entity.Mushroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MushroomRepository extends JpaRepository <Mushroom, Long> {

}
