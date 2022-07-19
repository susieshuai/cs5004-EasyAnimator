package controller;

import cs5004.animator.controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;

/**
 * This mock controller is created to test if controller works properly. It
 * implements Controller and ActionListener Interface. It has the same methods as ControllerImpl
 * class.
 * 
 * @author shishuai
 *
 */
public class MockController implements ActionListener, Controller {
  protected MockView view;
  protected String viewType;
  protected JButton buttonClicked;

  /**
   * Construct a mock controller with the given view type and click command.
   * 
   * @param viewType     the view type as a string
   * @param clickCommand the click command as a string
   */
  public MockController(String viewType, String clickCommand) {
    this.view = new MockView();
    this.viewType = viewType;

    switch (clickCommand) {
      case "start button":
        this.buttonClicked = this.view.startButton;
        break;
      case "pause button":
        this.buttonClicked = this.view.pauseButton;
        break;
      case "restart button":
        this.buttonClicked = this.view.restartButton;
        break;
      case "loop button":
        this.buttonClicked = this.view.loopButton;
        break;
      case "increase speed button":
        this.buttonClicked = this.view.increaseSpeedButton;
        break;
      case "decrease speed button":
        this.buttonClicked = this.view.decreaseSpeedButton;
        break;
      default:
        //no action is intended
    }
  }

  @Override
  public void run() throws IOException {
    switch (this.viewType) {

      case "text":
        this.view.display();
        break;

      case "visual":
        this.view.display();
        break;

      case "playback":
        this.view.setListeners(this);  
        this.buttonClicked.doClick();
        break;

      case "svg":
        this.view.display();
        break;

      default:
        throw new IOException("Display error.");
    }
  }

  @Override
  public void actionPerformed(ActionEvent clicks) {

    switch (clicks.getActionCommand()) {

      // start play
      case "start button":
        try {
          this.view.startPlay();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        break;

      // pause/resume
      case "pause button":
        this.view.pausePlay();
        break;

      // restart
      case "restart button":
        this.view.restartPlay();
        break;

      // enable/disable Looping
      case "loop button":
        this.view.loopPlay();
        break;

      // increase speed
      case "increase speed button":
        this.view.increaseSpeed();
        break;

      // decrease speed
      case "decrease speed button":
        this.view.decreaseSpeed();
        break;
        
      default:
        //no action is intended 
    }
  }

}
