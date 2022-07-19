package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Size;

/**
 * This class represents scale animation. It extends AbstractAnimation class.
 * 
 * @author shishuai
 *
 */
public class Scale extends AbstractAnimation {

  /**
   * Construct a scale animation object with the given parameters.
   * 
   * @param shapeName the name of the shape
   * @param shapeType the type of the shape
   * @param fromTime  the starting time of this animation
   * @param toTime    the ending time of this animation
   * @param fromSize  the old size of this shape
   * @param toSize    the new size of this shape
   */
  public Scale(String shapeName, ShapeType shapeType, int fromTime, int toTime, Size fromSize,
      Size toSize) {
    super(shapeName, shapeType, AnimationType.SCALE, fromTime, toTime);
    this.fromSize = fromSize;
    this.toSize = toSize;
  }

  /**
   * Override the toString method. Return a string that describes a scale
   * animation.
   */
  @Override
  public String toString() {
    if (this.shapeType == ShapeType.RECTANGLE) {
      return String.format(
          "Shape %s scales from Width: %.1f, Height: %.1f "
              + "to Width: %.1f, Height: %.1f from t=%d to t=%d\n",
          this.shapeName, this.fromSize.getPara1(), this.fromSize.getPara2(),
          this.toSize.getPara1(), this.toSize.getPara2(), this.fromTime, this.toTime);
    } else {
      return String.format(
          "Shape %s scales from X radius: %.1f, Y radius: %.1f "
              + "to X radius: %.1f, Y radius: %.1f from t=%d to t=%d\n",
          this.shapeName, this.fromSize.getPara1(), this.fromSize.getPara2(),
          this.toSize.getPara1(), this.toSize.getPara2(), this.fromTime, this.toTime);
    }
  }

  /**
   * Calculate the current size at the given time.
   * 
   * @param currentTime current time
   * @return current size
   */
  public Size currentSize(int currentTime) {
    double para1 = calculateTween(this.fromSize.getPara1(), this.toSize.getPara1(), currentTime);
    double para2 = calculateTween(this.fromSize.getPara2(), this.toSize.getPara2(), currentTime);
    return new Size(para1, para2);
  }

  /**
   * Get the starting size.
   * 
   * @return the starting size
   */
  public Size getFromSize() {
    return this.fromSize;
  }

  /**
   * Get the ending size.
   * 
   * @return the ending size
   */
  public Size getToSize() {
    return this.toSize;
  }
}
