package database;

import java.util.HashMap;
import java.util.TreeMap;

public class InMemoryDatabase {

	private HashMap<String, String> map = new HashMap<String, String>();// HashMap used to store Key value pairs or user data
	private HashMap<String, Integer> valueCountMap = new HashMap<String, Integer>(); // HashMap used to keep value counts 
	private TreeMap<String, String> transactionMap = new TreeMap<String, String>(); // TreeMap used to keep track of transaction data 

	private boolean beginTransactionsFlag = false;  // begin Transaction flag


	/**
	 * Method used to save user data as a key value pair 
	 * @param userData
	 */

	public void setData(String userData) {

		try {
			String[] tempData = userData.split(" ");
			if(tempData.length !=3 ) {
				System.out.println("Please provide a Name and Value!");
				return;
			} else {

				if(!map.isEmpty() && (map.get(tempData[1]) != tempData[2]) ) {  // Dec cont 
					decrementValueCount(map.get(tempData[1])); // dec count since the value has been switched 
					addCountValue(tempData[2]); // inc vacle on the new value
				} else {
					addCountValue(tempData[2]);
				}
				if(beginTransactionsFlag == true) {   // check for Transaction input flag 
					transactionMap.put(tempData[1], map.get(tempData[1])); // copy data from main table
					this.beginTransactionsFlag = false;
				}
				map.put(tempData[1], tempData[2]);
			}

		} catch (Exception e) {
			System.out.println(e); // log exception 

		}

	}

	/**
	 * Method used to process Transactions rollBack process 
	 * 
	 */

	public void rollBackTransactions( String userData) {
		try {
			String[] tempData = userData.split(" ");
			if(tempData.length != 1) {
				System.out.println("Please enter a valid command!");
				return;
			} else {

				if(!transactionMap.isEmpty()) {
					addCountValue(transactionMap.get(transactionMap.lastKey()));
					map.put(transactionMap.lastKey(), transactionMap.get(transactionMap.lastKey())); // copy data back to the man table
					transactionMap.put(transactionMap.lastKey(), null);
				}
			}

		} catch (Exception e) {
			System.out.println(e); // log exception 
		}

	}

	/**
	 * Method used to process data commit
	 */

	public void commitData(String userData) {
		try {
			String[] tempData = userData.split(" ");
			if(tempData.length > 1) {
				System.out.println("Please enter a valid command!");
				return;
			} else {
				this.beginTransactionsFlag = false;
				this.transactionMap.clear();
			}
		}catch (Exception e) {
			System.out.println(e); // log exception 
		}

	}

	/**
	 * Method used to get data given a key via input 
	 * @param userData
	 * @return null or value 
	 */

	public String getData(String userData) {
		String[] tempData = userData.split(" ");
		if(tempData.length != 2) {
			System.out.println("Please provide a Name/key!");
			return null;
		} else {
			if(map.get(tempData[1]) == null) {
				return "NULL";
			} else {
				return map.get(tempData[1]);
			}
		}
	}

	/**
	 * Method used delete data from database
	 * @param userData ( key/name)
	 */
	public void deleteData(String userData) { 
		try {
			String[] tempData = userData.split(" ");

			if(tempData.length != 2) {
				System.out.println("Please provide a Name!");
				return;
			} else {
				transactionMap.put(tempData[1], map.get(tempData[1])); 
				decrementValueCount(map.get((tempData)[1]));
				map.remove((tempData)[1]);
			}
		} catch (Exception e) {
			System.out.println(e); // log exception 
		}
		
	}

	/**
	 * Method used to count values inside  map/db
	 * @param userData (input values) 
	 */
	public void countData(String userData) {

		try {
			String[] tempData = userData.split(" ");

			if(tempData.length < 2) {
				System.out.println("Please provide a Value!");
				return;
			} else {
				if (valueCountMap.get(tempData[1]) == null) {
					System.out.println(0);

				} else {
					System.out.println(valueCountMap.get(tempData[1]));
				}			
			}
		} catch (Exception e) {
			System.out.println(e); // log exception 
		}

	}

	/**
	 * Method used to add count for a specific user data values 
	 * @param value ( input value)
	 */

	private void addCountValue(String key) {
		try {
			if(valueCountMap.isEmpty()) {
				valueCountMap.put(key, 1);
			}
			else {
				if(valueCountMap.containsKey(key)) {
					valueCountMap.put(key, valueCountMap.get(key) + 1);
				} else {
					valueCountMap.put(key, 1);
				}
			}
		} catch (Exception e) {
			System.out.println(e); // log exception 
		}

	}

	/**
	 * Method used to decrement count for a specific user data value
	 * @param key ( input value)
	 */

	private void decrementValueCount(String key) {
		try {
			if(valueCountMap.containsKey(key) && !valueCountMap.isEmpty()) {
				valueCountMap.put(key, valueCountMap.get(key) - 1);
			} else {
				return;
			}
		} catch (Exception e) {
			System.out.println(e); // log exception 
		}

	}

	/**
	 * Method used to process begin transaction command and set flag.
	 */

	public void beginTransactions(String userData) {
		try {
			String[] tempData = userData.split(" ");
			if(tempData.length != 1) {
				System.out.println("Please enter a valid command!");
				return;
			} else {
				this.beginTransactionsFlag = true;
			}
		} catch (Exception e) {
			System.out.println(e); // log exception 
		}

	}

}
