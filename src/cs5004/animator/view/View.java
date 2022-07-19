package cs5004.animator.view;

import java.io.IOException;

/**
 * This is the interface of the view. It contains one method, display user the
 * interface. In this program, there are four kinds of views: 
 * 1) Textual View: display the animation information in the console or save in
 * a .txt file
 * 2) Visual View: display the animation directly
 * 3) Editor View: display the animation, as well as provide features like start,
 * pause, restart, loop, increase and decrease playing speed
 * 4) SVG View:  display the animation information in the console or save in
 * a .svg file
 * 
 * @author shishuai
 *
 */
public interface View {

  /**
   * Display the user interface.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  void display() throws IOException;
}
