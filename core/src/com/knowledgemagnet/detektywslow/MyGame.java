package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.knowledgemagnet.detektywslow.dataBase.SharePreferencesHelper;
import com.knowledgemagnet.detektywslow.screens.LevelsSelectScreen;
import com.knowledgemagnet.detektywslow.screens.MenuScreen;

public class MyGame extends com.badlogic.gdx.Game {

    public AssetManager assetManager;


    public final String gameName="detektyw słow";
	@Override
	public void create () {
        assetManager =new AssetManager();
        this.setScreen(new Loading(this));

	}

	public void startScreenPlay(){
	    float density= Gdx.graphics.getDensity();
        BitmapFont bitmapFont =assetManager.get("font/balo.fnt", BitmapFont.class);
         bitmapFont.getData().setScale(density*0.51f);

        BitmapFont bitmapFont2 =assetManager.get("font/baloblack.fnt", BitmapFont.class);
        bitmapFont2.getData().setScale(density*0.51f);
	  //  this.setScreen(new PlayScreen(this,1));
      // this.setScreen(new MenuScreen(this));
        this.setScreen(new LevelsSelectScreen(this));
    }
    public void playLevel(int lvl){
        SharePreferencesHelper settings=new SharePreferencesHelper();

        settings.setCurrentLvlNr(lvl);
	    this.setScreen(new PlayScreen(this,lvl));
    }
	

}
