package vistas;

import ModelosAux.HistorialVentana;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import modeloReportes.CorteTurno;
import modeloReportes.RetirosParciales;
import modelos.Caja;
import modelos.Configuracion;
import modelos.DetalleTurno;
import modelos.Empleado;
import modelos.Estacionamiento;
import modelos.Rest;
import modelos.Turno;
import org.jdesktop.application.Action;


public class FrmPrincipal extends JFrame{
    JLabel lblNombreOperador;
    private Estacionamiento estacionamiento;
    private Empleado empleado;
    private boolean turnoAbrierto;
    private FrmPrincipal frmPrincipal;
    private Turno turno;
    static private ArrayList<HistorialVentana> ventanas = new ArrayList();
   

    public FrmPrincipal() {
        super("Sistema PARE");
        lblNombreOperador = new JLabel("");
        initComponents();  
        iniciaOtrosComponentes();     
       
        Rest.login(estacionamiento);
        if(Configuracion.getDatos().getTerminal().equals(Estacionamiento.CAJA))
            initLogin();
        else if(Configuracion.getDatos().getTerminal().equals(Estacionamiento.CLIENTE))
            this.validaCliente();
        
        
    }
    
    public static void nuevaVentana(JDialog ventana){
        if (ventanas.size()> 0){
        ventanas.get(ventanas.size()-1).getVentana().setSize(300, 40);
        ventanas.get(ventanas.size()-1).getVentana().setLocation((ventanas.size()-1)*300, (int)( Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100));
        }
        ventanas.add(new HistorialVentana(ventana,ventana.getBounds()));
    }
    
    public static void restaurarUltimaVentana(){
        if (ventanas.size()> 1){
            ventanas.get(ventanas.size()-2).getVentana().setBounds( ventanas.get(ventanas.size()-2).getBordes());
            ventanas.get(ventanas.size()-2).getVentana().setLocationRelativeTo(null);
            ventanas.get(ventanas.size()-2).getVentana().pack();
            
            ventanas.remove(ventanas.size()-1);
        }
    }  
    
    public void initLogin(){
        btnCerrarSesion.setVisible(false);
        new FrmLogin(this, true);
 
    }
      
    private void iniciaOtrosComponentes() {
        estacionamiento = Estacionamiento.getDatos();
        frmPrincipal = this;
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), "parking");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "caja");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "auditoria");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "reportes");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "usuarios");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), "configuracion");
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");
             
        getRootPane().getActionMap().put("parking", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEstacionameinto();
            }
        });
        
        getRootPane().getActionMap().put("caja", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCaja();
            }
        });
        getRootPane().getActionMap().put("auditoria", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAuditoria();
            }
        });
        getRootPane().getActionMap().put("reportes", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onReportes();
            }
        });
        getRootPane().getActionMap().put("usuarios", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUsuarios();
            }
        });
        getRootPane().getActionMap().put("configuracion", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onConfiguracion();
            }
        });
        getRootPane().getActionMap().put("exit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSalir();
            }
        });

        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
     }
    
    @Action
    public void onEstacionameinto() {
        new FrmMenuParking(this,false,turno,estacionamiento);
    }
    
         @Action
    public void onCaja() {
        new FrmCaja(this,false,turno,false,empleado,estacionamiento);
    }
         @Action
    public void onAuditoria() {
        Iterator<Map.Entry<String, DetalleTurno>> iterator = turno.getDetallesTurno().entrySet().iterator();
        while(iterator.hasNext()){
            
            new FrmEstadoEstacionamiento(this,false,turno,estacionamiento,iterator.next().getKey());
            
        }
    }
    
         @Action
    public void onReportes() {
        new FrmReportes(this,false,estacionamiento);
    }
    
         @Action
    public void onUsuarios() {
        new FrmUsuarios(this,false,turno,estacionamiento);
    }
    
         @Action
    public void onConfiguracion() {
        new FrmMenuAdministracion(this,false,turno,estacionamiento);
    }


    @Action
    public void onSalir() {
        if(turnoAbrierto){
            int res =  JOptionPane.showConfirmDialog(this, "Quieres tambien hacer corte de turno?","Cerrar sesion", JOptionPane.YES_NO_CANCEL_OPTION);
            if(res ==  JOptionPane.YES_OPTION){
                if(Caja.getByCaseta(estacionamiento.getCaseta().getId()).getFondo()>0 ){
                     new FrmCaja(this,true,turno,true,empleado,estacionamiento);
                }else{
                    turno.realizarCorte(empleado.getId());
                    turno.actualizar();
                    new CorteTurno(turno, estacionamiento).generarReporte();    
                    new RetirosParciales(turno, estacionamiento).generarReporte();

                    empleado = null;
                    btnCerrarSesion.setVisible(false);
                    initLogin();
                }
             }else if(res ==  JOptionPane.NO_OPTION){
                 empleado = null;
                 btnCerrarSesion.setVisible(false);
                 initLogin();
             }
        }else{
            empleado = null;
            btnCerrarSesion.setVisible(false);
            initLogin();
       }
    }
    
    public void validaCliente(){
        Turno turnoTemp = Turno.existeTurnoAbiertoActivo();
        if(turnoTemp == null){
            JOptionPane.showMessageDialog(this, "Es necesario que el sistema en casa este activo, reintente de nuevo");
        }
        else{
            new FrmLeerCodigoBarras(this, true,turnoTemp,"COBRO",estacionamiento);
        }
    }
   
    public void validaPermisos(Empleado empleado) {
        //Busco turno abierto
        Turno turnoTemp = Turno.existeTurnoAbierto();
        //Si no hay turno pregunto si quiere abrir nuevo turno
        if(turnoTemp == null){
             int showConfirmDialog = JOptionPane.showConfirmDialog(this,"¿Quiere abrir un nuevo turno?", 
                "Turno nuevo", JOptionPane.YES_NO_OPTION );
            if(showConfirmDialog == JOptionPane.YES_OPTION){
                turno = new Turno(estacionamiento);
                turno.inicializarTurno(empleado.getId(),"");
                new FrmNuevoTurno(this,true,turno,estacionamiento);
                turno.actualizar();
                turnoAbrierto  = true;
            }else{
                turnoAbrierto  = false;
                turno = new Turno(estacionamiento);
                turno.setEmpleadoEntrada(empleado);
            }
              

        }else{
            turnoAbrierto  = true;
            turno = turnoTemp;
            turno.setEstacionamiento(estacionamiento);
            turno.setEmpleadoEntrada(empleado);
            if(turnoTemp.getEmpleadoEntrada().getId() != empleado.getId()){
                JOptionPane.showMessageDialog(this, "Turno abierto, empleado temporal",
                        "Temporal", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        
        this.empleado = empleado;
        
        btnAparcamiento.setVisible(true);
        btnCaja.setVisible(true);
        btnEstacionamiento.setVisible(true);
        btnReportes.setVisible(true);
        btnUsuarios.setVisible(true);
        btnConfiguracion.setVisible(true);
       
       switch (empleado.getTipoPuesto()) {
           case "Cajero":
                if(!turnoAbrierto){
                    btnAparcamiento.setVisible(false);
                    btnCaja.setVisible(false);
                }
                btnUsuarios.setVisible(false);
                btnReportes.setVisible(false);
                btnEstacionamiento.setVisible(false);
                btnConfiguracion.setVisible(false);    
               break;
           case "Encargado":
                if(!turnoAbrierto){
                    btnAparcamiento.setVisible(false);
                    btnCaja.setVisible(false);
                }  
                btnReportes.setVisible(false);   
                btnEstacionamiento.setVisible(false); 
                btnConfiguracion.setVisible(false);
               break;
           case "Supervisor":
                if(!turnoAbrierto) {
                    btnAparcamiento.setVisible(false);
                    btnCaja.setVisible(false);
                }
                btnConfiguracion.setVisible(false);
                break;
            case "Auditor":
               if(!turnoAbrierto) {
                   btnAparcamiento.setVisible(false);
                   btnCaja.setVisible(false);
               }  
                btnConfiguracion.setVisible(false);
               break;
           case "Administrador":
               if(!turnoAbrierto){
                   btnAparcamiento.setVisible(false);
                   btnCaja.setVisible(false);
               }  break;
       }
        btnCerrarSesion.setText("<html><b>Cerrar sesion de "+empleado.getUsuario()+"</b><br><center>(Esc)</center></html>");
        btnCerrarSesion.setVisible(true);    
    }

    public void setCajaAlarma(boolean requiereRetitroParcial) {
       if(requiereRetitroParcial)
           btnCaja.setBackground(Color.red);
       else
           btnCaja.setBackground(new Color(255,255,255));
    }
    
    @Action
    public void cerrarTurno(){
        turno.realizarCorte(empleado.getId());
        turno.actualizar();
        new CorteTurno(turno, estacionamiento).generarReporte();
        new RetirosParciales(turno, estacionamiento).generarReporte();

        empleado = null;
        btnCerrarSesion.setVisible(false);
        initLogin();
    }

    public static void main(String[] args) {
        new FrmPrincipal();
    }    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnAparcamiento = new javax.swing.JButton();
        btnCaja = new javax.swing.JButton();
        btnEstacionamiento = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmPrincipal.class);
        setBackground(resourceMap.getColor("Form.background")); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmPrincipal.class, this);
        btnAparcamiento.setAction(actionMap.get("onEstacionameinto")); // NOI18N
        btnAparcamiento.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnAparcamiento.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnAparcamiento.setText(resourceMap.getString("btnAparcamiento.text")); // NOI18N
        btnAparcamiento.setName("btnAparcamiento"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(btnAparcamiento, gridBagConstraints);

        btnCaja.setAction(actionMap.get("onCaja")); // NOI18N
        btnCaja.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnCaja.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnCaja.setText(resourceMap.getString("btnCaja.text")); // NOI18N
        btnCaja.setName("btnCaja"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(btnCaja, gridBagConstraints);

        btnEstacionamiento.setAction(actionMap.get("onAuditoria")); // NOI18N
        btnEstacionamiento.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnEstacionamiento.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnEstacionamiento.setText(resourceMap.getString("btnEstacionamiento.text")); // NOI18N
        btnEstacionamiento.setName("btnEstacionamiento"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(btnEstacionamiento, gridBagConstraints);

        btnReportes.setAction(actionMap.get("onReportes")); // NOI18N
        btnReportes.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnReportes.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnReportes.setText(resourceMap.getString("btnReportes.text")); // NOI18N
        btnReportes.setName("btnReportes"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(btnReportes, gridBagConstraints);

        btnUsuarios.setAction(actionMap.get("onUsuarios")); // NOI18N
        btnUsuarios.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnUsuarios.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnUsuarios.setText(resourceMap.getString("btnUsuarios.text")); // NOI18N
        btnUsuarios.setName("btnUsuarios"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(btnUsuarios, gridBagConstraints);

        btnConfiguracion.setAction(actionMap.get("onConfiguracion")); // NOI18N
        btnConfiguracion.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnConfiguracion.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnConfiguracion.setText(resourceMap.getString("btnConfiguracion.text")); // NOI18N
        btnConfiguracion.setName("btnConfiguracion"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(btnConfiguracion, gridBagConstraints);

        btnCerrarSesion.setAction(actionMap.get("onSalir")); // NOI18N
        btnCerrarSesion.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnCerrarSesion.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnCerrarSesion.setText(resourceMap.getString("btnCerrarSesion.text")); // NOI18N
        btnCerrarSesion.setName("btnCerrarSesion"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        getContentPane().add(btnCerrarSesion, gridBagConstraints);

        jPanel1.setName("jPanel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.weighty = 100.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 50;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAparcamiento;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEstacionamiento;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
