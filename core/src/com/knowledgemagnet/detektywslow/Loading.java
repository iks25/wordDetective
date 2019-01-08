package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Igor on 07.01.2019.
 */

class Loading implements Screen {



    MyGame myGame;
    public ShapeRenderer shapeRenderer;
    private float progres;
    public Loading(MyGame mygame) {
        myGame = mygame;
        shapeRenderer=new ShapeRenderer();
        progres=0f;
        queueAssets();
    }

    private void queueAssets() {

        //loadFont();

        myGame.assetManager.load("brick.png", Texture.class);
        myGame.assetManager.load("brickgreen.png", Texture.class);
        myGame.assetManager.load("brickred.png", Texture.class);


    }

    private void loadFont() {
       //load font
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(114/255f,173/255f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(Gdx.graphics.getWidth()/2-314,Gdx.graphics.getHeight()/2-64,628,128);

        shapeRenderer.setColor(new Color(0xb7d5ffff));
        shapeRenderer.rect(Gdx.graphics.getWidth()/2-310,Gdx.graphics.getHeight()/2-60,620,120);

        shapeRenderer.setColor(new Color(0xeef5ffff));
        //shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(Gdx.graphics.getWidth()/2-295,Gdx.graphics.getHeight()/2-45,600*progres,90);

        shapeRenderer.end();

    }



    private void update(float delta) {
        progres= myGame.assetManager.getProgress();
        System.out.println(progres);
        if(myGame.assetManager.update()){
            myGame.startScreenPlay();
        }

    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}