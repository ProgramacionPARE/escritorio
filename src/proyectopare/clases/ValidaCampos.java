/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectopare.clases;
import java.awt.event.*;
import javax.swing.JTextField;
/**
 *
 * @author Administrador
 */
public class ValidaCampos {

    /*** Valor entero para indicar una validacion que admita cualquier tipo de caracter.*/
    public static final int VARCHAR = 1;
    /*** Valor entero para indicar una validacion que admita solo letras y números.*/
    public static final int NCHAR = 2;
    /*** Valor entero para indicar una validacion que admita solamente letras.*/
    public static final int CHAR = 3;
    /*** Valor entero para indicar una validacion que admita solo números.*/
    public static final int INT = 4;
    /*** Valor entero para indicar una validacion que admita solo números y un punto decimal.*/
    public static final int FLOAT = 5;
    public static final int DATE = 6;
    public static final int TIME = 7;

    public static final int[] letters = {65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,89,90,97,98,99,100,101,102,103,104,105,106,107,108,109,
            110,111,112,113,114,115,116,117,118,119,120,121,122};
    public static final int[] special_letters = {193,201,205,209,211,218,225,233,237,241,243,250};
    public static final int[] numbers = {48,49,50,51,52,53,54,55,56,57};
    public static boolean letter, special_letter, number;

    /**
     * Funcion que valida un JTextField para que pueda contenido del tipo especificado por
     * el entero con una longitud dada. Esta función debe agregarse en el evento keyTyped
     * del objeto JTextField.
     * @param campo El objeto JTextField que se va a validar.
     * @param evt El evento de tipo KeyEvent generado por keyTyped.
     * @param tipo El entero que marca el tipo de dato a validar, pueden ser constantes de la clase.
     * @param longitud La longitud máxima de caracteres que puede contener el campo.
     */
    public static void validar(JTextField campo, KeyEvent evt, int tipo, int longitud){
        letter = false;
        special_letter = false;
        number = false;
        String texto = campo.getText();
        int codigo = evt.getKeyCode();
        char caracter = evt.getKeyChar();

        if(codigo==KeyEvent.VK_ENTER){return;}

        for(int i=0;i<letters.length;i++)
            if(caracter==letters[i]){
                letter = true;
                break;
            }
        for(int i=0;i<special_letters.length;i++)
            if(caracter==special_letters[i]){
                special_letter = true;
                break;
            }
        for(int i=0;i<numbers.length;i++)
            if(caracter==numbers[i]){
                number = true;
                break;
            }

        switch(tipo){
            case VARCHAR:
                if(texto.length()>=longitud)
                    evt.setKeyChar((char)0);
                break;
            case NCHAR:
                if(texto.length()>=longitud)
                    evt.setKeyChar((char)0);
                else if(!letter && !number && !special_letter &&
                        codigo!=KeyEvent.VK_SPACE &&
                        codigo!=KeyEvent.VK_BACK_SPACE)
                    evt.setKeyChar((char)0);
                break;
            case CHAR:
                if(texto.length()>=longitud)
                    evt.setKeyCode((char)0);
                else if(!letter && !special_letter &&
                        codigo!=KeyEvent.VK_SPACE &&
                        codigo!=KeyEvent.VK_BACK_SPACE)
                    evt.setKeyCode((char)0);
                break;
            case INT:
                if(texto.length()>=longitud)
                    evt.setKeyChar((char)0);
                else if(!number)
                    evt.setKeyChar((char)0);
                break;
            case FLOAT:
                if(texto.length()>=longitud)
                    evt.setKeyChar((char)0);
                else if(caracter==46 && texto.indexOf('.') != -1)
                    evt.setKeyChar((char)0);
                else if(!number && caracter != 46)
                    evt.setKeyChar((char)0);
                break;
            case DATE:
                if(texto.length()>=10)
                    evt.setKeyChar((char)0);
                else if(!number && caracter!='-')
                    evt.setKeyChar((char)0);
                break;
            case TIME:
                if(texto.length()>=10)
                    evt.setKeyChar((char)0);
                else if(!number && caracter!=':')
                    evt.setKeyChar((char)0);
                break;
            default:
                break;
        }
    }

    /**0
     * Funcion que valida un JTextField para que pueda recibir cualquier caracter con una
     * longitud dada. Esta función debe agregarse en el evento keyTyped del objeto JTextField.
     * @param campo El objeto JTextField que se va a validar.
     * @param evt El evento de tipo KeyEvent generado por keyTyped.
     * @param longitud La longitud máxima de caracteres que puede contener el campo.
     */
    /**
     * Funcion que valida un JTextField que cambia a mayúsculas cualquier letra ingresada con
     * longitud dada. Esta función debe agregarse en el evento keyTyped del objeto JTextField.
     * upperCase no sobrecarga el método String.toUpperCase() y
     * no valida el tipo de contenido, por lo que se recomienda utilizarlo despues
     * del método validar.
     * @param campo El objeto JTextField que se va a validar.
     * @param evt El evento de tipo KeyEvent generado por keyTyped.
     * @param longitud La longitud máxima de caracteres que puede contener el campo.
     */
    public static void upperCase(javax.swing.JTextField campo, KeyEvent evt){
        char caracter = evt.getKeyChar();
        try{
            if((caracter>=97 && caracter<=122) ||
                    caracter==225 || caracter==233 ||
                    caracter==237 || caracter==243 ||
                    caracter==250 || caracter==241){
                evt.setKeyChar((char)(caracter-32));
            }
        }catch(Exception e){}
    }

    /**
     * Funcion que valida un JTextField que cambia a minúsculas cualquier letra ingresada con
     * longitud dada. Esta función debe agregarse en el evento keyTyped del objeto JTextField.
     * lowerCase no sobrecarga el método String.toLowerCase() y
     * no valida el tipo de contenido, por lo que se recomienda utilizarlo despues
     * del metodo validar.
     * @param campo El objeto JTextField que se va a validar.
     * @param evt El evento de tipo KeyEvent generado por keyTyped.
     * @param longitud La longitud máxima de caracteres que puede contener el campo.
     */
    public static void cadenaMin(javax.swing.JTextField campo, KeyEvent evt){
        char caracter = evt.getKeyChar();
        try{
            if((caracter>=65 && caracter<=90) ||
                    caracter==193 || caracter==201 ||
                    caracter==205 || caracter==211 ||
                    caracter==218 || caracter==209){
                evt.setKeyChar((char)(caracter+32));
            }
        }catch(Exception e){}
    }

    public static void fechaFormatoL(javax.swing.JTextField campo, KeyEvent evt){
        String texto = campo.getText();
        if(texto.length()>10)
            evt.setKeyChar((char) 0);
        else if(texto.length()==2 || texto.length()==5 && evt.getKeyChar()!='-')
            evt.setKeyChar((char) 0);
        else if(evt.getKeyChar()< 48 || evt.getKeyChar() > 57 && evt.getKeyChar()!='-')
            evt.setKeyChar((char)0);
    }

    public static void horaHRS(javax.swing.JTextField campo, KeyEvent evt){

    }

    public static void horaAMPM(javax.swing.JTextField campo, KeyEvent evt){

    }
}
