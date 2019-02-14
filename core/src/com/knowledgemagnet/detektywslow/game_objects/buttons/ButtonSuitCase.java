package com.knowledgemagnet.detektywslow.game_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.PlayScreen;
import com.knowledgemagnet.detektywslow.game_objects.MyPage;


/**
 * Created by Igor on 13.02.2019.
 */

public class ButtonSuitCase extends Image {
    MyPage page;
    public ButtonSuitCase(PlayScreen playScreen, MyPage page) {
    super(playScreen.game.assetManager.get("sutcase.png", Texture.class));
        int size= Gdx.graphics.getWidth()/6;
        this.setSize(size,size);
        this.setY(10*Gdx.graphics.getDensity());
        this.setX(10*Gdx.graphics.getDensity());
        this.page=page;
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                page.show();
                super.clicked(event, x, y);
            }
        });
    }




}
