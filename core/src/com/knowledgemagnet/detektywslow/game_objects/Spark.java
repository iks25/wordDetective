package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.knowledgemagnet.detektywslow.PlayScreen;
import com.knowledgemagnet.detektywslow.game_objects.buttons.Direction;


/**
 * Created by Igor on 01.02.2019.
 */

public class Spark {
    ParticleEffect sparkView;
    FindedWordPosition findedWordPosition;
    BrickInArray brickfirs,bricklast;
    Boolean droawNow=false;
    Direction direction;
    float x=0;
    float y=200;
    public Spark(PlayScreen playScreen){
        sparkView=new ParticleEffect();
        sparkView.load(Gdx.files.internal("iskra.pe"),Gdx.files.internal(""));
        findedWordPosition=new FindedWordPosition(1,1,4,1);
        brickfirs =new BrickInArray('I',playScreen.game,1,1);
        bricklast =new BrickInArray('I',playScreen.game,5,5);
        direction=Direction.right;
    }

    public void showSpark(BrickInArray brickfirs,BrickInArray brickLast,Direction direction){
        this.brickfirs=brickfirs;
        this.bricklast=brickLast;
        this.direction=direction;
        x=brickfirs.getX()-10;
        y=brickfirs.getY()-10;


        sparkView.setPosition(x, y);
        droawNow=true;


    }

        private void moweToRight(){

        sparkView.setPosition(findedWordPosition.x1,findedWordPosition.y2);
        };
    public void draw(SpriteBatch batch){
        if(droawNow){
            moveSpark();
            sparkView.draw(batch, Gdx.graphics.getDeltaTime());
            sparkView.setPosition(x,y);



        }
    }
float velocity=500;
    private void moveSpark() {

        float delta=Gdx.graphics.getDeltaTime();
        if(direction==Direction.right){
            x+=velocity*delta;
            if(x>bricklast.getX()+bricklast.getWidth()+20){
                direction=Direction.down;
            }
            return;
        }

        if(direction==Direction.up){
            y+=velocity*delta;
            if(y>=bricklast.getY()+bricklast.getHeight()+20){
                direction=Direction.right;
            }
            return;
        }

        if(direction==Direction.down){
            y-=velocity*delta;
            if(y<=brickfirs.getY()-20){
                direction=Direction.left;
            }
            return;
        }

        if(direction==Direction.left){
            x-=velocity*delta;
            if(x<=brickfirs.getX()-20){
                direction=Direction.up;
            }
            return;
        }

    }
}
