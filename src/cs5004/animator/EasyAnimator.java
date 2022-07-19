package cs5004.animator;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ModelImpl.Builder;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is created to run this Animation Application.
 * 
 * @author shishuai
 *
 */
public final class EasyAnimator {

  /**
   * This main() method will be the entry point for this program. User will need
   * to create an Application run configuration in Eclipse/IntelliJ that chooses
   * cs5004.animator.EasyAnimator as its main class. In this run configuration,
   * user can also specify command-line arguments.
   * 
   * <p>The command-line arguments will be of the form: 
   * -in "name-of-animation-file" (mandatory) 
   * -view "type-of-view" (mandatory) 
   * -out "where-output-show-go" (optional, default: system.out.print) 
   * -speed "integer-ticks-per-second" (optional, default: 1)
   * 
   * @param args the given command-line arguments
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  public static void main(String[] args) throws IOException {
    if (!validArgs(args)) {
      JOptionPane.showMessageDialog(new JFrame(), "Invalid command-line arguments.", "ERROR",
          JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    String inputFileName = "";
    String outputFileName = "";
    String viewType = "";
    String speed = "1";

    // extract the information from given arguments
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-in")) {
        inputFileName = args[i + 1];
      } else if (args[i].equalsIgnoreCase("-view")) {
        viewType = args[i + 1];
      } else if (args[i].equalsIgnoreCase("-out")) {
        outputFileName = args[i + 1];
      } else if (args[i].equalsIgnoreCase("-speed")) {
        speed = args[i + 1];
      }
    }

    // model set up
    AnimationBuilder<Model> builder = new Builder();
    Model model = new ModelImpl();
    try {
      // files/ is the path to get the file
      InputStream inputStream = new FileInputStream("files/" + inputFileName);
      //InputStream inputStream = new FileInputStream(inputFileName);
      model = AnimationReader.parseFile(new InputStreamReader(inputStream), builder);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "Fail to read the file.", "ERROR",
          JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    // controller set up
    Controller controller = new ControllerImpl(model, viewType, outputFileName, speed);

    // run program
    controller.run();
  }

  /**
   * This method is to check the arguments are valid or not.
   * 
   * @param args the given arguments
   * @return true if the arguments are valid, otherwise return false
   */
  private static Boolean validArgs(String[] args) {
    Boolean inArgs = false;
    Boolean viewArgs = false;
    if (args.length == 4 || args.length == 6 || args.length == 8) {
      for (int i = 0; i < args.length; i = i + 2) {

        if (args[i].equalsIgnoreCase("-in")) {
          inArgs = true;
          if (!args[i + 1].contains("txt")) {
            return false;
          }
        } else if (args[i].equalsIgnoreCase("-view")) {
          viewArgs = true;
          if (!args[i + 1].contains("text") && !args[i + 1].contains("visual")
              && !args[i + 1].contains("svg") && !args[i + 1].contains("playback")) {
            return false;
          }
        } else if (args[i].equalsIgnoreCase("-out")) {
          if (!args[i + 1].contains("svg") && !args[i + 1].contains("txt")) {
            return false;
          }
        } else if (args[i].equalsIgnoreCase("-speed")) {
          String speed = args[i + 1];
          if (Integer.parseInt(speed) == 0) {
            return false;
          } else if (!speed.matches("[0-9]+")) {
            return false;
          }
        }
      }
    }
    return inArgs && viewArgs;
  }

}
