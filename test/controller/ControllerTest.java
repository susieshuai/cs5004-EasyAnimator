package controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.IOException;

/**
 * This class tests for controller. In order to test wiring relationship of
 * controller and view, mock view class and mock controller class are created to
 * complete the test.
 * 
 * @author shishuai
 *
 */
public class ControllerTest {

  /**
   * Test display method to see if the controller can successfully invoke the
   * view.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  @Test
  public void testDisplay() throws IOException {
    MockController c1 = new MockController("text", "");
    c1.run();
    assertEquals("view invoked", c1.view.displayInfo);

    MockController c2 = new MockController("svg", "");
    c2.run();
    assertEquals("view invoked", c2.view.displayInfo);

    MockController c3 = new MockController("visual", "");
    c3.run();
    assertEquals("view invoked", c3.view.displayInfo);
  }

  /**
   * Test click start button works properly.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  @Test
  public void testStartButton() throws IOException {
    MockController c = new MockController("playback", "start button");
    c.run();
    assertEquals("view invoked", c.view.displayInfo);
    assertEquals("start button clicked", c.view.clickStart);
  }

  /**
   * Test click pause button works properly.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  @Test
  public void testPauseButton() throws IOException {
    MockController c = new MockController("playback", "pause button");
    c.run();
    assertEquals("pause button clicked", c.view.clickPause);
  }

  /**
   * Test click restart button works properly.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  @Test
  public void testRestartButton() throws IOException {
    MockController c = new MockController("playback", "restart button");
    c.run();
    assertEquals("restart button clicked", c.view.clickRestart);
  }

  /**
   * Test click loop button works properly.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  @Test
  public void testLoopButton() throws IOException {
    MockController c = new MockController("playback", "loop button");
    c.run();
    assertEquals("loop button clicked", c.view.clickLoop);
  }

  /**
   * Test click increase speed button works properly.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  @Test
  public void testIncreaseSpeedButton() throws IOException {
    MockController c = new MockController("playback", "increase speed button");
    c.run();
    assertEquals("increase speed button clicked", c.view.clickIncreaseSpeed);
  }

  /**
   * Test click decrease speed button works properly.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  @Test
  public void testDecreaseSpeedButton() throws IOException {
    MockController c = new MockController("playback", "decrease speed button");
    c.run();
    assertEquals("decrease speed button clicked", c.view.clickDecreaseSpeed);
  }

}
