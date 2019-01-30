package com.knowledgemagnet.detektywslow;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
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
        myGame.assetManager.load("brickbreak.txt", TextureAtlas.class);
        myGame.assetManager.load("brick.png", Texture.class);
        myGame.assetManager.load("brickgreen.png", Texture.class);
        myGame.assetManager.load("shoot1.png", Texture.class);
        myGame.assetManager.load("shoot2.png", Texture.class);
        myGame.assetManager.load("light.png", Texture.class);
        myGame.assetManager.load("lupa.png", Texture.class);
        myGame.assetManager.load("viewfinder.png", Texture.class);
        myGame.assetManager.load("brickred.png", Texture.class);
        myGame.assetManager.load("sound/buttonTouch.mp3", Sound.class);
        myGame.assetManager.load("sound/shootloading.mp3", Sound.class);
        myGame.assetManager.load("sound/weapondeloading.mp3", Sound.class);
        myGame.assetManager.load("sound/weponshoot.mp3", Sound.class);
        myGame.assetManager.load("sound/goodshoot.mp3", Sound.class);
        myGame.assetManager.load("sound/correctSound.mp3", Sound.class);
        myGame.assetManager.load("sound/no.mp3", Sound.class);
        myGame.assetManager.load("menu.png", Texture.class);
        myGame.assetManager.load("nextlevel.png", Texture.class);
        myGame.assetManager.load("youWin.png", Texture.class);


        loadFont1();


    }

    private void loadFont1() {

        String path="font/Baloo-Regular.ttf";     //can be inside nested folder
        String fileName = "font1.ttf" ;

        AssetManager manager=myGame.assetManager;
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = path;    // path of .ttf file where that exist
        parms.fontParameters.size= (int) (32*Gdx.graphics.getDensity());
        parms.fontParameters.borderColor= new Color(0,0,0,0.5f);
        parms.fontParameters.borderWidth=(int)(2*Gdx.graphics.getDensity());
        parms.fontParameters.color=new Color(240f/255f,243f/255f,203f/255f,0.8f);
        manager.load(fileName, BitmapFont.class, parms);   // fileName with extension, sameName will use to get from manager
        manager.finishLoading();
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
        //todo polish letter

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
