package com.gmcsoft.wogo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.gmcsoft.wogo.actors.MasterActor;
import com.gmcsoft.wogo.messages.Result;
import scala.concurrent.Await;
import scala.concurrent.Future;

import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by randiel.melgarejo on 20/02/2017.
 */
public class MapReduceApplication {
    public static void main(String[] args) throws Exception {
        ActorSystem _system = ActorSystem.create("MapReduceApp");
        ActorRef master = _system.actorOf(Props.create(MasterActor.class),"master");

        //final Inbox inbox = Inbox.create(_system);

        master.tell("en una galaxia lejana hace mucho mucho tiempo", ActorRef.noSender());
        master.tell("vivia un guerrero que esperaba encontrarse con el guerrero de una galaxia proxima", ActorRef.noSender());

        Thread.sleep(5000);

        Result msg = new Result();
        Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));
        Future<Object> future = Patterns.ask(master, msg, timeout);
        String result = (String) Await.result(future, timeout.duration());
        System.out.println(result);
        _system.terminate();
    }

}
