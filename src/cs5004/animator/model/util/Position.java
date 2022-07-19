package cs5004.animator.model.util;

/**
 * This class represents the position of a shape. This point is denoted in
 * Cartesian coordinates as (x,y).
 * 
 * @author shishuai
 *
 */
public class Position {
  private double x;
  private double y;

  /**
   * Construct a position with the given coordinates. For a rectangle, the
   * position is the lower left corner. For an oval, the position is the center.
   *
   * @param x the x-coordinate of this position
   * @param y the y-coordinate of this position
   */
  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the x-coordinate of this position.
   * 
   * @return x the x-coordinate of this position
   */
  public double getX() {
    return x;
  }

  /**
   * Get the y-coordinate of this position.
   * 
   * @return y the x-coordinate of this position
   */
  public double getY() {
    return y;
  }

  /**
   * Override toString method. Return a string to represent a position. 
   * e.g. (100.0, 200.0)
   */
  @Override
  public String toString() {
    return String.format("(%.1f, %.1f)", this.x, this.y);
  }
}
