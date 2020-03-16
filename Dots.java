// Dots.java
// by Luc Barenghien
// CSCI 1302
// Project1
// 02/29/2020

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;

public class Dots
{
  //main
  public static void main(String[] args)
  {
    JSlider slider;
    //Add slider as instructed in D2L
    slider=new JSlider(1,10,1);
    //^ above, add the maximum and minimum values as specified in the instruction in D2L.
    slider.setMajorTickSpacing(1);
    slider.setPaintTicks(true);
    DotsPanel dotsPanel=new DotsPanel();
    JFrame frame = new JFrame("Dots");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(dotsPanel,BorderLayout.CENTER);
    frame.getContentPane().add(slider,BorderLayout.PAGE_END);

    frame.pack();
    frame.setVisible(true);
    slider.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent event)
      {
         int value=slider.getValue();
         dotsPanel.setSpeedMultiplier(value);
      }
    });
  }
}