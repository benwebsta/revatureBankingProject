package com.revature.bankingproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {

	int customerId;// customerId for uniqueness
	String userName, passWord;

	/**
	 * 
	 * @param customerId
	 *            the input customer id
	 * @param username
	 *            the user namer of the account to be created
	 * @param password
	 *            the password for the account
	 * @return boolean whether it created account successfully
	 */
	public String signUpForServices(String username, String password) {
		String success;// whether the sign up was successful

		// assign instance variables to the input from main
		this.userName = username;
		this.customerId = username.hashCode();
		this.passWord = password;

		// open reader and writer for data file
		try (BufferedReader br = new BufferedReader(new FileReader(
				"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\BankingProject\\src\\com\\revature\\bankingproject\\Data.txt"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"C:\\Users\\Ben\\Documents\\workspace-sts-3.8.3.RELEASE\\BankingProject\\src\\com\\revature\\bankingproject\\Data.txt",
						true))) {
			String line;
			try {

				// loop through every line in the file to check for username
				while ((line = br.readLine()) != null) {

					int location = 0;
					int colonCount = 0;// switch variable to see how many colon you've seen
					String currentUserName;
					String dataType = "";

					// loop through every character in the line, check for username match
					for (int i = 0; i < line.length(); i++) {

						// when we find a colon or end of line, there is a word
						if (line.charAt(i) == ':' || i == line.length() - 1) {
							colonCount++;// increment colon every time we find a word

							// get the type of data on that line
							if (colonCount == 1) {
								dataType = line.substring(location, i);
							}

							// only check customer data types
							if (dataType.equals("customer") && colonCount == 3) {
								currentUserName = line.substring(location, i);
								// if username is in file, throw custom exception
								if (username.equals(currentUserName)) {
									throw new UserExistsException("This username is already in use.");
								}
							}
							location = i + 1;
						}
					}
				}
				
				//add customer account to data with id, username, and password
				bw.write("customer:" + this.customerId + ":" + username + ":" + password);
				bw.newLine();
				success = "Account created successfully!";
			} catch (UserExistsException e) {
				success = e.getMessage();
			}
		} catch (IOException e) {
			success = "Unable to create account.";
			System.out.println("IO Exception");
		} catch (Exception e) {
			success = "Unable to create account.";
			System.out.println("General Exception");
		}

		return success;
	}

}
