package com.bala.learning.first_akka.main;

import java.util.concurrent.TimeUnit;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

import com.bala.learning.first_akka.Result;
import com.bala.learning.first_akka.actors.MasterActor;

public class MapReduceApplication {

	public static void main(String args[]) throws Exception{
		Timeout timeout = new Timeout(new FiniteDuration(5, TimeUnit.SECONDS));
		ActorSystem _system = ActorSystem.create("MapReduceApp");
		ActorRef master = _system.actorOf(Props.create(MasterActor.class), "master");
		master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog",null);
		master.tell("Dog is man's best friend", null);
		master.tell("Dog and fox belong to the same family", null);
		Thread.sleep(1000);
		Future<Object> future = Patterns.ask(master, new Result(), timeout);
		String result = (String) Await.result(future, timeout.duration());
		System.out.println(result);
		_system.shutdown();
	}
}
