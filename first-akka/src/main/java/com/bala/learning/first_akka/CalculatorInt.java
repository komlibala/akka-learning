package com.bala.learning.first_akka;

import scala.concurrent.Future;
import akka.japi.Option;

public interface CalculatorInt {

	public Future<Integer> add(Integer first,Integer second);
	
	public Future<Integer> subtract(Integer first,Integer second);
	
	public void incrementCount();
	
	public Option<Integer> incrementAndReturn();
}
