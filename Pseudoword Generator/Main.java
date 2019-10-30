
public class Main {
  
    private static String[] corpus = {"corpus/EN-WarAndPeace.txt", "corpus/EN-AliceInWonderland.txt", 
        "corpus/JP-Rashomon.txt", "corpus/IT-DantesInferno.txt", "corpus/CH(1).txt"};
    
    public static void main(String[] args) {    
        double[][] pMatrix = new double[27][27]; // the probability matrix
        pMatrix[0][0] = -1; // used as a flag later on
        
//        pMatrix = TransitionMatrixCreator.inputMatrix("pMatrix.txt");
        TransitionMatrixCreator.createMatrixFromFile(corpus[2], pMatrix);   

        if(pMatrix[0][0] == -1) { 
            System.out.println("Matrix Creation Failed"); return;
        } 
        
        TransitionMatrixCreator.outputMatrix(pMatrix, "pMatrix.txt");

        MarkovChain mc = new MarkovChain(pMatrix);

        System.out.println("\n\n");
        for(int i=0; i<1000; i++) {
            int nextInt = mc.step();
            if(nextInt == 26) nextInt = -65;
            System.out.print((char)(nextInt + 97));
        }
        
        
    } 
} // end class 
