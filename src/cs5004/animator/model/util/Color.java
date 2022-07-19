package cs5004.animator.model.util;

/**
 * This class represents the color of a shape. The color is in RGB format.
 * 
 * @author shishuai
 *
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * Construct a color with the given red code, green code, and blue code.
   * 
   * @param red   the given red code
   * @param green the given green code
   * @param blue  the given blue code
   * @throw IllegalArgumentException if any of RGB values is smaller than 0 or
   *        bigger than 255
   */
  public Color(double red, double green, double blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("RGB valid range [0, 255].");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Get the red code of a color.
   * 
   * @return the red code of a color
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Get the green code of a color.
   * 
   * @return the green code of a color
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Get the blue code of a color.
   * 
   * @return the blue code of a color
   */
  public double getBlue() {
    return this.blue;
  }

  /**
   * Override toString method. Return a string to represent a color. 
   * e.g. (255.0, 255.0, 255.0)
   */
  @Override
  public String toString() {
    return String.format("(%.1f, %.1f, %.1f)", this.red, this.green, this.blue);
  }

}
