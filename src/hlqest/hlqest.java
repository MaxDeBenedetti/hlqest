/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hlqest;

import QuickSort.InsertionSort;

import java.util.ArrayList;
import java.util.Random;

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
    private int leftIsZero;
    
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

        int index, row, col;
        double element, pivot;
        
        //sort the data
        //I will replace the insertion sort with a quick sort at a later date.
        dataSet = InsertionSort.sort(dataSet);
        
        
        for(int i = 0; i <= n; i++){
            int[] anArray = {i,i,n};
            data.add(anArray);
        }
        
        //int k = partitionSize(data)/2;
        //int k = (int) Math.ceil(dataSet.length*(dataSet.length + 1)/4);
        int k = (dataSet.length)/2;
        
        int leftSize = 0;
        leftIsZero =0;//By preventing too many consecutive iterations where the left side has no elements, I should remove any remaining infinite loops
        
        //Here is the all important partitioning process.
        while (partitionSize(data) > k){
            left.clear();
            right.clear();
            
            //We start with the top right element
            index = 0; 
//            
//            if(data.get(index)[2]-data.get(index)[1] < 2){
//                index++;
//            }
            
            row = data.get(index)[0];
            col = data.get(index)[2];
            pivot = pickPivot(data, dataSet);
            //pivot = pickPivot(dataSet);
            
            if(Double.isNaN(pivot))
                break;
            
            
            while(isInBounds(index, col)){
                element = (dataSet[row] + dataSet[col])/2;

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
        
        
            leftSize = partitionSize(left);
            if(leftSize == 0){
                leftIsZero++;
            }
            else
                leftIsZero--;

            data.clear();

            if(leftSize > k){
                data.addAll(left);
            }
            else{
                data.addAll(right);
            }
           
            if(leftIsZero > 10){
                n=n;
            }

        }
        
        if(k%2 == 0){
            return fastBiggest(data, dataSet);
        }
        else{
            return (fastBiggest(left, dataSet)+fastBiggest(right, dataSet))/2;
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
        
        double pivot1 = Math.random()*range + dataSet[0];
        double pivot2 = Math.random()*range + dataSet[0];
        double pivot3 = Math.random()*range + dataSet[0];
        
        double pivot = QuickSort.Compare3.median(pivot1,pivot2,pivot3);
        
        return pivot;
    }
    
    /*
     * Similar to quick sort, this algorithm requires a the selection of a pivot element for the partitioning process.
     * This version is a quick version that will select a random double that is within the range of the data set. I may use a better version later.
     * 
     * If the range of the elements is 0, then every element is the same and no further partitioning can be done.
     * This actually means that the program worked, but this will result in an infinite loop.
     * This method will return NaN if the range is 0.
     */
    private double pickPivot(ArrayList<int[]> part, double[] dataSet){
        double biggest = ( dataSet[part.get(part.size()-1)[0]] + dataSet[part.get(part.size()-1)[2]] )/2;
        double smallest = ( dataSet[part.get(0)[0]] + dataSet[part.get(0)[1]] )/2;
        double range = biggest-smallest;
        
        Random rand = new Random();
        int index = rand.nextInt(part.size());
        int row = part.get(index)[2]-part.get(index)[1];
        int value = (row>0) ? part.get(index)[1]+rand.nextInt(row): part.get(index)[1];
        double pivot1 = (dataSet[part.get(index)[0]]+dataSet[value])/2;
        
        index = rand.nextInt(part.size());
        row = part.get(index)[2]-part.get(index)[1];
        value = (row>0) ? part.get(index)[1]+rand.nextInt(row): part.get(index)[1];
        double pivot2 = (dataSet[part.get(index)[0]]+dataSet[value])/2;
        
        index = rand.nextInt(part.size());
        row = part.get(index)[2]-part.get(index)[1];
        value = (row>0) ? part.get(index)[1]+rand.nextInt(row): part.get(index)[1];
        double pivot3 = (dataSet[part.get(index)[0]]+dataSet[value])/2;
        
        double pivot = (range >.3) ? (pivot1+pivot2+pivot3)/3 : Double.NaN;
        
        if(leftIsZero > 10){
                pivot = (dataSet[part.get(0)[0]]+dataSet[part.get(0)[2]])/2;
                leftIsZero = 0;
        }
                
        return pivot;
    }
    
    private int partitionSize(ArrayList<int[]> part){
        
        int sum = 0;
        for(int[] bounds : part){
            sum += bounds[2]-bounds[1]+1;
        }
        
        return sum;
    }
    
    //Assumes that the largest element is the last element allowing for selection in linear time
    //This assumption seems to hold true once the partitioning process has stopped
    private double fastBiggest(ArrayList<int[]> part, double[] nums){
        double biggest = ( nums[part.get(part.size()-1)[0]] + nums[part.get(part.size()-1)[2]] )/2;
        return biggest;
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
    
    private double smallest(ArrayList<int[]> part, double[] nums){
        double min = Double.POSITIVE_INFINITY;
        double num;
        for(int[] bounds : part){
            for(int i = bounds[1]; i <= bounds[2]; i++){
                num = (nums[bounds[0]] + nums[i])/2;
                 min = (num < min) ? num : min;
            }
        }
        
        double smallest = ( nums[part.get(0)[0]] + nums[part.get(0)[1]] )/2;
        
        if(Math.abs(smallest - min)<.1)
            System.out.println("small");
        
        return min;
    }
    
    private double middle(ArrayList<int[]> part, double[] nums){
        int size = partitionSize(part);
        double[] median = new double[size];
        int k = 0;
        
        for(int[]bounds: part){
            for(int i = bounds[1]; i <= bounds[2]; i++){
                if( i == 710){
                    n=n;
                }
                median[k]= (nums[bounds[0]] + nums[i])/2;
                k++;
            }
        }        
        
        return fastMedian.find_kth_smallest_double(median, size, size/2);
    }
    
}
