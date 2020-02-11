package Assignment2;

import javax.swing.*;
import java.awt.*;

public class MineCircle extends JPanel{

    Dimension size;
    int circleSize;
    int theMiddle;

    public MineCircle(Dimension panelSize){
        this.size = panelSize;

        this.setBackground(Color.RED);
        newSize();
    }

    public void newSize(){
        if (size.getWidth() > size.getHeight()){
            circleSize = (int)(size.getHeight()*0.95);
        } else{
            circleSize = (int)(size.getWidth()*0.95);
        }

        theMiddle = (int)((size.getWidth() - circleSize)/2);
    }

    @Override
    public void paintComponent(Graphics g){
        this.draw(g);
        //creates a circle using posX and posY to get the position
        //and bound by a rectangle defined by twice the radius of it
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(theMiddle,0, circleSize,circleSize);
        //creates a circle using posX and posY to get the position
        //and bound by a rectangle defined by twice the radius of it
    }
}
