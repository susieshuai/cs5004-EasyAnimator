package cs5004.animator.model;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;
import cs5004.animator.util.AnimationBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements Interface Model.
 * 
 * @author shishuai
 *
 */
public class ModelImpl implements Model {

  // a map stores all the shapes
  // key: shape name
  // value: shape object
  private Map<String, Shape> allShapes;

  // a map stores all the animations
  // key: shape name
  // value: a list of animations that belongs to this shape
  private Map<String, List<Animation>> allAnimations;

  // a list stores all the animations in the order of staring time
  private List<Animation> sortedAnimations;

  // a list stores shapes in each frame
  // this list is used for visual view
  private List<List<Shape>> frameShapes;

  private Canvas canvas;

  /**
   * Construct a model object with no argument.
   */
  public ModelImpl() {
    this.allShapes = new LinkedHashMap<>(); // LinkedHashMap to record adding order
    this.allAnimations = new HashMap<>(); // order doesn't matter, okay to use HashMap
    this.sortedAnimations = new ArrayList<>(); // for text view
    this.frameShapes = new ArrayList<>(); // for graphic view
  }

  @Override
  public Map<String, Shape> getAllShapes() {
    Map<String, Shape> newAllShapes = new LinkedHashMap<>();
    for (String shapeName : this.allShapes.keySet()) {
      newAllShapes.put(shapeName, this.allShapes.get(shapeName));
    }
    return newAllShapes;
  }

  @Override
  public Map<String, List<Animation>> getAllAnimations() {
    Map<String, List<Animation>> newAllAnimations = new HashMap<>();
    for (String shapeName : this.allAnimations.keySet()) {
      newAllAnimations.put(shapeName, this.allAnimations.get(shapeName));
    }
    return newAllAnimations;
  }

  @Override
  public List<Animation> getSortedAnimations() {
    return new ArrayList<>(this.sortedAnimations);
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (shapeExists(shape.getName())) {
      throw new IllegalArgumentException("Cannot add a shape with the existing name.");
    }
    this.allShapes.put(shape.getName(), shape);
  }

  /**
   * Check if a shape to be added has already exists.
   * 
   * @param shapeName the name of the shape to be added
   * @return true if a shape with the same name has already exists, otherwise
   *         return false
   */
  private Boolean shapeExists(String shapeName) {
    for (String name : this.allShapes.keySet()) {
      if (name.equals(shapeName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (!shapeExists(shapeName)) {
      throw new IllegalArgumentException("Cannot remove a shape that doesn't exist.");
    }
    // remove this shape from shapes map
    this.allShapes.remove(shapeName);
    // remove animations of this shape from animations map
    this.allAnimations.remove(shapeName);
    // remove animations of this shape from sorted animations list
    this.sortedAnimations.removeIf(animation -> animation.getShapeName().equals(shapeName));
  }

  @Override
  public void addAnimation(Animation animation) throws IllegalArgumentException {
    // shape doesn't exist
    String shapeName = animation.getShapeName();
    if (!shapeExists(shapeName)) {
      throw new IllegalArgumentException("Shape with this name doesn't exist.");
    }
    // shape exists, but animation time is out of shape's life span
    Shape shape = this.allShapes.get(shapeName);
    if (animation.getFromTime() < shape.getAppearTime()
        || animation.getToTime() > shape.getDisappearTime()) {
      throw new IllegalArgumentException("Animation should exist when shape appears.");
    }
    // animation has time conflicts with existing animations
    if (animationOverlap(animation)) {
      throw new IllegalArgumentException("Animation time conflicts with existing animations.");
    }

    // animation valid
    // add to animations map
    if (!this.allAnimations.containsKey(shapeName)) {
      this.allAnimations.put(shapeName, new ArrayList<>());
    }
    List<Animation> animationList = this.allAnimations.get(shapeName);
    animationList.add(animation);

    // add to sorted animations list
    if (this.sortedAnimations.isEmpty()) {
      this.sortedAnimations.add(animation);
    } else {
      int i;
      for (i = 0; i < this.sortedAnimations.size(); i++) {
        Animation myAnimation = this.sortedAnimations.get(i);
        if (myAnimation.getFromTime() > animation.getFromTime()) {
          break;
        }
      }
      this.sortedAnimations.add(i, animation);
    }
  }

  /**
   * Check if the animation to be added overlaps with the existing animations or
   * not.
   * 
   * @param animation the animation to be added
   * @return true if the animation to be added overlaps with existing same type of
   *         animations of the same shape, otherwise return false
   */
  private Boolean animationOverlap(Animation animation) {
    List<Animation> myAnimationList = this.allAnimations.get(animation.getShapeName());
    if (myAnimationList == null) {
      return false;
    }
    for (Animation myAnimation : myAnimationList) {
      if (myAnimation.getAnimationType() == animation.getAnimationType()) {
        if (animation.getToTime() <= myAnimation.getFromTime()
            || animation.getFromTime() >= myAnimation.getToTime()) {
          continue;
        } else {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Shapes:\n");

    // shapes
    Collection<Shape> allShapeCollection = this.allShapes.values();
    for (Shape shape : allShapeCollection) {
      description.append(shape.toString());
      description.append("\n");
    }

    // animations
    for (Animation animation : this.sortedAnimations) {
      description.append(animation.toString());
    }

    return description.toString();
  }

  @Override
  public Canvas getCanvas() {
    //return new Canvas(this.canvas.getX(), this.getCanvas().getY(), this.getCanvas().getWidth(),
    //this.getCanvas().getHeight());
    return this.canvas;
  }

  @Override
  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  /////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////

  /**
   * This method is mainly for the visual view.
   */
  @Override
  public List<List<Shape>> getFrameShapes() {
    // initialize the list
    for (int i = 0; i <= this.getEndTime(); i++) {
      this.frameShapes.add(i, new ArrayList<>());
    }

    // add shapes to list frame by frame
    Collection<Shape> allShapeCollection = this.allShapes.values();
    for (Shape shape : allShapeCollection) {
      List<Shape> frames = convertToFrame(shape);
      for (int i = 0; i <= shape.getDisappearTime(); i++) {
        this.frameShapes.get(i).add(frames.get(i));
      }
    }

    return this.frameShapes;

  }

  /**
   * Convert one shape to frame list.
   * 
   * @param shape the given shape
   * @return a frame list that contains a shape's state at each frame
   */
  private List<Shape> convertToFrame(Shape shape) {
    List<Shape> frameList = new ArrayList<>();
    Shape shapeToAdd = null;
    for (int i = 0; i <= shape.getDisappearTime(); i++) {
      //      if (i < shape.getAppearTime()) {
      //        // do nothing
      //      }
      // initialize shape to be added
      if (i == shape.getAppearTime()) {
        shapeToAdd = shape.cloneShape(); // first frame: clone state at appear time
      }
      if (i > shape.getAppearTime()) {
        // clone from last frame, then update if there are animations
        shapeToAdd = frameList.get(i - 1).cloneShape(); 
      }
      // update shape if there are animations
      List<Animation> shapeAnimations = this.allAnimations.get(shape.getName());
      if (shapeAnimations != null) {
        for (Animation animation : shapeAnimations) {
          if (animation.getFromTime() <= i && i <= animation.getToTime()) {
            if (animation instanceof Move) {
              Position pos = ((Move) animation).currentPosition(i);
              shapeToAdd.setPosition(pos);
            }
            if (animation instanceof Scale) {
              Size size = ((Scale) animation).currentSize(i);
              shapeToAdd.setSize(size);
            }
            if (animation instanceof ChangeColor) {
              Color color = ((ChangeColor) animation).currentColor(i);
              shapeToAdd.setColor(color);
            }
          }
        }
      }
      frameList.add(i, shapeToAdd);
    }
    return frameList;
  }

  /**
   * Get the end time of the animations.
   * 
   * @return the end time of the animations
   */
  private int getEndTime() {
    int endTime = 0;
    Collection<Shape> allShapeCollection = this.allShapes.values();
    for (Shape shape : allShapeCollection) {
      if (shape.getDisappearTime() > endTime) {
        endTime = shape.getDisappearTime();
      }
    }
    return endTime;
  }

  /////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Implement the AnimationBuilder interface to connect the given parsing code
   * with model.
   * 
   * @author shishuai
   *
   */
  public static final class Builder implements AnimationBuilder<Model> {
    private Model model;

    /**
     * Construct a Builder object with no argument.
     */
    public Builder() {
      this.model = new ModelImpl();
    }

    @Override
    public Model build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<Model> setBounds(int x, int y, int width, int height) {
      this.model.setCanvas(new Canvas(x, y, width, height));
      return this;
    }

    @Override
    public AnimationBuilder<Model> declareShape(String name, String type) {
      if (type.equalsIgnoreCase("rectangle")) {
        this.model.addShape(
            new Rectangle(name, new Position(0, 0), new Size(0, 0), new Color(0, 0, 0), 0, 0));
      } else if (type.equalsIgnoreCase("ellipse")) {
        this.model
            .addShape(new Oval(name, new Position(0, 0), new Size(0, 0), new Color(0, 0, 0), 0, 0));
      }
      return this;
    }

    @Override
    public AnimationBuilder<Model> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2)
        throws IllegalArgumentException {
      if (this.model.getAllShapes().get(name) == null) {
        throw new IllegalArgumentException("Shape doesn't exist.");
      }

      Shape shape = this.model.getAllShapes().get(name);
      // if it is the first time to add a motion to a shape, initialize the shape
      if (shape.getAppearTime() == 0 && shape.getDisappearTime() == 0) {
        shape.setPosition(new Position(x1, y1));
        shape.setSize(new Size(w1, h1));
        shape.setColor(new Color(r1, g1, b1));
        shape.setAppearTime(t1);
        shape.setDisappearTime(t2);
      }
      // add motion
      // extend the disappear time
      shape.setDisappearTime(t2);
      // move
      if (x1 != x2 || y1 != y2) {
        this.model.addAnimation(
            new Move(name, shape.getType(), t1, t2, new Position(x1, y1), new Position(x2, y2)));
      }
      // scale
      if (w1 != w2 || h1 != h2) {
        this.model.addAnimation(
            new Scale(name, shape.getType(), t1, t2, new Size(w1, h1), new Size(w2, h2)));
      }
      // color change
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        this.model.addAnimation(new ChangeColor(name, shape.getType(), t1, t2,
            new Color(r1, g1, b1), new Color(r2, g2, b2)));
      }
      return this;
    }
  }

}
