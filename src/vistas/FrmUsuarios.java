/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import ModelosAux.Seguridad;
import java.awt.Frame;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Empleado;
import modelos.Estacionamiento;
import modelos.Main;
import modelos.Operacion;
import modelos.Turno;
import vistas.formatos.FrmInfoUsuario;

/**
 *
 * @author sistema
 */
public class FrmUsuarios extends javax.swing.JDialog {

    Turno turno;
    Estacionamiento estacionamiento;
    Empleado empleado;
    String estado = "";
    private Frame parent;
    /**
     * Creates new form FrmUsuarios
     */
    public FrmUsuarios(Frame parent, boolean modal) {
        super(parent, "Usuarios", modal);
        initComponents(); 
        this.turno = Main.getInstance().getTurnoActual();
        this.estacionamiento = Main.getInstance().getEstacionamiento();
        this.parent = parent;
        cargarTabla();
        tblUsuario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(estado.equals("")){
                    empleado = Empleado.getById(Long.valueOf((String) tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 0)));
                    btnEliminar.setVisible(true);
                    btnImprimir.setVisible(true);
                    btnModificar.setVisible(true);
                    cargarDatos();
                }
            }
        });
        
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        
        btnEliminar.setVisible(false);
        btnCambiarContraseña.setVisible(false);
        btnImprimir.setVisible(false);
        String [] permisos=null;
        if ( turno.getEmpleadoEntrada().getTipoPuesto().equals("Cajero") ){  
            permisos =  new String[] { "Cajero" };
        }else if ( turno.getEmpleadoEntrada().getTipoPuesto().equals( "Encargado") ){
            permisos =  new String[] { "Cajero","Encargado" };
        }else if ( turno.getEmpleadoEntrada().getTipoPuesto().equals(  "Supervisor") ){
            permisos =  new String[] { "Cajero","Encargado","Supervisor" };
        }else if ( turno.getEmpleadoEntrada().getTipoPuesto().equals(  "Auditor" )){
            permisos =  new String[] { "Cajero","Encargado","Supervisor","Auditor"};
        }else if ( turno.getEmpleadoEntrada().getTipoPuesto().equals( "Administrador") ){
            permisos =  new String[] { "Cajero","Encargado","Supervisor","Auditor","Administrador", };
        } 
       cbxTipo.setModel(new javax.swing.DefaultComboBoxModel(permisos));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );     
        tblUsuario.setDefaultRenderer(String.class, centerRenderer);
        FrmPrincipal.nuevaVentana(this);    
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void cargarTabla() {
        DefaultTableModel model = (DefaultTableModel) this.tblUsuario.getModel();
        model.getDataVector().removeAllElements();
        ArrayList<Empleado> empleados = Empleado.getAll();
        Iterator<Empleado> iterator = empleados.iterator();
        while (iterator.hasNext()) {
            Empleado next = iterator.next();
            if(new Operacion(this.parent).requierePermisos( turno.getEmpleadoEntrada(), next.getTipoPuesto(),false))
                model.addRow(new String[]{String.valueOf(next.getId()), next.getNombre(), next.getTipoPuesto()});
            
        }
         btnModificar.setVisible(false);
         btnEliminar.setVisible(false);
         btnImprimir.setVisible(false);
    }

    private void cargarDatos() {
        this.txtNombre.setText(empleado.getNombre());
        this.txtUsuario.setText(empleado.getUsuario());
        this.txtNivel.setText(String.valueOf(empleado.getNivel()));
        this.txtContraseña.setText(empleado.getContraseña());
        switch (empleado.getTipoPuesto()){
            case "Cajero":
                cbxTipo.setSelectedIndex(0);
                break;
            case "Encargado":
                cbxTipo.setSelectedIndex(1);
                break;
            case "Supervisor":
                cbxTipo.setSelectedIndex(2);
                break;
            case "Auditor":
                cbxTipo.setSelectedIndex(3);
                break;
            case "Administrador":
                cbxTipo.setSelectedIndex(4);
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox();
        btnAgregar = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNivel = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        btnCambiarContraseña = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        filler1.setName("filler1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblUsuario.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuario.setName("tblUsuario"); // NOI18N
        jScrollPane1.setViewportView(tblUsuario);
        if (tblUsuario.getColumnModel().getColumnCount() > 0) {
            tblUsuario.getColumnModel().getColumn(0).setMinWidth(40);
            tblUsuario.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblUsuario.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jLabel2.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");
        jLabel2.setName("jLabel2"); // NOI18N

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setName("txtNombre"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de usiario");
        jLabel3.setName("jLabel3"); // NOI18N

        cbxTipo.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1" }));
        cbxTipo.setToolTipText("");
        cbxTipo.setEnabled(false);
        cbxTipo.setName("cbxTipo"); // NOI18N

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        filler2.setName("filler2"); // NOI18N

        btnModificar.setBackground(new java.awt.Color(255, 255, 255));
        btnModificar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setName("btnModificar"); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel4.setText("Nivel");
        jLabel4.setName("jLabel4"); // NOI18N

        txtNivel.setEditable(false);
        txtNivel.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtNivel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNivel.setText("1");
        txtNivel.setName("txtNivel"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel5.setText("Sistema");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel6.setText("Usuario");
        jLabel6.setName("jLabel6"); // NOI18N

        txtUsuario.setEditable(false);
        txtUsuario.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setName("txtUsuario"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        jLabel7.setText("Contraseña");
        jLabel7.setName("jLabel7"); // NOI18N

        txtContraseña.setEditable(false);
        txtContraseña.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContraseña.setName("txtContraseña"); // NOI18N

        btnCambiarContraseña.setBackground(new java.awt.Color(255, 255, 255));
        btnCambiarContraseña.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnCambiarContraseña.setText("<html><center>Generar <br>contraseña</center></html>");
        btnCambiarContraseña.setName("btnCambiarContraseña"); // NOI18N
        btnCambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContraseñaActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setName("btnImprimir"); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setFont(new java.awt.Font("Droid Sans", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("    ");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(860, 860, 860)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)
                        .addGap(6, 6, 6)
                        .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnCambiarContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

            btnAgregar.setVisible(false);
            btnModificar.setVisible(false);
            btnEliminar.setVisible(false);
            btnGuardar.setVisible(true);
            btnCancelar.setVisible(true);
            btnCambiarContraseña.setVisible(true);
            btnImprimir.setVisible(false);

            txtNombre.setEditable(true);
            cbxTipo.setEnabled(true);
            txtNivel.setEditable(true);
            txtUsuario.setEditable(true);
            tblUsuario.setEnabled(false);

            txtNombre.setText("");
            txtNivel.setText("");
            txtUsuario.setText("");
            txtContraseña.setText("");

            estado = "Agregar";

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
      
            btnAgregar.setVisible(false);
            btnModificar.setVisible(false);
            btnEliminar.setVisible(false);
            btnGuardar.setVisible(true);
            btnCancelar.setVisible(true);
            btnCambiarContraseña.setVisible(true);
            btnImprimir.setVisible(false);

            txtNombre.setEditable(true);
            cbxTipo.setEnabled(true);
            txtNivel.setEditable(true);
            txtUsuario.setEditable(true);
            tblUsuario.setEnabled(false);

            estado = "Modificar";

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        empleado.eliminar();
        estado = "Eliminar";
        cargarTabla();
        txtNombre.setText("");
        txtNivel.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        estado = "";
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(validaCamposEntrada()){
            if(estado.equals("Agregar")){
                Empleado nuevoEmpleado = new Empleado(0, Integer.valueOf(txtNivel.getText()),txtNombre.getText()
                        , (String)cbxTipo.getSelectedItem(),txtUsuario.getText(),txtContraseña.getText(),Seguridad.getClave(6));
                nuevoEmpleado.guardar();
                empleado = nuevoEmpleado;
                regresarEstado();
            }else if(estado.equals("Modificar")){
                empleado.setNivel( Integer.valueOf(txtNivel.getText()));
                empleado.setNombre(txtNombre.getText());
                empleado.setUsuario(txtUsuario.getText());
                empleado.setContraseña(txtContraseña.getText());
                empleado.setTipoPuesto((String) cbxTipo.getSelectedItem());
                empleado.actualizar();
                regresarEstado();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
 private boolean validaCamposEntrada() {
        if (txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el nombre completo del empleado.",
            "Campo faltante",JOptionPane.WARNING_MESSAGE);
            txtNombre.grabFocus();
            return false;
        }
        if (txtNivel.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el nivel donde se ubica el empleado.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtNivel.grabFocus();
            return false;
        }
         if (txtUsuario.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce el nombre de usuario del empleado.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtUsuario.grabFocus();
            return false;
        }
          if (txtContraseña.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Genera la contraseña del empleado.",
            "Formato erroneo",JOptionPane.WARNING_MESSAGE);
            txtContraseña.grabFocus();
            return false;
        }
          
        return true;
     }
    void regresarEstado(){
        btnAgregar.setVisible(true);
        btnModificar.setVisible(true);
        btnEliminar.setVisible(false);
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        btnImprimir.setVisible(false);
        txtNombre.setEditable(false);
        cbxTipo.setEnabled(false);
        txtNivel.setEditable(false);
        txtUsuario.setEditable(false);
        tblUsuario.setEnabled(true);
        btnCambiarContraseña.setVisible(false);
        btnImprimir.setVisible(false);
        if (empleado!=null)cargarDatos();
        cargarTabla();
        estado = "";
    }
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        regresarEstado();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContraseñaActionPerformed
        txtContraseña.setText(Seguridad.getClave(6));
    }//GEN-LAST:event_btnCambiarContraseñaActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
      new FrmInfoUsuario(this,false,PrinterJob.getPrinterJob());
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
         FrmPrincipal.restaurarUltimaVentana();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCambiarContraseña;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cbxTipo;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtNivel;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
