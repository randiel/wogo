package com.gmcsoft.wogo.actors;

import akka.actor.UntypedActor;
import com.gmcsoft.wogo.messages.MapData;
import com.gmcsoft.wogo.messages.WordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public class MapActor extends UntypedActor {
    String[] STOP_WORDS = {"de","el","la","en","con","del","al","para"};
    private List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);
    @Override
    public void onReceive (Object message) throws Exception {
        if (message instanceof String){
            String work = (String) message;
            getSender().tell(evaluateExpression(work), getSelf());
        } else
            unhandled(message);
    }

    private MapData evaluateExpression(String line){
        // logic to map de words in sentences
        List<WordCount> dataList = new ArrayList<WordCount>();
        StringTokenizer parser = new StringTokenizer(line);
        while (parser.hasMoreTokens()) {
            String word = parser.nextToken().toLowerCase();
            if (!STOP_WORDS_LIST.contains(word)) {
                dataList.add(new WordCount(word, Integer.valueOf(1)));
            }
        }
        return new MapData(dataList);
    }
}
