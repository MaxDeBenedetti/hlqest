/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hlqest;

/**
 *
 * @author modsm_000
 */
public class fastMedian {
    /*
 * Finds the (K_VALUE)th smallest element in double array (DATA) with (NUM_ELEMS) elements.
 * Adapted from
 * N. Wirth, Algorithms and Data Structures, Prentice Hall, 1985.
 * This may be the most compact and fastest algorithm to do this task.
 */


public static double find_kth_smallest_double ( double[] data, int num_elems, int k_value )
{
 int left, right;
 int i, j;
 double tmp_val;
 double pivot;

 left = 0;
 right = num_elems - 1;

 while ( left < right )
  {
   pivot = data[k_value];
   i = left;
   j = right;

   do
    {
     while ( data[i] < pivot ) { i++; }
     while ( pivot < data[j] ) { j--; }

     if ( i <= j )
      {
       tmp_val = data[i];
       data[i] = data[j];
       data[j] = tmp_val;
       i++;
       j--;
      }
    }
   while ( i <= j );

   if ( j < k_value ) { left = i; }
   if ( k_value < i ) { right = j; }
  }

 return data[k_value];
}
}
