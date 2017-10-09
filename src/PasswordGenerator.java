/*
 * @author: Created by Dat Dang as a side project
 * Project: Password Generator 
 * Goal: Create a good password generator so you don't have to do make one yourself 
 * 		
 * Completed:  	Creating a random password generator with random words
 * 			   	Allow user to add number at the end of the password 
 * 				Output all the resulting passwords to a file
 *  			Includes prompt for user to try again instead of force quitting 
 * 			   	
 * Current Goal: Find a way to create readable password 
 * 				 Create password from a list of given words 
 * 				 Include special characters 
 * 				 Numbers need to be random??
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class PasswordGenerator {
	//Establishing global variables 

	//Length of the password 
	private static int pwLength;
	//Numbers to be added, default is zero if user answers no  
	private static int passNumber = 0; 
	//User's response to adding numbers to their password 
	private static char userAnswer; 
	//Flag for user's response to adding numbers to their password
	private static boolean wantNumbers = false; 
	//How many passwords need to be generated 
	private static int numElement;
	private static char retryAgain; 



	/*
	 * This method is used to print out (soon to be) a list of passwords
	 */
	public static void printLPass(char[] printArray)
	{
		//prints the word
		for (int i = 0; i < pwLength + passNumber; i++)
			System.out.print(printArray[i]);
	}


	/*
	 * This method is used to assign a random password 
	 */
	public static void assignPass(char[] result)
	{
		Random wordGenerator = new Random();
		for(int i = 0; i < pwLength; i++) 
		{
			//ASCII starts at 65 or capital A 
			int randomWord = wordGenerator.nextInt(58) + 65; 

			if(randomWord >= 91 && randomWord <= 96)
			{
				//First case of special character becomes capital A
				randomWord = randomWord % 90 + 64;
			}
			//System.out.println((char)randomWord);

			result[i] = (char)randomWord;
		}

	}


	/*
	 * This method is used to assign a bunch of random numbers 
	 * after a password
	 */
	public static void assignNumber(char[] result)
	{
		Random numberGenerator = new Random(); 

		for(int i = result.length - passNumber; i < result.length; i++)
		{
			int randomNumber = numberGenerator.nextInt(10);
			result[i] = Integer.toString(randomNumber).charAt(0);
		}
	}


	/*
	 * This method prints the list of passwords in the console to a file
	 */
	public static <E> void outputFile(List<E> listOfWords)
	{
		String fileName = "output.txt";
		try {
			PrintWriter outputStream = new PrintWriter(fileName);

			outputStream.println("List of Generated Password:");
			for(int i = 0; i < listOfWords.size(); i++)
			{
				outputStream.println(listOfWords.get(i));
			}

			//Closed the stream so the saved data can be flushed 
			outputStream.close();

			//Prints out the finished message
			System.out.println("\nCopying list to " + "\""+fileName+"\"" + " completed");
		} catch (FileNotFoundException e) {

			System.out.println("Process did not complete. Please try again!");
			e.printStackTrace();
		}

	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		//Creates a scanner to read user's answer 
		Scanner reader = new Scanner(System.in); 

		//Prints out the intro
		System.out.println("********* DAT'S USELESS PASSWORD GENERATOR *********");
		System.out.println("* @author: Created by Dat Dang as a side project   *");
		System.out.println("* @project: Password Generator                     *");
		System.out.println("* @version: 0.4                                    *");
		System.out.println("* Goal: Create a good/trustworthy password 	   *");
		System.out.println("* 	generator that I can can rely on           *");
		System.out.println("* Feedback: Any kind of feedback is appreciated if *");
		System.out.println("*           it means I have more opportunity to    *");
		System.out.println("*           improve my program.                    *");
		System.out.println("****************************************************");
		System.out.println("");

		System.out.println(" -------------------------------------------------- ");
		System.out.println("| Completed:  1. Creating a random password 	   |");
		System.out.println("|                generator with random words       |");
		System.out.println("|             2. Allow user to add number at       |");
		System.out.println("|                the end of the password           |");
		System.out.println("|             3. Output all the resulting passwords|");
		System.out.println("|                to a file                         |");
		System.out.println("|             4. Includes prompt for user to try   |");
		System.out.println("|                again instead of force quitting   |");
		System.out.println("|                                                  |");
		System.out.println("| Current Goals: Find a way to create readable     |");
		System.out.println("|                password                          |");
		System.out.println("|                Create passwords from a list of   |");
		System.out.println("|                given words                       |");
		System.out.println("|                Include special characters        |");
		System.out.println("|                Allow user to name the output     |");
		System.out.println("|                file                              |");
		System.out.println("|                Clean up code to improve          |");
		System.out.println("|                efficiency                        |");
		System.out.println(" -------------------------------------------------- ");


		do{
			//Ask question 
			System.out.print("\n\nHow long do you want your password to be?(input number here): ");

			//Save user's input 
			pwLength = reader.nextInt(); 

			//Ask the user for to include number in the passwords *FIX THIS IN THE FUTURE BY REMOVING THE DO METHOD*
			do{
				System.out.print("\nDo you want to have number(s) behind your password?(y/n): ");

				userAnswer = reader.next().charAt(0);

				if( userAnswer == 'y')
				{
					System.out.print("\nHow many number(s)?: ");
					passNumber = reader.nextInt();
					wantNumbers = true;
					break; 
				}

				//request user to try again if their answer is neither 'n' nor 'y' 
				else if(userAnswer != 'n' && userAnswer != 'y' ){
					System.out.println("You did not type in 'y' or 'n'. Please try again!");
				}


			}while(userAnswer != 'n');

			//How many passwords needed
			System.out.print("\nHow many passwords need to be generated?: ");
			numElement = reader.nextInt(); 

			//Create a character array to generate word 
			char[] result = new char[pwLength + passNumber];

			//Checking the length of the array
			//System.out.println(result.length);

			List<String> passwordList = new ArrayList<String>(); 

			//Prints out title 
			System.out.println("\nList of Generated Passwords:");

			for(int i = 0; i < numElement; i++){

				//Pass-by-reference(pass the value's address to another class instead 
				//of a copy) 
				assignPass(result);

				//Add number if user requested 
				if(wantNumbers == true)
					assignNumber(result);

				//Covert a whole char array into a single word 
				String theWord = String.valueOf(result);

				//Add the word to the list 
				passwordList.add(theWord);

				//prints the word
				printLPass(result);

				//Space between each generated password 
				System.out.println("");
			}

			//Prompts user to output the list of passwords 
			System.out.print("\nDo you want to save the result to a file?(y/n): ");
			if(reader.next().charAt(0) == 'y')
			{
				outputFile(passwordList);
				System.out.println("\nThank you for using this program.");
			}

			else {
				System.out.println("\nList was not saved");
			}

			System.out.print("\nDo you want to generate another set of password?(y/n): ");
			retryAgain = reader.next().charAt(0); 

		}while(retryAgain != 'n');
		
		System.out.println("THANK YOU FOR USING THIS PROGRAM!");
		
		System.out.println("_______________________________________________");
		System.out.println("Please hit ENTER to exit.");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
