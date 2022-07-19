package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;

/**
 * This interface represents the animations. It contains all the methods that all
 * the animations should support.
 * 
 * @author shishuai
 *
 */
public interface Animation {
  
  /**
   * Get the name of the shape that this animation belongs to.
   * 
   * @return the name of the shape that this animation belongs to
   */
  String getShapeName();
  
  /**
   * Get the shape type of the shape that this animation belongs to.
   * 
   * @return the shape type of the shape that this animation belongs to
   */
  ShapeType getShapeType();
  
  /**
   * Get the animation type.
   * 
   * @return the animation type
   */
  AnimationType getAnimationType();
  
  /**
   * Get the starting time of this animation.
   * 
   * @return the starting time of this animation
   */
  int getFromTime();
  
  /**
   * Get the ending time of this animation.
   * 
   * @return the ending time of this animation
   */
  int getToTime();

}
