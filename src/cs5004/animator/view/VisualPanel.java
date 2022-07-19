package cs5004.animator.view;

import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * This class represents a visual panel. In this panel, shapes are paint frame
 * by frame.
 * 
 * @author shishuai
 *
 * @param <Shape> the shapes list
 */
@SuppressWarnings("hiding")
public class VisualPanel<Shape> extends JPanel {

  private static final long serialVersionUID = 1L;
  protected List<List<Shape>> frameShapes;
  protected int count = 0;

  /**
   * Construct a visual panel with given frame shapes list.
   * 
   * @param frameShapes the frame list that contains shapes at each frame
   */
  public VisualPanel(List<List<Shape>> frameShapes) {
    this.frameShapes = frameShapes;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.white);

    if (this.count == this.frameShapes.size()) {
      return;
    }

    List<Shape> frame = this.frameShapes.get(count++);
    for (Shape shape : frame) {
      Graphics2D graphics2d = (Graphics2D) g;
      if (shape instanceof Rectangle) {
        // paint a rectangle
        Rectangle rectangle = (Rectangle) shape;
        graphics2d.setColor(new Color((int) rectangle.getColor().getRed(),
            (int) rectangle.getColor().getGreen(), (int) rectangle.getColor().getBlue()));
        graphics2d.fillRect((int) rectangle.getPosition().getX(),
            (int) rectangle.getPosition().getY(), (int) rectangle.getSize().getPara1(),
            (int) rectangle.getSize().getPara2());
      } else if (shape instanceof Oval) {
        // paint an oval
        Oval oval = (Oval) shape;
        graphics2d.setColor(new Color((int) oval.getColor().getRed(),
            (int) oval.getColor().getGreen(), (int) oval.getColor().getBlue()));
        graphics2d.fillOval((int) oval.getPosition().getX(), (int) oval.getPosition().getY(),
            (int) oval.getSize().getPara1(), (int) oval.getSize().getPara2());
      }
    }

  }

}
