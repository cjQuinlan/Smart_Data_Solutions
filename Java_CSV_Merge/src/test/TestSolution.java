package test;

import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

public class TestSolution {

	private static final String CSV_1_PATH = "resources/1.csv";
	private static final String CSV_2_PATH = "resources/2.csv";
	private static final String OUTPUT_CSV_PATH = "resources/my_output.csv";

	public static void main( String[] args ) {

		try {
			File csv1 = new File( CSV_1_PATH );
			File csv2 = new File( CSV_2_PATH );
			FileWriter output = new FileWriter( OUTPUT_CSV_PATH );
			TestSolution solution = new TestSolution( );

			// First we check that the files are sorted with unique numbers in the first column since this algorithim assumes that they are sorted.
			System.out.println("Validating Input Files...");
			solution.checkFileSortedWithUniqueKeys(csv1);
			solution.checkFileSortedWithUniqueKeys(csv2);

			// Then this algorithim is of order n1 + n2 time complexity and order 1 space complexity.
			System.out.println("Merging Files...");
			solution.combineFiles( csv1, csv2, output );

			// Then validate the output
			System.out.println("Validating Output Files...");
			File checkOutput = new File( OUTPUT_CSV_PATH );
			solution.checkFileSortedWithUniqueKeys(checkOutput);

			System.out.println("Done.");

		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}

	public void combineFiles( File csv1, File csv2, FileWriter output ) throws Exception {
		// Initialize Values
        Scanner sc1 = new Scanner(csv1);
		Scanner sc2 = new Scanner(csv2);
		KeyValueFromCSV CSV1 = new KeyValueFromCSV(sc1.nextLine());
		KeyValueFromCSV CSV2 = new KeyValueFromCSV(sc2.nextLine());
		
		// Loop Through Both Documents
		boolean notDone = true;
		while (notDone) {
			// First check if they are equal
			if (CSV1.key == CSV2.key){
				output.write(CSV1.key + "," + CSV1.value + "," + CSV2.value + '\n');
				// Print and advance both lines
				if (sc1.hasNextLine()) CSV1 = new KeyValueFromCSV(sc1.nextLine());
				if (sc2.hasNextLine()) CSV2 = new KeyValueFromCSV(sc2.nextLine());
			}
			// Then check if we advance to the next line of the second document
			else if(CSV1.key > CSV2.key){
				if (sc2.hasNextLine()) CSV2 = new KeyValueFromCSV(sc2.nextLine());
			}
			// Else we advance the line of the first document
			else{
				if (sc1.hasNextLine()) CSV1 = new KeyValueFromCSV(sc1.nextLine());
			}
			notDone = sc1.hasNextLine()||sc2.hasNextLine();
		}

		// Close Resources
		sc1.close();
		sc2.close();
		output.close();
	}

	private void checkFileSortedWithUniqueKeys( File csv) throws Exception{
		int count = 0;
		Scanner sc = new Scanner(csv);
		boolean output = true;
		// File must have at least one line
		if(!sc.hasNextLine()){sc.close(); throw new Exception(csv.toPath().toString() + " does not have any content.");}
		int lastKey = Integer.parseInt(sc.nextLine().split(",",2)[0]);
		int nextKey;
		while(sc.hasNextLine()){
			nextKey = Integer.parseInt(sc.nextLine().split(",",2)[0]);
			count++;
			// Next key must be strictly greater than last key
			if (nextKey <= lastKey){
				output = false;
				break;
			}
			else{
				lastKey = nextKey;
			}
		}
		sc.close();
		// File must be sorted from least to greatest and have unique keys
		if(output == false){throw new Exception(csv.toPath().toString() + " is not sorted with unique keys. See key number: " + lastKey);}
		System.out.println(csv.toPath().toString() + "Line Count: " + count);
	}
	
	private class KeyValueFromCSV{
		int key;
		String value;
		KeyValueFromCSV(String Line){
			String[] SplitLine = Line.split(",",2);
			key = Integer.parseInt(SplitLine[0]);
			value = SplitLine[1];
		}
	}


}