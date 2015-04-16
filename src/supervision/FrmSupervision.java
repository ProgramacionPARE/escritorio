package supervision;

import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.Auto;
import modelos.Conexion;
import modelos.Configuracion;
import modelos.Estacionamiento;
import modelos.IUseCalendar;
import modelos.Main;
import modelos.Monitor;
import modelos.MonitorEstacionamiento;
import modelos.Rest;
import modelos.Turno;
import modelos.TurnoDetalles;
import vistas.FrmCalendario;

public class FrmSupervision extends java.awt.Dialog implements IUseCalendar {

    private ArrayList<MonitorEstacionamiento> estacionamientos;
    private ArrayList<Turno> turnosGlo;
    private ArrayList<TurnoDetalles> detallesGlo;
    private ArrayList<Auto> autosGlo;

    private Turno turnoGlo;
    private TurnoDetalles detalleGlo;
    private Auto autoGlo;

    private Estacionamiento estacionamiento;

    Frame parent;
    Turno turno;

    public FrmSupervision(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        this.turno = Main.getInstance().getTurnoActual();
        initComponents();
        personalizarTablas();
        this.setBackground(Color.white);
        pack();
        setLocationRelativeTo(parent);
        estacionamientos = new ArrayList();
        this.txtFecha.setText(Tiempo.getFecha());
        Monitor monitor = Monitor.getInstancia();
        Iterator<MonitorEstacionamiento> iterator = monitor.getEstacionamientos().iterator();

        while (iterator.hasNext()) {
            MonitorEstacionamiento next = iterator.next();
            estacionamientos.add(next);
            cbxEstacionamientos.addItem(next.getNombre());

        }
        conexionEstacionamiento();
        eventosTablas();
    }

    private void personalizarTablas() {

        tblAutos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (table.getModel().getValueAt(row, 1).equals("Fuera")) {
                    c.setBackground(new Color(255, 255, 102));
                }
                if (table.getModel().getValueAt(row, 1).equals("Dentro")) {
                    c.setBackground(new Color(153, 255, 102));
                }
                if (table.getModel().getValueAt(row, 1).equals("Contra")) {
                    c.setBackground(new Color(255, 0, 255));
                }
                if (table.getModel().getValueAt(row, 1).equals("Manual")) {
                    c.setBackground(new Color(0, 255, 255));
                }
                if (table.getModel().getValueAt(row, 1).equals("Perdido")) {
                    c.setBackground(new Color(255, 102, 102));
                }
                if (table.getModel().getValueAt(row, 1).equals("Cancelado")) {
                    c.setBackground(new Color(153, 153, 153));
                }

                this.setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });
    }

    private void conexionEstacionamiento() {
        cbxEstacionamientos.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == 1) {
                    Configuracion.getInstancia().setIp(estacionamientos.get(cbxEstacionamientos.getSelectedIndex() - 1).getIp());
                    Conexion conexion = Conexion.getInstance();
                    String testConex = conexion.testConnection();
                    if (testConex.equals("ok")) {
                        estacionamiento = Estacionamiento.getDatos();
                        Rest.login(estacionamiento);
                        JOptionPane.showMessageDialog(null, "Conexion exitosa", "Ok", JOptionPane.INFORMATION_MESSAGE);
                        // cargarTurnos();

                    } else {
                        JOptionPane.showMessageDialog(null, testConex, "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    private void cargarTurnos() {
        turnosGlo = Turno.getTurnosByFechaAbierto(txtFecha.getText());
        Iterator<Turno> iterator = turnosGlo.iterator();
        DefaultTableModel modelTurno = (DefaultTableModel) tblTurnos.getModel();
        modelTurno.getDataVector().removeAllElements();
        modelTurno.fireTableDataChanged();
        while (iterator.hasNext()) {
            Turno turnoAux = iterator.next();
            modelTurno.addRow(new Object[]{
                turnoAux.getTipoTurno(),
                turnoAux.getHoraCierre() != null ? "CERRADO" : "ABIERTO",
                turnoAux.getFechaApertura(),
                turnoAux.getHoraApertura(),
                turnoAux.getFechaCierre(),
                turnoAux.getHoraCierre(),""
                //turnoAux.getEmpleadoEntrada().getNombre()

            });
        }
        this.tblTurnos.setModel(modelTurno);
    }

    private void cargarDetalles(HashMap<String, TurnoDetalles> detallesTurno) {
        detallesGlo = new ArrayList();
        Iterator<Map.Entry<String, TurnoDetalles>> iterator = detallesTurno.entrySet().iterator();
        DefaultTableModel modelDetalle = (DefaultTableModel) tblDetalles.getModel();
        modelDetalle.getDataVector().removeAllElements();
        modelDetalle.fireTableDataChanged();
        while (iterator.hasNext()) {
            Map.Entry<String, TurnoDetalles> next = iterator.next();
            TurnoDetalles detalle = next.getValue();
            detallesGlo.add(detalle);
            modelDetalle.addRow(new Object[]{
                detalle.getFolioInicial(),
                detalle.getFolioFinal(),
                detalle.getNoBol(),
                detalle.getNoBolCobrados(),
                detalle.getNoBolContra(),
                detalle.getNoBolManual(),
                detalle.getNoBolCancelados(),
                detalle.getNoBolPerdidos(),
                detalle.getNoBolTurnoA(),
                detalle.getNoBolTurnoS(),
                detalle.getTotal()
            });
        }
    }

    private void cargarAutos(Turno turno, TurnoDetalles deta) {
        ArrayList<Auto> autos = Auto.getAutosCerradosTurnoActual(turno.getId(), deta.getSerie());
        autos.addAll(Auto.getAutosPendientesByTurnoEntrada(turno.getId(), deta.getSerie()));
        Auto.ordenarByProgresivo(autos);
        autosGlo = autos;
        Iterator<Auto> iterator = autos.iterator();
        DefaultTableModel modelAutos = (DefaultTableModel) tblAutos.getModel();
        modelAutos.getDataVector().removeAllElements();
        modelAutos.fireTableDataChanged();
        while (iterator.hasNext()) {
            String estado = "";
            Auto auto = iterator.next();
            if (auto.isDentro()) {
                estado = "Dentro";
            } else if (auto.isBoletoCancelado()) {
                estado = "Cancelado";
            } else if (auto.isBoletoContra()) {
                estado = "Contra";
            } else if (auto.isBoletoManual()) {
                estado = "Manual";
            } else if (auto.isBoletoPerdido()) {
                estado = "Perdido";
            } else {
                estado = "Fuera";
            }

            modelAutos.addRow(new Object[]{
                auto.getProgresivo(),
                estado,
                auto.getIdTurnoEntrada(),
                auto.getFechaEntrada(),
                auto.getHoraEntrada(),
                auto.getIdTurnoSalida(),
                auto.getFechaSalida(),
                auto.getHoraSalida(),
                auto.getMontoTangible()
            });
        }

    }

    private void eventosTablas() {
        tblTurnos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tblTurnos.getSelectedRow() >= 0) {
                    turnoGlo = turnosGlo.get(tblTurnos.getSelectedRow());
                    cargarDetalles(turnosGlo.get(tblTurnos.getSelectedRow()).getDetallesTurno());

                } else {
                    DefaultTableModel modelDetalle = (DefaultTableModel) tblDetalles.getModel();
                    modelDetalle.getDataVector().removeAllElements();
                }
            }
        });

        tblDetalles.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tblDetalles.getSelectedRow() >= 0) {
                    detalleGlo = detallesGlo.get(tblDetalles.getSelectedRow());
                    cargarAutos(turnoGlo, detallesGlo.get(tblDetalles.getSelectedRow()));
                } else {
                    DefaultTableModel modelDetalle = (DefaultTableModel) tblDetalles.getModel();
                    modelDetalle.getDataVector().removeAllElements();
                }
            }
        });

        tblAutos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tblAutos.getSelectedRow() >= 0) {
                    autoGlo = autosGlo.get(tblAutos.getSelectedRow());
                }/*else{
                 DefaultTableModel modelDetalle = (DefaultTableModel) tblDetalles.getModel();
                 modelDetalle.getDataVector().removeAllElements();
                 }*/

            }
        });

    }

    @Override
    public void updateFecha(String fecha) {
        this.txtFecha.setText(fecha);
        cargarTurnos();
    }

    @Override
    public String getFechaActual() {
        return this.txtFecha.getText();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbxEstacionamientos = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarFolio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAutos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalles = new javax.swing.JTable();
        btnActualizarTurno = new javax.swing.JButton();
        btnActualizarDetalle = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();
        Cobrar = new javax.swing.JButton();
        btnAgregarConcepto = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTurnos = new javax.swing.JTable();
        btnCerrarDia = new javax.swing.JButton();
        btnGenerarStickers = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnAgregarDeposito1 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Estacionamientos");
        jLabel1.setName("jLabel1"); // NOI18N

        cbxEstacionamientos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cbxEstacionamientos.setName("cbxEstacionamientos"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel2.setText("Buscar por folio");
        jLabel2.setName("jLabel2"); // NOI18N

        txtBuscarFolio.setName("txtBuscarFolio"); // NOI18N
        txtBuscarFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarFolioActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel3.setText("Cambiar fecha");
        jLabel3.setName("jLabel3"); // NOI18N

        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setName("txtFecha"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setText("Detalle turnos");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel6.setText("Turnos");
        jLabel6.setName("jLabel6"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tblAutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Progresivo", "Estado", "Turno entrada", "Fecha entrada", "Hora entrada", "Turno salida ", "Fecha entrada", "Hora salida", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAutos.setName("tblAutos"); // NOI18N
        jScrollPane2.setViewportView(tblAutos);

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel7.setText("Autos");
        jLabel7.setName("jLabel7"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        tblDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio inicial", "Folio Final", "Expedidos", "Cobrados normal", "Cobrados contra", "Cobrados manualmente", "Boletos cancelados", "Boletos perdidos", "Pendientes TA", "Pendiente TS", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDetalles.setName("tblDetalles"); // NOI18N
        jScrollPane3.setViewportView(tblDetalles);

        btnActualizarTurno.setText("Actualizar");
        btnActualizarTurno.setName("btnActualizarTurno"); // NOI18N
        btnActualizarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTurnoActionPerformed(evt);
            }
        });

        btnActualizarDetalle.setText("Actualizar");
        btnActualizarDetalle.setName("btnActualizarDetalle"); // NOI18N
        btnActualizarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarDetalleActionPerformed(evt);
            }
        });

        Actualizar.setText("Actualizar");
        Actualizar.setName("Actualizar"); // NOI18N
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        Cobrar.setText("Cerrar boleto");
        Cobrar.setName("Cobrar"); // NOI18N
        Cobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CobrarActionPerformed(evt);
            }
        });

        btnAgregarConcepto.setText("Agregar boleto");
        btnAgregarConcepto.setName("btnAgregarConcepto"); // NOI18N
        btnAgregarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarConceptoActionPerformed(evt);
            }
        });

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        tblTurnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo turno", "Estado", "Fecha apertura", "Hora apertura", "Fecha cierre", "Hora cierre", "Cajero"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTurnos.setName("tblTurnos"); // NOI18N
        jScrollPane4.setViewportView(tblTurnos);

        btnCerrarDia.setText("Cerrar dia");
        btnCerrarDia.setName("btnCerrarDia"); // NOI18N

        btnGenerarStickers.setText("Generar stickers");
        btnGenerarStickers.setName("btnGenerarStickers"); // NOI18N
        btnGenerarStickers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarStickersActionPerformed(evt);
            }
        });

        jButton1.setText("...");
        jButton1.setToolTipText("");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAgregarDeposito1.setText("Agregar deposito");
        btnAgregarDeposito1.setName("btnAgregarDeposito1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarTurno))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxEstacionamientos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(txtBuscarFolio))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCerrarDia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnGenerarStickers, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(276, 276, 276))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarDetalle))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarConcepto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cobrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Actualizar))
                    .addComponent(jScrollPane4))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(887, 887, 887)
                    .addComponent(btnAgregarDeposito1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addGap(287, 287, 287)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(btnGenerarStickers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEstacionamientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarDia)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarTurno)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarDetalle)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Actualizar)
                    .addComponent(Cobrar)
                    .addComponent(btnAgregarConcepto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(btnAgregarDeposito1)
                    .addContainerGap(629, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FrmCalendario(null, true, this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnActualizarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTurnoActionPerformed
        if (turnoGlo != null) {
            Rest.sendTurno(turnoGlo, estacionamiento);
        }
    }//GEN-LAST:event_btnActualizarTurnoActionPerformed

    private void btnActualizarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarDetalleActionPerformed
        if (detalleGlo != null) {
            Rest.sendTurnoDetalle(detalleGlo, estacionamiento);
        }
    }//GEN-LAST:event_btnActualizarDetalleActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        if (autoGlo != null) {
            Rest.sendAuto(autoGlo, estacionamiento);
            JOptionPane.showMessageDialog(this, "Auto actualizado");
        }
    }//GEN-LAST:event_ActualizarActionPerformed

    private void CobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CobrarActionPerformed
        if (autoGlo != null) {
            if (autoGlo.isDentro()) {
                new FrmSupervisionCerrarBoleto(null, true, autoGlo, estacionamiento, detalleGlo);
            }
            //else
            // J 
        }
    }//GEN-LAST:event_CobrarActionPerformed

    private void txtBuscarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarFolioActionPerformed
        Auto autoByProgresivo = Auto.getAutoByProgresivo(Integer.valueOf(txtBuscarFolio.getText()));
        if (autoByProgresivo != null) {
            String fechaEntrada = autoByProgresivo.getFechaEntrada();
            this.txtFecha.setText(fechaEntrada);
            cargarTurnos();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFolioActionPerformed

    private void btnAgregarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarConceptoActionPerformed
        Auto auto = new Auto();
        auto.setHoraEntrada(Tiempo.getHora());
        auto.setFechaEntrada(Tiempo.getFecha());
        new FrmSupervisionAgregarBoleto(parent,true);
        //(parent, true, turno, auto, estacionamiento);
    }//GEN-LAST:event_btnAgregarConceptoActionPerformed

    private void btnGenerarStickersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarStickersActionPerformed
       
    }//GEN-LAST:event_btnGenerarStickersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Cobrar;
    private javax.swing.JButton btnActualizarDetalle;
    private javax.swing.JButton btnActualizarTurno;
    private javax.swing.JButton btnAgregarConcepto;
    private javax.swing.JButton btnAgregarDeposito1;
    private javax.swing.JButton btnCerrarDia;
    private javax.swing.JButton btnGenerarStickers;
    private javax.swing.JComboBox cbxEstacionamientos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblAutos;
    private javax.swing.JTable tblDetalles;
    private javax.swing.JTable tblTurnos;
    private javax.swing.JTextField txtBuscarFolio;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables

}
