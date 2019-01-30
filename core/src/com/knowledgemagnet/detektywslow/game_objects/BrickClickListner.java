package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.Board;

import java.util.ArrayList;

/**
 * Created by Igor on 18.01.2019.
 */

public class BrickClickListner {

    private final Board board;
    Sound corectTextSound, missShootSound, shootSound;


    public BrickClickListner(Board board){
        this.board=board;
        shootSound=board.getGame().assetManager.get("sound/weponshoot.mp3",Sound.class);
        corectTextSound=board.getGame().assetManager.get("sound/goodshoot.mp3",Sound.class);
        //missShootSound=board.getGame().assetManager.get("sound/weponshoot.mp3",Sound.class);
        shootSound.setVolume(1,0.5f);
    };

    public ClickListener getListener(final BrickInArray brick) {
        return new ClickListener(){
            public int nrSelectedBrick;
            ArrayList<BrickInArray> selectedBricks=new ArrayList<BrickInArray>();
            int firstX,firstY;
            boolean isVertical;
            int distance;
            int nrBrickToSelect;
            boolean previousOrientation;

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(board.shootingMode==true&&brick.clicable==true){
                    if(brick.isFakeBrick()){
                        brick.clicable=false;
                        brick.isFake=false;
                        corectTextSound.play();
                        board.shootingModeEnd();
                        brick.breakBrickAnimation();
                        board.moveDownBricks(brick.xPosition,brick.yPosition);

                    }else {
                        //   missShootSound.play();
                        shootSound.play();
                        brick.wrongAnwer();
                    }
                        board.shootingModeEnd();
                }
                super.clicked(event, x, y);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               if(board.shootingMode==false && brick.clicable==true){
                brick.select();
                firstX=(int)brick.getWidth()/2;
                firstY=(int)brick.getWidth()/2;
                selectedBricks.add(brick);
               }
                return super.touchDown(event, x, y, pointer, button);

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(board.shootingMode==false&& brick.clicable==true){
                String selectedWord="";
                for(BrickInArray b:selectedBricks){
                    selectedWord=selectedWord+b.getLetter();
                   // b.unselect();
                }
                    board.cheakWord(selectedBricks,selectedWord,isVertical);
                    selectedBricks.clear();
                }
                super.touchUp(event, x, y, pointer, button);
            }



            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {

                if(board.shootingMode==false&& brick.clicable==true) {
                    if (Math.abs(x) > Math.abs(y)) {
                        isVertical = false;
                        distance = (int) x;
                    } else {
                        isVertical = true;
                        distance = (int) y;
                    }
                    if (distance < brick.getGap()) {
                        nrBrickToSelect = distance / (int) (brick.getWidth() + brick.getGap());
                        nrBrickToSelect--;
                    } else {
                        nrBrickToSelect = distance / (int) (brick.getWidth() + brick.getGap());
                    }
                    orientationChanges(isVertical);
                    if (isVertical) {
                        selectVerticaly();
                    } else
                        selectHorizontally();

                    drawSelectedWord();
                    drawSelectedWord2(brick.getxPosition(),brick.getyPosition());
                    unselectBrick();

                    nrSelectedBrick = nrBrickToSelect;

                }

                super.touchDragged(event, x, y, pointer);
            }

            private void orientationChanges(boolean isVertical) {
                if(previousOrientation!=isVertical){

                    for(int i=selectedBricks.size();i>1;i--){
                        BrickInArray brick=selectedBricks.get(i-1);
                        brick.unselect();
                        selectedBricks.remove(brick);
                    }
                }
                previousOrientation=isVertical;

            }

            private void unselectBrick() {
                if(selectedBricks.size()>1){
                    if (nrSelectedBrick > 0 && nrBrickToSelect < nrSelectedBrick) {
                        BrickInArray brick = selectedBricks.get(selectedBricks.size() - 1);
                        brick.unselect();
                        selectedBricks.remove(brick);
                        nrSelectedBrick--;
                        return;
                    }
                    if (nrSelectedBrick < 0 && nrBrickToSelect > nrSelectedBrick) {
                        BrickInArray brick = selectedBricks.get(selectedBricks.size() - 1);
                        brick.unselect();
                        selectedBricks.remove(brick);
                        nrSelectedBrick++;
                        return;
                    }
                }

            }

            private void selectHorizontally() {

                if(nrBrickToSelect>0){
                    for(int i=0;i<nrBrickToSelect;i++){


                        if(nrBrickToSelect+brick.getxPosition()>=board.numberOfBriks||
                                board.bricks[i+brick.getxPosition()+1][brick.getyPosition()]==null){
                            break;
                        }
                        else {
                            if(board.bricks[i+brick.getxPosition()+1][brick.getyPosition()].select()){
                                selectedBricks.add(board.bricks[i+brick.getxPosition()+1][brick.getyPosition()]);
                            };
                        }
                    }
                }else{
                    for(int i=0;i>nrBrickToSelect;i--){
                        if((brick.getxPosition()+nrBrickToSelect)<0||
                                board.bricks[i+brick.getxPosition()-1][brick.getyPosition()]==null){
                            break;
                        }
                        else {
                            if(board.bricks[i+brick.getxPosition()-1][brick.getyPosition()].select()){
                                selectedBricks.add(board.bricks[i+brick.getxPosition()-1][brick.getyPosition()]);
                            };
                        }
                    }
                }
            }

            private void selectVerticaly() {
                if(nrBrickToSelect>0){
                    for(int i=0;i<nrBrickToSelect;i++){
                        if(nrBrickToSelect+brick.getyPosition()>=board.numberOfBriks||
                                board.bricks[brick.getxPosition()][i+brick.getyPosition()+1]==null){
                            break;
                        }
                        else {
                            if(board.bricks[brick.getxPosition()][i+brick.getyPosition()+1].select()){
                                selectedBricks.add(board.bricks[brick.getxPosition()][i+brick.getyPosition()+1]);
                            };
                        }
                    }
                }else{ //nrBrickToSelect<0
                    for(int i=0;i>nrBrickToSelect;i--){
                        if((brick.getyPosition()+nrBrickToSelect)<0||
                                board.bricks[brick.getxPosition()][i+brick.getyPosition()]==null){
                            break;
                        }
                        else {
                            if(board.bricks[brick.getxPosition()][i+brick.getyPosition()-1].select()){
                                selectedBricks.add(board.bricks[brick.getxPosition()][i+brick.getyPosition()-1]);
                            };
                        }
                    }
                }

            }
            private void drawSelectedWord(){
                String selectetWord="";
                for(BrickInArray brickInArray:selectedBricks)
                    selectetWord=selectetWord+brickInArray.getLetter();
                    board.selectedWordLabel.setText(selectetWord);
            }
            private void drawSelectedWord2(float x, float y) {

                String selectetWord=x+" "+y+"  ";
                for(BrickInArray brickInArray:selectedBricks)
                    selectetWord=selectetWord+brickInArray.getLetter();
                board.selectedWordLabel.setText(selectetWord);
            }
        };


    }





}
