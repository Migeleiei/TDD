package se233.chapter5_tdd.model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import se233.chapter5_tdd.view.Platform;

import java.util.Random;

public class Food {
    private Point2D position;
    private Random rn;
    // ex 3 // add score variables
    private int score = 1;
    private Color color = Color.RED;

    public Food(Point2D position) {
        this.rn = new Random();
        this.position = position;
    }

    public Food() {
        this.rn = new Random();
        respawn();
    }
    // for testing (add any string on args2)
    public Food(Point2D position,String s) {
        this.rn = new Random();
        this.position = position;
        score = 5;
        color = Color.GREEN;
    }


    public void respawn() {
        Point2D prev_position = this.position;
        do {
            this.position = new Point2D(rn.nextInt(Platform.WIDTH), rn.nextInt(Platform.HEIGHT));
            //ex 3 // random chance 50/50 for special food
            if(rn.nextInt(2) == 1){
                score = 1;
                color = Color.RED;
            }else { // make special food
                score = 5;
                color = Color.GREEN;
            }
        } while (prev_position == this.position);
    }

    public Point2D getPosition() {
        return position;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}