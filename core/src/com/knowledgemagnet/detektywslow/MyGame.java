package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.assets.AssetManager;
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
	    this.setScreen(new PlayScreen(this,5));
       // this.setScreen(new MenuScreen(this));
    }
	

}
