/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hlqest;

import QuickSort.InsertionSort;

import java.util.ArrayList;

/**
 *
 * @author modsm_000
 */
public class hlqest {
    
    /*
     * The three ArrayLists represent partitionings of the data set. data represents the set that is being partitioned. left are the elements < than the pivot. right are the elements > than the pivot.
     * Each element of the ArrayLists stores the location of te row ends of the upper triangular matrix. Each element is a 3 elemnt array. 0 represents the row. 1 represents the row's start. 2 represents the row's end.
     */
    private ArrayList<int[]> data, left, right;
    private int n;//pointer to the last element of the data set
    
    public hlqest(){
        data = new ArrayList();
        left = new ArrayList();
        right = new ArrayList();
        n = 0;
    }
    
    
    public double HLEstimator(double[] dataSet){
        data.clear();
        left.clear();
        right.clear();
        n = dataSet.length - 1;
        
        
        int k = (int) Math.ceil(dataSet.length*(dataSet.length + 1)/4);
        int index, row, col;
        double element, pivot;
        
        //sort the data
        //I will replace the insertion sort with a quick sort at a later date.
        dataSet = InsertionSort.sort(dataSet);
        
        
        for(int i = 0; i <= n; i++){
            int[] anArray = {i,i,n};
            data.add(anArray);
        }
        
        //Here is the all important partitioning process.
        while (partitionSize(data) > k){
            left.clear();
            right.clear();
            
            //We start with the top right element
            index = 0; 
            row = data.get(index)[0];
            col = data.get(index)[2];
            
            while(isInBounds(index, col)){
                element = (dataSet[row] + dataSet[col])/2;
                pivot = pickPivot(dataSet);
                
                if(element < pivot){
                    int[] addToLeft = {row, data.get(index)[1], col};//adds the elemnts that is smaller than the pivot to the partitioning of elements smaller than the pivot
                    left.add(addToLeft);
                    if(isInBounds(index, col+1)){
                        int[] addToRight = {row, col +1, data.get(index)[2]};//adds the elements larger than the pivot to the partitioning of elements larger than the pivot
                        right.add(addToRight);
                    }
                    row++;//move down
                    index++;
                }
                else{
                    col--;//move left
                }
                
            }
            //Add any remaining rows to the partitioning of elements larger than the pivot 
            while(index < data.size()){
                right.add(data.get(index));
                index++;
            }
        
        
            int leftSize = partitionSize(left);

            data.clear();

            if(leftSize > k){
                data.addAll(left);
            }
            else{
                data.addAll(right);
            }

        }
        
        if(k%2 == 0){
            return biggest(data, dataSet);
        }
        else{
            return (biggest(left, dataSet)+biggest(right, dataSet))/2;
        }
    }
    
    
    /*
     * Checks to see if a specified location is within the partition
     */
    private boolean isInBounds(int index, int col){
        if(index >= data.size())
            return false;
        
        int[] bounds = data.get(index);
        if(col < bounds[1] || col > bounds[2])
            return false;
        
        return true;
    }
    
    /*
     * Similar to quick sort, this algorithm requires a the selection of a pivot element for the partitioning process.
     * This version is a quick version that will select a random double that is within the range of the data set. I may use a better version later.
     */
    private double pickPivot(double[] dataSet){
        double range = dataSet[dataSet.length-1]-dataSet[0];
        
        double pivot = Math.random()*range + dataSet[0];
        
        return pivot;
    }
    
    private int partitionSize(ArrayList<int[]> part){
        
        int sum = 0;
        for(int[] bounds : part){
            sum += bounds[2]-bounds[1];
        }
        
        return sum;
    }
    
    private double biggest(ArrayList<int[]> part, double[] nums){
        double max = Double.NEGATIVE_INFINITY;
        double num;
        for(int[] bounds : part){
            for(int i = bounds[1]; i <= bounds[2]; i++){
                num = (nums[bounds[0]] + nums[i])/2;
                max = (num > max) ? num : max;
            }
        }
        
        return max;
    }
    
}
