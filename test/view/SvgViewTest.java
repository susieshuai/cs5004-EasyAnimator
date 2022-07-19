package view;

import static org.junit.Assert.assertEquals;

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
import cs5004.animator.view.SvgView;
import cs5004.animator.view.View;
import org.junit.Before;
import org.junit.Test;

/**
 * This is SVG view test class. It tests if the SvgView class produces SVG file
 * with correct format.
 * 
 * @author shishuai
 *
 */
public class SvgViewTest {
  private View svgView;

  /**
   * Set up the testing sample.
   */
  @Before
  public void setUp() {
    Model model = new ModelImpl();
    
    Shape rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100), 
        new Color(1, 0, 0), 1,
        100);
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), 
        new Color(0, 0, 1), 6, 100);
    // this shape has no motion
    Shape oval2 = new Oval("C2", new Position(50, 50), new Size(50, 50), 
        new Color(0, 0, 1), 6, 100);
    
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
    
    model.addShape(rectangle);
    model.addShape(oval);
    model.addShape(oval2);
    model.addAnimation(animation1);
    model.addAnimation(animation2);
    model.addAnimation(animation3);
    model.addAnimation(animation4);
    model.addAnimation(animation5);

    svgView = new SvgView(model.getAllShapes(), model.getAllAnimations(), "", "10");
  }
  
  /**
   * Test display method.
   */
  @Test
  public void testDisplay() {
    String expected = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(1,0,0)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\"x\""
        + " from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"4000ms\" attributeName=\"y\""
        + " from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" attributeName=\"width\""
        + " from=\"50\" to=\"25\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"5100ms\" dur=\"1900ms\" attributeName=\""
        + "height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" attributeName=\"x\""
        + " from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"7000ms\" dur=\"3000ms\" attributeName=\"y\""
        + " from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"30\" ry=\"15\" fill=\"rgb(0,0,1)\""
        + " visibility=\"visible\" >\n"
        + "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"5000ms\" attributeName=\"cx\""
        + " from=\"500\" to=\"500\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"2000ms\" dur=\"5000ms\" attributeName=\"cy\""
        + " from=\"100\" to=\"400\" fill=\"freeze\" />\n"
        + "\t<animate attributeType=\"xml\" begin=\"5000ms\" dur=\"3000ms\" attributeName=\"fill\""
        + " from=\"rgb(0,0,1)\" to=\"rgb(0,1,0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "<ellipse id=\"C2\" cx=\"50\" cy=\"50\" rx=\"25\" ry=\"25\" fill=\"rgb(0,0,1)\""
        + " visibility=\"hidden\" >\n"
        + "\t<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" begin=\"600ms\""
        + " fill=\"freeze\"/>\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>";
    assertEquals(expected, svgView.toString());
  }

}
