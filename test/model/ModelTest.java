package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
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
 * This class tests for model.
 * 
 * @author shishuai
 *
 */
public class ModelTest {
  private Model m0;
  private Model m1;
  
  /**
   * Set up the testing samples.
   */
  @Before
  public void setUp() {
    // m0 is an empty model
    m0 = new ModelImpl();
    
    // m1 is a model that set up with shapes and animations
    m1 = new ModelImpl();
    
    Shape rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100), 
        new Color(1, 0, 0), 1,
        100);
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), 
        new Color(0, 0, 1), 6, 100);
    m1.addShape(rectangle);
    m1.addShape(oval);
    
    Animation animation1 = new Move("R", ShapeType.RECTANGLE, 10, 50, new Position(200, 200),
        new Position(300, 300));
    Animation animation2 = new Move("C", ShapeType.OVAL, 20, 70, new Position(500, 100),
        new Position(500, 400));
    Animation animation3 = new ChangeColor("C", ShapeType.OVAL, 50, 80, new Color(0, 0, 1),
        new Color(0, 1, 0));
    Animation animation4 = new Scale("R", ShapeType.RECTANGLE, 51, 70, new Size(50, 100), 
        new Size(25, 100));
    Animation animation5 = new Move("R", ShapeType.RECTANGLE, 70, 100, new Position(300, 300),
        new Position(200, 200));
    m1.addAnimation(animation1);
    m1.addAnimation(animation2);
    m1.addAnimation(animation3);
    m1.addAnimation(animation4);
    m1.addAnimation(animation5);
  }
  
  /**
   * Test add shape to an empty model.
   */
  @Test
  public void testAddShapeToM0() {
    assertEquals(0, m0.getAllShapes().size());

    Shape rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100), 
        new Color(1, 0, 0), 1,
        100);
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), 
        new Color(0, 0, 1), 6, 100);
    m0.addShape(rectangle);
    m0.addShape(oval);
    
    assertEquals(2, m0.getAllShapes().size());
    
    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n";
    assertEquals(expected, m0.toString());
    
    Shape rectangle2 = new Rectangle("R2", new Position(100, 100), new Size(25, 50), 
        new Color(255, 255, 255), 1,
        100);
    m0.addShape(rectangle2);
    
    assertEquals(3, m0.getAllShapes().size());
    
    expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: R2\n"
        + "Type: rectangle\n"
        + "Min corner: (100.0, 100.0), Width: 25.0, Height: 50.0, Color: (255.0, 255.0, 255.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n";
    assertEquals(expected, m0.toString());
  }
  
  /**
   * Test add shape to a populated model.
   */
  @Test
  public void testAddShapeToM1() {
    Shape rectangle2 = new Rectangle("R2", new Position(100, 100), new Size(25, 50), 
        new Color(255, 255, 255), 1,
        100);
    m1.addShape(rectangle2);
    
    assertEquals(3, m1.getAllShapes().size());
    
    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: R2\n"
        + "Type: rectangle\n"
        + "Min corner: (100.0, 100.0), Width: 25.0, Height: 50.0, Color: (255.0, 255.0, 255.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0, 0.0, 1.0) to (0.0, 1.0, 0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100\n";
    assertEquals(expected, m1.toString());
  }
  
  /**
   * Test add an existing shape to the model.
   */
  @Test
  public void testAddExistingShape() {
    Shape rectangle2 = new Rectangle("R", new Position(100, 100), new Size(25, 50), 
        new Color(255, 255, 255), 1,
        100);
    try {
      m1.addShape(rectangle2);
      fail("Method didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }
    
    Shape oval2 = new Rectangle("C", new Position(100, 100), new Size(25, 50), 
        new Color(255, 255, 255), 1,
        100);
    try {
      m1.addShape(oval2);
      fail("Method didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }
    
    assertEquals(2, m1.getAllShapes().size());
    
    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0, 0.0, 1.0) to (0.0, 1.0, 0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100\n";
    assertEquals(expected, m1.toString());
  }
  
  /**
   * Test removeShape method.
   */
  @Test
  public void testRemoveShape() {
    assertEquals(2, m1.getAllShapes().size());
    assertEquals(2, m1.getAllAnimations().size());
    assertEquals(5, m1.getSortedAnimations().size());
    
    m1.removeShape("R");
    
    assertEquals(1, m1.getAllShapes().size());
    assertEquals(1, m1.getAllAnimations().size());
    assertEquals(2, m1.getSortedAnimations().size());
    
    String expected = "Shapes:\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0, 0.0, 1.0) to (0.0, 1.0, 0.0) from t=50 to t=80\n";
    assertEquals(expected, m1.toString());
    
    m1.removeShape("C");
    assertEquals(0, m1.getAllShapes().size());
    assertEquals(0, m1.getAllAnimations().size());
    assertEquals(0, m1.getSortedAnimations().size());
    
    expected = "Shapes:\n";
    assertEquals(expected, m1.toString());
  }
  
  /**
   * Test remove a shape that doesn't exist in the model.
   */
  @Test
  public void testRemoveNonExistingShape() {
    try {
      m1.removeShape("R2");
      fail("Method didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {
      
    }
    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0, 0.0, 1.0) to (0.0, 1.0, 0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100\n";
    assertEquals(expected, m1.toString());
  }
  
  /**
   * Test toString method.
   */
  @Test
  public void testToString() {

    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 0.0, 0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0, 0.0, 1.0) to (0.0, 1.0, 0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) from t=70 to t=100\n";
  
    assertEquals(expected, m1.toString());
  
  }

  /**
   * Test getAllShapes method.
   */
  @Test
  public void testGetAllShapes() {
    assertEquals(0, m0.getAllShapes().size());
    assertEquals(2, m1.getAllShapes().size());
  }
  
  /**
   * Test getAllAnimations method.
   */
  @Test
  public void testGetAllAnimations() {
    assertEquals(0, m0.getAllAnimations().size());
    assertEquals(2, m1.getAllAnimations().size());
  }
  
  /**
   * Test getSortedAnimations method.
   */
  @Test
  public void testGetSortedAnimations() {
    assertEquals(0, m0.getSortedAnimations().size());
    assertEquals(5, m1.getSortedAnimations().size());
  }
  
  /**
   * Test add animation method.
   */
  @Test
  public void testAddAnimation() {
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), 
        new Color(0, 0, 1), 6, 100);
    Animation animation1 = new Move("C", ShapeType.OVAL, 20, 70, new Position(500, 100),
        new Position(500, 400));
    Animation animation2 = new ChangeColor("C", ShapeType.OVAL, 50, 80, new Color(0, 0, 1),
        new Color(0, 1, 0));
    Animation animation3 = new Scale("C", ShapeType.OVAL, 50, 80, new Size(60, 30),
        new Size(30, 60));
    m0.addShape(oval);
    m0.addAnimation(animation1);
    m0.addAnimation(animation2);
    m0.addAnimation(animation3);
    
    String expected = "Shapes:\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 0.0, 1.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape C moves from (500.0, 100.0) to (500.0, 400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0, 0.0, 1.0) to (0.0, 1.0, 0.0) from t=50 to t=80\n"
        + "Shape C scales from X radius: 60.0, Y radius: 30.0 to X radius: 30.0, Y radius: 60.0 "
        + "from t=50 to t=80\n";
    assertEquals(expected, m0.toString());
  }
  
  /**
   * Test invalid add animation.
   */
  @Test
  public void testInvalidAddAnimation() {
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 1), 6,
        100);
    Animation animation1 = new Move("C", ShapeType.OVAL, 20, 70, new Position(500, 100),
        new Position(500, 400));
    Animation animation2 = new ChangeColor("C", ShapeType.OVAL, 50, 80, new Color(0, 0, 1),
        new Color(0, 1, 0));
    Animation animation3 = new Scale("C", ShapeType.OVAL, 50, 80, new Size(60, 30),
        new Size(30, 60));
    m0.addShape(oval);
    m0.addAnimation(animation1);
    m0.addAnimation(animation2);
    m0.addAnimation(animation3);

    // shape doesn't exist
    Animation animation4 = new Move("R", ShapeType.RECTANGLE, 10, 50, new Position(200, 200),
        new Position(300, 300));
    try {
      m0.addAnimation(animation4);
      fail("Method didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }

    // shape exists, but animation time is out of shape's life span
    Animation animation5 = new Move("C", ShapeType.OVAL, 90, 110, new Position(500, 400),
        new Position(500, 100));
    try {
      m0.addAnimation(animation5);
      fail("Method didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }

    // animation has time conflicts with existing animations
    Animation animation6 = new Scale("C", ShapeType.OVAL, 50, 60, new Size(60, 30), new Size(5, 5));
    try {
      m0.addAnimation(animation6);
      fail("Method didn't throw an exception when it should have.");
    } catch (IllegalArgumentException ignored) {

    }
  }
}
