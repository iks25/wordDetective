package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.knowledgemagnet.detektywslow.MyGame;


/**
 * Created by Igor on 07.01.2019.
 */

public class Brick extends Group implements LetterContainer{
    Character letter;
    int xPosition,yPosition;
    Image image;
    MyGame game;
    private Action action;
    private Texture normal,green,red;
    private Sound touchSound;
    private BreakBrickAnimation animationBreak;
    private Label letterLabel;
    public boolean clicable=true;

    public Brick(char letter,MyGame game) {
        super();
        this.letter = letter;
        this.game=game;
        initTextures(game);

        setSize(image.getWidth(),image.getHeight());
        addActor(image);
        addActor(animationBreak);



        letterLabel=Letter.createLetter(letter,game);
        addActor(letterLabel);
        letterLabel.setX(this.getWidth()/2-letterLabel.getWidth()/2);
        letterLabel.setY(this.getHeight()/2-letterLabel.getHeight()/2+3);
        this.setOrigin(getWidth()/2,getHeight()/2);
        touchSound=game.assetManager.get("sound/buttonTouch.mp3",Sound.class);
    }

    private void initTextures(MyGame game) {
        normal=game.assetManager.get("brick.png", Texture.class);
        green=game.assetManager.get("brickgreen.png", Texture.class);
        red=game.assetManager.get("brickred.png", Texture.class);
        image=new Image(normal);

        animationBreak=BreakBrickAnimation.createAnimation(game.assetManager);
        int w=Gdx.graphics.getWidth();
        int h=Gdx.graphics.getHeight();
        int lowestSize;
        if(w>h)
            lowestSize=h;
        else
            lowestSize=w;

        image.setWidth(lowestSize/9);
        image.setHeight(lowestSize/9);
        animationBreak.setWidth(lowestSize/9);
        animationBreak.setHeight(lowestSize/9);
        

    }

    boolean isSelect=false;
    public boolean select(){

        if (!isSelect) {
            isSelect=true;
            touchSound.play();
            image.setDrawable(new SpriteDrawable(new Sprite(green)));
            this.addAction(new SequenceAction(
                    Actions.scaleBy(0.04f, 0.04f, 0.08f),
                    Actions.scaleBy(-0.04f, -0.04f, 0.08f)
            ));
            return true;
        }
        else
            return false;

    }
    public void unselect(){
        image.setDrawable(new SpriteDrawable(new Sprite(normal)));
        this.addAction(Actions.sequence(
                Actions.scaleBy(-0.05f, -0.05f,0.03f),
                Actions.scaleBy(0.05f,0.05f,.03f),
                new Action() {
                    @Override
                    public boolean act(float delta) {
                        image.setDrawable(new SpriteDrawable(new Sprite(normal)));
                        isSelect=false;
                        return true;
                    }
                }
        ));



    }
    public void wrongAnwer(){
        image.setDrawable(new SpriteDrawable(new Sprite(red)));
        Action action1= getSequenceShake();
        Action action2= getSequenceAction2();
        this.addAction(new ParallelAction(action1,action2));
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
                         isSelect=false;
                         return true;
                     }
                 }
         );
    }

    public void breakBrickAnimation(){

        image.setVisible(false);
        letterLabel.setVisible(false);
        animationBreak.play();

    }


    /////////////////geters sette
    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    @Override
    public Character getLetter() {
        return letter;
    }

    public void goodAnswerAnimation() {
        clicable=false;
        image.setDrawable(new SpriteDrawable(new Sprite(normal)));
        Action animationGood= new SequenceAction(
                Actions.scaleBy(0.07f, 0.07f, 0.08f),
                Actions.moveBy(0, 10f, 03f),
                Actions.delay(0.1f),

                new Action() {
                    @Override
                    public boolean act(float delta) {
                        invisible();
                        return false;
                    }
                }
        );
    }

    private void invisible() {
        this.setVisible(false);
    }

}
