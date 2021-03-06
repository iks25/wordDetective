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

    public void centerVertically(Actor actor1,Actor actorWhere ){//w pionie
        actor1.setY(actorWhere.getY()+(actorWhere.getHeight()*actorWhere.getScaleY()/2-actor1.getHeight()*actor1.getScaleY()/2));
    }
    public void centerHorizontally(Actor actor1,Actor actorWhere ){//w pionie
        actor1.setX(actorWhere.getX()+(actorWhere.getWidth()*actorWhere.getScaleX()/2-actor1.getWidth()*actor1.getScaleX()/2));
    }

    public void centerHorizontally(Actor actor){//w pionie
        actor.setX(Gdx.graphics.getWidth()/2-actor.getWidth()*actor.getScaleX()/2);
    }
    public void setUnder(int position,float margin,Actor actor){
        actor.setY(position-margin-actor.getHeight()*actor.getScaleY());
    }

    public void setUnder(Actor actorPosition,float margin,Actor actor){
        float position=actorPosition.getY();
        actor.setY(position-margin-actor.getHeight()*actor.getScaleY());
    }

    public void setAbove(int position,float margin,Actor actor){
        actor.setY(position+margin);
    }
    public void setTopInside(Actor positionActor,float margin,Actor actor){
        float top=positionActor.getY()+positionActor.getHeight()*positionActor.getScaleY();
        float actorSize=actor.getHeight()*actor.getScaleY();

        actor.setY(top-actorSize-margin);
    }

    public void setRightInside(Actor positionActor,float margin,Actor actor){
        float top=positionActor.getX()+positionActor.getWidth()*positionActor.getScaleX();
        float actorSize=actor.getWidth()*actor.getScaleX();

        actor.setX(top-actorSize-margin);
    }


    public void setAbove(Actor actorPosition,float margin,Actor actor){
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
