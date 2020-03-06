/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lockme.application;

/**
 *
 * @author bhavana
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.lockme.model.UserCreds;
import com.lockme.model.Users;

public class AuthClass {
	//Input data
	private static Scanner Keyboard;		//console
	private static Scanner input;			//file
	private static Scanner storageinput;
	
	//Output data
	private static PrintWriter output;
	private static PrintWriter storageOutput;
	
	//model to store data
	private static Users users;
	private static UserCreds userCredentials;
	
	public static void initApp() {
	
		try {
			
		File databaseFile= new File("database.txt"); 		// This File is the pointer to read the file
		File storageFile= new File("storage.txt");		    // This File is the pointer to read the file
	
		boolean db =  databaseFile.createNewFile();	
		boolean str = storageFile.createNewFile();
			
			//reading data from database.
			input=new Scanner(databaseFile);
			
			//reading data from storage.
			storageinput=new Scanner(storageFile);
			
			//reading data from keyboard
			Keyboard=new Scanner(System.in);
			
			
			//output
			output =new PrintWriter(new FileWriter(databaseFile, true));
			storageOutput =new PrintWriter(new FileWriter(storageFile, true));
			
			users=new Users();
			userCredentials=new UserCreds();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("404: File not found");
		}
		
	}

	
	public static void main(String[] args) {
		
	initApp();	
	mainScreen();
	signInOptions();
	
		
}
	public static void signInOptions() {
		System.out.println(" Please Type 1 for registration.");
		System.out.println(" Please Type 2 for Login.    	");
		System.out.println("==============================  ");
		System.out.println("1. Registration");
		System.out.println("2. Login");
		int option=Keyboard.nextInt();
		switch(option) {
			case 1:
				registerUser();
				break;
			case 2:
					loginUser();
					break;
			default :
				System.out.println("Please select 1 or 2");
				break;
			
		}
		Keyboard.close();
		input.close();
	}
	
	public static void storageOptions(String inpUserName) {
		System.out.println("1. FETCH ALL STORED CREDENTIALS");
		System.out.println("2. STORED CREDENTIALS");
		int option=Keyboard.nextInt();
		switch(option) {
			case 1:
				fethCreds(inpUserName);
				break;
			case 2:
				storeCreds(inpUserName);
					break;
			default :
				System.out.println("Please select 1 or 2");
				break;
			
		}
		Keyboard.close();
		input.close();
	}
	public static void registerUser() {
		System.out.println("============================================");
		System.out.println("*										   ");
		System.out.println("*	    WELCOME TO OUR REGISTRATION PAGE	 ");
		System.out.println("*										   ");
		System.out.println("============================================");
		System.out.println("Please Enter the User name here :" );
		String username=Keyboard.next();
	
		users.setUsername(username);
		System.out.println("Please Enter your Password here:" );
		String password=Keyboard.next();
		users.setPassword(password);
		output.println(users.getUsername());
		output.println(users.getPassword());
		System.out.println("Hurrah! Your account has been registered successfully.");
		output.close();
		
		
	}
	public static void loginUser() {
		System.out.println("============================================");
		System.out.println("*										   ");
		System.out.println("*	    WELCOME TO OUR LOGIN PAGE		   ");
		System.out.println("*										   ");
		System.out.println("============================================");
		System.out.println("Please Enter the User Name here:" );
		String inputusername=Keyboard.next();
		boolean found=false;
			while(input.hasNext()&& !found)
			{
				if(input.next().equals(inputusername)){
					System.out.println("Please Enter your Password here:");
					String inpassword=Keyboard.next();
					if(input.next().equals(inpassword)) {
						System.out.println("Hurrah! You have been Login successfully!");
						found=true;
						storageOptions(inputusername);
						break;
					}
				}
				
			}
			if(!found) {
				System.out.println("Sorry! You are not an existing user, Please register first.");
			}
			
	}
	//store credentials
	public static void storeCreds(String loggedInUser) {
		System.out.println("============================================");
		System.out.println("*										   ");
		System.out.println("*	    WELCOME TO OUR STORAGE .		   ");
		System.out.println("*		STORE YOUR CREDENTIALS HERE			 ");
		System.out.println("============================================");
		userCredentials.setLoggedInUser(loggedInUser);
		
		System.out.println("Enter The Site Name:" );
		String siteName=Keyboard.next();
		userCredentials.setSiteName(siteName);
		
		System.out.println("Enter The User Name:" );
		String username=Keyboard.next();
		userCredentials.setUsername(username);
		
		System.out.println("Enter The Password:" );
		String password=Keyboard.next();
		userCredentials.setPassword(password);
		
		storageOutput.println(userCredentials.getLoggedInUser());
		storageOutput.println(userCredentials.getSiteName());
		storageOutput.println(userCredentials.getUsername());
		storageOutput.println(userCredentials.getPassword());
		
		System.out.println("WE HAVE STORED YOUR CREDENTIALS SUCCESSFULLY!");
	
		storageOutput.close();
		
	}
	//fetching your credentials
	public static void  fethCreds(String inpUserName) {
		System.out.println("============================================");
		System.out.println("*										   ");
		System.out.println("*	    WELCOME TO DIGITAL STORAGE  	   ");
		System.out.println("*	     YOUR CREDENTIALS ARE	          ");
		System.out.println("*										   ");
		System.out.println("============================================");
		
		while(storageinput.hasNext())
		{
			if(storageinput.next().equals(inpUserName)){
				System.out.println(inpUserName);
				System.out.println("Site Name:"+ storageinput.next());
				System.out.println("User Name:" +storageinput.next());
				System.out.println("Password :" +storageinput.next());
				System.out.println("Thanks for your trust. Good Bye !");
					
				}
			}
		
		
	}
	public static void mainScreen() {
		System.out.println("============================================");
		System.out.println("*										   ");
		System.out.println("*	 Hi, Welcome to LockMe.com			      ");
		System.out.println("*	 This is your Personal Storage.   	  ");
		System.out.println("*	 									   ");
		System.out.println("============================================");
		
	}
	
	
}
