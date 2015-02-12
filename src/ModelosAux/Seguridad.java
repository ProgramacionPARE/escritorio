/*
 * Clase para generar letras aleatorias de n longitud
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
