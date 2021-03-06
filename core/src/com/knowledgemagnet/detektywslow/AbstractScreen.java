package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Igor on 07.01.2019.
 */

public abstract class AbstractScreen  implements Screen{

    public MyGame game;
    public Stage stage;
    private Texture bg;
    private OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected ShapeRenderer shapeRenderer;



    public AbstractScreen(MyGame game){
        this.game = game;
        createCamera();
        bg=game.assetManager.get("tlo.png", Texture.class);
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        shapeRenderer=new ShapeRenderer();

    }

    protected abstract void init();

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()+10);
        spriteBatch.end();
        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());
    }
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    //todo można strzelac w nieistniejacego brica
    @Override
    public void show() {

    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        game.dispose();
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
}
