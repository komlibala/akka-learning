package com.bala.learning.first_akka;

import java.util.Map;

public class ReduceData {

	private final Map<String,Integer> reduceDataList;
	
	public Map<String,Integer> getReduceDataList(){
		return reduceDataList;
	}
	public ReduceData(Map<String,Integer> reduceDataList){
		this.reduceDataList = reduceDataList;
	}
}
