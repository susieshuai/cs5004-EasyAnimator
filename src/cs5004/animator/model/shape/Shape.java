package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This interface represents the shapes. It contains all the getters and setters
 * that all the shapes should support.
 * 
 * @author shishuai
 *
 */
public interface Shape {

  /**
   * Get the name of this shape.
   * 
   * @return the name of this shape
   */
  String getName();

  /**
   * Get the type of this shape.
   * 
   * @return the type of this shape
   */
  ShapeType getType();

  /**
   * Get the position of this shape. For a rectangle, the position is the lower
   * left corner. For an oval, the position is the center.
   * 
   * @return the position of this shape
   */
  Position getPosition();

  /**
   * Get the size of this shape. For a rectangle, the size para1 is the width,
   * para2 is the height. For an oval, the size para1 is X radius, para2 is the Y
   * radius.
   * 
   * @return the size of this shape
   */
  Size getSize();

  /**
   * Get the color of this shape.
   * 
   * @return the color of this shape
   */
  Color getColor();

  /**
   * Get the appear time of this shape.
   * 
   * @return the appear time of this shape
   */
  int getAppearTime();

  /**
   * Get the disappear time of this shape.
   * 
   * @return the disappear time of this shape
   */
  int getDisappearTime();

  /**
   * Set the position of this shape.
   * 
   * @param newPosition the new position of this shape
   */
  void setPosition(Position newPosition);

  /**
   * Set the size of this shape.
   * 
   * @param newSize the new size of this shape
   */
  void setSize(Size newSize);

  /**
   * Set the color of this shape.
   * 
   * @param newColor the new color of this shape
   */
  void setColor(Color newColor);

  /**
   * Set the appear time of this shape.
   * 
   * @param newAppearTime the appear time of this shape
   */
  void setAppearTime(int newAppearTime);

  /**
   * Set the disappear time of this shape.
   * 
   * @param newDisappearTime the disappear time of this shape
   */
  void setDisappearTime(int newDisappearTime);
  
  /**
   * Clone the shape.
   * 
   * @return the cloned shape of this shape
   */
  Shape cloneShape();
}
