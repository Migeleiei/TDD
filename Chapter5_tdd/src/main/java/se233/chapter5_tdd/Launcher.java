package se233.chapter5_tdd;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se233.chapter5_tdd.controller.GameLoop;
import se233.chapter5_tdd.model.Food;
import se233.chapter5_tdd.model.Snake;
import se233.chapter5_tdd.model.SpecialFood;
import se233.chapter5_tdd.view.Platform;

public class Launcher extends Application {
    public static Stage primaryStage;
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(platform.WIDTH/2, platform.HEIGHT/2));
        Food food = new Food();
        SpecialFood specialFood = new SpecialFood();
        GameLoop gameLoop = new GameLoop(platform, snake, food, specialFood);
        Scene scene = new Scene(platform, platform.WIDTH * platform.TITLE_SIZE, platform.HEIGHT * platform.TITLE_SIZE);
        scene.setOnKeyPressed(event -> platform.setKey(event.getCode()));
        scene.setOnKeyReleased(event -> platform.setKey(null));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        (new Thread(gameLoop)).start();
    }
}
