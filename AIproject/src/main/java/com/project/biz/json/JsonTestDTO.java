package com.project.biz.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonTestDTO<R> {
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private double averageScore;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int totalCount;
	
	private R items;
	
	private List<Comments> comments;
	
	
}
