package cs5004.animator.model.util;

/**
 * This class represents the size of a shape.
 * 
 * @author shishuai
 *
 */
public class Size {
  private double para1;
  private double para2;

  /**
   * Construct a size object with the given parameters. For a rectangle, the size
   * para1 is the width, para2 is the height. For an oval, the size para1 is X
   * radius, para2 is the Y radius.
   * 
   * @param para1 the fist given parameter
   * @param para2 the second given parameter
   * @throws IllegalArgumentException if any of the size parameters is negative
   */
  public Size(double para1, double para2) throws IllegalArgumentException {
    if (para1 < 0 || para2 < 0) {
      throw new IllegalArgumentException("Size parameters should be positive.");
    }
    this.para1 = para1;
    this.para2 = para2;
  }

  /**
   * Return the first parameter of a size object.
   * 
   * @return the first parameter of a size object
   */
  public double getPara1() {
    return this.para1;
  }

  /**
   * Return the second parameter of a size object.
   * 
   * @return the second parameter of a size object
   */
  public double getPara2() {
    return this.para2;
  }

}
