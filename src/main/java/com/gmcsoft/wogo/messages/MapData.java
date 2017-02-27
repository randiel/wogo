package com.gmcsoft.wogo.messages;

import java.util.List;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public final class MapData {
    private final List<WordCount> dataList;

    public List<WordCount> getDataList(){
        return this.dataList;
    }

    public MapData (List<WordCount> dataList) {
        this.dataList = dataList;
    }
}
