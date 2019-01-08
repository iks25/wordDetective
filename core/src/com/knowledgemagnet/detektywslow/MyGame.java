package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends com.badlogic.gdx.Game {

    public AssetManager assetManager;


    public final String gameName="detektyw słow";
	@Override
	public void create () {
        assetManager =new AssetManager();
        this.setScreen(new Loading(this));

	}

	public void startScreenPlay(){
        this.setScreen(new PlayScreen(this));
    }
	

}
