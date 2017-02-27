package com.gmcsoft.wogo.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import com.gmcsoft.wogo.messages.MapData;
import com.gmcsoft.wogo.messages.ReduceData;
import com.gmcsoft.wogo.messages.Result;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public class MasterActor extends UntypedActor {
    ActorRef mapActor = getContext().actorOf(Props.create(MapActor.class).withRouter(new RoundRobinPool(5)), "map");
    ActorRef reduceActor = getContext().actorOf(Props.create(ReduceActor.class).withRouter(new RoundRobinPool(5)),"reduce");
    ActorRef aggregateActor = getContext().actorOf(Props.create(AggregateActor.class).withRouter(new RoundRobinPool(5)),"aggregate");

    @Override
    public void onReceive (Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message, getSelf());
        } else if (message instanceof MapData) {
            reduceActor.tell(message, getSelf());
        } else if (message instanceof ReduceData) {
            aggregateActor.tell(message, getSelf());
        } else if (message instanceof Result) {
            aggregateActor.forward(message, getContext()); //getContext()
        } else
            unhandled(message);
    }
}
