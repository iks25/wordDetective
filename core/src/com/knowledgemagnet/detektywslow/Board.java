package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.game_objects.Brick;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;

/**
 * Created by Igor on 09.01.2019.
 */

public class Board {
    char tab[][];
    BrickInArray[][] bricks;
    Stage stage;
    MyGame game;
    public Board(Stage stage, MyGame game,ILeverReader level) {
        this.stage=stage;
        this.game=game;

        bricks=new BrickInArray[10][10];
        //todo change on real levelReader
        tab=level.getBoardLevel(1);

    }

    public void addBoardToStage(){
        int margin=2;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){

                BrickInArray b=new BrickInArray(tab[i][j],game,i,j);
                bricks[i][j]=b;
                bricks[i][j].addListener(getListener(bricks[i][j]));
                stage.addActor(bricks[i][j]);
            }
        }
        bricks[5][9].addListener(getListener(bricks[5][9]));
    }

    private ClickListener getListener(final BrickInArray brick) {
        return new ClickListener(){


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                brick.select();
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                brick.unselect();
                super.touchUp(event, x, y, pointer, button);
            }
                //todo zrob przesuwanie napraw bland

        };
    }

    ;
}
