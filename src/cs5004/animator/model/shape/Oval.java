package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents Oval. It offers all the operations mandated by the
 * Shape interface.
 * 
 * @author shishuai
 *
 */
public class Oval extends AbstractShape {

  /**
   * Construct an oval with the given name, position, size, color, appear time and
   * disappear time.
   * 
   * @param name          the name of this oval
   * @param position      the position of this oval
   * @param size          the size of this oval
   * @param color         the color of this oval
   * @param appearTime    the appear time of this oval
   * @param disappearTime the disappear time of this oval
   */
  public Oval(String name, Position position, Size size, Color color, int appearTime,
      int disappearTime) {
    super(name, ShapeType.OVAL, position, size, color, appearTime, disappearTime);
  }

  /**
   * Override the toString method. Return a string that describes an oval.
   * e.g.
   * Name: C
   * Type: oval
   * Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
   * Appears at t=6
   * Disappears at t=100
   */
  @Override
  public String toString() {
    String description = "Name: " + this.name + "\n" 
                + "Type: " + this.type.toSting() + "\n" 
                + "Center: " + this.position.toString()
                + String.format(", X radius: %.1f, Y radius: %.1f, ",
                    this.size.getPara1(), this.size.getPara2())
                + "Color: " + this.color.toString() + "\n" 
                + "Appears at t=" + this.appearTime + "\n"
                + "Disappears at t=" + this.disappearTime + "\n";
    return description;
  }
  
  @Override
  public Oval cloneShape() {
    return new Oval(this.name, this.position, this.size, this.color,
        this.appearTime, this.disappearTime);
  }
}
