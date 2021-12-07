package com.example.overlappingportfolio.service;

import java.util.List;

import org.json.JSONArray;

import com.example.overlappingportfolio.model.PortfolioModel;

public interface PortfolioService {
	List<String> getCurrentPortfolio();
	void setCurrentPortfolio(String[] fundNames);
	
	List<String> calculateFundOverlap(String fundName);
	
	JSONArray addStock(String fundName, String stockName);
	JSONArray getStocks(String fundName);
	void setStocks(String filePath);
	
}
