package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Igor on 16.01.2019.
 */

public class ReadLVLFile implements ILeverReader{

    char[][]board;
    String text;
    List<String> words;
    List<Character> lettersToShoot;
    public ReadLVLFile(int lvl){
        FileHandle handle = Gdx.files.internal("lvl/"+lvl+".txt");
        //todo what if not exist or data is bad
        String fileString=handle.readString();
        Gdx.app.log("lvl",fileString);

        createBoardfromFile(fileString);
    }

    private void createBoardfromFile(String fileString) {
        //libgdx is draw from down to selectedWordLabel; file text is save from selectedWordLabel to down
        board=new char[8][8];
        String[] splitedFile=null;
        splitedFile=fileString.split("\n");
        int nr=0;
        for(int a=7;a>=0;a--){
            String row[]=splitedFile[a].split(";");
            for(int i=0;i<8;i++){
                board[i][nr]=row[i].charAt(0);
                Gdx.app.log("row "+nr,""+board[i][nr]);
            }
            nr++;
        }

        text=splitedFile[9];
        Gdx.app.log("text ","------->  "+text);
        //todo nastepne

       words=new ArrayList<String>();
       String a[]= splitedFile[11].split(";");
       words= Arrays.asList(a);

        lettersToShoot=new ArrayList<Character>();
        String letters[]= splitedFile[12].split(";");
        for(int j=0;j<letters.length;j++){
            lettersToShoot.add(letters[j].charAt(0));
            Gdx.app.log("text ","------->  "+letters[j].charAt(0));

        }
    }

    ;

    @Override
    public char[][] getBoardLevel(int lvl) {

        return board;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public List<String> getWords() {
        return words;
    }

    @Override
    public List<Character> getLettersToShootDown() {
        return lettersToShoot;
    }
}
