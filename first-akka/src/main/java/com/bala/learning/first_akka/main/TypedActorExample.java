package com.bala.learning.first_akka.main;

import java.util.concurrent.TimeUnit;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.util.Timeout;

import com.bala.learning.first_akka.Calculator;
import com.bala.learning.first_akka.CalculatorInt;

public class TypedActorExample {

	public static void main(String args[]) throws Exception{
		Timeout timeout = new Timeout(new FiniteDuration(5, TimeUnit.SECONDS));
		ActorSystem _system = ActorSystem.create("TypedActorExample");
		CalculatorInt calcualtor = TypedActor.get(_system).typedActorOf(new TypedProps<Calculator>(CalculatorInt.class,Calculator.class));
		calcualtor.incrementCount();
		Future<Integer> future = calcualtor.add(Integer.valueOf(14),
				Integer.valueOf(6));
				Integer result = Await.result(future, timeout.duration());
		System.out.println(result);
		TypedActor.get(_system).poisonPill(calcualtor);
	}
}
