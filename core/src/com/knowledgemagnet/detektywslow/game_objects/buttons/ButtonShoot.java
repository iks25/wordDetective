package com.knowledgemagnet.detektywslow.game_objects.buttons;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.knowledgemagnet.detektywslow.Board;
import com.knowledgemagnet.detektywslow.MyGame;

/**
 * Created by Igor on 18.01.2019.
 */

public class ButtonShoot extends HelpingButton {

    Board board;
    private Sound loadingSound,shootingcanselSound;
    private ButtonShoot(int nr, Texture texture, MyGame game,Board board) {
        super(nr, texture, game);
        this.setX(Gdx.graphics.getWidth()-gap-3*(this.getWidth()+margin));
        this.board=board;
        this.addListener(shootClikLisner());
        loadingSound=game.assetManager.get("sound/shootloading.mp3",Sound.class);
        shootingcanselSound=game.assetManager.get("sound/weapondeloading.mp3",Sound.class);

    }

    private ClickListener shootClikLisner() {
        return new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(board.shootingMode==false){
                    loadShootingMode();
                }else{
                    finishShootingMode();
                    shootingcanselSound.play();
                }
            }


        };
    }

    public void decriseNrOfShooting() {
        //todo nrOfShooting
    }

    public void finishShootingMode() {
        Texture texture=board.getGame().assetManager.get("shoot1.png",Texture.class);
        this.img.setDrawable(new SpriteDrawable(new Sprite(texture)));
        board.shootingMode=false;
    }

    private void loadShootingMode() {
        loadingSound.play();
        Texture texture=board.getGame().assetManager.get("shoot2.png",Texture.class);
        this.img.setDrawable(new SpriteDrawable(new Sprite(texture)));
        board.shootingMode=true;

    }

    ;

    public static ButtonShoot createButtonShoot(Board board){

        int nr=getNrFromDataBase();
        Texture texture=board.getGame().assetManager.get("shoot1.png", Texture.class);
        ButtonShoot button=new ButtonShoot(nr, texture, board.getGame(),board);
        //todo
        return button;
    };

    private static int getNrFromDataBase() {
        //todo
        return 5;
    }


}
