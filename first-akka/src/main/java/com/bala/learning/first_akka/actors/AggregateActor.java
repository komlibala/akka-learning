package com.bala.learning.first_akka.actors;

import java.util.HashMap;
import java.util.Map;

import akka.actor.UntypedActor;

import com.bala.learning.first_akka.ReduceData;
import com.bala.learning.first_akka.Result;

public class AggregateActor extends UntypedActor{

	private Map<String,Integer> finalReduceMap = new HashMap<String, Integer>();
	
	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof ReduceData){
			ReduceData reduceData = (ReduceData) message;
			aggregateInMemoryMapReduce(reduceData.getReduceDataList());
		}else if(message instanceof Result){
			getSender().tell(finalReduceMap.toString(), getSelf());
		}else
			unhandled(message);
	}

	private void aggregateInMemoryMapReduce(Map<String,Integer> reduceDataMap) {
		Integer count = null;
		for(String word : reduceDataMap.keySet()){
			if(finalReduceMap.containsKey(word)){
				count = reduceDataMap.get(word) + finalReduceMap.get(word);
				finalReduceMap.put(word, count);
			}else{
				finalReduceMap.put(word, reduceDataMap.get(word));
			}
		}
	}

}
