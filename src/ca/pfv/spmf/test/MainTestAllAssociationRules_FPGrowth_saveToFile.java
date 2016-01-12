package ca.pfv.spmf.test;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.io.FileReader;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;
/**
 * Example of how to mine all association rules with FPGROWTH and save
 * the result to a file, from the source code.
 * 
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class MainTestAllAssociationRules_FPGrowth_saveToFile {

	public static void main(String [] arg) throws IOException{
		//String input = fileToPath("./foo.txt");
		String input = "data/test.txt";
		String output = "data/output.txt";
//		String output = "C:\\patterns\\association_rules.txt";
	
		// STEP 1: Applying the FP-GROWTH algorithm to find frequent itemsets
		double minsupp = 0.0;
		AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
		Itemsets patterns = fpgrowth.runAlgorithm(input, null, minsupp);
//		patterns.printItemsets(database.size());
		fpgrowth.printStats();
		int databaseSize = fpgrowth.getDatabaseSize();
		
		// STEP 2: Generating all rules from the set of frequent itemsets (based on Agrawal & Srikant, 94)
		double  minconf = 0.00;
		AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
		algoAgrawal.runAlgorithm(patterns, output, databaseSize, minconf);
		algoAgrawal.printStats();
	}
	
	public static void Load_CSV_data(String filename) throws IOException{
		String fileName = "/home/shiqing/java_workspace/spmf_simple/dx_train.csv";
		CSVReader reader = new CSVReader(new FileReader(fileName ));
		
		// if the first line is the header
		String[] header = reader.readNext();
		System.out.println(header[0]);
		// iterate over reader.readNext until it returns null
		String[] line = reader.readNext();
		System.out.println(line[0]);
		
	}
	
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestAllAssociationRules_FPGrowth_saveToFile.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
