package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

import java.util.Random;

public class Asteroid extends Entity {
    public int getSegments() {
        return segments;
    }

    private int segments;
    private int segmentFluctuation = 6;
    private int segmentFluctuationHalved = segmentFluctuation/2;

    public int getSegmentFluctuationHalved() {
        return segmentFluctuationHalved;
    }

    public int getSegmentFluctuation() {
        return segmentFluctuation;
    }

    public int getSeed() {
        return seed;
    }

    private int seed = new Random().nextInt(10);


    public Asteroid() {
        this.segments = 15;
        this.setShapeX(new float[this.segments]);
        this.setShapeY(new float[this.segments]);
        super.setHitBoxRadius(16);
        super.setSize(16);
    }
}
