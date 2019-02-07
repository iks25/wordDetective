package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.game_objects.Brick;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;
import com.knowledgemagnet.detektywslow.game_objects.Spark;
import com.knowledgemagnet.detektywslow.game_objects.buttons.Direction;
import com.knowledgemagnet.detektywslow.screens.YouWonWindow;

/**
 * Created by Igor on 07.01.2019.
 */

public class PlayScreen extends AbstractScreen {
    public int lvlNumber;
    Brick brick;
    ParticleEffect particleEffect;
    Board board;
    YouWonWindow wonWindow;
    Spark spark;


    public PlayScreen(MyGame game) {
        super(game);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }
    public PlayScreen(MyGame game,int lvlNumber) {
        super(game);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        this.lvlNumber=lvlNumber;
        init();

    }



    @Override
    protected void init() {

        ReadLVLFile lvlFile=new ReadLVLFile(this.lvlNumber);
        ILeverReader level=new ReadLVLFile(this.lvlNumber);
        board=new Board(stage,game,level);
        board.addBoardToStage();
        board.addButtonsToStage();

        particleEffect=new ParticleEffect();
        particleEffect.load(Gdx.files.internal("starbuu"),Gdx.files.internal(""));
        particleEffect.getEmitters().first().setPosition(250,600);
        particleEffect.start();

        spark=new Spark(this);

        Button button=new Button(new Button.ButtonStyle());
        button.setWidth(150);
        button.setHeight(150);
        button.setDebug(true);
        stage.addActor(button);

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                board.getBricksArray()[1][1].wrongAnwer();
                board.getBricksArray()[4][1].wrongAnwer();
                spark.showSpark(board.getBricksArray()[1][1],board.getBricksArray()[4][1], Direction.up);
                super.clicked(event, x, y);
            }
        });

          wonWindow=new YouWonWindow(this);
          stage.addActor(wonWindow);
          wonWindow.setPosition(Gdx.graphics.getWidth()/2-wonWindow.getWidth()/2,
                  Gdx.graphics.getHeight()*5/6-wonWindow.getHeight());


    }



    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        particleEffect.update(Gdx.graphics.getDeltaTime());
        spriteBatch.begin();
        particleEffect.draw(spriteBatch);
        spark.draw(spriteBatch);
        spriteBatch.end();

    }
    public int getLvlNumber() {
        return lvlNumber;
    }
    private void update() {

    }

    private void youWon() {
        wonWindow.setVisible(true);
    }

}
