import java.util.Random;

public class MarkovChain {
    
    double[][] pMatrix;
    double[][] intervalMatrix;
    
    int state = -1;
    Random rand;
    
    public MarkovChain(double[][] pMatrix) {
        this.pMatrix = pMatrix;
        this.rand = new Random();
        this.state = this.rand.nextInt(pMatrix.length); // pick a rand start
    
        createIntervalMatrix();
    } 
    
    private void createIntervalMatrix() {    
        intervalMatrix = new double[27][27];
        for(int r=0; r<27; r++) {
            intervalMatrix[r][0] = pMatrix[r][0];
            for(int c=1; c<27; c++) {
                intervalMatrix[r][c] = pMatrix[r][c] + intervalMatrix[r][c-1];
            }
        }
        System.out.println("Interval Matrix Created");
//        TransitionMatrixCreator.outputMatrix(intervalMatrix, "test.txt");
    } 
    
    public int step() {
        double randProb = rand.nextDouble();
        for(int i=0; i<27; i++) {
            if(randProb < intervalMatrix[state][i]) {
                return (state = i);
            } 
        }
        return -1;
    } 
        
    
    
} // end class 