package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents Rectangle. It offers all the operations mandated by the
 * Shape interface.
 * 
 * @author shishuai
 *
 */
public class Rectangle extends AbstractShape {

  /**
   * Construct a rectangle with the given name, position, size, color, appear time
   * and disappear time.
   * 
   * @param name          the name of this rectangle
   * @param position      the position of this rectangle
   * @param size          the size of this rectangle
   * @param color         the color of this rectangle
   * @param appearTime    the appear time of this rectangle
   * @param disappearTime the disappear time of this rectangle
   */
  public Rectangle(String name, Position position, Size size, Color color, int appearTime,
      int disappearTime) {
    super(name, ShapeType.RECTANGLE, position, size, color, appearTime, disappearTime);
  }
  
  /**
   * Override the toString method. Return a string that describes a rectangle.
   * e.g. Name: R Type: rectangle Min corner: (200.0,200.0), Width: 50.0, Height:
   * 100.0, Color: (1.0,0.0,0.0) Appears at t=1 Disappears at t=100
   */
  @Override
  public String toString() {
    String description = "Name: " + this.name + "\n" 
        + "Type: " + this.type.toSting() + "\n"
        + "Min corner: " + this.position.toString()
        + String.format(", Width: %.1f, Height: %.1f, ", this.size.getPara1(), this.size.getPara2())
        + "Color: " + this.color.toString() + "\n" 
        + "Appears at t=" + this.appearTime + "\n"
        + "Disappears at t=" + this.disappearTime 
        + "\n";
    return description;
  }

  @Override
  public Rectangle cloneShape() {
    return new Rectangle(this.name, this.position, this.size, this.color, this.appearTime,
        this.disappearTime);
  }
}
