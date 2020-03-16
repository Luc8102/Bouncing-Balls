// SpeedControlPanel.java
// by Luc Barenghien
// CSCI 1302
// Chapter 10 Lab #2
// 02/25/2020

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SpeedControlPanel extends JPanel
{
  private final int WIDTH = 600;
  private final int HEIGHT = 400;
  private final int BALL_SIZE = 50;
  private Circle bouncingBall; // the object that moves
  private Timer timer;
  private int moveX, moveY; // increment to move each time
  private JSlider lucSlider;
  private JLabel lucLabel;
  private JPanel lucPanel;
// --------------------------------------------
// Sets up the panel, including the timer
// for the animation
// --------------------------------------------
    public SpeedControlPanel ()
        {
            timer = new Timer(30, new ReboundListener());
            this.setLayout (new BorderLayout());
            bouncingBall = new Circle(BALL_SIZE);
            moveX = moveY = 5;
            lucSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 30);
            lucSlider.setMajorTickSpacing(40);
            lucSlider.setMinorTickSpacing(10);
            lucSlider.setPaintTicks(true);
            lucSlider.setPaintLabels(true);
            lucSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
            lucSlider.addChangeListener(new SlideListener());
            lucSlider.setPreferredSize(new Dimension(200, 50));
            lucLabel = new JLabel("Timer Delay");
            lucLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            lucPanel = new JPanel();
            lucPanel.add(lucLabel);
            lucPanel.add(lucSlider);
            add(lucPanel,BorderLayout.SOUTH);
            setPreferredSize (new Dimension (WIDTH, HEIGHT));
            setBackground(Color.black);
            timer.start();
        }
// ---------------------
// Draw the ball
// ---------------------
    public void paintComponent (Graphics page)
        {
            super.paintComponent (page);
            bouncingBall.draw(page);
        }
// ***************************************************
// An action listener for the timer
// ***************************************************
public class ReboundListener implements ActionListener
{
// ----------------------------------------------------
// actionPerformed is called by the timer -- it updates
// the position of the bouncing ball
// ----------------------------------------------------
    public void actionPerformed(ActionEvent action)
    {
        bouncingBall.move(moveX, moveY);
        // change direction if ball hits a side
        int slidePanelHt = lucSlider.getSize().height;
        int x = bouncingBall.getX();
        int y = bouncingBall.getY();
        if (x < 0 || x >= WIDTH - BALL_SIZE)
        moveX = moveX * -1;
        if (y < slidePanelHt - BALL_SIZE || y>HEIGHT- slidePanelHt -BALL_SIZE-10)
        moveY = moveY * -1;
 //       if (y <= 0 || y >= slidePanelHt - BALL_SIZE)
 //       moveY = moveY * -1;
        repaint();
    }
}
// ***************************************************
// A change listener for the slider.
// ***************************************************
    private class SlideListener implements ChangeListener
        {
        // ------------------------------------------------
        // Called when the state of the slider has changed;
        // resets the delay on the timer.
        // ------------------------------------------------
            public void stateChanged (ChangeEvent event)
            {
              timer.setDelay(lucSlider.getValue());
            }
            
        }
}