package com.example.overlappingportfolio.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import com.example.overlappingportfolio.constants.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

public class Stocks {
	private static JSONObject stocks = new JSONObject();

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
            JSONObject jsonObj = new JSONObject(sb.toString());
            JSONArray data = (JSONArray)jsonObj.get("funds");
            for(int i=Constants.ZERO;i<data.length();i++){
                JSONObject obj = (JSONObject)data.get(i);
                if(obj != null){
                    stocks.put(obj.get("name").toString(),obj.get("stocks"));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }

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

}