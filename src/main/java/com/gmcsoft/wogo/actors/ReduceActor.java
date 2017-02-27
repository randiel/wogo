package com.gmcsoft.wogo.actors;

import akka.actor.UntypedActor;
import com.gmcsoft.wogo.messages.MapData;
import com.gmcsoft.wogo.messages.ReduceData;
import com.gmcsoft.wogo.messages.WordCount;

import java.util.HashMap;
import java.util.List;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public class ReduceActor extends UntypedActor {
    @Override
    public void onReceive (Object message) throws Exception {
        if (message instanceof MapData) {
            MapData mapData = (MapData) message;
            getSender().tell(reduce(mapData.getDataList()), getSelf());
        } else
            unhandled(message);
    }
    private ReduceData reduce (List<WordCount> dataList) {
        HashMap<String,Integer> reducedMap = new HashMap<String, Integer>();
        for (WordCount wordCount : dataList) {
            if (reducedMap.containsKey(wordCount.getWord())) {
                Integer value = (Integer)reducedMap.get(wordCount.getWord());
                value++;
                reducedMap.put(wordCount.getWord(),value);
            } else {
                reducedMap.put(wordCount.getWord(),Integer.valueOf(1));
            }
        }
        return new ReduceData(reducedMap);
    }
}
