package com.example.overlappingportfolio.service;

import java.util.List;

public interface PortfolioService {
	public List<String> calculateFundOverlap(String fundName);
	public void executeCommand(String command, String[] commandList);
	public void calculatePortfolio(String[] arguments);
	
}
