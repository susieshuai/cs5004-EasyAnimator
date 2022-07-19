package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;

/**
 * This class represents change color animation. It extends AbstractAnimation
 * class.
 * 
 * @author shishuai
 *
 */
public class ChangeColor extends AbstractAnimation {

  /**
   * Construct a change color animation object with the given parameters.
   * 
   * @param shapeName the name of the shape
   * @param shapeType the type of the shape
   * @param fromTime  the starting time of this animation
   * @param toTime    the ending time of this animation
   * @param fromColor the old color of this shape
   * @param toColor   the new color of this shape
   */
  public ChangeColor(String shapeName, ShapeType shapeType, int fromTime, int toTime,
      Color fromColor, Color toColor) {
    super(shapeName, shapeType, AnimationType.CHANGECOLOR, fromTime, toTime);
    this.fromColor = fromColor;
    this.toColor = toColor;
  }

  /**
   * Override the toString method. Return a string that describes a change color
   * animation.
   */
  @Override
  public String toString() {
    String description = "";
    description += "Shape " + this.shapeName + " " + this.animationType.toSting() + " from "
        + this.fromColor.toString() + " to " + this.toColor.toString()
        + String.format(" from t=%d to t=%d\n", this.fromTime, this.toTime);
    return description;
  }

  /**
   * Calculate the current color at the given time.
   * 
   * @param currentTime current time
   * @return current color
   */
  public Color currentColor(int currentTime) {
    double red = calculateTween(this.fromColor.getRed(), this.toColor.getRed(), currentTime);
    double green = calculateTween(this.fromColor.getGreen(), this.toColor.getGreen(), currentTime);
    double blue = calculateTween(this.fromColor.getBlue(), this.toColor.getBlue(), currentTime);
    return new Color(red, green, blue);
  }

  /**
   * Get the starting color.
   * 
   * @return the starting color
   */
  public Color getFromColor() {
    return this.fromColor;
  }

  /**
   * Get the ending color.
   * 
   * @return the ending color
   */
  public Color getToColor() {
    return this.toColor;
  }
}
