package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.knowledgemagnet.detektywslow.MyGame;

/**
 * Created by Igor on 10.01.2019.
 */

public class BrickInArray extends Brick {

    int xPosition,yPosition;
    private int marginLeft, marginBottom,gap;
    Boolean isFake=false;



    public BrickInArray(char letter, MyGame game, int xPosition, int yPosition) {
        super(letter, game);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        marginLeft=30;
        marginBottom=300;
        gap=5;
        setPositionOnScreen();
    }

    private void setPositionOnScreen() {
        this.setX(marginLeft+xPosition*(this.getWidth()+gap));
        this.setY(marginBottom+yPosition*(this.getWidth()+gap));

    }

    public void drop(){
        //todo drop
        yPosition--;
        addAction(Actions.moveTo(
                marginLeft+xPosition*(this.getWidth()+gap),
                marginBottom+yPosition*(this.getWidth()+gap),
                2f));

    }
    @Override
    public int getxPosition() {
        return xPosition;
    }

    @Override
    public int getyPosition() {
        return yPosition;
    }

    public int getGap() {
        return gap;
    }

    public void setIsFake(boolean isFake) {
        this.isFake = isFake;
    }
    public Boolean isFakeBrick() {
        return isFake;
    }
}
