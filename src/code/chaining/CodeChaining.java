/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package code.chaining;

import s.project.core.Query;

/**
 *
 * @author auliayf
 */
public class CodeChaining {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Query x = new Query("KO");
        System.out.println(x.join("KI", "KI.A = KO.A", "").where("A = '01'").where("B", "01").select("A, B").get());
    }
    
}
