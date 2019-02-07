package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.knowledgemagnet.detektywslow.MyGame;

/**
 * Created by Igor on 09.01.2019.
 */

public class Letter {


    public static  Label createLetter(char c, MyGame game){
        FreeTypeFontGenerator.setMaxTextureSize(FreeTypeFontGenerator.NO_MAXIMUM);
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        BitmapFont bitmapFont;
        bitmapFont=game.assetManager.get("font1.ttf",BitmapFont.class);
       // bitmapFont=game.assetManager.get("font/balo.fnt", BitmapFont.class);
        Label.LabelStyle labelStyle=new Label.LabelStyle();
        labelStyle.font=bitmapFont;


       // if(c=='Ó'){c='O';}
        String text=c+"";
        Label label=new Label(text,labelStyle);

        BitmapFont cos = game.assetManager.get("font/balo1.fnt", BitmapFont.class);

        Label.LabelStyle labelStyle2=new Label.LabelStyle();
        labelStyle2.font=cos;
        Label label1=new Label(text,labelStyle2) ;
        label1.setDebug(true);

        label.setDebug(true);
        return label1;
    }
}
