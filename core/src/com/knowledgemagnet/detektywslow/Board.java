package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.game_objects.Brick;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;

import java.util.ArrayList;

import sun.rmi.runtime.Log;


/**
 * Created by Igor on 09.01.2019.
 */

public class Board {
    char tab[][];
    BrickInArray[][] bricks;
    Stage stage;
    MyGame game;
    int numberOfBriks=10;
    public Board(Stage stage, MyGame game,ILeverReader level) {
        this.stage=stage;
        this.game=game;

        bricks=new BrickInArray[10][10];
        //todo change on real levelReader
        tab=level.getBoardLevel(1);

    }
Label up,down;
    public void addBoardToStage(){

        BitmapFont bitmapFont;
        bitmapFont=game.assetManager.get("font1.ttf",BitmapFont.class);
        Label.LabelStyle labelStyle=new Label.LabelStyle();
        labelStyle.font=bitmapFont;
        up=new Label("up 0",labelStyle);
        down=new Label("down 0",labelStyle);
        up.setVisible(true);

        down.setY(Gdx.graphics.getHeight()-100);
        up.setY(Gdx.graphics.getHeight()-250);


        stage.addActor(up);
        stage.addActor(down);

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
            ArrayList<BrickInArray> selectedBricks=new ArrayList<BrickInArray>();
            int firstX,firstY;
            boolean isVertical;
            int distance;
            int nrBrickToSelect;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                brick.select();
                firstX=(int)brick.getWidth()/2;
                firstY=(int)brick.getWidth()/2;
                down.setText("x "+ firstX +"    y "+y);
                selectedBricks.add(brick);

               Gdx.app.debug("to","helo");
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                String selectedWord="";
                for(BrickInArray b:selectedBricks){
                    selectedWord=selectedWord+b.getLetter();
                    b.unselect();
                }

                up.setText("sw = "+selectedWord);
                //todo down konczy sie po up
                selectedBricks.clear();
                super.touchUp(event, x, y, pointer, button);
            }



            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {


                if(Math.abs(x)>Math.abs(y)){
                    isVertical=false;
                    distance=(int)x;
                }
                else {
                    isVertical=true;
                    distance = (int) y;
                }
                if(distance<brick.getGap()){
                    nrBrickToSelect=distance/(int)(brick.getWidth()+brick.getGap());
                    nrBrickToSelect--;
                }
                else{
                    nrBrickToSelect=distance/(int)(brick.getWidth()+brick.getGap());
                }
                //todo dla coraz wiecej
                if(isVertical){
                    selectVerticaly();
                }
                else
                    selectHorizontally();



                up.setText("dragX = "+nrBrickToSelect);
                super.touchDragged(event, x, y, pointer);
            }

            private void selectHorizontally() {

                if(nrBrickToSelect>0){
                    for(int i=0;i<nrBrickToSelect;i++){

//                                bricks[brick.getxPosition()][i+brick.getyPosition()]==null){
//                            break;
                        if(nrBrickToSelect+brick.getxPosition()>=numberOfBriks||
                                bricks[i+brick.getxPosition()][brick.getyPosition()]==null){
                            break;
                        }
                        else {
                           // if(bricks[brick.getxPosition()][i+brick.getyPosition()+1].select()){
                            if(bricks[i+brick.getxPosition()+1][brick.getyPosition()].select()){
                              //selectedBricks.add(bricks[brick.getxPosition()][i+brick.getyPosition()+1]);
                                selectedBricks.add(bricks[i+brick.getxPosition()+1][brick.getyPosition()]);
                            };
                        }
                    }
                }else{ //nrBrickToSelect<0
                    for(int i=0;i>nrBrickToSelect;i--){
                        if((brick.getxPosition()+nrBrickToSelect)<0||
                          //    bricks[brick.getxPosition()][i+brick.getyPosition()]==null){
                                bricks[i+brick.getxPosition()][brick.getyPosition()]==null){
                            break;
                        }
                        else {
                       //   if(bricks[brick.getxPosition()][i+brick.getyPosition()-1].select()){
                            if(bricks[i+brick.getxPosition()-1][brick.getyPosition()].select()){
                           //   selectedBricks.add(bricks[brick.getxPosition()][i+brick.getyPosition()-1]);
                                selectedBricks.add(bricks[i+brick.getxPosition()-1][brick.getyPosition()]);
                            };
                        }
                    }
                }
            }

            private void selectVerticaly() {
                if(nrBrickToSelect>0){
                    for(int i=0;i<nrBrickToSelect;i++){
                        if(nrBrickToSelect+brick.getyPosition()>=numberOfBriks||
                        bricks[brick.getxPosition()][i+brick.getyPosition()]==null){
                            break;
                        }
                        else {
                            if(bricks[brick.getxPosition()][i+brick.getyPosition()+1].select()){
                                selectedBricks.add(bricks[brick.getxPosition()][i+brick.getyPosition()+1]);
                            };
                        }
                    }
                }else{ //nrBrickToSelect<0
                    for(int i=0;i>nrBrickToSelect;i--){
                        if((brick.getyPosition()+nrBrickToSelect)<0||
                                bricks[brick.getxPosition()][i+brick.getyPosition()]==null){
                            break;
                        }
                        else {
                            if(bricks[brick.getxPosition()][i+brick.getyPosition()-1].select()){
                                selectedBricks.add(bricks[brick.getxPosition()][i+brick.getyPosition()-1]);
                            };
                        }
                    }
                }
            }
        };
    }

    ;
}
