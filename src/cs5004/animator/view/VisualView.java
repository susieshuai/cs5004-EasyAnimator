package cs5004.animator.view;

import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * This class represents a visual view. It implements the View Interface.
 * 
 * @author shishuai
 *
 */
public class VisualView implements View {
  private JFrame frame;
  private VisualPanel<Shape> myPanel;
  private Timer timer;

  /**
   * Construct a visual view.
   * 
   * @param frameShapes the list of frames that each frame contains shapes
   * @param canvas      the canvas of the view
   * @param tempo       the speed of animation
   */
  public VisualView(List<List<Shape>> frameShapes, Canvas canvas, int tempo) {

    // create frame
    this.frame = new JFrame("Easy Animator Visual View");
    this.frame.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getWidth()));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create panel
    this.myPanel = new VisualPanel<>(frameShapes);
    this.myPanel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getWidth()));

    // refresh the window automatically
    // mechanism: paint the shapes in one frame -> refresh the window -> paint next
    // frame of shapes
    int delay = 1000 / tempo;
    this.timer = new Timer(delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.repaint();
        if (myPanel.count == frameShapes.size()) {
          timer.stop();
        }
      }
    });
    
    // create scroll pane
    JScrollPane scrollPane = new JScrollPane(this.myPanel);
    this.frame.add(scrollPane);

    
    this.frame.pack();
    this.frame.setVisible(true);
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
