package com.revature.bankingproject;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Menu for banking program
		System.out.println("Enter a number option");
		System.out.println("----------------------");
		System.out.println("1: Customer Sign up");
		System.out.println("2: Customer Login");
		System.out.println("3: Employee Login");
		System.out.println("4: Admin Login");

		//read in option selected for menu
		try(Scanner sc = new Scanner(System.in);) {
			int response = sc.nextInt();
			String username, password;

			switch (response) {
			case 1://take in customer account info and try to create account
				System.out.println("Customer Sign Up Page");
				System.out.println("-----------------------");
				System.out.println("Enter username:");
				username = sc.next();
				System.out.println("Enter password:");
				password = sc.next();
				Customer c1 = new Customer();
				String result = c1.signUpForServices(username, password);
				System.out.println(result);
				if(!(result.equals("Account created successfully!"))){
					main(null);
				}
				break;
			case 2:
				System.out.println("Customer Login");
				break;
			case 3:
				System.out.println("Employee Login");
				break;
			case 4:
				System.out.println("Admin Login");
				break;
			default:
				System.out.println("You didn't enter a correct number option");
				break;
			}
		}
		catch(Exception e){
			System.out.println("General Exception");
		}
	}
}
