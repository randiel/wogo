package com.gmcsoft.wogo.actors;

import akka.actor.UntypedActor;
import com.gmcsoft.wogo.messages.ReduceData;
import com.gmcsoft.wogo.messages.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public class AggregateActor extends UntypedActor {
    private Map<String, Integer> finalReducedMap = new HashMap<String, Integer>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ReduceData) {
            ReduceData reduceData = (ReduceData) message;
            aggregareInMemoryReduce(reduceData.getReduceDataList());
        } else if  (message instanceof Result) {
            getSender().tell(finalReducedMap.toString(), getSelf());
        } else
            unhandled(message);
    }

    private void aggregareInMemoryReduce(Map<String, Integer> reducedList) {
        Integer count = null;
        for (String key : reducedList.keySet()) {
            if (finalReducedMap.containsKey(key)) {
                count = reducedList.get(key) + finalReducedMap.get(key);
                finalReducedMap.put(key, count);
            } else {
                finalReducedMap.put(key, reducedList.get(key));
            }
        }
    }
}
