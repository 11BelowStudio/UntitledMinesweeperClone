package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;
//these are used to tell the difference between left click and right click events

public class MouseClickListener implements MouseListener {

    private Game game; // game passed through to allow for game manipulation
    //private GameObject object;

    public MouseClickListener(Game game/*, GameObject ob*/) {
        this.game = game;
        /*
        this.object = ob;
        System.out.println(object);
        */
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(e.getComponent());
        if (isLeftMouseButton(e)){
            if (e.getSource() instanceof GameObject){
                if (!((GameObject) e.getSource()).getRevealed()){
                    ((GameObject) e.getSource()).showContentsIngame();
                }
            }
        } else if (isRightMouseButton(e)){
            if (e.getSource() instanceof GameObject){
                if (!((GameObject) e.getSource()).getRevealed()){
                    ((GameObject) e.getSource()).flag();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
