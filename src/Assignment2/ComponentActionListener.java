package Assignment2;


import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ComponentActionListener implements ComponentListener {

    private Game game;

    public ComponentActionListener(Game game){
        this.game = game;
    }

    @Override
    public void componentResized(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
