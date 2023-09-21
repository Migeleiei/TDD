package se233.chapter5_tdd;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import se233.chapter5_tdd.controller.GameLoop;
import se233.chapter5_tdd.model.Snake;
import se233.chapter5_tdd.model.Food;
import se233.chapter5_tdd.view.Platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest;
    private Method update;
    private Method collision;
    private Method redraw;
    @Before
    public void init() throws NoSuchMethodException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food());
        update = GameLoop.class.getDeclaredMethod("update");
        update.setAccessible(true);
        collision = GameLoop.class.getDeclaredMethod("checkCollision");
        collision.setAccessible(true);
        redraw = GameLoop.class.getDeclaredMethod("redraw");
        redraw.setAccessible(true);
    }
    private void clockTickHelper() throws InvocationTargetException,
            IllegalAccessException {
        update.invoke(gameLoopUnderTest);
        collision.invoke(gameLoopUnderTest);
        redraw.invoke(gameLoopUnderTest);
    }
    private void clockTickHelper(int c) throws InvocationTargetException,
            IllegalAccessException {
        for (int i = 0; i < c; i++) {
            update.invoke(gameLoopUnderTest);
            collision.invoke(gameLoopUnderTest);
            redraw.invoke(gameLoopUnderTest);
        }
    }
    @Test
    public void testClockTick() throws InvocationTargetException,
            IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),
                new Food());
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));
    }
    @Test
    public void testNoBack() throws InvocationTargetException,
            IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),
                new Food());
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,1));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,2));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0,3));
    }
    @Test
    public void testGameOver(){ // ex 1 //
        // set running
        gameLoopUnderTest.setRunning(false);
        /// ex 1 // game over
        // check if popup came up when running is false
        if(gameLoopUnderTest.isRunning()){
            // as the alert was undefined at first
            // check if popup came up alert must not be null
            assertFalse(gameLoopUnderTest.getAlert() == null);
        }
    }
    @Test
    public void testFoodAddScore() // ex 2 //
            throws InvocationTargetException, IllegalAccessException
    {
        // setup snake on food
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),
                new Food(new Point2D(0,5)));
        clockTickHelper(5); // run 5 step
        //ex 2 // food should add score
        assertTrue("score is now added",
                gameLoopUnderTest.getScore() > 0);
    }
    @Test
    public void testSpecialFoodAddScore() // ex 3 //
            throws InvocationTargetException, IllegalAccessException
    {
        // setup snake on food
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),
                new Food(new Point2D(0,5),"special"));// create new special food
        clockTickHelper(5); // run 5 step
        // ex 3 // food should add score to 5
        assertTrue("score is now added",
                gameLoopUnderTest.getScore() == 5);
    }
}
