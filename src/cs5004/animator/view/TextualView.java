package cs5004.animator.view;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This class represents textual view. It implements View Interface.
 * 
 * @author shishuai
 *
 */
public class TextualView implements View {
  private Map<String, Shape> allShapes;
  private List<Animation> sortedAnimations;
  private String outputFileName;
  private String speed;

  /**
   * Construct a textual view object.
   * 
   * @param allShapes        the map of all the shapes
   * @param sortedAnimations the list of all sorted animations
   * @param outputFileName   the name of the output file that the data will be
   *                         written into
   * @param speed            the speed of animation
   */
  public TextualView(Map<String, Shape> allShapes, List<Animation> sortedAnimations,
      String outputFileName, String speed) {
    this.allShapes = allShapes;
    this.sortedAnimations = sortedAnimations;
    this.outputFileName = outputFileName;
    this.speed = speed;
  }

  @Override
  public void display() throws IOException {

    // if output file name is undefined, print out in the console
    // otherwise write data in the file
    String description = textOutput();
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

  /**
   * Get the text output that follows the required format.
   * 
   * @return the require format text output
   */
  private String textOutput() {
    StringBuilder output = new StringBuilder();

    // shapes
    Collection<Shape> allShapeCollection = allShapes.values();
    for (Shape shape : allShapeCollection) {
      output.append("Create ").append(shape.getType().toSting()).append(" ").append(shape.getName())
          .append(" of color ").append(shape.getColor().toString()).append(" ");

      if (shape.getType() == ShapeType.RECTANGLE) {
        output.append("with corner at ").append(shape.getPosition().toString())
            .append(String.format(", width %.1f and height %.1f\n", shape.getSize().getPara1(),
                shape.getSize().getPara2()));
      } else {
        output.append("with center at ").append(shape.getPosition().toString())
            .append(String.format(", radius %.1f and %.1f\n", shape.getSize().getPara1(),
                shape.getSize().getPara2()));
      }
    }
    output.append("\n");

    // print a note to clarify the speed of the animation
    int tempo = Integer.parseInt(this.speed);
    output.append(String.format("NOTE: Animation speed is %d tick(s) per second.\n\n", tempo));

    // appear and disappear times
    for (Shape shape : allShapeCollection) {
      output.append(shape.getName()).append(" appears at time t=").append(shape.getAppearTime())
          .append(" and disappears at time t=").append(shape.getDisappearTime()).append("\n");
    }
    output.append("\n");

    // motions
    for (Animation animation : sortedAnimations) {
      output.append(animation.toString());
    }

    return output.toString();
  }
  
  /**
   * Override the toString method to get the string of textual information, in
   * order to test textual view.
   */
  @Override
  public String toString() {
    return textOutput();
  }

}
