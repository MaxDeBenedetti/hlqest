/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hlqest;

/**
 *
 * @author modsm_000
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        hlqest hlq = new hlqest();
        
        double[] data = {2,3,5,7,8};
        System.out.print(hlq.HLEstimator(data));
    }
}
