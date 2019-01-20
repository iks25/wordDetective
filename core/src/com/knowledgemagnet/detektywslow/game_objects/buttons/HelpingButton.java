package com.knowledgemagnet.detektywslow.game_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.knowledgemagnet.detektywslow.MyGame;

/**
 * Created by Igor on 18.01.2019.
 */

public abstract class HelpingButton extends Group  {
    Image img;
    Label labelNr;
    int nr;
    protected static int gap= (int) (10*Gdx.graphics.getDensity());
    protected int margin= (int) (4*Gdx.graphics.getDensity());



    public  HelpingButton(int nr,Texture texture, MyGame game) {
        this.img = img;

        img=new Image(texture);
        labelNr=createLabelNr(nr,game);
        int size= Gdx.graphics.getWidth()/6;
        img.setSize(size,size);
        this.setY(10*Gdx.graphics.getDensity());


        labelNr.setY(-10*Gdx.graphics.getDensity());
        this.addActor(img);
        this.addActor(labelNr);
        this.setSize(img.getWidth(),img.getHeight());
        this.setDebug(true);
    }

    public static  Label createLabelNr(int nr, MyGame game){

        BitmapFont bitmapFont;
        bitmapFont=game.assetManager.get("font1.ttf",BitmapFont.class);
        //todo change label style
        Label.LabelStyle labelStyle=new Label.LabelStyle();
        labelStyle.font=bitmapFont;

        String text=nr+"";
        Label label=new Label(text,labelStyle);

        return label;
    }
    public void setNr(int nr) {
        labelNr.setText(nr+"");
        this.nr = nr;

    }

    public int getNr() {
        return nr;
    }



}
