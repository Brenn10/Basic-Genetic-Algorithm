import Population

def fitness(gene):
    try:
        x=int("".join(map(str,gene[1:(len(gene)+1)/2])),2)/float(int("".join(map(str,gene[(len(gene)+1)/2:])),2))

        if(gene[0]==1):
            x=-x
    except:
        return 0
    
    discrepancy=abs(-17*x**9+4*x**5-4*x**4-.6*x**3+2*x**2-5*x-9) #Trying to get the value as close to zero as possible
    
    if(discrepancy>1):
        return 0
    
    return 1/(discrepancy*discrepancy)


alleleLength=100
startStyle=1
populationSize=1000
mutationRate=.05
tourneySize=300
pop = Population.Population(alleleLength,startStyle,populationSize,mutationRate,tourneySize,fitness)

print pop.pop
print pop.fitness(pop.best)

best1=1
best2=2
best3=3
counter=0
best=0
while best1!=best2 and best2!=best3:
    print counter
    pop.evolve()
    print pop.best
    print pop.fitness(pop.best)
    
    if(pop.fitness(best)<pop.fitness(pop.best)):
        best=pop.best
        counter=0
    else:
        counter+=1
    if counter==50:
        break
print pop.getX(pop.best)
