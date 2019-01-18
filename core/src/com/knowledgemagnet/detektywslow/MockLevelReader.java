package com.knowledgemagnet.detektywslow;

import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;

import java.util.List;

/**
 * Created by Igor on 09.01.2019.
 */

public class MockLevelReader implements ILeverReader {

    char[][]board;
    @Override
    public char[][] getBoardLevel(int lvl) {
        char[][]board=new char[8][8];
        char c1='1';
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                char c=c1++;
                board[j][i]=c;
            }
        }        //return new char[0][];
        return board;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<String> getWords() {
        return null;
    }

    @Override
    public List<Character> getLettersToShootDown() {
        return null;
    }
}
