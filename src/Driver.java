import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws IOException {
		boolean toFile=false;
		/*
		 *  sizeOfPop		higher is better
		 *  sizeOfGene		higher shows effectiveness better
		 *  mutationRate	lower allows for higher fitness and 
		 *  tourneySize		20-40% of the pop size is the sweetspot
		 *  randomStart		true if you want this to initialize randomly
		 */
		//Create population
		Population pop = new Population(100, 100, 0.001, 20,false);
		
		//Get initial values
		System.out.println("Highest: " +pop.highestFitness()+ "\tAverage: " + pop.average());
		
		//Open file
		FileWriter writer= new FileWriter(new File("output.csv"));
		
		//Go through n generations
		
		int counter=0;
		int highest=0;
		for(int i = 0; i < 1000; ++i)
		{
			//evolve
			pop.evolve();

			int highestFitness=pop.highestFitness();
			if(highestFitness>highest)
			{
				counter=0;
				highest=highestFitness;
			}
			if(highest==highestFitness)
			{
				counter+=1;
				if(counter==20)
				{
					System.out.println("Generation: " + (i-19));
					break;
				}
			}
			//if outputting to file, output
			if(toFile) writer.append(highestFitness+ "," + pop.average()+"\n");
			//output to console
			System.out.println("Gen "+ i+ "\tHighest: " +highestFitness+ "\tAverage: " + pop.average()+"\tCounter: "+counter);
		}
		writer.close();
	}
}
