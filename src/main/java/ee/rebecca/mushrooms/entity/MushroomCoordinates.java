package ee.rebecca.mushrooms.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MushroomCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCoordinates;
    private double latitude;
    private double longitude;

//    public MushroomCoordinates(double latitude, double longitude) {
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
