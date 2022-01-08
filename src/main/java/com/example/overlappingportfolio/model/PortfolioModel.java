package com.example.overlappingportfolio.model;

import java.util.ArrayList;
import java.util.List;
import com.example.overlappingportfolio.constants.Constants;

public class PortfolioModel {
	private List<String> funds = new ArrayList<String>();
	Commands commands = new Commands();

	public List<String> getCurrentPortfolio(){
		return funds;
	}
	
	public void setCurrentPortfolio(String[] fundNames) {
		for(int i = Constants.ZERO; i< fundNames.length;i++){
			if(fundNames[i] != null){
				funds.add(fundNames[i]);
			}
		}		
	}
	
}
