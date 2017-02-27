package com.gmcsoft.wogo.messages;

import java.util.HashMap;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public class ReduceData {
    private final HashMap<String, Integer> reduceDataList;

    public HashMap<String, Integer> getReduceDataList() {
        return reduceDataList;
    }

    public ReduceData(HashMap<String, Integer> reduceDataList) {
        this.reduceDataList = reduceDataList;
    }
}
