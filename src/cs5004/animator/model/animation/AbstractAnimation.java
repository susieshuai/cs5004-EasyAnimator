package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents an abstract animation that implements Animation
 * Interface.
 * 
 * @author shishuai
 *
 */
public abstract class AbstractAnimation implements Animation {
  protected String shapeName;
  protected ShapeType shapeType;
  protected AnimationType animationType;
  protected Position fromPosition;
  protected Position toPosition;
  protected Size fromSize;
  protected Size toSize;
  protected Color fromColor;
  protected Color toColor;
  protected int fromTime;
  protected int toTime;

  /**
   * Construct an abstract animation object with the given parameters.
   * 
   * @param shapeName     the name of the shape
   * @param shapeType     the type of the shape
   * @param animationType the type of the animation
   * @param fromTime      the starting time of this animation
   * @param toTime        the ending time of this animation
   * @throws IllegalArgumentException if staring or ending time is smaller than 0
   *                                  or ending time comes before starting time
   */
  public AbstractAnimation(String shapeName, ShapeType shapeType, AnimationType animationType,
      int fromTime, int toTime) throws IllegalArgumentException {
    if (fromTime < 0 || toTime < 0) {
      throw new IllegalArgumentException("Time reference cannot be negative.");
    }
    if (toTime < fromTime) {
      throw new IllegalArgumentException(
          "Animation ending time cannot be smaller than starting time.");
    }
    this.shapeName = shapeName;
    this.shapeType = shapeType;
    this.animationType = animationType;
    this.fromTime = fromTime;
    this.toTime = toTime;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }

  @Override
  public AnimationType getAnimationType() {
    return this.animationType;
  }

  @Override
  public int getFromTime() {
    return this.fromTime;
  }

  @Override
  public int getToTime() {
    return this.toTime;
  }

  /**
   * Calculate the intermediate state of a shape with the given starting state,
   * ending state, and time. The state could be any attribute of a shape, like
   * position, size, and color.
   * 
   * @param a           the starting state of a shape
   * @param b           the ending state of a shape
   * @param currentTime the current time
   * @return the current state of a shape
   * @throws IllegalArgumentException if the current time is not in the duration
   *                                  when this animation is performed
   */
  protected double calculateTween(double a, double b, int currentTime)
      throws IllegalArgumentException {
    if (currentTime < this.fromTime || currentTime > this.toTime) {
      throw new IllegalArgumentException("Current time must be in range [fromTime, toTime].");
    }
    return a * (this.toTime - currentTime) / (this.toTime - this.fromTime)
        + b * (currentTime - this.fromTime) / (this.toTime - this.fromTime);
  }
  
 
}
