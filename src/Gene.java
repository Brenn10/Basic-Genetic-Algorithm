import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Gene {
	private ArrayList<Boolean> gene;
	private Random rand;
	
	/**
	 * Default constructor
	 */
	public Gene()
	{
		gene=new ArrayList<Boolean>();
	}
	
	/**
	 * Initializes the gene with values
	 * @param length number of boolean alleles
	 * @param random whether or not to initialize with random values(true is random)
	 */
	public Gene(int length,boolean random)
	{
		gene=new ArrayList<Boolean>();
		rand= new Random();
		for(int i = 0 ; i < length; ++i)
		{
			if(random)
				gene.add(rand.nextBoolean());
			else
				gene.add(false);
		}
	}
	
	/**
	 * Finds the fitness of this gene
	 * @return Fitness, higher is better
	 */
	public int fitness()
	{
		int sum = 0;
		for(Boolean b : gene)
		{
			if(b)
				++sum;
		}
		return sum;
	}
	
	/**
	 * iterable of the gene, allows iteration through it
	 * @return The Gene as a chromosome
	 */
	public Iterable<Boolean> iterable()
	{
		return this.getArrayList();
	}
	
	/**
	 * Add data to the end of the gene
	 * @param data data to be added
	 * @return if the add was successful
	 */
	public boolean add(Boolean data)
	{
		return gene.add(data);
	}
	
	/**
	 * Put data at index, replacing current data
	 * @param data data to add
	 * @param index where to add the data and remove the old data
	 * @return data previously at the index
	 */
	public Boolean replace(Boolean data, int index)
	{
		return gene.set(index, data);
	}
	
	/**
	 * Gets the data at an index
	 * @param index index to get data from
	 * @return data previously at 
	 */
	public Boolean get(int index)
	{
		return gene.get(index);
	}
	
	/**
	 * Gives the size of the gene
	 * @return size of the gene
	 */
	public int size()
	{
		return gene.size();
	}
	
	/**
	 * Returns the arraylist of alleles
	 * @return arraylist of the alleles
	 */
	public ArrayList<Boolean> getArrayList()
	{
		ArrayList<Boolean> tmp = new ArrayList<Boolean>();
		Collections.copy(tmp,gene);
		return tmp;
	}
	
	/**
	 * String representation of the Gene
	 * @return String representation of the gene
	 */
	public String toString()
	{
		String ret = "{ ";
		for(Boolean b : gene)
		{
			if(b)
				ret+="1 ";
			else
				ret+="0 ";
		}
		return ret+"}";
	}
}
