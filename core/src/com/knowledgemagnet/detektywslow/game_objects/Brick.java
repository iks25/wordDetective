package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.knowledgemagnet.detektywslow.MyGame;


/**
 * Created by Igor on 07.01.2019.
 */

public class Brick extends Group{
    char letter;
    int xPosition,yPosition;
    Image image;
    MyGame game;
    private Action action;
    private Texture normal,green,red;
    private Sound touchSound,wrongSound;

    public Brick(char letter,MyGame game) {
        super();
        this.letter = letter;
        this.game=game;
        initTextures(game);

        setSize(image.getWidth(),image.getHeight());
        setDebug(true);
        addActor(image);


        Label c=Letter.createLetter(letter,game);
        addActor(c);
        c.setX(this.getWidth()/2-c.getWidth()/2);
        c.setY(this.getHeight()/2-c.getHeight()/2+3);
        this.setOrigin(getWidth()/2,getHeight()/2);

        touchSound=game.assetManager.get("sound/buttonTouch.mp3",Sound.class);
        //todo badSound

    }

    private void initTextures(MyGame game) {
        normal=game.assetManager.get("brick.png", Texture.class);
        green=game.assetManager.get("brickgreen.png", Texture.class);
        red=game.assetManager.get("brickred.png", Texture.class);
        image=new Image(normal);

        int w=Gdx.graphics.getWidth();
        int h=Gdx.graphics.getHeight();
        int lowestSize;
        if(w>h)
            lowestSize=h;
        else
            lowestSize=w;
        image.setWidth(lowestSize/11);
        image.setHeight(lowestSize/11);
    }

    boolean isSelect=false;
    public void select(){

        if (!isSelect) {
            isSelect=true;
            touchSound.play();
            this.addAction(new SequenceAction(
                    Actions.scaleBy(0.04f, 0.04f, 0.08f),
                    Actions.scaleBy(-0.04f, -0.04f, 0.08f),
                    new Action() {
                        @Override
                        public boolean act(float delta) {
                            image.setDrawable(new SpriteDrawable(new Sprite(green)));
                            return true;
                        }
                    }
            ));
            isSelect=true;
        }

    }
    public void unselect(){
        image.setDrawable(new SpriteDrawable(new Sprite(normal)));
        isSelect=false;
    }
    public void wrongAnwer(){
        image.setDrawable(new SpriteDrawable(new Sprite(red)));
        Action action1= getSequenceShake();
        Action action2= getSequenceAction2();
        this.addAction(new ParallelAction(action1,action2));
        //todo add sound
    }

    private SequenceAction getSequenceShake() {
        return new SequenceAction(
                Actions.rotateBy(15,0.03f),
                Actions.rotateBy(-30,0.06f),
                Actions.rotateBy(30,0.06f),
                Actions.rotateBy(-15,0.03f)
         );
    }

    private SequenceAction getSequenceAction2() {
        return new SequenceAction(
                Actions.scaleBy(0.04f, 0.04f, 0.08f),
                Actions.scaleBy(-0.1f, -0.1f, 0.12f),
                Actions.scaleBy(0.06f, 0.06f, 0.08f),
                Actions.delay(0.1f),
 
                new Action() {
                     @Override
                     public boolean act(float delta) {
                         image.setDrawable(new SpriteDrawable(new Sprite(normal)));
                         return true;
                     }
                 }
         );
    }


    /////////////////geters sette
    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public char getLetter() {
        return letter;
    }
}
