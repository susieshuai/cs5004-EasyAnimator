package cs5004.animator.view;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.AnimationType;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class represents svg view. It implements View Interface.
 * 
 * @author shishuai
 *
 */
public class SvgView implements View {
  private Map<String, Shape> allShapes;
  private Map<String, List<Animation>> allAnimations;
  private String outputFileName;
  private String speed;

  /**
   * Construct a textual view object.
   * 
   * @param allShapes      the map of all the shapes
   * @param allAnimations  the map of all the animations
   * @param outputFileName the name of the output file that the data will be
   *                       written into
   * @param speed          the speed of animation
   */
  public SvgView(Map<String, Shape> allShapes, Map<String, List<Animation>> allAnimations,
      String outputFileName, String speed) {
    this.allShapes = allShapes;
    this.allAnimations = allAnimations;
    this.outputFileName = outputFileName;
    this.speed = speed;
  }

  @Override
  public void display() throws IOException {

    // if output file name is undefined, print out in the console
    // otherwise write data in the file
    String description = svgOutput();
    if (this.outputFileName.equals("")) {
      System.out.println(description);
    } else {
      try {
        FileWriter fileWriter = new FileWriter(this.outputFileName);
        fileWriter.write(description);
        fileWriter.close();
      } catch (IOException ioe) {
        throw new IOException("Fail to write file.");
      }
    }
  }

  private String svgOutput() {
    StringBuilder output = new StringBuilder();

    // heading
    output.append("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n");

    // body
    // build up shape by shape
    for (Map.Entry<String, Shape> entry : this.allShapes.entrySet()) {

      String shapeName = entry.getKey();
      Shape shape = entry.getValue();
      List<Animation> animations = this.allAnimations.get(shapeName);

      if (animations != null) {
        // shape
        output.append(svgShape(shape));
        // animation
        for (Animation animation : animations) {
          output.append(svgAnimation(animation));
        }
      } else {
        // If a shape doen't have any animation(move, scale, or change color), it only
        // shows at specified time. The following codes are to set the visibility.
        output.append(svgInvisibleShape(shape));
      }

      // end note
      if (shape.getType() == ShapeType.RECTANGLE) {
        output.append("</rect>\n");
      } else if (shape.getType() == ShapeType.OVAL) {
        output.append("</ellipse>\n");
      }

    }

    // ending
    output.append("\n</svg>");

    return output.toString();

  }

  /**
   * Convert shape information into SVG format.
   * 
   * @param shape the given shape
   * @return a string of shape information in SVG format
   */
  private String svgShape(Shape shape) {
    String shapeInfo = "";
    if (shape.getType() == ShapeType.RECTANGLE) {
      shapeInfo = String.format(
          "\n<rect id=\"%s\" x=\"%.0f\" y=\"%.0f\" width=\"%.0f\" height=\"%.0f\""
              + " fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"visible\" >\n",
          shape.getName(), shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getSize().getPara1(), shape.getSize().getPara2(), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue());
    } else if (shape.getType() == ShapeType.OVAL) {
      shapeInfo = String.format(
          "\n<ellipse id=\"%s\" cx=\"%.0f\" cy=\"%.0f\" rx=\"%.0f\" ry=\"%.0f\""
              + " fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"visible\" >\n",
          shape.getName(), shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getSize().getPara1() / 2, shape.getSize().getPara2() / 2, shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue());
    }
    return shapeInfo;
  }

  /**
   * Convert a special invisible shape information into SVG format. This kind of
   * shape has no animation, it only appears at the specified time.
   * 
   * @param shape the given shape
   * @return a string of invisible shape information in SVG format
   */
  private String svgInvisibleShape(Shape shape) {
    String shapeInfo = "";
    if (shape.getType() == ShapeType.RECTANGLE) {
      shapeInfo = String.format(
          "\n<rect id=\"%s\" x=\"%.0f\" y=\"%.0f\" width=\"%.0f\" height=\"%.0f\""
              + " fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n",
          shape.getName(), shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getSize().getPara1(), shape.getSize().getPara2(), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue());
    } else if (shape.getType() == ShapeType.OVAL) {
      shapeInfo = String.format(
          "\n<ellipse id=\"%s\" cx=\"%.0f\" cy=\"%.0f\" rx=\"%.0f\" ry=\"%.0f\""
              + " fill=\"rgb(%.0f,%.0f,%.0f)\" visibility=\"hidden\" >\n",
          shape.getName(), shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getSize().getPara1() / 2, shape.getSize().getPara2() / 2, shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue());
    }
    
    int tempo = Integer.parseInt(this.speed);
    double startTime = shape.getAppearTime() * 1000 / tempo;
    //double endTime = shape.getDisappearTime() * 1000 / tempo;
    //double duration = endTime - startTime;
    
    shapeInfo += String.format("\t<set attributeName=\"visibility\" attributeType=\"CSS\""
        + " to=\"visible\" begin=\"%.0fms\" fill=\"freeze\"/>\n", startTime);
    
    return shapeInfo;
  }
  
  /**
   * Convert animation information into SVG format.
   * 
   * @param animation the given animation
   * @return a string of animation information in SVG format
   */
  private String svgAnimation(Animation animation) {
    // different attribute names
    String positionX = "";
    String positionY = "";
    String sizeX = "";
    String sizeY = "";

    if (animation.getShapeType() == ShapeType.RECTANGLE) {
      positionX = "x";
      positionY = "y";
      sizeX = "width";
      sizeY = "height";
    } else if (animation.getShapeType() == ShapeType.OVAL) {
      positionX = "cx";
      positionY = "cy";
      sizeX = "rx";
      sizeY = "ry";
    }

    int tempo = Integer.parseInt(this.speed);
    double startTime = animation.getFromTime() * 1000 / tempo;
    double endTime = animation.getToTime() * 1000 / tempo;
    double duration = endTime - startTime;

    StringBuilder animationInfo = new StringBuilder();

    if (animation.getAnimationType() == AnimationType.MOVE) {
      Move move = (Move) animation;
      animationInfo.append(String.format(
          "\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\""
              + " attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
          startTime, duration, positionX, move.getFromPosition().getX(),
          move.getToPosition().getX()));

      animationInfo.append(String.format(
          "\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\""
              + " attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
          startTime, duration, positionY, move.getFromPosition().getY(),
          move.getToPosition().getY()));
    } else if (animation.getAnimationType() == AnimationType.SCALE) {
      Scale scale = (Scale) animation;
      if (scale.getShapeType() == ShapeType.RECTANGLE) {
        animationInfo.append(String.format(
            "\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\""
                + " attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
            startTime, duration, sizeX, scale.getFromSize().getPara1(),
            scale.getToSize().getPara1()));

        animationInfo.append(String.format(
            "\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\""
                + " attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
            startTime, duration, sizeY, scale.getFromSize().getPara2(),
            scale.getToSize().getPara2()));
      } else if (scale.getShapeType() == ShapeType.OVAL) {
        animationInfo.append(String.format(
            "\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\""
                + " attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
            startTime, duration, sizeX, scale.getFromSize().getPara1() / 2,
            scale.getToSize().getPara1() / 2));

        animationInfo.append(String.format(
            "\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\""
                + " attributeName=\"%s\" from=\"%.0f\" to=\"%.0f\" fill=\"freeze\" />\n",
            startTime, duration, sizeY, scale.getFromSize().getPara2() / 2,
            scale.getToSize().getPara2() / 2));
      }
    } else if (animation.getAnimationType() == AnimationType.CHANGECOLOR) {
      ChangeColor changeColor = (ChangeColor) animation;
      animationInfo.append(String.format(
          "\t<animate attributeType=\"xml\" begin=\"%.0fms\" dur=\"%.0fms\""
              + " attributeName=\"fill\" from=\"rgb(%.0f,%.0f,%.0f)\" to=\"rgb(%.0f,%.0f,%.0f)\""
              + " fill=\"freeze\" />\n",
          startTime, duration, changeColor.getFromColor().getRed(),
          changeColor.getFromColor().getGreen(), changeColor.getFromColor().getBlue(),
          changeColor.getToColor().getRed(), changeColor.getToColor().getGreen(),
          changeColor.getToColor().getBlue()));
    }

    return animationInfo.toString();
  }

  
  /**
   * Override the toString method to get the string of svg information, in
   * order to test svg view.
   */
  @Override
  public String toString() {
    return svgOutput();
  }
}
