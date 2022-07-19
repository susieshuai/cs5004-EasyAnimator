package cs5004.animator.controller;

import cs5004.animator.model.Model;
import cs5004.animator.view.EditorView;
import cs5004.animator.view.SvgView;
import cs5004.animator.view.TextualView;
import cs5004.animator.view.View;
import cs5004.animator.view.VisualView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class implements Interface Controller. A controller takes input from
 * user. It connects model and view. It takes data from model and tells view to
 * display.
 * 
 * @author shishuai
 *
 */
public class ControllerImpl implements ActionListener, Controller {
  private Model model;
  private View view;
  private String viewType;
  private String outputFileName;
  private String speed;

  /**
   * Construct a controller object.
   * 
   * @param model          the given model
   * @param viewType       the view type of the view
   * @param outputFileName the name of the output file that the data will be
   *                       written into
   * @param speed          the speed of animation
   */
  public ControllerImpl(Model model, String viewType, String outputFileName, String speed) {
    this.model = model;
    this.viewType = viewType;
    this.outputFileName = outputFileName;
    this.speed = speed;
  }

  @Override
  public void run() throws IOException {
    switch (this.viewType) {
    
      case "text":
        this.view = new TextualView(this.model.getAllShapes(), this.model.getSortedAnimations(),
          this.outputFileName, this.speed);
        this.view.display();
        break;
        
      case "visual":
        this.view = new VisualView(this.model.getFrameShapes(), this.model.getCanvas(),
          Integer.parseInt(this.speed));
        this.view.display();
        break;
        
      case "playback":
        this.view = new EditorView(this.model.getFrameShapes(), this.model.getCanvas(),
          Integer.parseInt(this.speed));
        if (this.view instanceof EditorView) {
          ((EditorView) this.view).setListeners(this);
        }
        break;
        
      case "svg":
        this.view = new SvgView(this.model.getAllShapes(), this.model.getAllAnimations(),
          this.outputFileName, this.speed);
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
        ((EditorView) this.view).startPlay();
        break;

      // pause/resume
      case "pause button":
        ((EditorView) this.view).pausePlay();
        break;

      // restart
      case "restart button":
        ((EditorView) this.view).restartPlay();
        break;
        
      // enable/disable Looping
      case "loop button":
        ((EditorView) this.view).loopPlay();
        break;

      // increase speed
      case "increase speed button":
        ((EditorView) this.view).increaseSpeed();
        break;

      // decrease speed
      case "decrease speed button":
        ((EditorView) this.view).decreaseSpeed();
        break;
        
      default:
        //no action is intended 
    }
  }

}
