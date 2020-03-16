// DotsPanel.java
// by Luc Barenghien
// CSCI 1302
// Project1
// 02/29/2020

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.security.*;

public class DotsPanel extends JPanel 
{
    int speedMultiplier = 1;
    int size[];
    Color colors[];
    AnimationListener am;
    SecureRandom randomNum;
    private ArrayList < Point > pointList;
  private int SIZE = 6;
//-----------------------------------------------------------------
// Constructor: Sets up this panel to listen for mouse events.
//-----------------------------------------------------------------

    public DotsPanel() 
    {
      // New array list to hold new dots as they are placed onto the canvas by the user.
        pointList = new ArrayList < Point > ();
        size = new int[5000];
        colors = new Color[5000];
        randomNum = new SecureRandom();
        DotsMouseMotionListener dml = new DotsMouseMotionListener();
        am = new AnimationListener();
        am.timer.start();
        // Set the backround color to black and set the dimentions
        setBackground(Color.black);
        setPreferredSize(new Dimension(300, 200));
        initializeColors();
        //add the listeners
        addMouseListener(dml);
        addMouseMotionListener(dml);
        addMouseWheelListener(dml);
    }
    
 //   public void paintComponent(Graphics page)
  //  {
 //     super.paintComponent(page);
      //Smooth transition of colors as described on D2L in the line below.
 //     Color color = new Color(i%255, (i*2)%255, (i+128)%255);
  //    current.setColor(color);
//
  //    for (Point spot : pointList)
 //     {
 //       page.fillRect(spot.x-SIZE, spot.y-SIZE, SIZE*2, SIZE*2);
  //    }
 //     height = (int)this.getSize().getHeight();
 //     width = (int) this.getSize().getWidth();
      //System.out.println(this.getSize());
//    }

    
    public void paint(Graphics page)
    {
      // Draws all of the dots stored in the list.
        super.paintComponent(page);
        Color color;
        //Smooth transition of colors removed for this project
        int index = 0;
        for (Point spot: pointList) 
        {
            color = colors[index];
            page.setColor(color);
            page.fillOval(spot.x - size[index],spot.y - size[index],size[index] * 2,size[index] * 2);
            index++;
        }
        color = new Color(255, 255, 255);
        page.setColor(color);
        page.drawString("Count: " + pointList.size(), 5, 15);
        //height = (int)this.getSize().getHeight();
        //width = (int) this.getSize().getWidth();
        //System.out.println(this.getSize());
    }
    public void setSpeedMultiplier(int value) 
    {
        speedMultiplier = value;
    }
    private void initializeColors() 
    {
        for (int i = 0; i < colors.length; i++) 
        {
            int a=randomNum.nextInt(256);
            int b=randomNum.nextInt(256);
            int c=randomNum.nextInt(256);
            int d=randomNum.nextInt(256);
            colors[i] = new Color(a,b,c,d);
        }
    }
    
    private class AnimationListener implements ActionListener 
    {
        int xRandomN[];
        int yRandomN[];
        Random rand;
        private Timer timer = new Timer(50, this);
        public void actionPerformed(ActionEvent event) 
        {
            java.awt.Rectangle window = getBounds();
            for (int i = 0; i < pointList.size(); i++) 
            {
                Point spot = pointList.get(i);
                spot.x += xRandomN[i] * speedMultiplier;
                spot.y += yRandomN[i] * speedMultiplier;
                if (spot.y <= 0) 
                {
                    yRandomN[i] = Math.abs(yRandomN[i]);
                } 
                else if (spot.y >= window.height - (SIZE * 2)) 
                {
                    yRandomN[i] = -Math.abs(yRandomN[i]);
                }
                if (spot.x <= 0) 
                {
                    xRandomN[i] = Math.abs(xRandomN[i]);
                }
                else if (spot.x >= window.width - (SIZE * 2)) 
                {
                    xRandomN[i] = -Math.abs(xRandomN[i]);
                }
            }
            repaint();
        }
        public AnimationListener() 
        {
            rand = new Random();
            xRandomN = new int[5000];
            yRandomN = new int[5000];
            for (int i = 0; i < 5000; i++) 
            {
                xRandomN[i] = rand.nextInt(20) - 10;
                yRandomN[i] = rand.nextInt(20) - 10;
            }
        }
    }
    private void addPoint(Point p) 
    {
        int pos = 0;
        if (pointList.size() < 5000) 
        {
            pointList.add(p);
            pos = pointList.size() - 1;
            size[pos] = SIZE;
            repaint();
        }
    }
    private void groupDots(Point p) 
    {
        for (Point point: pointList) 
        {
            if (Math.abs(point.x - p.x) < 1000 && Math.abs(point.y - p.y) < 1000)
            {
                point.y = p.y;
                point.x = p.x;
            }
        }
        repaint();
    }
  //From trash
//listener for mouse events.
    private class DotsMouseMotionListener implements MouseMotionListener,MouseListener,MouseWheelListener
    {
      // This method below allows the user to draw continuously the left mouse button held down
        public void mouseDragged(MouseEvent event)
        {
            addPoint(event.getPoint());
        }
     //The new implementation of mouse wheel moved event method.
        public void mouseWheelMoved(MouseWheelEvent mwe) 
        {
            int hits = mwe.getWheelRotation();
            if (hits > 0) 
            {
                if (SIZE > 3) 
                {
                    SIZE--;
                }
            }
            else 
            {
                SIZE++;
            }
        }
        //When the mouse is clicked
        public void mouseClicked(MouseEvent me)
        {
          System.out.println("clicked : "+ me.getButton()); 
          Point type = me.getPoint();
            if (me.getButton() == MouseEvent.BUTTON3 || me.getButton() == MouseEvent.BUTTON2) 
            {
              System.out.println("Right clicked");  
              groupDots(type);
            }
        }
        // Below are some silent, unused methods.
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        public void mousePressed(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {}
        public void mouseMoved(MouseEvent event) {}
    }
 //  private class ReboundListener implements ActionListener
//   {
  //    Random r = new Random();
  //    public void actionPerformed(ActionEvent event)
  //    {
        
        //for(Point spot : pointList) {
        //  spot.x += moveX;
        //  .....
        //}
  //      for(i=0;i<pointList.size();i++)
  //      {
  //        if(i % 2 == 0)
   //       {
    //        pointList.get(i).x += moveX.get(i);
    //        pointList.get(i).y += moveY.get(i);
   //       }
   //       else
   //      {
    //        pointList.get(i).x -= moveX.get(i);
    //        pointList.get(i).y += moveY.get(i);
   //       }
          //pointList.get(i).x += moveX;
          //pointList.get(i).y += moveY;
          //System.out.println(this.getSize());
 //         if (pointList.get(i).x <= 0 || pointList.get(i).x >= width - SIZE)
 //           moveX.set(i,moveX.get(i) * - MOVEX);
 //         if (pointList.get(i).y <= 0 || pointList.get(i).y >= height - SIZE)
 //           moveY.set(i, moveY.get(i) * - MOVEY);
  //        repaint();
  //      }
  //    }
 //  }
}