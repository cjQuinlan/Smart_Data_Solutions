package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestSolution {

	private static final String CSV_1_PATH = "resources/1.csv";
	private static final String CSV_2_PATH = "resources/2.csv";
	private static final String OUTPUT_CSV_PATH = "resources/my_output.csv";

	public void combineFiles( File csv1, File csv2, File outputFile ) throws Exception {
		Integer Count = 0;
		Integer Limit = 10;

        Scanner sc1 = new Scanner(new File(CSV_1_PATH));
		Scanner sc2 = new Scanner(new File(CSV_2_PATH));

		String Line1 = sc1.nextLine();
		String[] SplitLine1 = Line1.split(",",2);
		Integer Key1 = Integer.parseInt(SplitLine1[0]);
		String Line2 = sc2.nextLine();
		String[] SplitLine2 = Line2.split(",",2);
		Integer Key2 = Integer.parseInt(SplitLine2[0]);

		/*System.out.println("Initial Values 1:");
		System.out.println(Line1);
		System.out.println(Key1);

		System.out.println("Initial Values 2:");
		System.out.println(Line2);
		System.out.println(Key2);*/

		while (Count++ < Limit) {
			// First check if they are equal
			if (Key1 == Key2){
				System.out.println(Line1 + "," + SplitLine2[1]);
				// Print and advance both lines
				Line1 = sc1.nextLine();
				SplitLine1 = Line1.split(",",2);
				Key1 = Integer.parseInt(SplitLine1[0]);
				Line2 = sc2.nextLine();
				SplitLine2 = Line2.split(",",2);
				Key2 = Integer.parseInt(SplitLine2[0]);
			}
			// Then check if we advance to the next line of the second document
			else if(Key1 > Key2){
				Line2 = sc2.nextLine();
				SplitLine2 = Line2.split(",",2);
				Key2 = Integer.parseInt(SplitLine2[0]);
			}
			// Else we advance the line of the first document
			else{
				Line1 = sc1.nextLine();
				SplitLine1 = Line1.split(",",2);
				Key1 = Integer.parseInt(SplitLine1[0]);
			}
		}
	}


	public static void main( String[] args ) {

		try {

			File csv1 = new File( CSV_1_PATH );
			File csv2 = new File( CSV_2_PATH );
			File output = new File( OUTPUT_CSV_PATH );

			TestSolution solution = new TestSolution( );
			solution.combineFiles( csv1, csv2, output );

		} catch ( Exception e ) {
            System.out.println("Hello, World! there was an error");
			e.printStackTrace( );
		}
	}
}