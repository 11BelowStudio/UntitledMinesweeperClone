package Assignment2;

import java.awt.*;

public class NotAMine extends GameObject {

    private int count;

    public NotAMine(int x, int y, int mineCount, Game game){
        super(x, y, game);
        this.add(text);
        count = mineCount;
        setForeground();
    }

    public void flag(){
        super.flag();
    }

    public void resized(){
        super.resized();
    }

    public void showContents(){
        super.showContents();
        this.setBackground(Color.lightGray.brighter());
        //will set the background colour of this to be slightly lighter
        this.text.setText(String.valueOf(count));
    }


    public void showContentsIngame(){
        showContents();
        game.gamePanel.safeRevealed(posX,posY,count);
    }

    public int getContents(){
        return count;
    }

    public String toString(){
        return String.valueOf(count);
    }

    public NotAMine(NotAMine nm){
        super(nm.posX,nm.posY, nm.game);
        count = nm.getContents();
        setForeground();
    }

    private void setForeground(){
        if (count == 0){
            text.setForeground(background);
        } else if(count< 4){
            text.setForeground(Color.GREEN.darker());
        } else if(count < 7){
            text.setForeground(Color.ORANGE);
        } else{
            text.setForeground(Color.RED);
        }
    }
}