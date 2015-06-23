package com.bala.learning.first_akka;

import scala.concurrent.Future;
import akka.actor.TypedActor.PostStop;
import akka.actor.TypedActor.PreStart;
import akka.dispatch.Futures;
import akka.japi.Option;

public class Calculator implements CalculatorInt,PreStart,PostStop {

	Integer counter = 0;
	
	public Future<Integer> add(Integer first, Integer second) {
		return Futures.successful(first + second);
	}

	public Future<Integer> subtract(Integer first, Integer second) {
		return Futures.successful(first - second);
	}

	public void incrementCount() {
		counter++;

	}

	public Option<Integer> incrementAndReturn() {
		return Option.some(++counter);
	}

	public void postStop() {
		System.out.println("Actor has stopped");
	}

	public void preStart() {
		System.out.println("Actor going to start");
	}

}
