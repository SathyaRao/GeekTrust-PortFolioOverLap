package com.example.overlappingportfolio;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import com.example.overlappingportfolio.service.PortfolioServiceImpl;

public class Geektrust 
{
	public static void main( String[] args ) throws Exception
    {
		Geektrust.setData(args);
		
    }
	public static void setData(String[] args) throws Exception {
		String filePath = "https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json";
		PortfolioServiceImpl serviceImpl = new PortfolioServiceImpl();
		String commandFile = args[0];
		FileReader file = new FileReader(commandFile);
		serviceImpl.setCommands(file);
        serviceImpl.setStocks(filePath);
        String[] arguments = serviceImpl.getCommands();
        if (arguments.length > 0)
        {
        	serviceImpl.calculatePortfolio(arguments);
        }
	}
}
