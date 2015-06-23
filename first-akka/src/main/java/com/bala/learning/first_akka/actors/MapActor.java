package com.bala.learning.first_akka.actors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import akka.actor.UntypedActor;

import com.bala.learning.first_akka.MapData;
import com.bala.learning.first_akka.WordCount;

public class MapActor extends UntypedActor{

	String[] STOP_WORDS = { "a", "am", "an", "and", "are", "as", "at",
			"be","do", "go", "if", "in", "is", "it", "of", "on", "the", "to" };
	List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);

	@Override
	public void onReceive(Object message) throws Exception {
		if(message instanceof String){
			String line = (String) message;
			getSender().tell(evaluateExpression(line),getSelf());
		}else{
			unhandled(message);
		}
	}

	private MapData evaluateExpression(String line) {
		List<WordCount> dataList = new ArrayList<WordCount>();
		StringTokenizer parser = new StringTokenizer(line);
		while(parser.hasMoreTokens()){
			String word = parser.nextToken().toLowerCase();
			if(!STOP_WORDS_LIST.contains(word))
				dataList.add(new WordCount(word, Integer.valueOf(1)));
		}
		return new MapData(dataList);
	}

}
