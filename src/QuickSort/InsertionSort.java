/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickSort;

/**
 *
 * @author Max De Benedetti
 */
public class InsertionSort {
    
    
    public static int[] sort(int[] data){
        for(int j = 1; j < data.length; j++){
            int key = data[j];
            
            int i = j - 1;
            while(i > 0 && data[i] > key){
                data[i + 1] = data[i];
                i--;
            }
            data[i]=key;
        }
        
        return data;
    }

    public static double[] sort(double[] data) {
        for(int j = 1; j < data.length; j++){
            double key = data[j];
            
            int i = j - 1;
            while(i > 0 && data[i] > key){
                data[i + 1] = data[i];
                i--;
            }
            data[i]=key;
        }
        
        return data;
    }
}
