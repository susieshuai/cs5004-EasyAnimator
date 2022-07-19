package cs5004.animator.model;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;

import java.util.List;
import java.util.Map;


/**
 * This interface represents the model. It contains all the operations that all
 * the models should support.
 * 
 * @author shishuai
 *
 */
public interface Model {

  /**
   * Get all shapes as a map.
   * 
   * @return a map contains all shapes
   */
  Map<String, Shape> getAllShapes();

  /**
   * Get all animations as a map.
   * 
   * @return a map contains all the animations.
   */
  Map<String, List<Animation>> getAllAnimations();

  /**
   * Get all animations sorted by starting time as a list.
   * 
   * @return a list contains all the animations sorted by staring time
   */
  List<Animation> getSortedAnimations();

  /**
   * Add a shape to the model.
   * 
   * @param shape the given shape object
   * @throws IllegalArgumentException if a shape with the same name has already
   *                                  exists
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Remove a shape from the model.
   * 
   * @param shapeName the name of the shape to be removed
   * @throws IllegalArgumentException if the shape to be removed doesn't exist in
   *                                  this model
   */
  void removeShape(String shapeName) throws IllegalArgumentException;

  /**
   * Add an animation to the model.
   * 
   * @param animation the animation to be added to the model
   * @throws IllegalArgumentException 1)if the shape that this animation belongs
   *                                  to doesn't exist, 2)or this animation's time
   *                                  duration is out of bounds, 3)or this
   *                                  animation has time conflict with existing
   *                                  animations
   */
  void addAnimation(Animation animation) throws IllegalArgumentException;

  /**
   * Get canvas.
   * 
   * @return the canvas
   */
  Canvas getCanvas();

  /**
   * Set canvas.
   * 
   * @param canvas the given canvas
   */
  void setCanvas(Canvas canvas);

  /**
   * Get the list of frames that each frame contains Shapes with corresponding
   * state. This list is used for visual view and editor view. We paint the shapes
   * frame by frame on the canvas. Thus plays the animations.
   * 
   * @return a list of frames that each frame contains Shapes with corresponding
   *         state
   */
  List<List<Shape>> getFrameShapes();
}
