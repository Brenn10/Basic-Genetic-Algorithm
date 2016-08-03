import random
class Population():
    def __init__(self,alleleLength,startStyle,populationSize,mutationRate,tourneySize,fitnessFunc):
        
        '''
        Initialize variables
        '''
        self.alleleLength=alleleLength
        self.startStyle=startStyle
        self.populationSize=populationSize
        self.mutationRate=mutationRate
        self.tourneySize=tourneySize
        self.fitness=fitnessFunc
        
        '''
        Create initial population
        '''
        if(startStyle==-1):
            self.pop=[[0]*alleleLength]*populationSize
        elif(startStyle==1):
            self.pop=[[1]*alleleLength]*populationSize
        else:
            self.pop=[]
            for i in range(populationSize):
                self.pop.append([])
                for u in range(alleleLength):
                    self.pop[i].append(random.randrange(2))
        self.best=self.currBest()
        
    def evolve(self):
        '''
        Create new gen
            select using tourney
            crossover two genes
            get best of the two crossovers
        Screw them kids up with random mutations and maybe shift mutations
        '''
        self.crossover()
        
        self.mutate()
        
        self.allTimeBest()
        
    def crossover(self):
        newGen = []
        
        for i in range(self.populationSize):
            new1 = self.tourneySelect()
            new2 = self.tourneySelect()
            index=random.randrange(self.alleleLength)
            new3=new1[:index]+new2[index:]
            new4=new2[:index]+new1[index:]
            
            newGen.append(self.getBestOfTwo(new3,new4))
        self.pop=newGen
            
    def tourneySelect(self):
        currbest=[]
        for i in range(self.tourneySize):
            if(currbest==[]):
                currbest=self.pop[random.randrange(self.populationSize)]
            else:
                this=self.pop[random.randrange(self.populationSize)]
                if(self.fitness(currbest)<self.fitness(this)):
                    currbest=this
        return currbest
        
    def getBestOfTwo(self,gene1,gene2):
        if(self.fitness(gene1)>self.fitness(gene2)):
            return gene1
        return gene2
    
    def mutate(self):
        for i in range(self.populationSize):
            for u in range(self.alleleLength):
                if(random.random()<self.mutationRate):
                    self.pop[i][u]=(self.pop[i][u]+1)%2

    
    def currBest(self):
        best=self.pop[0]
        for i in self.pop:
            if(self.fitness(i)>self.fitness(best)):
                best = i
        return best
                
    def allTimeBest(self):
        curr=self.currBest()
        if self.fitness(curr)>self.fitness(self.best):
            self.best=curr
            
