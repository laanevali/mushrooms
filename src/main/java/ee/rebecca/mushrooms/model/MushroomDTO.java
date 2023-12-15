package ee.rebecca.mushrooms.model;

import lombok.Data;

import java.util.List;

@Data
public class MushroomDTO {
    private int id;
    private List<Double> coordinates;
    private String comment;
}
