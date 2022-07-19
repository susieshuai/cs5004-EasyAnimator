package cs5004.animator.model.shape;

/**
 * This enum represents the shape types. Currently there are two shape types,
 * rectangle and oval.
 * 
 * @author shishuai
 *
 */
public enum ShapeType {
  RECTANGLE("rectangle"), 
  OVAL("oval");

  private String type;

  private ShapeType(String type) {
    this.type = type;
  }

  /**
   * Return the shape type as a string.
   * 
   * @return the shape type as a string.
   */
  public String toSting() {
    return this.type;
  }
}
