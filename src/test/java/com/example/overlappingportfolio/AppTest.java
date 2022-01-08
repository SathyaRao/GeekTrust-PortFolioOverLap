package com.example.overlappingportfolio;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.example.overlappingportfolio.constants.Constants;
import com.example.overlappingportfolio.model.Commands;
import com.example.overlappingportfolio.model.PortfolioModel;
import com.example.overlappingportfolio.model.Stocks;
import com.example.overlappingportfolio.service.PortfolioServiceImpl;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class AppTest
{
    
    PortfolioServiceImpl portfolioServiceImpl = new PortfolioServiceImpl();
	Stocks stocksModel = new Stocks();
	PortfolioModel portfolio = new PortfolioModel();
	Commands commands = new Commands();
	String CALCULATE_OVERLAP = Constants.CALCULATE_OVERLAP;
	String ADD_STOCK =  Constants.ADD_STOCK;
	int ONE =  Constants.ONE;
	int TWO =  Constants.TWO;
	int HUNDRED =  Constants.HUNDRED;
	@Test
    public void setStocksTest(){
		
		String filePath = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";
    	stocksModel.setStocks(filePath);
 
    	assertNotNull(filePath);
    }
	@Test
    public void setStocksTest1(){
		
		String filePath = null;
    	stocksModel.setStocks(filePath);
 
    	assertNull(filePath);
    }
	@Test
    public void calculatePortfolioTest(){
		String commandFile = "/Users/srao/Projects/Java/GeekTrust-PortFolioOverLap-main/src/main/java/resources/commands.txt";
		FileReader file;
		try {
			file = new FileReader(commandFile);
			commands.setCommands(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] arguments = commands.getCommands();
        if (arguments.length > Constants.ZERO)
        {
        	portfolioServiceImpl.calculatePortfolio(arguments);
        }
    	assertNotNull(commands.getCommands().length);
    }
	@Test
    public void calculatePortfolioTest2(){
		String commandFile = "/Users/srao/Projects/Java/GeekTrust-PortFolioOverLap-main/src/main/java/resources/commands2.txt";
		FileReader file;
		try {
			file = new FileReader(commandFile);
			commands.setCommands(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] arguments = commands.getCommands();
        if (arguments.length > Constants.ZERO)
        {
        	portfolioServiceImpl.calculatePortfolio(arguments);
        }
    	assertNotNull(commands.getCommands().length);
    }
	@Test
    public void calculatePortfolioTest3(){
		String commandFile = "/Users/srao/Projects/Java/GeekTrust-PortFolioOverLap-main/src/main/java/resources/commands3.txt";
		
		FileReader file;
		try {
			file = new FileReader(commandFile);
			commands.setCommands(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] arguments = commands.getCommands();
        if (arguments.length > Constants.ZERO)
        {
        	portfolioServiceImpl.calculatePortfolio(arguments);
        }
    	assertNotNull(commands.getCommands().length);
    }
	@Test
    public void setDataTest(){
		String[] args = new String[1];
		args[Constants.ZERO] = "/Users/srao/Projects/Java/GeekTrust-PortFolioOverLap-main/src/main/java/resources/commands.txt";
		try {
			Geektrust.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	assertNotNull(args);
    }
	@Test
    public void setDataTest2(){
		String[] args = new String[1];
		args[Constants.ZERO] = "/Users/srao/Projects/Java/GeekTrust-PortFolioOverLap-main/src/main/java/resources/commands2.txt";
		try {
			Geektrust.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	assertNotNull(args);
    }
	@Test
    public void setDataTest3(){
		String[] args = new String[1];
		args[Constants.ZERO] = "/Users/srao/Projects/Java/GeekTrust-PortFolioOverLap-main/src/main/java/resources/commands3.txt";
		try {
			Geektrust.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	assertNotNull(args);
    }
	@Test
    public void setDataTest4(){
		String[] args = null;
		try {
			Geektrust.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	assertNull(args);
    }
    
    @Test
    public void getCommandsTest(){
    	String[] commandList = new String[19];
    	commandList = commands.getCommands();
    	assertNotNull(commandList);
    }
    
    @Test
    public void executeCommandTest(){
    	String[] commandList = {"ICICI_PRU_NIFTY_NEXT_50_INDEX", "AXIS_BLUECHIP", "AXIS_MIDCAP"};
    	portfolioServiceImpl.executeCommand(Constants.CURRENT_PORTFOLIO, commandList);
    	assertNotNull(commandList);
    }
    
    @Test
    public void setCommandCountTest(){
    	commands.setCommandCount(3);
    	assertEquals(19,commands.getCommands().length);
    }
    
    @Test
    public void getCurrentPortfolioTest(){
    	List<String> stocks = portfolio.getCurrentPortfolio();
    	assertNotNull(stocks);
    }
    
    
	@Test
    public void compareCommonStocksTest(){
    	String fundName = "ICICI_PRU_BLUECHIP";
		
    	List<String> portfolioStocks = new ArrayList<String>();
		portfolioStocks.add("ICICI_PRU_NIFTY_NEXT_50_INDEX");
		portfolioStocks.add("AXIS_BLUECHIP");
		portfolioStocks.add("AXIS_MIDCAP");
    	stocksModel.commonStocksCount(portfolioStocks,fundName);
    	assertNotNull(portfolioStocks);
    }
    
}
