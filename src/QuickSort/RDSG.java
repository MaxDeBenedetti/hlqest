/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickSort;

import java.util.Random;

/**
 *
 * @author Max De Benedetti
 */
public class RDSG {

    public static double[] generateDataSet(int size, int seed){
        double rands[] = new double[size];
        Random rand = new Random(seed);
        
        for(int i = 0; i < size ; i++){
            rands[i]=rand.nextDouble()*500;
        }
        
        
        return rands;
        
    }
}
