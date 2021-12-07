package com.example.overlappingportfolio;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.example.overlappingportfolio.constants.Constants;
import com.example.overlappingportfolio.service.PortfolioServiceImpl;

//import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    /*public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    /*public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    /*public void testApp()
    {
        assertTrue( true );
    }*/
    
    PortfolioServiceImpl portfolioServiceImpl = new PortfolioServiceImpl();
	//Water water = new Water();
    
	
	@Test
    public void setStocksTest(){
		String CURRENT_PORTFOLIO = Constants.CURRENT_PORTFOLIO;
		String CALCULATE_OVERLAP = Constants.CALCULATE_OVERLAP;
		String ADD_STOCK =  Constants.ADD_STOCK;
		int ZERO =  Constants.ZERO;
		int ONE =  Constants.ONE;
		String filePath = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";
    	portfolioServiceImpl.setStocks(filePath);
 
    	assertNotNull(portfolioServiceImpl.stocks);
    }
	@Test
    public void calculatePortfolioTest(){
		String commandFile = "D:\\Java\\geektrust\\src\\main\\java\\resources\\commands";
		FileReader file;
		try {
			file = new FileReader(commandFile);
			portfolioServiceImpl.setCommands(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arguments = portfolioServiceImpl.getCommands();
        if (arguments.length > 0)
        {
        	portfolioServiceImpl.calculatePortfolio(arguments);
        }
    	assertNotNull(portfolioServiceImpl.commandCount);
    }
	@Test
    public void calculatePortfolioTest2(){
		String commandFile = "D:\\Java\\geektrust\\src\\main\\java\\resources\\commands2";
		FileReader file;
		try {
			file = new FileReader(commandFile);
			portfolioServiceImpl.setCommands(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arguments = portfolioServiceImpl.getCommands();
        if (arguments.length > 0)
        {
        	portfolioServiceImpl.calculatePortfolio(arguments);
        }
    	assertNotNull(portfolioServiceImpl.commandCount);
    }
	@Test
    public void calculatePortfolioTest3(){
		String commandFile = "D:\\Java\\geektrust\\src\\main\\java\\resources\\commands3";
		FileReader file;
		try {
			file = new FileReader(commandFile);
			portfolioServiceImpl.setCommands(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arguments = portfolioServiceImpl.getCommands();
        if (arguments.length > 0)
        {
        	portfolioServiceImpl.calculatePortfolio(arguments);
        }
    	assertNotNull(portfolioServiceImpl.commandCount);
    }
	@Test
    public void setDataTest(){
		String[] args = new String[1];
		args[0] = "D:\\Java\\geektrust\\src\\main\\java\\resources\\commands3";
		try {
			Geektrust.setData(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	assertNotNull(args);
    }
    
    @Test
    public void getCommandsTest(){
    	String[] commandList = new String[19];
    	commandList = portfolioServiceImpl.getCommands();
    	assertNotNull(commandList);
    }
    
    @Test
    public void executeCommandTest(){
    	String[] commandList = {"ICICI_PRU_NIFTY_NEXT_50_INDEX", "AXIS_BLUECHIP", "AXIS_MIDCAP"};
    	portfolioServiceImpl.executeCommand("CURRENT_PORTFOLIO", commandList);
    	assertNotNull(commandList);
    }
    
    @Test
    public void setCommandCountTest(){
    	portfolioServiceImpl.setCommandCount(3);
    	assertEquals(3,portfolioServiceImpl.commandCount);
    }
    
    @Test
    public void getCurrentPortfolioTest(){
    	List<String> stocks = portfolioServiceImpl.getCurrentPortfolio();
    	assertNotNull(stocks);
    }
    
    
	@Test
    public void compareCommonStocksTest(){
    	JSONArray fundName = new JSONArray();
    	JSONArray portfolioStocks = new JSONArray();
    	float ratio = portfolioServiceImpl.compareCommonStocks(fundName,portfolioStocks);
    	assertNotNull(ratio);
    }
    
    /*@Test
    public void getCommandsTest(){
    	portfolioServiceImpl.allotWater("2","3:7");
    	assertEquals(900,water.getAllottedWater());
    }
    
    @Test
    public void findTotalBillTest(){
    	String[] commands = {"ALLOT_WATER", "3", "5:4", "ADD_GUESTS", "3", "ADD_GUESTS", "5", "BILL"};
    	portfolioServiceImpl.findTotalBill(commands);
    	assertEquals(1500,water.getAllottedWater());
    }*/
}
