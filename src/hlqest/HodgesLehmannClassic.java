/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hlqest;

import java.util.ArrayList;

/**
 *
 * @author modsm_000
 */
public class HodgesLehmannClassic {
    public static double HLEstimator(double[] dataSet){
        double num;
        double[] pairs = new double[(dataSet.length*(dataSet.length + 1))/2];
        int pos =0;
        for(int j = 0; j < dataSet.length; j++){
            for(int i =0; i <= j; i ++){
                pairs[pos] = ((dataSet[i]+dataSet[j])/2);
                pos++;
            }
        }
        num =fastMedian.find_kth_smallest_double(pairs, pairs.length, pairs.length/2);
        return num;
    }
}
