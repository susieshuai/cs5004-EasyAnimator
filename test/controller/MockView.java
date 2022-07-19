package controller;

import cs5004.animator.view.View;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;


/**
 * This mock view is created to test if controller works properly. It implements
 * View Interface. It has the same methods as EditorView class.
 * 
 * @author shishuai
 *
 */
public class MockView implements View {
  protected JButton startButton;
  protected JButton pauseButton;
  protected JButton restartButton;
  protected JButton loopButton;
  protected JButton increaseSpeedButton;
  protected JButton decreaseSpeedButton;

  protected String clickStart;
  protected String clickPause;
  protected String clickRestart;
  protected String clickLoop;
  protected String clickIncreaseSpeed;
  protected String clickDecreaseSpeed;

  protected String displayInfo;

  /**
   * Construct a mock view with no argument.
   */
  public MockView() {
    // create buttons
    this.startButton = new JButton("start");
    this.startButton.setActionCommand("start button");

    this.pauseButton = new JButton("pause/resume");
    this.pauseButton.setActionCommand("pause button");

    this.restartButton = new JButton("restart");
    this.restartButton.setActionCommand("restart button");

    this.loopButton = new JButton("enable/disable loop");
    this.loopButton.setActionCommand("loop button");

    this.increaseSpeedButton = new JButton("increase speed");
    this.increaseSpeedButton.setActionCommand("increase speed button");

    this.decreaseSpeedButton = new JButton("decrease speed");
    this.decreaseSpeedButton.setActionCommand("decrease speed button");
  }

  /**
   * Set listeners to each button. So they can perform actions when specific
   * action happens.
   * 
   * @param clicks the mouse clicks by user
   */
  public void setListeners(ActionListener clicks) {
    this.startButton.addActionListener(clicks);
    this.pauseButton.addActionListener(clicks);
    this.restartButton.addActionListener(clicks);
    this.loopButton.addActionListener(clicks);
    this.increaseSpeedButton.addActionListener(clicks);
    this.decreaseSpeedButton.addActionListener(clicks);
  }

  @Override
  public void display() throws IOException {
    // TODO Auto-generated method stub
    displayInfo = "view invoked";
  }

  /**
   * Start to play the animation.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  public void startPlay() throws IOException {
    this.display();
    clickStart = "start button clicked";
  }

  /**
   * Pause or resume to play animation.
   */
  public void pausePlay() {
    clickPause = "pause button clicked";
  }

  /**
   * Restart to play animation.
   */
  public void restartPlay() {
    clickRestart = "restart button clicked";
  }

  /**
   * Enable or disable loop play.
   */
  public void loopPlay() {
    clickLoop = "loop button clicked";
  }

  /**
   * Increase the playing speed.
   */
  public void increaseSpeed() {
    clickIncreaseSpeed = "increase speed button clicked";
  }

  /**
   * Decrease the playing speed. Each click on corresponding button halves the
   * playing speed.
   */
  public void decreaseSpeed() {
    clickDecreaseSpeed = "decrease speed button clicked";

  }

}
