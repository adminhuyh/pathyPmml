package org.jpmml.evaluator.api.util;

import java.util.List;
import java.util.Map;

import org.jpmml.evaluator.ProbabilityDistribution;
import org.jpmml.evaluator.Value;
import org.jpmml.evaluator.ValueMap;

public class MyProbabilityDistribution extends  ProbabilityDistribution<Number>{

	public MyProbabilityDistribution(ValueMap<String, Number> probabilities) {
		super(probabilities);
		
	}
	public List getWinnerRankings(){
		return getWinnerList(getType(), entrySet());
	}

}
