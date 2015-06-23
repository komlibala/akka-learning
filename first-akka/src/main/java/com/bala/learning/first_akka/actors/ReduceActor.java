package com.bala.learning.first_akka.actors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import akka.actor.UntypedActor;

import com.bala.learning.first_akka.MapData;
import com.bala.learning.first_akka.ReduceData;
import com.bala.learning.first_akka.WordCount;

public class ReduceActor extends UntypedActor{

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof MapData){
			MapData mapData = (MapData) message;
			getSender().tell(reduce(mapData.getDataList()),getSelf());
		}else{
			unhandled(message);
		}
	}

	private ReduceData reduce(List<WordCount> dataList) {
		Map<String,Integer> reduceDataList = new HashMap<String, Integer>();
		for(WordCount word : dataList){
			if(reduceDataList.containsKey(word.getWord())){
				Integer value = reduceDataList.get(word.getWord());
				value++;
				reduceDataList.put(word.getWord(), value);
			}else{
				reduceDataList.put(word.getWord(), Integer.valueOf(1));
			}
		}
		return new ReduceData(reduceDataList);
	}

}
