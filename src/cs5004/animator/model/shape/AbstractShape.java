package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents an abstract shape that implements Shape Interface.
 * 
 * @author shishuai
 *
 */
public abstract class AbstractShape implements Shape {
  protected String name;
  protected ShapeType type;
  protected Position position;
  protected Size size;
  protected Color color;
  protected int appearTime;
  protected int disappearTime;

  /**
   * Construct an abstract shape object with the given parameters.
   * 
   * @param name          the name of this shape
   * @param type          the type of this shape
   * @param position      the position of this shape
   * @param size          the size of this shape
   * @param color         the color of this shape
   * @param appearTime    the appear time of this shape
   * @param disappearTime the disappear time of this shape
   * @throws IllegalArgumentException if appear time or disappear time is negative
   *                                  or disappear time comes before appear time
   */
  public AbstractShape(String name, ShapeType type, Position position, Size size, Color color,
      int appearTime, int disappearTime) throws IllegalArgumentException {
    if (name == null || type == null || position == null || size == null || color == null) {
      throw new IllegalArgumentException("Pointer cannot be null.");
    }
    if (appearTime < 0 || disappearTime < 0) {
      throw new IllegalArgumentException("Time reference cannot be negative.");
    }
    if (disappearTime < appearTime) {
      throw new IllegalArgumentException("Disappear time cannot be smaller than appear time.");
    }
    this.name = name;
    this.type = type;
    this.position = position;
    this.size = size;
    this.color = color;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public Size getSize() {
    return this.size;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public void setPosition(Position newPosition) {
    this.position = newPosition;
  }

  @Override
  public void setSize(Size newSize) {
    this.size = newSize;
  }

  @Override
  public void setColor(Color newColor) {
    this.color = newColor;
  }

  @Override
  public void setAppearTime(int newAppearTime) {
    this.appearTime = newAppearTime;
  }

  @Override
  public void setDisappearTime(int newDisappearTime) {
    this.disappearTime = newDisappearTime;
  }

}
