package com.knowledgemagnet.detektywslow.dataBase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Igor on 08.02.2019.
 */

public class SharePreferencesHelper {
    Preferences preferences;

    public SharePreferencesHelper() {
        this.preferences = Gdx.app.getPreferences("ustawienie.knowledgemagnet.detektywslow");
    }

    public int nrOfLevels(){
        return 10;//todo change nr of levels
    }

    public int getCurrentLvlNr(){
        int nr=preferences.getInteger(DataBaseNames.currentLevel,1);
        return nr;
    }

    public void setCurrentLvlNr(int currentLvlNr){
        preferences.putInteger(DataBaseNames.currentLevel,currentLvlNr);
        preferences.flush();

    }
    public void nextLevel(){
        int newCurentLevel=getCurrentLvlNr()+1;
        setCurrentLvlNr(newCurentLevel);

        int unlockLvl=getUnlockLvlNr();
        if(unlockLvl<newCurentLevel){
            preferences.putInteger(DataBaseNames.unlockLevel,newCurentLevel);
            preferences.flush();
        }

    }

    public int getUnlockLvlNr(){
        int nr=preferences.getInteger(DataBaseNames.unlockLevel,1);
        return nr;
    }


    ///////
    public int getNrOfBullets(){
        int nr=preferences.getInteger(DataBaseNames.bulletsNr,6);
        return nr;
    }

    public int getNrOfLights(){
        int nr=preferences.getInteger(DataBaseNames.lightsNr,3);
        return nr;
    }

    public int getNrOfLoupes(){
        int nr=preferences.getInteger(DataBaseNames.loupesNr,3);
        return nr;
    }

    public void useLight(){
        int nr=getNrOfLights();
        nr--;
        preferences.putInteger(DataBaseNames.lightsNr,nr);
        preferences.flush();
    }

    public void useLoupes(){
        int nr=getNrOfLoupes();
        nr--;
        preferences.putInteger(DataBaseNames.loupesNr,nr);
        preferences.flush();
    }

    public void useBullet(){
        int nr=getNrOfBullets();
        nr--;
        preferences.putInteger(DataBaseNames.bulletsNr,nr);
        preferences.flush();
    }

    public void addBullets(int numberToAdd){
        int nr=getNrOfBullets();
        nr=nr+numberToAdd;
        preferences.putInteger(DataBaseNames.bulletsNr,nr);
        preferences.flush();
    }

    public void addLights(int numberToAdd){
        int nr=getNrOfLights();
        nr=nr+numberToAdd;
        preferences.putInteger(DataBaseNames.lightsNr,nr);
        preferences.flush();
    }

    public void addLoupes(int numberToAdd){
        int nr=getNrOfLoupes();
        nr=nr+numberToAdd;
        preferences.putInteger(DataBaseNames.loupesNr,nr);
        preferences.flush();
    }


}
