/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import ModelosAux.Tiempo;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Auto;
import org.jdesktop.application.Action;
import sockets.ClienteMonitor;

/**
 *
 * @author oscar
 */
public class FrmMonitorDetalle extends javax.swing.JDialog {
    private Auto auto;
    private ClienteMonitor clienteMonitor;
            
    public FrmMonitorDetalle(java.awt.Frame parent, boolean modal,Auto auto,ClienteMonitor clienteMonitor) {
        super(parent,"Detalle de auto", modal);
        initComponents();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );     
        tblDetalle.setDefaultRenderer(String.class, centerRenderer);
        this.auto = auto;
        this.clienteMonitor = clienteMonitor;
        this.btnCobrar.setEnabled(auto.isDentro());
        this.btnCancelado.setEnabled(auto.isDentro());
        this.btnPerdido.setEnabled(auto.isDentro());
        
        llenarTabla();
        this.setLocationRelativeTo(parent);
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
        llenarTabla();
        this.pack();
    }
    
    
    
    public void llenarTabla(){
        DefaultTableModel modelTurno = (DefaultTableModel) this.tblDetalle.getModel();
        modelTurno.getDataVector().removeAllElements();
        modelTurno.addRow(new Object[]{auto.getProgresivo(),auto.isDentro()?"Dentro":"Fuera",auto.getFechaEntrada(),auto.getHoraEntrada(),auto.getFechaSalida(),
          auto.getHoraSalida(),auto.getHorasTangibles(),auto.getMinutosTangibles(),auto.getMontoTangible(),auto.isBoletoPerdido()?"SI":"NO",
          auto.isBoletoCancelado()?"SI":"NO",auto.isBoletoManual()?"SI":"NO",auto.isBoletoContra()?"SI":"NO"});
        tblDetalle.setModel(modelTurno);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        btnCobrar = new javax.swing.JButton();
        btnCancelado = new javax.swing.JButton();
        btnPerdido = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblDetalle.setFont(new java.awt.Font("Droid Sans", 0, 16)); // NOI18N
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Progresivo", "Estado", "Fecha entrada", "Hora entrada", "Fecha salida", "Hora salida", "Horas", "Minutos", "Monto", "Perdido", "Cancelado", "Manual", "Contra"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetalle.setName("tblDetalle"); // NOI18N
        jScrollPane1.setViewportView(tblDetalle);
        if (tblDetalle.getColumnModel().getColumnCount() > 0) {
            tblDetalle.getColumnModel().getColumn(0).setResizable(false);
            tblDetalle.getColumnModel().getColumn(1).setResizable(false);
            tblDetalle.getColumnModel().getColumn(2).setResizable(false);
            tblDetalle.getColumnModel().getColumn(3).setResizable(false);
            tblDetalle.getColumnModel().getColumn(4).setResizable(false);
            tblDetalle.getColumnModel().getColumn(5).setResizable(false);
            tblDetalle.getColumnModel().getColumn(6).setResizable(false);
            tblDetalle.getColumnModel().getColumn(7).setResizable(false);
            tblDetalle.getColumnModel().getColumn(8).setResizable(false);
            tblDetalle.getColumnModel().getColumn(9).setResizable(false);
            tblDetalle.getColumnModel().getColumn(10).setResizable(false);
            tblDetalle.getColumnModel().getColumn(11).setResizable(false);
            tblDetalle.getColumnModel().getColumn(12).setResizable(false);
        }

        btnCobrar.setText("Cobrar");
        btnCobrar.setName("btnCobrar"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmMonitorDetalle.class, this);
        btnCancelado.setAction(actionMap.get("onCancelar")); // NOI18N
        btnCancelado.setText("Cancelado");
        btnCancelado.setName("btnCancelado"); // NOI18N

        btnPerdido.setAction(actionMap.get("onPerdido")); // NOI18N
        btnPerdido.setText("Perdido");
        btnPerdido.setName("btnPerdido"); // NOI18N

        btnActualizar.setText("Actualizar remoto");
        btnActualizar.setName("btnActualizar"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCobrar)
                    .addComponent(btnCancelado)
                    .addComponent(btnPerdido)
                    .addComponent(btnActualizar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void onCancelar() {
        int showConfirmDialog;
        showConfirmDialog = JOptionPane.showConfirmDialog(this, "Estas seguro de cancelar este boleto",
        "Cancelar boleto",JOptionPane.YES_NO_OPTION);
        if(showConfirmDialog == JOptionPane.YES_OPTION){
            auto.setIsBoletoOficina(true);
            auto.setIsBoletoCancelado(true);
            auto.setHoraSalida(Tiempo.getHora());
            auto.setFechaSalida(Tiempo.getFecha());
            clienteMonitor.actualizarAuto(auto);
            JOptionPane.showMessageDialog(this, "El boleto se cancelo con exito");
        }
        
    }

    @Action
    public void onPerdido() {
        int showConfirmDialog;
        showConfirmDialog = JOptionPane.showConfirmDialog(this, "Estas seguro de guardar este boleto como perdido",
        "Boleto perdido",JOptionPane.YES_NO_OPTION);
        if(showConfirmDialog == JOptionPane.YES_OPTION){
            auto.setIsBoletoOficina(true);
            auto.setIsBoletoPerdido(true);
            auto.setHoraSalida(Tiempo.getHora());
            auto.setFechaSalida(Tiempo.getFecha());
            clienteMonitor.actualizarAuto(auto);
            JOptionPane.showMessageDialog(this, "El boleto se cancelo con exito");
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelado;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnPerdido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDetalle;
    // End of variables declaration//GEN-END:variables
}
