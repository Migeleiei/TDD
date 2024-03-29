package se233.chapter5_tdd.model;

import javafx.geometry.Point2D;
import org.junit.Test;
import se233.chapter5_tdd.view.Platform;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Snake {
    private Direction direction;
    private Point2D head;
    private Point2D prev_tail;
    private ArrayList<Point2D> body;

    private Snake snake;

    public Snake(Point2D position) {
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
    }

    public void update() {
        head = head.add(direction.current);
        prev_tail = body.remove(body.size() - 1);
        body.add(0, head);
    }

    public void setCurrentDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getCurrentDirection() {
        return this.direction;
    }

    public Point2D getHead() {
        return head;
    }

    public boolean isCollidingWith(Food food) {
        return head.equals(food.getPosition());
    }

    public void grow() {
        body.add(prev_tail);
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }


    public boolean isDead() {
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() >
                Platform.WIDTH || head.getY() > Platform.HEIGHT;
        boolean isHitBody = body.lastIndexOf(head) > 0;
        return isOutOfBound || isHitBody;
    }

    @Test
    public void collisionFlagShouldRaiseIfSnakeCollideWithFood() {
        Food food = new Food(new Point2D(0, 0));
        assertTrue(snake.isCollidingWith(food));
    }

    @Test
    public void foodShouldRespawnOnDifferentCoordinates() {
        Food food = new Food(new Point2D(0, 0));
        food.respawn();
        assertNotSame(food.getPosition(), new Point2D(0, 0));
    }

    @Test
    public void snakeGrowthShouldAddItsLengthByOne() {
        snake.grow();
        assertEquals(snake.getLength(), 2);
    }

    @Test
    public void bodyOfGrownSnakeShouldContainPreviousHead() {
        Point2D cur_head = snake.getHead();
        snake.update();
        snake.grow();
        assertTrue(snake.getBody().contains(cur_head));
    }

}
