package se233.chapter5_tdd;

import de.saxsys.javafx.test.JfxRunner;

import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import se233.chapter5_tdd.controller.GameLoop;
import se233.chapter5_tdd.model.Direction;
import se233.chapter5_tdd.model.Snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@RunWith(JfxRunner.class)
public class SnakeTest {
    private Snake snake;

    @Before
    public void setup() {
        snake = new Snake(new Point2D(0, 0));
    }

    @Test
    public void snakeShouldBeSpawnAtTheCoordinateItWasCreated() {
        assertEquals(snake.getHead(), new Point2D(0, 0));
    }

    @Test
    public void snakeShouldMoveDownwardIfItIsHeadingDownward() {
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        assertEquals(snake.getHead(), new Point2D(0, 1));
    }

    @Test
    public void snakeWillDieIfItGoesBeyondTheGameBorder() {
        snake = new Snake(new Point2D(30, 30));
        snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
        assertTrue(snake.isDead());
    }

    @Test
    public void snakeWillDieIfItHitsItsBody() {
        snake = new Snake(new Point2D(0, 0));
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.LEFT);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.UP);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
        snake.grow();
        assertTrue(snake.isDead());
    }
    @Test
    public void gameOverPopupWhenDie() {
        // borrowing previous death case
        snake = new Snake(new Point2D(0, 0));
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.LEFT);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.UP);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
        snake.grow();
        assertTrue(snake.isDead());
    }
}