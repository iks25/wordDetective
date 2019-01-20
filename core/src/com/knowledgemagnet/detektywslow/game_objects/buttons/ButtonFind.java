package com.knowledgemagnet.detektywslow.game_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.knowledgemagnet.detektywslow.Board;
import com.knowledgemagnet.detektywslow.MyGame;

/**
 * Created by Igor on 18.01.2019.
 */

public class ButtonFind extends HelpingButton {

    Board board;
    private ButtonFind(int nr, Texture texture, MyGame game,Board board) {
        super(nr, texture, game);
        this.setX(Gdx.graphics.getWidth()-gap-1*(this.getWidth()+margin));
        this.board=board;
    }
    public static ButtonFind createButtonShoot(Board board){

        int nr=getNrFromDataBase();
        Texture texture=board.getGame().assetManager.get("lupa.png", Texture.class);
        ButtonFind button=new ButtonFind(nr, texture, board.getGame(),board);
        //todo
        return button;
    };

    private static int getNrFromDataBase() {
        //todo
        return 5;
    }
}