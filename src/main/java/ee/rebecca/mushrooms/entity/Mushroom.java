package ee.rebecca.mushrooms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Mushroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private MushroomCoordinates mushroomCoordinates;
//    private ArrayList<MushroomCoordinates2> mushroomCoordinates;

    private String comment;
}

class MushroomCoordinates2 {
    private double latitude;
    private double longitude;
}
