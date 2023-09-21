package se233.chapter5_tdd.view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se233.chapter5_tdd.controller.GameLoop;
import se233.chapter5_tdd.model.Food;
import se233.chapter5_tdd.model.Snake;

import java.util.Random;

public class Platform extends Pane {
public static final int WIDTH = 30;
public static final int HEIGHT = 30;
public static final int TILE_SIZE = 10;
private Canvas canvas;
private KeyCode key;
public Platform() {
    this.setHeight(TILE_SIZE * HEIGHT);
    this.setWidth(TILE_SIZE * WIDTH);
    canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
    this.getChildren().add(canvas);
}
public void render(Snake snake, Food food, int score) { // ex 3 // added score args
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0,0,WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
    gc.setFill(Color.BLUE);
    snake.getBody().forEach(p -> {
        gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    });
    gc.setFill(food.getColor()); // ex 3 // set color to food's color
    gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    gc.fillText("score : " + score , 10 , 10); // ex 3 // set text to the current score
}
public KeyCode getKey() { return key; }
public void setKey(KeyCode key) { this.key = key; }
}
