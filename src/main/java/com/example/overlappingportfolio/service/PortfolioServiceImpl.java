package com.example.overlappingportfolio.service;

import java.util.List;
import java.util.Set;
import com.example.overlappingportfolio.constants.Constants;
import com.example.overlappingportfolio.model.Commands;
import com.example.overlappingportfolio.model.PortfolioModel;
import com.example.overlappingportfolio.model.Stocks;

public class PortfolioServiceImpl implements PortfolioService {
	Commands commandList = new Commands();
	private Set<String> commands = commandList.getCommandsList();
	public PortfolioModel portfolio = new PortfolioModel();
	public Stocks stocksModel = new Stocks();
	
	@Override
	public void calculatePortfolio(String[] arguments){
		String command = null;
		for (int i = Constants.ZERO;i<arguments.length;i++){
	    	if(arguments[i] != null){
				String[] commandsList = new String[arguments.length];
				if(commands.contains(arguments[i])){
					command = arguments[i];
				}
				commandsList = commandList.getCommands(arguments,i);
				int commandCount = commandList.getCommandCount();
				i = i + commandCount;
				if(command != null){
					this.executeCommand(command,commandsList);
				}
	    	} else { 
				break;
			}
	    }
	}

	@Override
	public void executeCommand(String command, String[] commandList){
		if(command.equals(Constants.CURRENT_PORTFOLIO)){
			portfolio.setCurrentPortfolio(commandList);
        } else if(command.equals(Constants.CALCULATE_OVERLAP)){
        	this.calculateFundOverlap(commandList[Constants.ZERO]);
        } else if(command.equals(Constants.ADD_STOCK)){
        	String stockName = "";
        	for(int i=1;i<commandList.length;i++){
        		if(commandList[i] != null){
        			stockName += commandList[i] + " ";
        		} else 
        		{
        			break;
        		}
        	}
        	stocksModel.addStock(commandList[Constants.ZERO], stockName);
        }
	}
	
	@Override
	public List<String> calculateFundOverlap(String fundName) {
		List<String> fundsInCurrentPortfolio = portfolio.getCurrentPortfolio();
		stocksModel.commonStocksCount(fundsInCurrentPortfolio, fundName);
		return null;
	}
	
}
