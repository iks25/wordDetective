package com.knowledgemagnet.detektywslow.game_objects.buttons;



import com.badlogic.gdx.Gdx;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;
import com.knowledgemagnet.detektywslow.game_objects.LetterContainer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;



/**
 * Created by Igor on 31.01.2019.
 */
public class PositionFinderTest {

    PositionFinder positionFinder;
    @Before
    public void setUp() throws Exception {
        positionFinder=new PositionFinder();
    }

    @Test
    public void findWordsOnLetterTest(){
        List<String> listOfWordsMock=
                Arrays.asList(new String[]{"ALA","BOGDAN","ADAM"});
        List<String> findedWords=positionFinder.findWordsOnLetter('A',listOfWordsMock);


        List<String> expectetWords=Arrays.asList(new String[]{"ALA","ADAM"});
        Assert.assertEquals(expectetWords,findedWords);

        List<String> listOfWordsMock2=
                Arrays.asList(new String[]{"ALA","BOGDAN","ADAM"});
        List<String> findedWords2=positionFinder.findWordsOnLetter('K',listOfWordsMock);


        List<String> expectetWords2=Arrays.asList(new String[]{});
        Assert.assertEquals(expectetWords2,findedWords2);
    }

    @Test
    public void isInUPTest(){
        Character testBoard[][]=
        {
            {'I','G','O','R'},
            {'G','G',null,'P'},
            {'A','U',null,'I'},
            {null,'T',null,'K'},
        };

        String potentialWord = "IGA";

      boolean my= positionFinder.isInUP(testBoard,potentialWord,1,3);
        Assert.assertFalse(my);


        my= positionFinder.isInUP(testBoard,potentialWord,2,0);
        Assert.assertFalse(my);

        my= positionFinder.isInUP(testBoard,potentialWord,0,0);
        Assert.assertTrue(my);
        //positionFinder.isInUP()
    }

//    @Test
////    public void isInDownTestShouldFail(){
////        Character testBoard[][]=
////                {
////                        {'I','G','O','R'},
////                        {'G','T',null,'P'},
////                        {'A','U',null,'I'},
////                        {null,'P',null,'K'},
////                };
////        String potentialWord = "IGA";
////
////        boolean my= positionFinder.isInDown(testBoard,potentialWord,0,3);//null position
////        Assert.assertFalse(my);
////
////         my= positionFinder.isInDown(testBoard,potentialWord,1,1); //to long word
////        Assert.assertFalse(my);
////
////        my= positionFinder.isInDown(testBoard,potentialWord,2,3); //Word is not correct
////        Assert.assertFalse(my);
//
//    }
    @Test
    public void boardLetterCreateTestForEmpty(){
        LetterContainer[][] letterContainersArray=new LetterContainer[2][2];
        Character testBoard[][]=positionFinder.boardLetterCreate(letterContainersArray);
        Character expected[][]=new Character[2][2];
        Assert.assertEquals(testBoard,expected);

    }

    @Test
    public void boardLetterCreateTest(){
        LetterContainer[][] letterContainersArray=new LetterContainer[2][2];
        letterContainersArray[0][0]=new MockLetterContainer('I');
        letterContainersArray[0][1]=null;
        letterContainersArray[1][0]=new MockLetterContainer('S');
        letterContainersArray[1][1]=new MockLetterContainer('D');

        Character testBoard[][]=positionFinder.boardLetterCreate(letterContainersArray);

        Character expected[][]=new Character[2][2];
        expected[0][0]='I';
        expected[0][1]=null;
        expected[1][0]='S';
        expected[1][1]='D';


        Assert.assertEquals(expected,testBoard);

    }

    @Test
    public void RotateArrayTest(){
        Character array[][]=new Character[2][2];
        array[0][0]='I';
        array[1][0]=null;
        array[0][1]='S';
        array[1][1]='D';

        Character expected[][]=new Character[2][2];
        expected[0][0]='I';
        expected[0][1]=null;
        expected[1][0]='S';
        expected[1][1]='D';

        Character[][] result=positionFinder.rotateArray(array);
        Assert.assertEquals(expected,result);
    }
    public class MockLetterContainer implements LetterContainer
    {
        Character letter;
        MockLetterContainer(char c){
            letter=c;
        }

        @Override
        public Character getLetter() {
            return letter;
        }
    }



}
