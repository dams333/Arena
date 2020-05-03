package ch.dams333.arena.objects.arene;

import java.util.ArrayList;
import java.util.List;

public class SdchachazeArene {

    private List<AreneRectangle> rectangles;

    public SdchachazeArene() {
        rectangles = new ArrayList<>();
    }

    public SdchachazeArene(List<AreneRectangle> rectangles) {

        this.rectangles = rectangles;
    }

    public void addRectangle(AreneRectangle rectangle){
        this.rectangles.add(rectangle);
    }

    public List<AreneRectangle> getRectangles() {
        return rectangles;
    }

    public void removeRectangle(int index){
        this.rectangles.remove(index);

    }
}
