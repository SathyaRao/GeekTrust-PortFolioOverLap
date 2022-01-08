package com.example.overlappingportfolio;
import java.io.*;

import com.example.overlappingportfolio.constants.Constants;
import com.example.overlappingportfolio.model.Commands;
import com.example.overlappingportfolio.model.Stocks;
import com.example.overlappingportfolio.service.PortfolioServiceImpl;

public class Geektrust 
{
	public static void main( String[] args ) throws Exception
    {
		Geektrust.setData(args);
		
    }
	public static void setData(String[] args) throws Exception {
		String filePath = Constants.DATA_URL;
		PortfolioServiceImpl serviceImpl = new PortfolioServiceImpl();
		String commandFile = args[Constants.ZERO];
		FileReader file = new FileReader(commandFile);
		Commands commands = new Commands();
		Stocks stocks = new Stocks();
		commands.setCommands(file);
        stocks.setStocks(filePath);
        String[] arguments = commands.getCommands();
        if (arguments.length > Constants.ZERO)
        {
        	serviceImpl.calculatePortfolio(arguments);
        }
	}
}
