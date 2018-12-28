import java.util.Scanner;
import database.InMemoryDatabase;
import utils.command;

public class main {

	public static void main(String[] args) {

		// scanner used to accept user input
		Scanner scanner = new Scanner(System.in);
		InMemoryDatabase inMemDatabase = new InMemoryDatabase();

		while (true) { 
			System.out.print(" >> : ");
			String input = scanner.nextLine();

			switch (input.toString().split(" ")[0]) { // Switch statement to handle different cases 
			case command.SET :  
				inMemDatabase.setData(input.toString());
				continue; 

			case command.GET :  
				if (inMemDatabase.getData(input) == null) {
					continue;		
				} else {
					System.out.println(inMemDatabase.getData(input));
					continue; 
				}
			case command.DELETE :  
				inMemDatabase.deleteData(input);
				continue;
			case command.COUNT:  
				inMemDatabase.countData(input);
				continue;
			case command.BEGIN:  
				inMemDatabase.beginTransactions(input);
				continue;
			case command.ROLLBACK:  
				inMemDatabase.rollBackTransactions(input);
				continue;
			case command.COMMIT : 
				inMemDatabase.commitData(input);
				continue;
			case command.END:  
				System.out.println(" >> : " + "Exiting!");
				break;
			default: 
				System.out.println("Please enter a valid command!");
				continue;
			}
			break;
		}
		scanner.close();
	}
}

