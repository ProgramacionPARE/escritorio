/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author oscar
 */
public class MonitorEstacionamiento{
        private  String ip;
        private String nombre;

        public MonitorEstacionamiento(String ip, String nombre) {
            this.ip = ip;
            this.nombre = nombre;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
        
    }        