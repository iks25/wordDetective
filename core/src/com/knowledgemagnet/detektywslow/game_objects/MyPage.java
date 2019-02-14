package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.AppearanceManager;
import com.knowledgemagnet.detektywslow.Board;
import com.knowledgemagnet.detektywslow.MyGame;
import com.knowledgemagnet.detektywslow.PlayScreen;

import java.util.ArrayList;

/**
 * Created by Igor on 11.02.2019.
 */

public class MyPage {
    Group group;
    Image page;
    Image exit;
    Table table;
    String title;
    Label titleLabel;

    MyGame game;
    Stage stage;
    Board board;
    ArrayList<Label> words;
    ArrayList<Image> icons;

    AppearanceManager appearanceManager;

    public MyPage(PlayScreen playScreen, Board board){
        group=new Group();
        group.setWidth(Gdx.graphics.getWidth());
        group.setHeight(Gdx.graphics.getHeight());
        game=playScreen.game;
        stage=playScreen.stage;
        this.board=board;
        createPage();
        addTitle();
        addWords();
        addIcons();


        stage.addActor(group);
        group.setVisible(false);

        exitAddClisckListner();
    }




    private void exitAddClisckListner() {
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();

                super.clicked(event, x, y);
            }
        });
    }

    private void hide() {
        group.setVisible(false);
    }

    private void createPage() {
        page=new Image(game.assetManager.get("page.png", Texture.class));
        exit=new Image(game.assetManager.get("clouse.png", Texture.class));
       appearanceManager=new AppearanceManager();
        appearanceManager.setSizeWidthPercent(page,80);
        appearanceManager.setSizeWidthPercent(exit,9.6f);
        appearanceManager.centerHorizontally(page);
        appearanceManager.centerVertically(page);

        float margin= Gdx.graphics.getWidth()*3/100;
        appearanceManager.setTopInside(page,margin,exit);
        appearanceManager.setRightInside(page,margin,exit);

        group.addActor(page);
        group.addActor(exit);




    }
    private void addTitle() {
        BitmapFont bitmapFont;
        bitmapFont = game.assetManager.get("font/baloblack.fnt", BitmapFont.class);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        title=board.getTitle();
        titleLabel = new Label(title, labelStyle);
        appearanceManager.centerHorizontally(titleLabel);
        appearanceManager.setTopInside(page,Gdx.graphics.getWidth()*10/100,titleLabel);

        group.addActor(titleLabel);

    }
    private void addWords() {

        BitmapFont bitmapFont;
        bitmapFont = game.assetManager.get("font/baloblack.fnt", BitmapFont.class);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        String text1="----------";
        titleLabel = new Label(text1, labelStyle);
        appearanceManager.centerHorizontally(titleLabel);
        appearanceManager.setTopInside(page,Gdx.graphics.getWidth()*10/100,titleLabel);

        group.addActor(titleLabel);

        titleLabel.setX(4);
        words=new ArrayList<>();

        board.getWordsToDiscover();

      //  BitmapFont bitmapFont;
        bitmapFont = game.assetManager.get("font/baloblack.fnt", BitmapFont.class);
       // Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;

        String text="?????????????";
        Label label1 = new Label(text, labelStyle);
        label1.setDebug(true);
        appearanceManager.setUnder(titleLabel,Gdx.graphics.getWidth()/10,label1);
        float x=Gdx.graphics.getWidth()*30/100;

        label1.setX(x);
        group.addActor(label1);
        words.add(label1);

        for(int a=1;a<board.getWordsToDiscover().size();a++){
            Label label = new Label(text, labelStyle);
            appearanceManager.setUnder(words.get(a-1),Gdx.graphics.getWidth()*2/100,label);
            label.setX(x);
            group.addActor(label);
            words.add(label);
        }

    }

    private void addIcons() {
        icons=new ArrayList<>();
        for(Label label:words){
            Image image=new Image(game.assetManager.get("ptaszek.png", Texture.class));
            appearanceManager.setSizeWidthPercent(image,7);
            image.setX(label.getX()-Gdx.graphics.getWidth()*11/100);
            image.setY(label.getY());
            icons.add(image);
            group.addActor(image);
        }
    }



    public void show(){
        group.setVisible(true);
    }

}
