/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ModelosAux;


public class Seguridad {
    public static String getClave(int n){
        String clave = "";
        for(int i = 0; i<n;i++ ){
            clave += (char)(((int)(Math.random()*25)+65));
        }
        return clave;
    }
}
