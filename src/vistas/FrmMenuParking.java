

package vistas;

import modelos.Turno;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.JFrame;
import org.jdesktop.application.Action;


public class FrmMenuParking extends javax.swing.JDialog {
    Frame parent;
    Turno turno;

    
    public FrmMenuParking(java.awt.Frame parent, boolean modal,Turno turno) {
        super(parent,"Estacionamiento", modal);
        this.parent = parent;
        this.turno = turno;
        initComponents();
        this.getContentPane().setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
  @Action
    public void onNuevaEntrada() {
        new FrmParkingEntrada(parent,true,turno);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntrada = new javax.swing.JButton();
        btnBoletoPerdido = new javax.swing.JButton();
        btnObjetoPrenda = new javax.swing.JButton();
        btnBoletoCancelado = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNoAutos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmMenuParking.class, this);
        btnEntrada.setAction(actionMap.get("onNuevaEntrada")); // NOI18N
        btnEntrada.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEntrada.setText("Entrada");
        btnEntrada.setName("btnEntrada"); // NOI18N

        btnBoletoPerdido.setAction(actionMap.get("onBoletoPerdido")); // NOI18N
        btnBoletoPerdido.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoPerdido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBoletoPerdido.setText("Boleto perdido");
        btnBoletoPerdido.setName("btnBoletoPerdido"); // NOI18N

        btnObjetoPrenda.setBackground(new java.awt.Color(255, 255, 255));
        btnObjetoPrenda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnObjetoPrenda.setText("Objeto en prenda");
        btnObjetoPrenda.setName("btnObjetoPrenda"); // NOI18N

        btnBoletoCancelado.setAction(actionMap.get("onBoletoCancelado")); // NOI18N
        btnBoletoCancelado.setBackground(new java.awt.Color(255, 255, 255));
        btnBoletoCancelado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBoletoCancelado.setText("Boleto Cancelado");
        btnBoletoCancelado.setName("btnBoletoCancelado"); // NOI18N

        btnSalida.setAction(actionMap.get("onNuevaSalida")); // NOI18N
        btnSalida.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSalida.setText("Salida");
        btnSalida.setName("btnSalida"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Autos en estacionamiento:");
        jLabel1.setName("jLabel1"); // NOI18N

        lblNoAutos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNoAutos.setName("lblNoAutos"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnObjetoPrenda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNoAutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBoletoCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNoAutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnObjetoPrenda, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBoletoCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void onNuevaSalida() {
        new FrmLeerCodigoBarras(parent, true,turno,"COBRO");
    }

    @Action
    public void onBoletoPerdido() {
        new FrmBoletoPerdidoPlaca(parent, true, turno);
    }

    @Action
    public void onBoletoCancelado() {
         new FrmLeerCodigoBarras(parent, true,turno,"CANCELAR");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBoletoCancelado;
    private javax.swing.JButton btnBoletoPerdido;
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnObjetoPrenda;
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNoAutos;
    // End of variables declaration//GEN-END:variables
}
