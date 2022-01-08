package com.example.overlappingportfolio.model;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.example.overlappingportfolio.constants.Constants;

public class Commands {
    private Set<String> commands = new HashSet<String>();
	private int commandCount = Constants.ZERO;
	private String[] arguments = new String[19];

    public Commands(){
        commands.add(Constants.CALCULATE_OVERLAP);
        commands.add(Constants.ADD_STOCK);
        commands.add(Constants.CURRENT_PORTFOLIO);
    }

    public Set<String> getCommandsList(){
        return this.commands;
    }
    public String[] getCommands(String[] arguments,int index){
		String[] list = new String[arguments.length];
		int k = Constants.ZERO;
		for (int j = index+Constants.ONE;j<arguments.length;j++){
    		if(arguments[j] == null){
        		break;
        	}
			if(!commands.contains(arguments[j])){
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
	
	public void setCommandCount(int num){
		this.commandCount = num;
	}
    public int getCommandCount(){
		return this.commandCount;
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
