import java.io.*;

public class TransitionMatrixCreator {
    
    public static double[][] inputMatrix(String filename) {
        double[][] pMatrix = new double[27][27];
        try {
            BufferedReader r = new BufferedReader(new FileReader(filename));
            
            for(int i=0; i<27; i++) {
                String line = r.readLine();
                String[] strNums = line.split("\\s+");
                double[] nums = new double[27];
                for(int j=0; j<27; j++) 
                    nums[j] = Double.parseDouble(strNums[j]);
                pMatrix[i] = nums;
            } 
            
            r.close();
            System.out.println("Matrix read from file");
        } catch (IOException e) {
            System.out.println("Could not read file");
        } 
        return pMatrix;
    } 
    
    public static void outputMatrix(double[][] pMatrix, String filename) {
        try {
            Writer w = new FileWriter(filename);
            
            for(int i=0; i<27; i++) {
                for(int j=0; j<27; j++) 
                    w.write(String.valueOf(pMatrix[i][j]) + "\t");
                w.write("\n");
            }
            
            w.close();
            System.out.println("Matrix written to file");
        } catch (IOException e) {
            System.out.println("Could not write file");
        } 
    } 
    
    public static void createMatrixFromFile(String filename, double[][] pMatrix) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));

            int[] samples = new int[27];
            
            String line;
            while( (line = parseString(in.readLine()) ) != null) { 
                if(line.length() < 2) continue;
                
                for(int numChar=1; numChar<line.length(); numChar++) {
                    
                    int fromState = ((int) line.charAt(numChar-1)) - 97;
                    int destState = ((int) line.charAt(numChar)  ) - 97;
                    if(fromState == -65) fromState = 26;
                    if(destState == -65) destState = 26;
                    
                    updateProbabilities(fromState, destState, samples, pMatrix);
                } // end loop over line
            } // end loop over file
            
            in.close();
        } catch (IOException e) {
            System.out.println("IOException Thrown");
        } 

    }
    
    private static String parseString(String s) { 
        if(s == null) return null;
        // removes all characters except for a-z and spacebar
        return s.toLowerCase().replaceAll("\\s", " ").replaceAll("[^ a-z]", "");
    }
    
    private static void updateProbabilities(int fromState, int destState, int[] samples, double[][] pMatrix) {
        double sampleRatio = (double) samples[fromState] / ++samples[fromState];
        
        for(int j=0; j<27; j++) {
            double oldProb = pMatrix[fromState][j];
            double newProb = oldProb * sampleRatio;
            if(j == destState) // add some additional probability
                newProb += (1.0 / samples[fromState]);            
            
            pMatrix[fromState][j] = newProb;
        }  
    }  
    
    
} // end class 