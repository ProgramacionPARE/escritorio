/*
 * ProyectoAristaView.java
 */

package proyectopare;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modeloReportes.CorteTurno;
import modelos.Caja;
import modelos.Empleado;
import modelos.Turno;
import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;
import vistas.FrmCaja;
import vistas.FrmEstadoEstacionamiento;
import vistas.FrmLogin;
import vistas.FrmMenuAdministracion;
import vistas.FrmMenuParking;
import vistas.FrmNuevoTurno;
import vistas.FrmReportes;
import vistas.FrmUsuarios;

/**
 * The application's main frame.
 */
public class ProyectoPareView extends FrameView {
   JLabel lblNombreOperador;
   private Empleado empleado;
   private boolean turnoAbrierto;

    public ProyectoPareView(SingleFrameApplication app) {
        super(app);
        lblNombreOperador = new JLabel("");
        initComponents();  
        iniciaOtrosComponentes();
       
}

  
    
    public void initLogin(){
        btnCerrarSesion.setVisible(false);
        Turno turnoTemp = Turno.existeTurnoAbierto();
        new FrmLogin(this.getFrame(), true);
 
    }
      
    private void iniciaOtrosComponentes() {
        ImageIcon img = new ImageIcon(
                getClass().getResource("/proyectopare/resources/parking_entrada18x18.gif"));
        this.getFrame().setIconImage(img.getImage());
        this.getFrame().setExtendedState(Frame.MAXIMIZED_BOTH);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        btnAparcamiento = new javax.swing.JButton();
        btnCaja = new javax.swing.JButton();
        btnEstacionamiento = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ProyectoPareView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setMaximumSize(new java.awt.Dimension(100, 100));
        mainPanel.setName("mainPanel"); // NOI18N

        jToolBar1.setBackground(resourceMap.getColor("jToolBar1.background")); // NOI18N
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(ProyectoPareView.class, this);
        btnAparcamiento.setAction(actionMap.get("llamadaParking")); // NOI18N
        btnAparcamiento.setBackground(resourceMap.getColor("btnAparcamiento.background")); // NOI18N
        btnAparcamiento.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnAparcamiento.setIcon(resourceMap.getIcon("btnAparcamiento.icon")); // NOI18N
        btnAparcamiento.setText(resourceMap.getString("btnAparcamiento.text")); // NOI18N
        btnAparcamiento.setFocusable(false);
        btnAparcamiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAparcamiento.setIconTextGap(0);
        btnAparcamiento.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnAparcamiento.setMaximumSize(new java.awt.Dimension(120, 45));
        btnAparcamiento.setMinimumSize(new java.awt.Dimension(100, 35));
        btnAparcamiento.setName("btnAparcamiento"); // NOI18N
        btnAparcamiento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAparcamiento);

        btnCaja.setBackground(resourceMap.getColor("btnCaja.background")); // NOI18N
        btnCaja.setFont(resourceMap.getFont("btnCaja.font")); // NOI18N
        btnCaja.setIcon(resourceMap.getIcon("btnCaja.icon")); // NOI18N
        btnCaja.setText(resourceMap.getString("btnCaja.text")); // NOI18N
        btnCaja.setFocusable(false);
        btnCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCaja.setIconTextGap(0);
        btnCaja.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnCaja.setMaximumSize(new java.awt.Dimension(120, 45));
        btnCaja.setMinimumSize(new java.awt.Dimension(100, 46));
        btnCaja.setName("btnCaja"); // NOI18N
        btnCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCaja);

        btnEstacionamiento.setBackground(resourceMap.getColor("btnEstacionamiento.background")); // NOI18N
        btnEstacionamiento.setFont(resourceMap.getFont("btnEstacionamiento.font")); // NOI18N
        btnEstacionamiento.setIcon(resourceMap.getIcon("btnEstacionamiento.icon")); // NOI18N
        btnEstacionamiento.setText(resourceMap.getString("btnEstacionamiento.text")); // NOI18N
        btnEstacionamiento.setFocusable(false);
        btnEstacionamiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEstacionamiento.setIconTextGap(0);
        btnEstacionamiento.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnEstacionamiento.setMaximumSize(new java.awt.Dimension(120, 45));
        btnEstacionamiento.setName("btnEstacionamiento"); // NOI18N
        btnEstacionamiento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEstacionamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstacionamientoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEstacionamiento);

        btnReportes.setBackground(resourceMap.getColor("btnReportes.background")); // NOI18N
        btnReportes.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnReportes.setIcon(resourceMap.getIcon("btnReportes.icon")); // NOI18N
        btnReportes.setText(resourceMap.getString("btnReportes.text")); // NOI18N
        btnReportes.setFocusable(false);
        btnReportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportes.setIconTextGap(0);
        btnReportes.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnReportes.setMaximumSize(new java.awt.Dimension(120, 45));
        btnReportes.setMinimumSize(new java.awt.Dimension(100, 46));
        btnReportes.setName("btnReportes"); // NOI18N
        btnReportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jToolBar1.add(btnReportes);

        btnUsuarios.setAction(actionMap.get("llamadaClientes")); // NOI18N
        btnUsuarios.setBackground(resourceMap.getColor("btnUsuarios.background")); // NOI18N
        btnUsuarios.setFont(resourceMap.getFont("btnUsuarios.font")); // NOI18N
        btnUsuarios.setIcon(resourceMap.getIcon("btnUsuarios.icon")); // NOI18N
        btnUsuarios.setText(resourceMap.getString("btnUsuarios.text")); // NOI18N
        btnUsuarios.setFocusable(false);
        btnUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUsuarios.setIconTextGap(0);
        btnUsuarios.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnUsuarios.setMaximumSize(new java.awt.Dimension(120, 45));
        btnUsuarios.setMinimumSize(new java.awt.Dimension(100, 44));
        btnUsuarios.setName("btnUsuarios"); // NOI18N
        btnUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        jToolBar1.add(btnUsuarios);

        btnConfiguracion.setAction(actionMap.get("llamadaClientes")); // NOI18N
        btnConfiguracion.setBackground(resourceMap.getColor("btnConfiguracion.background")); // NOI18N
        btnConfiguracion.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnConfiguracion.setIcon(resourceMap.getIcon("btnConfiguracion.icon")); // NOI18N
        btnConfiguracion.setText(resourceMap.getString("btnConfiguracion.text")); // NOI18N
        btnConfiguracion.setFocusable(false);
        btnConfiguracion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfiguracion.setIconTextGap(0);
        btnConfiguracion.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnConfiguracion.setMaximumSize(new java.awt.Dimension(120, 45));
        btnConfiguracion.setName("btnConfiguracion"); // NOI18N
        btnConfiguracion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        jToolBar1.add(btnConfiguracion);

        btnCerrarSesion.setBackground(resourceMap.getColor("btnCerrarSesion.background")); // NOI18N
        btnCerrarSesion.setFont(resourceMap.getFont("btnAparcamiento.font")); // NOI18N
        btnCerrarSesion.setIcon(resourceMap.getIcon("btnCerrarSesion.icon")); // NOI18N
        btnCerrarSesion.setText(resourceMap.getString("btnCerrarSesion.text")); // NOI18N
        btnCerrarSesion.setFocusable(false);
        btnCerrarSesion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrarSesion.setIconTextGap(0);
        btnCerrarSesion.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnCerrarSesion.setMaximumSize(null);
        btnCerrarSesion.setName("btnCerrarSesion"); // NOI18N
        //btnCerrarSesion.setLayout(new BorderLayout());
        //btnCerrarSesion.add(BorderLayout.NORTH,new JLabel("Cerrar Sesion"));
        //btnCerrarSesion.add(BorderLayout.SOUTH,lblNombreOperador);
        btnCerrarSesion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCerrarSesion);

        mainPanel.add(jToolBar1);
        jToolBar1.setBounds(0, 0, 970, 50);

        setComponent(mainPanel);
        setToolBar(jToolBar1);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        FrmReportes dialog = new FrmReportes(ProyectoPareApp.getApplication().getMainFrame(), true);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        if(turnoAbrierto){
            int res =  JOptionPane.showConfirmDialog(this.getFrame(), "Quieres tambien hacer corte de turno?","Cerrar sesion", JOptionPane.YES_NO_CANCEL_OPTION);
            if(res ==  JOptionPane.YES_OPTION){
                if(Caja.getByCaseta(turno.getEmpleado().getCaseta().getId()).getFondo()>0 ){
                     new FrmCaja(this.getFrame(),true,turno,true,empleado);
                }else{
                    turno.realizarCorte(empleado);
                    turno.actualizar();
                    new CorteTurno(turno).generarReporte(); 
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
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed
        new FrmCaja(this.getFrame(),true,turno,false,empleado);
    }//GEN-LAST:event_btnCajaActionPerformed

    private void btnEstacionamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstacionamientoActionPerformed
        new FrmEstadoEstacionamiento(this.getFrame(),true,turno);        
    }//GEN-LAST:event_btnEstacionamientoActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
       new FrmMenuAdministracion(this.getFrame(),true,turno);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
         new FrmUsuarios(this.getFrame(), true, turno);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    @Action
    public void llamadaParking() {
        new FrmMenuParking(this.getFrame(),true,turno);
        //if(parking == null) {
        //new VenParking (ProyectoPareApp.getApplication().getMainFrame(), false,turno).setVisible(true);
        //}
        //parking.setVisible(true);
    }

    public void validaPermisos(Empleado empleado) {
        //Busco turno abierto
        Turno turnoTemp = Turno.existeTurnoAbierto();
        //Si no hay turno pregunto si quiere abrir nuevo turno
        if(turnoTemp == null){
             int showConfirmDialog = JOptionPane.showConfirmDialog(this.getFrame(),"¿Quiere abrir un nuevo turno?", 
                "Turno nuevo", JOptionPane.YES_NO_OPTION );
            if(showConfirmDialog == JOptionPane.YES_OPTION){
                turno = new Turno();
                turno.inicializarTurno(empleado,"");
                turno.guardar();
                new FrmNuevoTurno(this.getFrame(),true,turno);
                turnoAbrierto  = true;
            }else{
                turnoAbrierto  = false;
                turno = new Turno();
                turno.setEmpleado(empleado);
            }
              

        }else{
            turnoAbrierto  = true;
            turno = turnoTemp;
            turno.setEmpleado(empleado);
            if(turnoTemp.getOperador().getId() != empleado.getId()){
                JOptionPane.showMessageDialog(this.getFrame(), "Turno abierto, empleado temporal",
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
        
        
        
        btnCerrarSesion.setText("Cerrar sesion de "+empleado.getUsuario());
        btnCerrarSesion.setVisible(true);

        
    }

    @Action
    public void llamadaAlarmas() {
//        if(imprimirBitacoraAlarmas == null) {
//            imprimirBitacoraAlarmas = new VenImprimeBitacoraAlarmas(ProyectoPareApp.getApplication().getMainFrame(), false);
//        }
//        imprimirBitacoraAlarmas.setVisible(true);
    }

    @Action
    public void llamadaUsuarios() {
//        if(usuarios == null) {
////            usuarios = new VenUsuarios(ProyectoPareApp.getApplication().getMainFrame(),false);
//            usuarios.setVisible(true);
//        }
//        else {
//            usuarios.setVisible(true);
//        }
    }

    @Action
    public void llamadaAsistenciaOperadores() {

        
    }

    @Action
    public void salir(boolean borrarAlarma) {
      //  if (borrarAlarma) new proyectopare.clases.Alarmas().removerAlarmaSalida(proyectopare.clases.PARAMETROS.ID_SALIDA);
//        if(aTiempoExtra != null)
//            aTiempoExtra.cerrarVentana();
//        if(actaAdmin != null)
//            actaAdmin.cerrarVentana();
//        if(acuseDoc != null)
//            acuseDoc.cerrarVentana();
//        if(autosPernoc != null)
//            autosPernoc.cerrarVentana();
//       // if(bolPerdido != null)
//       //     bolPerdido.cerrarVentana();
//        if(comunInterno != null)
//            comunInterno.cerrarVentana();
//        if(config != null)
//            config.cerrarVentana();
//        if(danAutos != null)
//            danAutos.cerrarVentana();
//        if(repFormatos  != null)
//            repFormatos.cerrarVentana();
//        if(movOrganiz != null)
//            movOrganiz.cerrarVentana();
//        if(objPrenda != null)
//            objPrenda.cerrarVentana();
//        if(parking != null)
//            parking.cerrarVentana();
//        if(progPesonal != null)
//            progPesonal.cerrarVentana();
//        if(recPago != null)
//            recPago.cerrarVentana();
//        if(regPensiones != null)
//            regPensiones.cerrarVentana();
//        if(reqPersonal != null)
//            reqPersonal.cerrarVentana();
//        if(segDiscip != null)
//            segDiscip.cerrarVentana();
//        if(solPapel != null)
//            solPapel.cerrarVentana();
//        if(vacProgram != null)
//            vacProgram.cerrarVentana();
//        if(operadores != null)
//            operadores.cerrarVentana();
//    //    public static VenTarifas tarifas = null;
//        if(caja != null)
//            caja.cerrarVentana();
//        if(tarifaPago != null)
//            tarifaPago.cerrarVentana();
//        if(imprimirESParking != null)
//            imprimirESParking.cerrarVentana();
//        if(imprimirESCaja != null)
//            imprimirESCaja.cerrarVentana();
//        if(catalogoListas != null)
//            catalogoListas.cerrarVentana();
//        if(catClientes != null)
//            catClientes.cerrarVentana();
//        if(usuarios != null)
//            usuarios.cerrarVentana();
//        if(imprimirExtractoCuenta != null)
//            imprimirExtractoCuenta.cerrarVentana();
//
//
//        if(imprimirBitacoraAlarmas != null)
//            imprimirBitacoraAlarmas.cerrarVentana();
//        if(foliosForma != null)
//            foliosForma.cerrarVentana();
//        if (borrarAlarma) System.exit(0);
    }

    @Action
    public void llamadaClientes() {
//        if(catClientes == null) {
//            //catClientes = new VenClientes(ProyectoPareApp.getApplication().getMainFrame(), false);
//        }
//        catClientes.setVisible(true);
    }

    public void setCajaAlarma(boolean requiereRetitroParcial) {
       if(requiereRetitroParcial)
           btnCaja.setBackground(Color.red);
       else
           btnCaja.setBackground(new Color(195,197,221));
    }
    
    @Action
    public void cerrarTurno(){
        turno.realizarCorte(empleado);
        turno.actualizar();
        new CorteTurno(turno).generarReporte();
        empleado = null;
        btnCerrarSesion.setVisible(false);
        initLogin();
    }

    @Action
    public void onTarifaMenu() {
       // VenTarifasPago  frmTarifas = new VenTarifasPago(this.getFrame(),true);
//        frmTarifas.setVisible(true);
        //FrmTarifas  frmTarifas = new FrmTarifas(this.getFrame(),true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAparcamiento;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEstacionamiento;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JDesktopPane mainPanel;
    // End of variables declaration//GEN-END:variables

    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private Turno turno;

//    public static VenATiempoExtra aTiempoExtra = null;
//    public static VenActaAdministrativa actaAdmin = null;
//    public static VenAcuseDocumentos acuseDoc = null;
//    public static VenAutosPernoctantes autosPernoc = null;
//    public static FrmBoletoPerdido bolPerdido = null;
//    public static VenComunicadoInterno comunInterno = null;
//    public static VenConfiguraciones config = null;
//    public static VenDaniosAutomovil danAutos = null;
//    public static VenEmisReportFormatos repFormatos  = null;
//    public static VenMovOrganizacionales movOrganiz = null;
//    public static VenObjetosPrenda objPrenda = null;
//    public static VenParking parking = null;
//    public static VenProgrSemanalPersonal progPesonal = null;
//    public static VenReciboPago recPago = null;
//    public static VenRegistroPensiones regPensiones = null;
//    public static VenRequisicionPersonal reqPersonal = null;
//    public static VenSDisciplinarioEmpleado segDiscip = null;
//    public static VenSolicitudPapeleria solPapel = null;
//    public static VenVacacionesProgramadas vacProgram = null;
//    public static VenOperadores operadores = null;
////    public static VenTarifas tarifas = null;
//    public static VenMovimientosCaja caja = null;
//    public static VenTarifasPago tarifaPago = null;
//    public static VenImprimeEntSalParking imprimirESParking = null;
//    public static VenImprimeEntSalCaja imprimirESCaja = null;
//    public static VenCatalogoListas catalogoListas = null;
//    public static VenClientes catClientes = null;
//    public static VenUsuarios usuarios = null;
//    public static VenImprimeExtractoCuenta imprimirExtractoCuenta = null;
//    public static VenImprimeBitacoraAlarmas imprimirBitacoraAlarmas = null;
//    public static Login loginUsuarios = null;
//    public static VenFoliosFormatos foliosForma = null;



}
