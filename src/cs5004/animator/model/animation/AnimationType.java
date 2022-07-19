package cs5004.animator.model.animation;

/**
 * This enum represents the animation types. Currently there are three animation
 * types, move, change color, and scale.
 * 
 * @author shishuai
 */
public enum AnimationType {
  MOVE("moves"), 
  CHANGECOLOR("changes color"), 
  SCALE("scales");

  private String type;

  private AnimationType(String type) {
    this.type = type;
  }

  /**
   * Return the animation type as a string.
   * 
   * @return the animation type as a string.
   */
  public String toSting() {
    return this.type;
  }
}
