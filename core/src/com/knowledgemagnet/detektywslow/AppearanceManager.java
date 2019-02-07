package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Igor on 06.02.2019.
 */

public class AppearanceManager {

    public void centerVertically(Actor actor){//w pionie
        actor.setY(Gdx.graphics.getHeight()/2-actor.getHeight()*actor.getScaleY()/2);
    }
    public void centerHorizontally(Actor actor){//w pionie
        actor.setX(Gdx.graphics.getWidth()/2-actor.getWidth()*actor.getScaleX()/2);
    }
    public void setUnder(int position,int margin,Actor actor){
        actor.setY(position-margin-actor.getHeight()*actor.getScaleY());
    }

    public void setUnder(Actor actorPosition,int margin,Actor actor){
        float position=actorPosition.getY();
        actor.setY(position-margin-actor.getHeight()*actor.getScaleY());
    }

    public void setAbove(int position,int margin,Actor actor){
        actor.setY(position+margin);
    }


    public void setAbove(Actor actorPosition,int margin,Actor actor){
        float position=actorPosition.getY()+actorPosition.getScaleY()*actorPosition.getHeight();
        actor.setY(position+margin);
    }
    public void setOnTop(Actor actor,int margin){
        actor.setY(Gdx.graphics.getHeight()-margin-actor.getHeight()*actor.getScaleY());

    }

    public void setSizeWidthPercent(Actor actor,float percent){
        float targetSize=(float) Gdx.graphics.getWidth()*percent/100;
        float delta=targetSize/(actor.getWidth()*actor.getScaleX());
        actor.setWidth(actor.getWidth()*delta);
        actor.setHeight(actor.getHeight()*delta);
    }
}
