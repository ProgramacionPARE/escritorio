/*
 * ProyectoAristaApp.java
 */

package proyectopare;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;


public class ProyectoPareApp extends SingleFrameApplication {
    private ProyectoPareView p;

    
    @Override protected void startup() {
        p = new ProyectoPareView(this);
        show(p);
        p.initLogin();
    }

  
    @Override protected void configureWindow(java.awt.Window root) {
    }


    public  ProyectoPareView getView() {
        return p;
    }
    
    public static ProyectoPareApp getApplication() {
        return Application.getInstance(ProyectoPareApp.class);
    }


    public static void main(String[] args) {
       launch(ProyectoPareApp.class, args);
    }

}

