/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickSort;

/**
 * An efficient algorithm that can be used to find the min, median, and max value of 3 elements
 * @author Max De Benedetti
 */
public class Compare3 {
    
    public static final int MIN = 0;
    public static final int MEDIAN = 1;
    public static final int MAX = 2;
    
    /**
     * sorts three elements passed to the program
     * @param a first int
     * @param b second int
     * @param c third int
     * @return an array with the three elements sorted
     */
    public static int[] sort(int a, int b, int c){
        int[] sort = new int[3];
        
        sort[1] =b;
        
        //compares the first and last elements and stores them into the array
        if(a < c){
            sort[0] =a;
            sort[2] = c;
        }
        else{
            sort[0] =c;
            sort[2] = a;
        }
        
        //compares the smallest and middle elements
        if(b < sort[0]){
            sort[1]=sort[0];
            sort[0]=b;
        }
        //compares the largest and middle element
        else if(sort[2] < b){
            sort[1]=sort[2];
            sort[2]=b;
        }
        
        return sort;
    }
    
        /**
     * sorts three elements passed to the program
     * @param a first int
     * @param b second int
     * @param c third int
     * @return an array with the three elements sorted
     */
    public static double[] sort(double a, double b, double c){
        double[] sort = new double[3];
        
        sort[1] =b;
        
        //compares the first and last elements and stores them into the array
        if(a < c){
            sort[0] =a;
            sort[2] = c;
        }
        else{
            sort[0] =c;
            sort[2] = a;
        }
        
        //compares the smallest and middle elements
        if(b < sort[0]){
            sort[1]=sort[0];
            sort[0]=b;
        }
        //compares the largest and middle element
        else if(sort[2] < b){
            sort[1]=sort[2];
            sort[2]=b;
        }
        
        return sort;
    }
    
    public static Comparable[] sort (Comparable a, Comparable b, Comparable c){
        Comparable[] sort = new Comparable[3];
        
        sort[1] =b;
        
        if(a.compareTo(c) < 0){
            sort[0] =a;
            sort[2] = c;
        }
        else{
            sort[0] =c;
            sort[2] = a;
        }
        
        if(b.compareTo(sort[0])<0){
            sort[1]=sort[0];
            sort[0]=b;
        }
        else if(sort[2].compareTo( b) < 0){
            sort[1]=sort[2];
            sort[2]=b;
        }
        
        return sort;
    }
    
    public static int min(int a, int b, int c){
        return sort(a, b, c)[0];
    }
    
     public static double min(double a, double b, double c){
        return sort(a, b, c)[0];
    }
    
    public static Comparable min(Comparable a, Comparable b, Comparable c){
        return sort(a, b, c)[0];
    }
    
    public static int median(int a, int b, int c){
        return sort(a, b, c)[1];
    }
    
    public static double median(double a, double b, double c){
        return sort(a, b, c)[1];
    }
    
    public static Comparable median(Comparable a, Comparable b, Comparable c){
        return sort(a, b, c)[1];
    }
    
    public static int max(int a, int b, int c){
        return sort(a, b, c)[2];
    }
    
    public static double max(double a, double b, double c){
        return sort(a, b, c)[3];
    }
    
    public static Comparable max(Comparable a, Comparable b, Comparable c){
        return sort(a, b, c)[2];
    }

    
}
