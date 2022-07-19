package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Position;

/**
 * This class represents move animation. It extends AbstractAnimation class.
 * 
 * @author shishuai
 *
 */
public class Move extends AbstractAnimation {

  /**
   * Construct a move animation object with the given parameters.
   * 
   * @param shapeName    the name of the shape
   * @param shapeType    the type of the shape
   * @param fromTime     the starting time of this animation
   * @param toTime       the ending time of this animation
   * @param fromPosition the old position of this shape
   * @param toPosition   the new position of this shape
   */
  public Move(String shapeName, ShapeType shapeType, int fromTime, int toTime,
      Position fromPosition, Position toPosition) {
    super(shapeName, shapeType, AnimationType.MOVE, fromTime, toTime);
    this.fromPosition = fromPosition;
    this.toPosition = toPosition;
  }

  /**
   * Override the toString method. Return a string that describes a move
   * animation.
   */
  @Override
  public String toString() {
    String description = "";
    description += "Shape " + this.shapeName + " " + this.animationType.toSting() + " from "
        + this.fromPosition.toString() + " to " + this.toPosition.toString()
        + String.format(" from t=%d to t=%d\n", this.fromTime, this.toTime);
    return description;
  }

  /**
   * Calculate the current position at the given time.
   * 
   * @param currentTime current time
   * @return current position
   */
  public Position currentPosition(int currentTime) {
    double x = calculateTween(this.fromPosition.getX(), this.toPosition.getX(), currentTime);
    double y = calculateTween(this.fromPosition.getY(), this.toPosition.getY(), currentTime);
    return new Position(x, y);
  }

  /**
   * Get the starting position.
   * 
   * @return the starting position
   */
  public Position getFromPosition() {
    return this.fromPosition;
  }

  /**
   * Get the ending position.
   * 
   * @return the ending position
   */
  public Position getToPosition() {
    return this.toPosition;
  }
}
