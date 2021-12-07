package com.example.overlappingportfolio.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.overlappingportfolio.constants.Constants;

public class PortfolioServiceImpl implements PortfolioService {
	public static List<String> funds = new ArrayList<String>();
	public static JSONObject stocks = new JSONObject();
	public static int commandCount = Constants.ZERO;
	public String[] arguments = new String[19];
	
	public void calculatePortfolio(String[] arguments){
		String command = null;
		for (int i = Constants.ZERO;i<arguments.length;i++){
	    	if(arguments[i] == null){
	    		break;
	    	}
	    	String[] commandList = new String[arguments.length];
	    	if(arguments[i].equals(Constants.CURRENT_PORTFOLIO) ||
	    			arguments[i].equals(Constants.CALCULATE_OVERLAP) ||
	    			arguments[i].equals(Constants.ADD_STOCK)){
	    		command = arguments[i];
			}
	    	commandList = this.getCommands(arguments,i);
	    	i = i + PortfolioServiceImpl.commandCount;
	    	if(command != null){
	    		this.executeCommand(command,commandList);
	    	}
	    }
	}
	public String[] getCommands(String[] arguments,int index){
		String[] list = new String[arguments.length];
		int k = Constants.ZERO;
		for (int j = index+Constants.ONE;j<arguments.length;j++){
    		if(arguments[j] == null){
        		break;
        	}
    		if(!arguments[j].equals(Constants.CURRENT_PORTFOLIO) &&
    				!arguments[j].equals(Constants.CALCULATE_OVERLAP) &&
    				!arguments[j].equals(Constants.ADD_STOCK)){
        		if(arguments[j] != null){
        			list[k++] = arguments[j];
        		}
        	} else {
        		break;
        	}
    	}
		setCommandCount(k);
		return list;
	}
	public void executeCommand(String command, String[] commandList){
		if(command.equals(Constants.CURRENT_PORTFOLIO)){
			this.setCurrentPortfolio(commandList);
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
        	
        	this.addStock(commandList[Constants.ZERO], stockName);
        }
	}
	
	public void setCommandCount(int num){
		commandCount = num;
	}
	@Override
	public List<String> getCurrentPortfolio(){
		return funds;
	}
	
	@Override
	public void setCurrentPortfolio(String[] fundNames) {
		for(int i = Constants.ZERO; i< fundNames.length;i++){
			if(fundNames[i] != null){
				funds.add(fundNames[i]);
			}
		}		
	}
	
	@Override
	public List<String> calculateFundOverlap(String fundName) {
		List<String> fundsInCurrentPortfolio = getCurrentPortfolio();
		commonStocksCount(fundsInCurrentPortfolio, fundName);
		return null;
	}
	
	@Override
	public JSONArray getStocks(String fundName){
		Object[] stockNames = stocks.keySet().toArray();
		JSONArray arr = new JSONArray();
		for(int i = Constants.ZERO;i<stockNames.length;i++){
			if(stockNames[i].equals(fundName)){
				arr = (JSONArray)stocks.get(fundName);
			}
		}
		return arr;
	}
	
	@Override
	public void setStocks(String filePath){
		URL url;
		StringBuilder sb = new StringBuilder();
		try {
			url = new URL(filePath);
			URLConnection request = url.openConnection();
		    request.connect();
		    BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String line;
	        while ((line = br.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        br.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
        JSONObject jsonObj = new JSONObject(sb.toString());
        JSONArray data = (JSONArray)jsonObj.get("funds");
		for(int i=Constants.ZERO;i<data.length();i++){
			JSONObject obj = (JSONObject)data.get(i);
			if(obj != null){
				stocks.put(obj.get("name").toString(),obj.get("stocks"));
			}
		}
	}

	@Override
	public JSONArray addStock(String fundName, String stockName) {
		JSONArray arr = getStocks(fundName);
		arr.put(stockName);
		stocks.put(fundName,arr);
		return arr;
	}
	
	public void commonStocksCount(List<String> fundsInCurrentPortfolio,String fundName){
		JSONArray stocks = getStocks(fundName);
		JSONArray portfolioStocks;
		float ratio = Constants.ZERO,sum = Constants.ZERO;
		for(int i = Constants.ZERO;i<fundsInCurrentPortfolio.size();i++){
			portfolioStocks = getStocks(fundsInCurrentPortfolio.get(i));
			ratio = compareCommonStocks(stocks,portfolioStocks);
			if(ratio > Constants.ZERO){
				System.out.println(fundName + " " + fundsInCurrentPortfolio.get(i) + " " + String.format("%.2f",ratio) + "%");
			}
			sum += ratio;
		}
		if(sum == Constants.ZERO){
			System.out.println("FUND_NOT_FOUND");
		}
	}
	
	public float compareCommonStocks(JSONArray fundName,JSONArray portfolioStocks){
		int count = Constants.ZERO, fundNameSize = fundName.length(), portfolioStocksSize = portfolioStocks.length();
		for(int i=Constants.ZERO;i<fundNameSize;i++){
			for(int j=Constants.ZERO;j<portfolioStocksSize;j++){
				if((fundName.get(i).toString().replaceAll(" ", "")).equals(portfolioStocks.get(j).toString().replaceAll(" ", ""))){
					count++;
					break;
				}
			}
		}
		float ratio = (float)(Constants.TWO*Constants.HUNDRED*(count))/(fundNameSize + portfolioStocksSize);
		return ratio;
	}
	
	public void setCommands(FileReader filePath){
		Scanner fileScanner = new Scanner(filePath);
		int commands = Constants.ZERO;
		
		while(fileScanner.hasNext()){
			this.arguments[commands++] = fileScanner.next();
		}
		fileScanner.close();
	}
	
	public String[] getCommands(){
		return this.arguments;
	}
	
}
