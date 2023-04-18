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
		String Line2 = sc2.nextLine();

		String[] SplitLine1 = Line1.split(",",1);
		String[] SplitLine2 = Line2.split(",",1);

		Integer Key1 = Integer.parseInt(SplitLine1[0]);
		Integer Key2 = Integer.parseInt(SplitLine1[0]);

		Integer Value1 = Integer.parseInt(SplitLine1[1]);
		Integer Value2 = Integer.parseInt(SplitLine1[1]);

		System.out.println("Initial Values 1:");
		System.out.println(Line1);
		System.out.println(Key1);
		System.out.println(Value1);

		System.out.println("Initial Values 2:");
		System.out.println(Line2);
		System.out.println(Key2);
		System.out.println(Value2);

		while (Count++ < Limit) {
			System.out.println(Count);
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