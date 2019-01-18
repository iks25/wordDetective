package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.game_objects.Brick;

/**
 * Created by Igor on 07.01.2019.
 */

public class PlayScreen extends AbstractScreen {
    Brick brick;
    Board board;
    public PlayScreen(MyGame game) {
        super(game);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }




    @Override
    protected void init() {
        ReadLVLFile lvlFile=new ReadLVLFile(1);
        brick=new Brick('T',game);
        brick.setPosition(100,100);

        stage.addActor(brick);
        //ILeverReader level=new MockLevelReader();

        ILeverReader level=new ReadLVLFile(1);
        board=new Board(stage,game,level);
        board.addBoardToStage();

        brick.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                brick.select();
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //brick.unselect();
                brick.wrongAnwer();
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }



    @Override
    public void render(float delta) {
        super.render(delta);
        update();
    }

    private void update() {

    }
}
