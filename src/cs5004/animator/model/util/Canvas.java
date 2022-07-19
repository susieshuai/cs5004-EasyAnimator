package cs5004.animator.model.util;

/**
 * This class represents the Canvas. The position of the canvas is represented
 * by the coordinates of the upper left corner. The dimension of the canvas is
 * represented by the width and height.
 * 
 * @author shishuai
 *
 */
public class Canvas {
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Construct a canvas with the given x, y, width, and height.
   * 
   * @param x      the x-coordinate of the upper left corner of this canvas
   * @param y      the y-coordinate of the upper left corner of this canvas
   * @param width  the width of this canvas
   * @param height the height of this canvas
   */
  public Canvas(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /**
   * Get the x-coordinate of the upper left corner of this canvas.
   * 
   * @return the x-coordinate of the upper left corner of this canvas
   */
  public int getX() {
    return this.x;
  }

  /**
   * Get the y-coordinate of the upper left corner of this canvas.
   * 
   * @return the y-coordinate of the upper left corner of this canvas
   */
  public int getY() {
    return this.y;
  }

  /**
   * Get the width of this canvas.
   * 
   * @return the width of this canvas
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of this canvas.
   * 
   * @return the height of this canvas
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Override the toString method to get the description of the canvas.
   */
  @Override
  public String toString() {
    return this.x + " " + this.y + " " + this.width + " " + this.height + "\n";
  }
}
