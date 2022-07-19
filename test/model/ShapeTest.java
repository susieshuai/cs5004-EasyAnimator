package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class for Shape Interface.
 * 
 * @author shishuai
 *
 */
public class ShapeTest {
  private Shape rectangle;
  private Shape oval;

  /**
   * Set up the testing samples.
   */
  @Before
  public void setUp() {
    rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100), new Color(1, 0, 0), 1,
        100);
    oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 1), 6, 100);
  }

  /**
   * Test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("R", rectangle.getName());
    assertEquals("C", oval.getName());
  }

  /**
   * Test getType method.
   */
  @Test
  public void testGetType() {
    assertEquals(ShapeType.RECTANGLE, rectangle.getType());
    assertEquals(ShapeType.OVAL, oval.getType());
  }

  /**
   * Test getPosition method.
   */
  @Test
  public void testGetPosition() {
    assertEquals(200, rectangle.getPosition().getX(), 0.001);
    assertEquals(200, rectangle.getPosition().getY(), 0.001);
    assertEquals(500, oval.getPosition().getX(), 0.001);
    assertEquals(100, oval.getPosition().getY(), 0.001);
  }

  /**
   * Test getSize method.
   */
  @Test
  public void testGetSize() {
    assertEquals(50, rectangle.getSize().getPara1(), 0.001);
    assertEquals(100, rectangle.getSize().getPara2(), 0.001);
    assertEquals(60, oval.getSize().getPara1(), 0.001);
    assertEquals(30, oval.getSize().getPara2(), 0.001);
  }

  /**
   * Test getColor method.
   */
  @Test
  public void testGetColor() {
    assertEquals(1, rectangle.getColor().getRed(), 0.001);
    assertEquals(0, rectangle.getColor().getGreen(), 0.001);
    assertEquals(0, rectangle.getColor().getBlue(), 0.001);
    assertEquals(0, oval.getColor().getRed(), 0.001);
    assertEquals(0, oval.getColor().getGreen(), 0.001);
    assertEquals(1, oval.getColor().getBlue(), 0.001);
  }

  /**
   * Test getAppearTime method.
   */
  @Test
  public void testGetAppearTime() {
    assertEquals(1, rectangle.getAppearTime());
    assertEquals(6, oval.getAppearTime());
  }

  /**
   * Test getDisappearTime method.
   */
  @Test
  public void testGetDisappearTime() {
    assertEquals(100, rectangle.getDisappearTime());
    assertEquals(100, oval.getDisappearTime());
  }

  /**
   * Test setPosition method.
   */
  @Test
  public void testSetPosition() {
    rectangle.setPosition(new Position(300, 300));
    assertEquals(300, rectangle.getPosition().getX(), 0.001);
    assertEquals(300, rectangle.getPosition().getY(), 0.001);

    oval.setPosition(new Position(100, 50));
    assertEquals(100, oval.getPosition().getX(), 0.001);
    assertEquals(50, oval.getPosition().getY(), 0.001);
  }

  /**
   * Test setSize method.
   */
  @Test
  public void testSetSize() {
    rectangle.setSize(new Size(75, 75));
    assertEquals(75, rectangle.getSize().getPara1(), 0.001);
    assertEquals(75, rectangle.getSize().getPara2(), 0.001);

    oval.setSize(new Size(20, 30));
    assertEquals(20, oval.getSize().getPara1(), 0.001);
    assertEquals(30, oval.getSize().getPara2(), 0.001);
  }

  /**
   * Test setColor method.
   */
  @Test
  public void testSetColor() {
    rectangle.setColor(new Color(255, 255, 255));
    assertEquals(255, rectangle.getColor().getRed(), 0.001);
    assertEquals(255, rectangle.getColor().getGreen(), 0.001);
    assertEquals(255, rectangle.getColor().getBlue(), 0.001);

    oval.setColor(new Color(0, 0, 0));
    assertEquals(0, oval.getColor().getRed(), 0.001);
    assertEquals(0, oval.getColor().getGreen(), 0.001);
    assertEquals(0, oval.getColor().getBlue(), 0.001);
  }

  /**
   * Test setAppearTime method.
   */
  @Test
  public void testSetAppearTime() {
    rectangle.setAppearTime(10);
    assertEquals(10, rectangle.getAppearTime());

    oval.setAppearTime(0);
    assertEquals(0, oval.getAppearTime());
  }

  /**
   * Test setDisappearTime method.
   */
  @Test
  public void testSetDisappearTime() {
    rectangle.setDisappearTime(200);
    assertEquals(200, rectangle.getDisappearTime());

    oval.setDisappearTime(150);
    assertEquals(150, oval.getDisappearTime());
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    String expected1 =
        "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n";
    assertEquals(expected1, rectangle.toString());
    
    String expected2 =
        "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n";
    assertEquals(expected2, oval.toString());
  }

  /**
   * Test invalid appear time and disappear time.
   */
  @Test
  public void testInvalidTime() {
    // appear time or disappear time < 0
    try {
      new Rectangle("R2", new Position(200, 200), new Size(50, 100),
          new Color(1, 0, 0), -1, 100);
      fail("Constructor didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {
      
    }

    try {
      new Oval("C2", new Position(500, 100), new Size(60, 30), new Color(0, 0, 1), -100,
          -6);
      fail("Constructor didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }

    // disappear time < appear time
    try {
      new Rectangle("R2", new Position(200, 200), new Size(50, 100),
          new Color(1, 0, 0), 100, 50);
      fail("Constructor didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }

    try {
      new Oval("C2", new Position(500, 100), new Size(60, 30), new Color(0, 0, 1), 300,
          250);
      fail("Constructor didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }
  }

  /**
   * Test invalid color values. All of the RGB components should be in [0, 255].
   */
  @Test
  public void testInvalidColor() {
    double red;
    double green;
    double blue;
    for (int i = 0; i < 1000; i++) {
      red = Math.random() * 500 * (Math.random() > 0.5 ? 1 : -1);
      green = Math.random() * 500 * (Math.random() > 0.5 ? 1 : -1);
      blue = Math.random() * 500 * (Math.random() > 0.5 ? 1 : -1);
      if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
        boolean myBool;
        myBool = true;
        assertTrue(myBool);
      } else {
        try {
          new Color(red, green, blue);
          fail("Constructor didn't throw an exception when it should have.");
        } catch (IllegalArgumentException ignored) {

        }
      }
    }
  }

  /**
   * Test invalid size. Size components should not be negative.
   */
  @Test
  public void testInvalidSize() {
    double para1;
    double para2;
    for (int i = 0; i < 1000; i++) {
      para1 = -Math.random() * 500 + 1;
      para2 = -Math.random() * 500 + 1;
      try {
        new Size(para1, para2);
        fail("Constructor didn't throw an exception when it should have.");
      } catch (IllegalArgumentException ignored) {

      }
    }
  }

}
