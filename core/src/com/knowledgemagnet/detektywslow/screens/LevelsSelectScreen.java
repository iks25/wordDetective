package com.knowledgemagnet.detektywslow.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.AbstractScreen;
import com.knowledgemagnet.detektywslow.AppearanceManager;
import com.knowledgemagnet.detektywslow.MyGame;
import com.knowledgemagnet.detektywslow.dataBase.SharePreferencesHelper;
import com.knowledgemagnet.detektywslow.game_objects.buttons.Direction;

/**
 * Created by Igor on 07.02.2019.
 */

public class LevelsSelectScreen extends AbstractScreen {

    Image board, bow;
    Image levelsButtons[];
    Button buttonlevel[];
    AppearanceManager viewManager;
    int nrlevelGroup=0;
    public LevelsSelectScreen(MyGame game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        board=new Image(game.assetManager.get("board.png", Texture.class));
        stage.addActor(board);

        bow=new Image(game.assetManager.get("wybierzlevel.png", Texture.class));
        stage.addActor(bow);

         viewManager=new AppearanceManager();
        viewManager.setSizeWidthPercent(board,105);
        viewManager.setSizeWidthPercent(bow,90);
        viewManager.centerHorizontally(board);
        viewManager.centerVertically(board);
        viewManager.centerHorizontally(bow);
        viewManager.setAbove(board,-bow.getHeight(),bow);

        addButtonsLevels();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    private void addButtonsLevels() {
        levelsButtons=new Image[10];
        boolean isNumberNeeded;
        for(int i=0;i<5;i++){

            Texture texture;
            if(isLeveUnlock(i+nrlevelGroup)){
                texture=game.assetManager.get("buttonLevel.png");
                isNumberNeeded=true;
            //todo add numbers
            }
            else{
                isNumberNeeded=false;
                texture=game.assetManager.get("lock.png");
            }

            levelsButtons[i]=new Image(texture);
            int size=15;
            viewManager.setSizeWidthPercent(levelsButtons[i],size);
            viewManager.setAbove(board,-Gdx.graphics.getWidth()/3-20*Gdx.graphics.getDensity(),levelsButtons[i]);

            float margin=Gdx.graphics.getWidth()/10;
            float gap=Gdx.graphics.getWidth()-(margin+margin+levelsButtons[i].getWidth()*5);
            gap=gap/4;
            levelsButtons[i].setX(margin+gap*i+levelsButtons[i].getWidth()*i);

            stage.addActor(levelsButtons[i]);

            if(isNumberNeeded){
            addNumber(i+nrlevelGroup,levelsButtons[i]);
            addClicableNewLevel(i+nrlevelGroup,levelsButtons[i]);
            }


        }

        for(int i=0;i<5;i++){
            Texture texture;
            if(isLeveUnlock(i+nrlevelGroup+5)){
                texture=game.assetManager.get("buttonLevel.png");
                isNumberNeeded=true;
                //todo add numbers
            }
            else{
                isNumberNeeded=false;
                texture=game.assetManager.get("lock.png");
            }


            levelsButtons[i+5]=new Image(texture);

            int size=15;
            viewManager.setSizeWidthPercent(levelsButtons[i+5],size);
            float margin=Gdx.graphics.getWidth()/10;
            float gap=Gdx.graphics.getWidth()-(margin+margin+levelsButtons[i].getWidth()*5);
            gap=gap/4;
            viewManager.setUnder(levelsButtons[i],gap,levelsButtons[i+5]);


            levelsButtons[i+5].setX(levelsButtons[i].getX());
            stage.addActor(levelsButtons[i+5]);

            if(isNumberNeeded)
                addNumber(i+5+nrlevelGroup,levelsButtons[i+5]);
        }

    }
    Button button1;
    private void addClicableNewLevel(int i,Image image) {

        Button button=new Button(new Button.ButtonStyle());
        button.setWidth(image.getWidth());
        button.setHeight(image.getHeight());
        button.setX(image.getX());
        button.setY(image.getY());
        button.setDebug(true);
        stage.addActor(button);

        int nr=i+1;
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("test1","test1"+nr);
                //todo level choose
                super.clicked(event, x, y);
            }
        });

    }

    private void addNumber(int i,Actor viewPosition) {
        BitmapFont //bitmapFont = game.assetManager.get("font/balo1.fnt", BitmapFont.class);
        bitmapFont=game.assetManager.get("font2.ttf",BitmapFont.class);

        Label.LabelStyle labelStyle=new Label.LabelStyle();
        labelStyle.font=bitmapFont;
        int nr=i+1;
        Label label=new Label(""+nr,labelStyle);
        stage.addActor(label);
        viewManager.centerVertically(label,viewPosition);
        viewManager.centerHorizontally(label,viewPosition);
    }

    private boolean isLeveUnlock(int i) {
        SharePreferencesHelper  settings=new SharePreferencesHelper();
        if(i+1>settings.getUnlockLvlNr())
            return false;
        else
            return true;

        //todo level unclock
    }
}
