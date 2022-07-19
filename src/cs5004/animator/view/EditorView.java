package cs5004.animator.view;

import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


/**
 * This class represents editor view. It extends JPanel class and implements
 * View interface.
 * 
 * @author shishuai
 *
 */
public class EditorView extends JPanel implements View {

  private static final long serialVersionUID = 1L;
  private JFrame frame;
  private VisualPanel<Shape> myPanel;
  private Timer timer;
  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private Boolean isPlaying;
  private Boolean isLooping;
  private int tempo;
  private int delay;
  private JLabel loopMessage;
  private JLabel speedMessage;

  /**
   * Construct an editor view.
   * 
   * @param frameShapes the list of frames that each frame contains shapes
   * @param canvas      the canvas of the view
   * @param tempo       the speed of animation
   */
  public EditorView(List<List<Shape>> frameShapes, Canvas canvas, int tempo) {
    this.tempo = tempo;
    this.delay = 1000 / this.tempo;
    this.isPlaying = true;
    this.isLooping = true;

    // create frame
    this.frame = new JFrame("Easy Animator Editor View");
    this.frame.setPreferredSize(new Dimension(800, 800));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setLayout(new FlowLayout());

    // create panel
    this.myPanel = new VisualPanel<>(frameShapes);
    this.myPanel.setPreferredSize(new Dimension(800, 700));
    this.frame.add(this.myPanel);
    
    this.timer = new Timer(this.delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.repaint();
        if (myPanel.count == frameShapes.size()) {
          timer.stop();
          if (isLooping) { // looping automatically
            myPanel.count = 0;
            timer.restart();
          } else { // stop, reset buttons
            isPlaying = false;
            pauseButton.setEnabled(false);
            loopButton.setEnabled(false);
            increaseSpeedButton.setEnabled(false);
            decreaseSpeedButton.setEnabled(false);
          }
        }
      }
    });
    
    // create scroll pane
    //JScrollPane scrollPane = new JScrollPane(myPanel);
    //this.frame.add(scrollPane);

    // set label
    this.loopMessage = new JLabel("Loop playback: Enabled");
    this.loopMessage.setPreferredSize(new Dimension(380, 20));
    this.frame.add(loopMessage);
    
    this.speedMessage = new JLabel("Speed: " + this.tempo + " tick(s) per second");
    this.speedMessage.setPreferredSize(new Dimension(380, 20));
    this.frame.add(speedMessage);
    
    // create buttons
    this.startButton = new JButton("start");
    this.startButton.setActionCommand("start button");
    this.frame.add(this.startButton);

    this.pauseButton = new JButton("pause/resume");
    this.pauseButton.setActionCommand("pause button");
    this.frame.add(this.pauseButton);

    this.restartButton = new JButton("restart");
    this.restartButton.setActionCommand("restart button");
    this.frame.add(this.restartButton);

    this.loopButton = new JButton("enable/disable loop");
    this.loopButton.setActionCommand("loop button");
    this.frame.add(this.loopButton);

    this.increaseSpeedButton = new JButton("increase speed");
    this.increaseSpeedButton.setActionCommand("increase speed button");
    this.frame.add(this.increaseSpeedButton);

    this.decreaseSpeedButton = new JButton("decrease speed");
    this.decreaseSpeedButton.setActionCommand("decrease speed button");
    this.frame.add(this.decreaseSpeedButton);

    this.frame.pack();
    this.frame.setVisible(true);
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

  /**
   * Start to play the animation. User must click the start button to start
   * playing the animation. This action is the entrance of playing. So display()
   * method is called here.
   */
  public void startPlay() {
    try {
      this.display();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.startButton.setEnabled(false);
  }

  /**
   * Pause or resume to play animation.
   */
  public void pausePlay() {
    if (this.isPlaying) {
      this.timer.stop();
      this.isPlaying = false;
    } else {
      this.timer.start();
      this.isPlaying = true;
    }
  }

  /**
   * Restart to play animation.
   */
  public void restartPlay() {
    this.myPanel.count = 0;
    this.timer.restart();
    this.pauseButton.setEnabled(true);
    loopButton.setEnabled(true);
    increaseSpeedButton.setEnabled(true);
    decreaseSpeedButton.setEnabled(true);
  }

  /**
   * Enable or disable loop play.
   */
  public void loopPlay() {
    if (this.isLooping) {
      this.isLooping = false;
      this.loopMessage.setText("Loop playback: Disabled");
    } else {
      this.isLooping = true;
      this.loopMessage.setText("Loop playback: Enabled");
    }
  }

  /**
   * Increase the playing speed. Each click on corresponding button doubles the
   * playing speed.
   */
  public void increaseSpeed() {
    this.tempo = this.tempo * 2;
    this.delay = 1000 / this.tempo;
    this.timer.setDelay(this.delay);
    this.speedMessage.setText("Speed: " + this.tempo + " tick(s) per second");
  }

  /**
   * Decrease the playing speed. Each click on corresponding button halves the
   * playing speed.
   */
  public void decreaseSpeed() {
    this.tempo = this.tempo / 2;
    this.delay = 1000 / this.tempo;
    this.timer.setDelay(this.delay);
    this.speedMessage.setText("Speed: " + this.tempo + " tick(s) per second");
  }

  @Override
  public void display() throws IOException {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        timer.start();
      }
    });
  }

}
