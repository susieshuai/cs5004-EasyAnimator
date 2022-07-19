package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * This interface represents the controller. It contains all the operations that
 * a controller should support. The purpose of a controller is to mediate the
 * interactions between the view and the model
 * 
 * @author shishuai
 *
 */
public interface Controller {
  /**
   * Run the program. It will invoke different views (textual view, visual view,
   * SVG view, or editor view) according to the arguments given by the user.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  void run() throws IOException;

  /**
   * This method is for editor view. Perform different action according to the
   * given action event made by user. In this program, it is the click on buttons.
   * 
   * @param e the action event made by the user
   */
  void actionPerformed(ActionEvent e);
}
