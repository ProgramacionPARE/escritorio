package vistas;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Estacionamiento;
import modelos.Tarifa;


public class FrmTarifas extends javax.swing.JDialog {
    Estacionamiento estacionamiento;
    private Tarifa tarifa;
    private String estado="";
    private final ArrayList<JTextField>  txtTarifas = new ArrayList();

    
    public FrmTarifas(java.awt.Frame parent, boolean modal, final Estacionamiento estacionamiento) {
        super(parent,"Tarifas", modal);
        initComponents();
        txtTarifas.add(txtFraccion1);
        txtTarifas.add(txtFraccion2);
        txtTarifas.add(txtFraccion3);
        txtTarifas.add(txtFraccion4);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
        this.estacionamiento = estacionamiento;
        this.setLocationRelativeTo(parent);
        tarifa = estacionamiento.getCaseta().getTarifas().get(0);
        tblTarifas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(estado.equals("")){
                    tarifa = Tarifa.getById((int) tblTarifas.getValueAt(tblTarifas.getSelectedRow(), 0));
                    btnEliminar.setVisible(true);
                    btnModificar.setVisible(true);
                    cargarDatos();
                }
            }
        });
        cargarTabla();
        cargarDatos();  
        setVisible(true);
    }
    
    private void cargarTabla(){
        estacionamiento.getCaseta().setTarifas(Tarifa.getAll());
        
        DefaultTableModel model = (DefaultTableModel) tblTarifas.getModel();
        ArrayList<Tarifa> tarifas = Tarifa.getAll(); 
        model.getDataVector().removeAllElements();
        Iterator<Tarifa> iterator = tarifas.iterator();
        while(iterator.hasNext()){
            Tarifa next = iterator.next();
           model.addRow(new Object[]{next.getId(), next.getDescripcion(), "$ "+next.getPrecioHora() });
        }
        btnModificar.setVisible(false);
         btnEliminar.setVisible(false);
       
    }
    
   private void cargarDatos(){
       
        this.txtPrecioHora.setText(String.valueOf(tarifa.getPrecioHora()));
        this.txtTarifaMaxima.setText(String.valueOf(tarifa.getTarifaMaxima()));
        this.txtHorasCompletas.setText(String.valueOf(tarifa.getHorasCompletas()));
        this.txtBoletoPerdido.setText(String.valueOf(tarifa.getPrecioBoletoPerdido()));
        this.txtDescripcion.setText(String.valueOf(tarifa.getDescripcion()));
        this.txtTarifaUnica.setText(String.valueOf(tarifa.getTarifaUnica()));
        int i=0;
        for(Float n:tarifa.getCostos()){
            txtTarifas.get(i).setText(String.valueOf(n));
            i++;
        }
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel19 = new javax.swing.JLabel();
        txtPrecioHora = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTarifaMaxima = new javax.swing.JTextField();
        txtBoletoPerdido = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtHorasCompletas = new javax.swing.JTextField();
        lblFraccion9 = new javax.swing.JLabel();
        txtFraccion1 = new javax.swing.JTextField();
        lblFraccion10 = new javax.swing.JLabel();
        txtFraccion2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtFraccion3 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtFraccion4 = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTarifas = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        txtTarifaUnica = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel19.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Precio hora:");
        jLabel19.setName("jLabel1"); // NOI18N

        txtPrecioHora.setEditable(false);
        txtPrecioHora.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtPrecioHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioHora.setName("txtPrecioHora"); // NOI18N

        jLabel20.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Tarifa maxima:");
        jLabel20.setName("jLabel2"); // NOI18N

        txtTarifaMaxima.setEditable(false);
        txtTarifaMaxima.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtTarifaMaxima.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarifaMaxima.setName("txtTarifaMaxima"); // NOI18N

        txtBoletoPerdido.setEditable(false);
        txtBoletoPerdido.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtBoletoPerdido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBoletoPerdido.setName("txtBoletoPerdido"); // NOI18N

        jLabel21.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Boleto perdido:");
        jLabel21.setName("jLabel3"); // NOI18N

        jLabel22.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Horas Completas:");
        jLabel22.setName("jLabel4"); // NOI18N

        txtHorasCompletas.setEditable(false);
        txtHorasCompletas.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtHorasCompletas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHorasCompletas.setName("txtHorasCompletas"); // NOI18N

        lblFraccion9.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lblFraccion9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFraccion9.setText("Fraccion 1");
        lblFraccion9.setName("lblFraccion1"); // NOI18N

        txtFraccion1.setEditable(false);
        txtFraccion1.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion1.setName("txtFraccion1"); // NOI18N

        lblFraccion10.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        lblFraccion10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFraccion10.setText("Fraccion 2");
        lblFraccion10.setName("lblFraccion2"); // NOI18N

        txtFraccion2.setEditable(false);
        txtFraccion2.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion2.setName("txtFraccion2"); // NOI18N

        jLabel24.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Fraccion 3");
        jLabel24.setName("jLabel8"); // NOI18N

        txtFraccion3.setEditable(false);
        txtFraccion3.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion3.setName("txtFraccion3"); // NOI18N

        jLabel25.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Fraccion 4");
        jLabel25.setName("jLabel9"); // NOI18N

        txtFraccion4.setEditable(false);
        txtFraccion4.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtFraccion4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFraccion4.setName("txtFraccion4"); // NOI18N

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setName("jButton2"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setName("jButton3"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Descripción");
        jLabel23.setName("jLabel23"); // NOI18N

        txtDescripcion.setEditable(false);
        txtDescripcion.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcion.setName("txtDescripcion"); // NOI18N

        jLabel1.setText(" ");
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblTarifas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Tarifa", "Precio hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTarifas.setName("tblTarifas"); // NOI18N
        jScrollPane1.setViewportView(tblTarifas);
        if (tblTarifas.getColumnModel().getColumnCount() > 0) {
            tblTarifas.getColumnModel().getColumn(0).setMinWidth(0);
            tblTarifas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblTarifas.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setName("jSeparator1"); // NOI18N

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setName("jSeparator2"); // NOI18N

        jLabel26.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Tarifa unica");
        jLabel26.setName("jLabel26"); // NOI18N

        txtTarifaUnica.setEditable(false);
        txtTarifaUnica.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtTarifaUnica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTarifaUnica.setName("txtTarifaUnica"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(lblFraccion9, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPrecioHora, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtFraccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(lblFraccion10, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTarifaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtFraccion2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtFraccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtHorasCompletas, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(txtFraccion4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTarifaUnica, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(lblFraccion9))
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecioHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFraccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(lblFraccion10))
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTarifaMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFraccion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel24))
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBoletoPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFraccion3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel25))
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHorasCompletas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFraccion4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel26)
                                        .addGap(13, 13, 13)
                                        .addComponent(txtTarifaUnica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        btnModificar.setVisible(false);
        btnCancelar.setVisible(true);
        btnGuardar.setVisible(true);
        btnAgregar.setVisible(false);
        btnEliminar.setVisible(false);
        
        
        txtBoletoPerdido.setEditable(true);
        txtDescripcion.setEditable(true);
        txtFraccion1.setEditable(true);
        txtFraccion2.setEditable(true);
        txtFraccion3.setEditable(true);
        txtFraccion4.setEditable(true);
        txtHorasCompletas.setEditable(true);
        txtPrecioHora.setEditable(true);
        txtTarifaMaxima.setEditable(true);
        txtTarifaUnica.setEditable(true);
        
        estado = "Modificar";

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
          regresarEstado();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       btnAgregar.setVisible(false);
            btnModificar.setVisible(false);
            btnEliminar.setVisible(false);
            btnGuardar.setVisible(true);
            btnCancelar.setVisible(true);
            
            txtBoletoPerdido.setEditable(true);
            txtDescripcion.setEditable(true);
            txtFraccion1.setEditable(true);
            txtFraccion2.setEditable(true);
            txtFraccion3.setEditable(true);
            txtFraccion4.setEditable(true);
            txtHorasCompletas.setEditable(true);
            txtPrecioHora.setEditable(true);
            txtTarifaMaxima.setEditable(true);
            txtTarifaUnica.setEditable(true);
        
            txtBoletoPerdido.setText("");
            txtDescripcion.setText("");
            txtFraccion1.setText("");
            txtFraccion2.setText("");
            txtFraccion3.setText("");
            txtFraccion4.setText("");
            txtHorasCompletas.setText("");
            txtPrecioHora.setText("");
            txtTarifaMaxima.setText("");

            estado = "Agregar";

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        tarifa.eliminar();
        estado = "Eliminar";
        cargarTabla();
        txtDescripcion.setText("");
        txtFraccion1.setText("");
        txtFraccion2.setText("");
        txtFraccion3.setText("");
        txtFraccion4.setText("");
        txtHorasCompletas.setText("");
        txtPrecioHora.setText("");
        txtTarifaMaxima.setText("");
        estado = "";
    }//GEN-LAST:event_btnEliminarActionPerformed

    private boolean validaCamposEntrada() {
        if (txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce la descripcion de la tarifa.",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtDescripcion.grabFocus();
            return false;
        }
        if (txtFraccion1.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el costo de la primera fraccion",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFraccion1.grabFocus();
            return false;
        }
         if (txtFraccion2.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el costo de la segunda fraccion",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFraccion2.grabFocus();
            return false;
        }
          if (txtFraccion3.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el costo de la tercera fraccion",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFraccion3.grabFocus();
            return false;
        }
        if (txtFraccion4.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el costo de la cuarta fraccion.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtFraccion4.grabFocus();
            return false;
        }
        if (txtHorasCompletas.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Cuanta horas se cobran completas.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtHorasCompletas.grabFocus();
            return false;
        }
        if (txtPrecioHora.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el precio de la hora.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtPrecioHora.grabFocus();
            return false;
        }
        if (txtTarifaMaxima.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce la tarifa maxima.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtTarifaMaxima.grabFocus();
            return false;
        }
        if (txtTarifaUnica.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Si existe introduce la tarifa unica, de lo contrario ingresa 0.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtTarifaUnica.grabFocus();
            return false;
        }
        return true;
     }
 
 //    float costos[];
//    float precioHora;
//    float tarifaMaxima;
//    float precioBoletoPerdido;
//    int horasCompletas;
//    String descripcion;
 
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         if(validaCamposEntrada()){
             float costos[] = {Float.valueOf(txtFraccion1.getText()),Float.valueOf(txtFraccion2.getText()),Float.valueOf(txtFraccion3.getText()),Float.valueOf(txtFraccion4.getText())};
            if(estado.equals("Agregar")){
                Tarifa nuevaTarifa = new Tarifa(0,4,costos,Float.valueOf(txtPrecioHora.getText()),
                Float.valueOf(txtTarifaMaxima.getText()),Float.valueOf(txtBoletoPerdido.getText()),
                Integer.valueOf(txtHorasCompletas.getText()),txtDescripcion.getText(),Float.valueOf(txtTarifaUnica.getText()) );
                nuevaTarifa.guardar();
                tarifa = nuevaTarifa;
                cargarTabla();
                regresarEstado();
            }else if(estado.equals("Modificar")){
                 tarifa.setHorasCompletas(Integer.valueOf( txtHorasCompletas.getText()));
                tarifa.setPrecioBoletoPerdido(Float.valueOf( txtBoletoPerdido.getText()));
                tarifa.setPrecioHora(Float.valueOf( txtPrecioHora.getText()));
                tarifa.setDescripcion(txtDescripcion.getText());
                tarifa.setTarifaMaxima(Float.valueOf( txtTarifaMaxima.getText()));
                tarifa.setCostos(new float[]{Float.valueOf( txtFraccion1.getText()),
                    Float.valueOf( txtFraccion2.getText()),Float.valueOf( txtFraccion3.getText()),
                    Float.valueOf( txtFraccion4.getText())});
                tarifa.actualizar();

               
                tarifa.actualizar();
                regresarEstado();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

  private void regresarEstado() {
        btnAgregar.setVisible(true);
        btnEliminar.setVisible(true);
        btnModificar.setVisible(true);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
        
        txtBoletoPerdido.setEditable(false);
        txtDescripcion.setEditable(false);
        txtFraccion1.setEditable(false);
        txtFraccion2.setEditable(false);
        txtFraccion3.setEditable(false);
        txtFraccion4.setEditable(false);
        txtHorasCompletas.setEditable(false);
        txtPrecioHora.setEditable(false);
        txtTarifaMaxima.setEditable(false);
        txtTarifaUnica.setEditable(false);
        
        estado = "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFraccion10;
    private javax.swing.JLabel lblFraccion9;
    private javax.swing.JTable tblTarifas;
    private javax.swing.JTextField txtBoletoPerdido;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFraccion1;
    private javax.swing.JTextField txtFraccion2;
    private javax.swing.JTextField txtFraccion3;
    private javax.swing.JTextField txtFraccion4;
    private javax.swing.JTextField txtHorasCompletas;
    private javax.swing.JTextField txtPrecioHora;
    private javax.swing.JTextField txtTarifaMaxima;
    private javax.swing.JTextField txtTarifaUnica;
    // End of variables declaration//GEN-END:variables

  
}
