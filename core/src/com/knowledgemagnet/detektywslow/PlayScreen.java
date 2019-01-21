package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.game_objects.BreakBrickAnimation;
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

        ILeverReader level=new ReadLVLFile(1);
        board=new Board(stage,game,level);
        board.addBoardToStage();
        board.addButtonsToStage();

    }



    @Override
    public void render(float delta) {
        super.render(delta);
        update();

    }

    private void update() {

    }
}
