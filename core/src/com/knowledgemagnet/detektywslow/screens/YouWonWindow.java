package com.knowledgemagnet.detektywslow.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.MyGame;
import com.knowledgemagnet.detektywslow.PlayScreen;

/**
 * Created by Igor on 28.01.2019.
 */

public class YouWonWindow extends Group {

    MyGame myGame;
    Image nextLevelButton,menuButton,windowImage;
    int lvlNumber;
    public YouWonWindow(PlayScreen playScreen) {

        myGame=playScreen.game;
        InitImages(playScreen);
        setView();
        setClickReaction();
        this.setVisible(false);
    }

    private void setClickReaction() {
        //todo menu button;
        nextLevelButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lvlNumber++;
                myGame.setScreen(new PlayScreen(myGame,lvlNumber));
                super.clicked(event, x, y);
            }
        });
    }

    private void InitImages(PlayScreen playScreen) {
        Texture window=myGame.assetManager.get("youWin.png",Texture.class);
        Texture nextLevel=myGame.assetManager.get("nextlevel.png",Texture.class);
        Texture menu=myGame.assetManager.get("menu.png",Texture.class);

        windowImage=new Image(window);
        nextLevelButton=new Image(nextLevel);
        menuButton=new Image(menu);

        lvlNumber=playScreen.getLvlNumber();
        this.addActor(windowImage);
        this.addActor(nextLevelButton);
        this.addActor(menuButton);
    }

    private void setView() {
        //windowImage.setScale(1.8f);
        float scale= (float) Gdx.graphics.getWidth()/(windowImage.getWidth()+2*50);
        windowImage.setScale(scale);
        this.setWidth(windowImage.getWidth()*windowImage.getScaleX());
        this.setHeight(windowImage.getHeight()*windowImage.getScaleY());

        float aditonalLack=20*Gdx.graphics.getDensity();
        nextLevelButton.setScale(scale*0.7f);
        nextLevelButton.setPosition(
                this.getWidth()/2-nextLevelButton.getWidth()/2*nextLevelButton.getScaleX(),
                75*Gdx.graphics.getDensity()+aditonalLack);

        menuButton.setScale(scale*0.6f);
        menuButton.setPosition(
                this.getWidth()/2-menuButton.getWidth()/2*menuButton.getScaleX(),
                -menuButton.getHeight()+aditonalLack
        );
    }
}
