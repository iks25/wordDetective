package com.knowledgemagnet.detektywslow.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.knowledgemagnet.detektywslow.AbstractScreen;
import com.knowledgemagnet.detektywslow.AppearanceManager;
import com.knowledgemagnet.detektywslow.MyGame;

/**
 * Created by Igor on 06.02.2019.
 */

public class MenuScreen extends AbstractScreen {
    public MenuScreen(MyGame game) {
        super(game);
        init();
    }

    @Override
    protected void init() {
        Label.LabelStyle labelStyle=new Label.LabelStyle();
        BitmapFont bitmapFont=new BitmapFont(Gdx.files.internal("font/balo.fnt"), Gdx.files.internal("font/balo.png"), false);

        BitmapFont cos = game.assetManager.get("font/balo.fnt", BitmapFont.class);
       
        labelStyle.font=bitmapFont;
        Label label=new Label("ŁĄÓTŻEFDSF",labelStyle) ;
        label.setDebug(true);



        Label.LabelStyle labelStyle2=new Label.LabelStyle();
        labelStyle2.font=cos;
        Label label1=new Label("Ż",labelStyle2) ;
        label1.setDebug(true);

        label1.setY(300);

        stage.addActor(label);
        stage.addActor(label1);

        AppearanceManager appearanceManager=new AppearanceManager();
        appearanceManager.centerHorizontally(label1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
