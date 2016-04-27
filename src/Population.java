import java.util.ArrayList;
import java.util.Random;

public class Population {
	ArrayList<Gene> population;
	int geneLength;
	int popSize;
	double mutationRate;
	int tourneySize;
	Random rand;
	boolean randomStart;
	int best;
	
	/**
	 * Creates the first generation of the population
	 * @param sizeOfPop number of individuals in the population
	 * @param sizeOfGene number of alleles in each gene
	 * @param mutationRate probability of a point mutation to occur
	 * @param tourneySize number of individuals to compete for mating
	 * @param randomStart whether or not to start with random values or all false
	 */
	public Population(int sizeOfPop, int sizeOfGene,double mutationRate,int tourneySize,boolean randomStart)
	{
		rand=new Random();
		population=new ArrayList<Gene>();
		this.geneLength=sizeOfGene;
		this.popSize=sizeOfPop;
		this.mutationRate=mutationRate;
		this.tourneySize=tourneySize;
		populate(randomStart);
	}
	
	/**
	 * Creates generation one of the population
	 * @param random true to initialize randomly
	 */
	public void populate(boolean random)
	{
		while(population.size()<popSize)
		{
			population.add(new Gene(this.geneLength,random));
		}
		this.best=bestFitness();
	}
	
	/**
	 * Evolve one generation
	 */
	public void evolve()
	{
		//Make a new population with the non dead population as the parents
		ArrayList<Gene> newPop = new ArrayList<Gene>(popSize);
		
		/*
		 * Mate the winners of tournaments to create a new generation
		 * Use crossover mutation to pass genes
		 */
		while(newPop.size()<popSize)
		{
			Gene g1= tournament();
			Gene g2 = tournament();
			while(g2.equals(g1))
			{
				g2=tournament();
			}
			newPop.add(crossover(g1, g2, rand.nextInt(g1.size())));
		}
		
		//place the new population in the place of the old
		population=newPop;
		
		//Perform random point mutations
		mutate();
		
		this.best=this.bestFitness();

	}
	
	/**
	 * Selects the best Gene from a group
	 * @return Best gene in a group
	 */
	public Gene tournament()
	{
		ArrayList<Gene> members =  new ArrayList<Gene>(tourneySize);
		while(members.size()<tourneySize)
		{
			Gene next = population.get(rand.nextInt(population.size()));
			while(members.contains(next))
			{
				next = population.get(rand.nextInt(population.size()));
			}
			members.add(next);
		}
		return bestGene(members);
	}
	
	/**
	 * performs the point mutations
	 */
	public void mutate()
	{
		for(int i = 0; i < population.size();++i)
		{
			Gene g = population.get(i);
			for(int u =0; u < g.size();++u)
			{
				if(rand.nextDouble()<mutationRate)
					g.replace(!g.get(u), u);
			}
		}
	}
	
	/**
	 * Performs a crossover mutation
	 * @param gene1 First gene to cross
	 * @param gene2 Second gene to Cross
	 * @param index where to cross at
	 * @return new gene created from the cross
	 */
	public Gene crossover(Gene gene1, Gene gene2, int index)
	{
		Gene gene = new Gene();
		for(int i =0; i<index;++i)
		{
			gene.add(gene1.get(i));
		}
		for(int i =index; i<gene1.size();++i)
		{
			gene.add(gene2.get(i));
		}
		return gene;
	}
	
	/**
	 * Gives the best fitness
	 * @return best fitness
	 */
	private int bestFitness()
	{
		int highest=0;
		for(Gene g : population)
		{
			if(g.fitness()>highest)
			{
				highest=g.fitness();
			}
		}
		return highest;
	}
	
	public int highestFitness()
	{
		return this.best;
	}
	
	/**
	 * returns the gene with the highest fitness in a group
	 * @param members genes to compete against each other
	 * @return the gene who kills all of the others. JK, just the coolest dude
	 */
	private Gene bestGene(ArrayList<Gene> members)
	{
		Gene g = members.remove(0);
		while(!members.isEmpty())
		{
			Gene g2 = members.remove(0);
			if(g2.fitness()>g.fitness())
			{
				g=g2;
			}
		}
		return g;
	}
	
	/**
	 * Gives the average fitness of the generation
	 * @return average fitness
	 */
	public double average()
	{
		int sum=0;
		for(Gene g : population)
		{
			sum+=g.fitness();
		}
		return ((double)sum)/population.size();
	}
	
	/**
	 * Gives the population as a String
	 *  @return String representation of the population
	 */
	public String toString()
	{
		String ret="{ ";
		for(Gene g : population)
		{
			ret+=g.toString()+":"+g.fitness()+" ";
		}
		return ret+" }";
	}
}
