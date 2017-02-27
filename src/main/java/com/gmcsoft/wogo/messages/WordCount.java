package com.gmcsoft.wogo.messages;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public final class WordCount {
    private final String word;
    private final Integer count;

    public WordCount (String inWord, Integer inCount) {
        word = inWord;
        count = inCount;
    }

    public String getWord(){
        return word;
    }

    public Integer getCount(){
        return count;
    }

}
