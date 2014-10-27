
package vistas;

import ModelosAux.Tiempo;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modeloReportes.CorteTurno;
import modelos.Arqueo;
import modelos.Auto;
import modelos.DetallesArqueo;
import modelos.DetallesMovimiento;
import modelos.Estacionamiento;
import modelos.RetiroParcial;
import modelos.Turno;

/**
 *
 * @author sistema
 */
public class FrmArqueo extends javax.swing.JDialog {
    Estacionamiento estacionamiento;
    Turno turno;
    String serie;
    /**
     * Creates new form FrmArqueo
     */
    public FrmArqueo(java.awt.Frame parent, boolean modal,Turno turno,Estacionamiento estacionamiento,String serie) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        ArrayList<DetallesMovimiento> generarDetalles = DetallesMovimiento.generarDetalles(
                Auto.getAutosCobradosTurnoActual(turno.getId(),serie),
                Auto.getAutosBoletoPerdidoTurnoActual(turno.getId(),serie));
        DetallesMovimiento.ordenarPorPU(generarDetalles);
        llenarTabla(generarDetalles,turno);
        total();
        this.estacionamiento =  estacionamiento;
        this.txtFolioInicial.setText(String.valueOf(turno.getDetallesTurno().get(serie).getFolioInicial()));
        this.txtFolioFinal.setText(String.valueOf(turno.getDetallesTurno().get(serie).getFolioFinal()));
        this.turno = turno;
        this.serie = serie;
        setVisible(true);
    }
    
    
    private void llenarTabla(ArrayList<DetallesMovimiento> generarDetalles,Turno turno){
        float totalImporteBoletos = 0f;
        int totalBoletos = 0;
        //Boletos
        DefaultTableModel modelVenta =(DefaultTableModel) this.tblVenta.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );     
        tblVenta.setDefaultRenderer(String.class, centerRenderer);
        Iterator<DetallesMovimiento> iterator = generarDetalles.iterator();
        while(iterator.hasNext()){
            DetallesMovimiento next = iterator.next();
           // if(next.getTipo().equals("Boleto")){
                totalBoletos+=next.getNoBol();
                totalImporteBoletos+= next.getImporte();
                modelVenta.addRow(new Object[]{next.getNoBol(),next.getRangoHorario(), next.getPrecioUnitario(),next.getImporte()});
            //}
        }
        txtNoBoletos.setText(String.valueOf(totalBoletos));
        txtImporteTotal.setText(String.valueOf(totalImporteBoletos));
        
        float totalImporteRetiros = 0f;
        
        //Retiros
        DefaultTableModel modelRetiros =(DefaultTableModel) this.tblRetiros.getModel();
        tblRetiros.setDefaultRenderer(String.class, centerRenderer);
        ArrayList<RetiroParcial> retirosParciales = turno.getRetirosParciales();
        Iterator<RetiroParcial> retiroIterator = retirosParciales.iterator();
        
        while(retiroIterator.hasNext()){
            RetiroParcial next = retiroIterator.next();
            totalImporteRetiros+= next.getMonto();
            modelRetiros.addRow(new Object[]{next.getProgresivo(),next.getHora(), next.getMonto()});
        }
        
        txtRetirosSubtotal.setText(String.valueOf(totalImporteRetiros));
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VENTAS = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        VENTAS1 = new javax.swing.JLabel();
        VENTAS2 = new javax.swing.JLabel();
        VENTAS3 = new javax.swing.JLabel();
        VENTAS4 = new javax.swing.JLabel();
        VENTAS6 = new javax.swing.JLabel();
        VENTAS5 = new javax.swing.JLabel();
        VENTAS7 = new javax.swing.JLabel();
        VENTAS8 = new javax.swing.JLabel();
        VENTAS9 = new javax.swing.JLabel();
        VENTAS10 = new javax.swing.JLabel();
        VENTAS11 = new javax.swing.JLabel();
        VENTAS12 = new javax.swing.JLabel();
        VENTAS13 = new javax.swing.JLabel();
        VENTAS14 = new javax.swing.JLabel();
        VENTAS15 = new javax.swing.JLabel();
        VENTAS16 = new javax.swing.JLabel();
        VENTAS17 = new javax.swing.JLabel();
        VENTAS18 = new javax.swing.JLabel();
        VENTAS19 = new javax.swing.JLabel();
        txtNoB500 = new javax.swing.JTextField();
        txtNoB200 = new javax.swing.JTextField();
        txtNoB100 = new javax.swing.JTextField();
        txtNoB50 = new javax.swing.JTextField();
        txtNoB20 = new javax.swing.JTextField();
        txtImporteB500 = new javax.swing.JTextField();
        txtImporteB200 = new javax.swing.JTextField();
        txtImporteB100 = new javax.swing.JTextField();
        txtImporteB50 = new javax.swing.JTextField();
        txtImporteB20 = new javax.swing.JTextField();
        txtNoM20 = new javax.swing.JTextField();
        txtImporteM20 = new javax.swing.JTextField();
        txtNoM10 = new javax.swing.JTextField();
        txtImporteM10 = new javax.swing.JTextField();
        txtNoM5 = new javax.swing.JTextField();
        txtImporteM5 = new javax.swing.JTextField();
        txtNoM2 = new javax.swing.JTextField();
        txtImporteM2 = new javax.swing.JTextField();
        txtNoM1 = new javax.swing.JTextField();
        txtImporteM1 = new javax.swing.JTextField();
        txtNoM02 = new javax.swing.JTextField();
        txtNoM05 = new javax.swing.JTextField();
        txtImporteM05 = new javax.swing.JTextField();
        txtImporteM02 = new javax.swing.JTextField();
        txtImporteMSubtotal = new javax.swing.JTextField();
        txtImporteBSubtotal = new javax.swing.JTextField();
        VENTAS21 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        VENTAS22 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtNoM01 = new javax.swing.JTextField();
        txtImporteM01 = new javax.swing.JTextField();
        btnImprimirArqueo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtFolioInicial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFolioFinal = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRetiros = new javax.swing.JTable();
        VENTAS23 = new javax.swing.JLabel();
        VENTAS24 = new javax.swing.JLabel();
        VENTAS25 = new javax.swing.JLabel();
        txtNoBoletos = new javax.swing.JTextField();
        VENTAS20 = new javax.swing.JLabel();
        txtRetirosSubtotal = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VENTAS.setText("VENTAS");
        VENTAS.setName("VENTAS"); // NOI18N
        getContentPane().add(VENTAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblVenta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Boletos", "Rangos", "Tarifas", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVenta.setName("tblVenta"); // NOI18N
        jScrollPane1.setViewportView(tblVenta);
        if (tblVenta.getColumnModel().getColumnCount() > 0) {
            tblVenta.getColumnModel().getColumn(0).setResizable(false);
            tblVenta.getColumnModel().getColumn(1).setResizable(false);
            tblVenta.getColumnModel().getColumn(2).setResizable(false);
            tblVenta.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 74, 439, 650));

        VENTAS1.setText("ARQUEO");
        VENTAS1.setName("VENTAS1"); // NOI18N
        getContentPane().add(VENTAS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 360, -1));

        VENTAS2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS2.setText("$ 500.00");
        VENTAS2.setName("VENTAS2"); // NOI18N
        getContentPane().add(VENTAS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 80, -1));

        VENTAS3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VENTAS3.setText("Cantidad");
        VENTAS3.setName("VENTAS3"); // NOI18N
        getContentPane().add(VENTAS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 120, -1));

        VENTAS4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VENTAS4.setText("Importe");
        VENTAS4.setName("VENTAS4"); // NOI18N
        getContentPane().add(VENTAS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 120, -1));

        VENTAS6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS6.setText("Subtotal");
        VENTAS6.setName("VENTAS6"); // NOI18N
        getContentPane().add(VENTAS6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 193, -1));

        VENTAS5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VENTAS5.setText("Billetes");
        VENTAS5.setMaximumSize(null);
        VENTAS5.setName("VENTAS5"); // NOI18N
        getContentPane().add(VENTAS5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 80, -1));

        VENTAS7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS7.setText("$ 200.00");
        VENTAS7.setName("VENTAS7"); // NOI18N
        getContentPane().add(VENTAS7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 80, -1));

        VENTAS8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS8.setText("$ 100.00");
        VENTAS8.setName("VENTAS8"); // NOI18N
        getContentPane().add(VENTAS8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 80, -1));

        VENTAS9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS9.setText("$ 50.00");
        VENTAS9.setName("VENTAS9"); // NOI18N
        getContentPane().add(VENTAS9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 80, -1));

        VENTAS10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS10.setText("$ 20.00");
        VENTAS10.setName("VENTAS10"); // NOI18N
        getContentPane().add(VENTAS10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 80, -1));

        VENTAS11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS11.setText("$ 1.00");
        VENTAS11.setName("VENTAS11"); // NOI18N
        getContentPane().add(VENTAS11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 80, -1));

        VENTAS12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS12.setText("$ 2.00");
        VENTAS12.setName("VENTAS12"); // NOI18N
        getContentPane().add(VENTAS12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 80, -1));

        VENTAS13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS13.setText("$ 5.00");
        VENTAS13.setName("VENTAS13"); // NOI18N
        getContentPane().add(VENTAS13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, 80, -1));

        VENTAS14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS14.setText("$ 10.00");
        VENTAS14.setName("VENTAS14"); // NOI18N
        getContentPane().add(VENTAS14, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 80, -1));

        VENTAS15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS15.setText("$ 20.00");
        VENTAS15.setName("VENTAS15"); // NOI18N
        getContentPane().add(VENTAS15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 80, 18));

        VENTAS16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS16.setText("Retiros Parciales");
        VENTAS16.setName("VENTAS16"); // NOI18N
        getContentPane().add(VENTAS16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 550, 335, -1));

        VENTAS17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS17.setText("$ 0.20");
        VENTAS17.setName("VENTAS17"); // NOI18N
        getContentPane().add(VENTAS17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 80, -1));

        VENTAS18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS18.setText("$ 0.50");
        VENTAS18.setName("VENTAS18"); // NOI18N
        getContentPane().add(VENTAS18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 80, -1));

        VENTAS19.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        VENTAS19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS19.setText("$ 0.10");
        VENTAS19.setName("VENTAS19"); // NOI18N
        getContentPane().add(VENTAS19, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 80, -1));

        txtNoB500.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoB500.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoB500.setText("0");
        txtNoB500.setToolTipText("");
        txtNoB500.setName("txtNoB500"); // NOI18N
        txtNoB500.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoB500FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoB500FocusLost(evt);
            }
        });
        txtNoB500.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoB500KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoB500KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoB500, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 120, -1));

        txtNoB200.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoB200.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoB200.setText("0");
        txtNoB200.setToolTipText("");
        txtNoB200.setName("txtNoB200"); // NOI18N
        txtNoB200.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoB200FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoB200FocusLost(evt);
            }
        });
        txtNoB200.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoB200KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoB200KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoB200, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 120, -1));

        txtNoB100.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoB100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoB100.setText("0");
        txtNoB100.setToolTipText("");
        txtNoB100.setName("txtNoB100"); // NOI18N
        txtNoB100.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoB100FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoB100FocusLost(evt);
            }
        });
        txtNoB100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoB100KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoB100KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoB100, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 120, -1));

        txtNoB50.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoB50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoB50.setText("0");
        txtNoB50.setToolTipText("");
        txtNoB50.setName("txtNoB50"); // NOI18N
        txtNoB50.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoB50FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoB50FocusLost(evt);
            }
        });
        txtNoB50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoB50KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoB50KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoB50, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 120, -1));

        txtNoB20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoB20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoB20.setText("0");
        txtNoB20.setToolTipText("");
        txtNoB20.setName("txtNoB20"); // NOI18N
        txtNoB20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoB20FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoB20FocusLost(evt);
            }
        });
        txtNoB20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoB20KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoB20KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoB20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 120, -1));

        txtImporteB500.setEditable(false);
        txtImporteB500.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteB500.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteB500.setText("0.0");
        txtImporteB500.setToolTipText("");
        txtImporteB500.setName("txtImporteB500"); // NOI18N
        getContentPane().add(txtImporteB500, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 122, -1));

        txtImporteB200.setEditable(false);
        txtImporteB200.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteB200.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteB200.setText("0.0");
        txtImporteB200.setToolTipText("");
        txtImporteB200.setName("txtImporteB200"); // NOI18N
        getContentPane().add(txtImporteB200, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 122, -1));

        txtImporteB100.setEditable(false);
        txtImporteB100.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteB100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteB100.setText("0.0");
        txtImporteB100.setToolTipText("");
        txtImporteB100.setName("txtImporteB100"); // NOI18N
        getContentPane().add(txtImporteB100, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 122, -1));

        txtImporteB50.setEditable(false);
        txtImporteB50.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteB50.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteB50.setText("0.0");
        txtImporteB50.setToolTipText("");
        txtImporteB50.setName("txtImporteB50"); // NOI18N
        getContentPane().add(txtImporteB50, new org.netbeans.lib.awtextra.AbsoluteConstraints(679, 161, 122, -1));

        txtImporteB20.setEditable(false);
        txtImporteB20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteB20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteB20.setText("0.0");
        txtImporteB20.setToolTipText("");
        txtImporteB20.setName("txtImporteB20"); // NOI18N
        getContentPane().add(txtImporteB20, new org.netbeans.lib.awtextra.AbsoluteConstraints(679, 188, 122, -1));

        txtNoM20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM20.setText("0");
        txtNoM20.setToolTipText("");
        txtNoM20.setName("txtNoM20"); // NOI18N
        txtNoM20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM20FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM20FocusLost(evt);
            }
        });
        txtNoM20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM20KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM20KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 120, -1));

        txtImporteM20.setEditable(false);
        txtImporteM20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM20.setText("0.0");
        txtImporteM20.setToolTipText("");
        txtImporteM20.setName("txtImporteM20"); // NOI18N
        getContentPane().add(txtImporteM20, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, 122, -1));

        txtNoM10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM10.setText("0");
        txtNoM10.setToolTipText("");
        txtNoM10.setName("txtNoM10"); // NOI18N
        txtNoM10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM10FocusLost(evt);
            }
        });
        txtNoM10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM10KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM10KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 120, -1));

        txtImporteM10.setEditable(false);
        txtImporteM10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM10.setText("0.0");
        txtImporteM10.setToolTipText("");
        txtImporteM10.setName("txtImporteM10"); // NOI18N
        getContentPane().add(txtImporteM10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 122, -1));

        txtNoM5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM5.setText("0");
        txtNoM5.setToolTipText("");
        txtNoM5.setName("txtNoM5"); // NOI18N
        txtNoM5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM5FocusLost(evt);
            }
        });
        txtNoM5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM5KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM5KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 120, -1));

        txtImporteM5.setEditable(false);
        txtImporteM5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM5.setText("0.0");
        txtImporteM5.setToolTipText("");
        txtImporteM5.setName("txtImporteM5"); // NOI18N
        getContentPane().add(txtImporteM5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, 122, -1));

        txtNoM2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM2.setText("0");
        txtNoM2.setToolTipText("");
        txtNoM2.setName("txtNoM2"); // NOI18N
        txtNoM2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM2FocusLost(evt);
            }
        });
        txtNoM2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM2KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM2KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 120, -1));

        txtImporteM2.setEditable(false);
        txtImporteM2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM2.setText("0.0");
        txtImporteM2.setToolTipText("");
        txtImporteM2.setName("txtImporteM2"); // NOI18N
        getContentPane().add(txtImporteM2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 360, 122, -1));

        txtNoM1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM1.setText("0");
        txtNoM1.setToolTipText("");
        txtNoM1.setName("txtNoM1"); // NOI18N
        txtNoM1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM1FocusLost(evt);
            }
        });
        txtNoM1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM1KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM1KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 120, -1));

        txtImporteM1.setEditable(false);
        txtImporteM1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM1.setText("0.0");
        txtImporteM1.setToolTipText("");
        txtImporteM1.setName("txtImporteM1"); // NOI18N
        getContentPane().add(txtImporteM1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 390, 122, -1));

        txtNoM02.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM02.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM02.setText("0");
        txtNoM02.setToolTipText("");
        txtNoM02.setName("txtNoM02"); // NOI18N
        txtNoM02.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM02FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM02FocusLost(evt);
            }
        });
        txtNoM02.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM02KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM02KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM02, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 120, -1));

        txtNoM05.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM05.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM05.setText("0");
        txtNoM05.setToolTipText("");
        txtNoM05.setName("txtNoM05"); // NOI18N
        txtNoM05.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM05FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM05FocusLost(evt);
            }
        });
        txtNoM05.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM05KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM05KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM05, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, 120, -1));

        txtImporteM05.setEditable(false);
        txtImporteM05.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM05.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM05.setText("0.0");
        txtImporteM05.setToolTipText("");
        txtImporteM05.setName("txtImporteM05"); // NOI18N
        getContentPane().add(txtImporteM05, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 420, 122, -1));

        txtImporteM02.setEditable(false);
        txtImporteM02.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM02.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM02.setText("0.0");
        txtImporteM02.setToolTipText("");
        txtImporteM02.setName("txtImporteM02"); // NOI18N
        getContentPane().add(txtImporteM02, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 450, 122, -1));

        txtImporteMSubtotal.setEditable(false);
        txtImporteMSubtotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteMSubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteMSubtotal.setText("0.0");
        txtImporteMSubtotal.setToolTipText("");
        txtImporteMSubtotal.setName("txtImporteMSubtotal"); // NOI18N
        getContentPane().add(txtImporteMSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 510, 122, -1));

        txtImporteBSubtotal.setEditable(false);
        txtImporteBSubtotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteBSubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteBSubtotal.setText("0.0");
        txtImporteBSubtotal.setToolTipText("");
        txtImporteBSubtotal.setName("txtImporteBSubtotal"); // NOI18N
        getContentPane().add(txtImporteBSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 122, -1));

        VENTAS21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS21.setText("TOTAL");
        VENTAS21.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        VENTAS21.setName("VENTAS21"); // NOI18N
        getContentPane().add(VENTAS21, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 730, -1, 40));

        txtImporteTotal.setEditable(false);
        txtImporteTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteTotal.setToolTipText("");
        txtImporteTotal.setName("txtImporteTotal"); // NOI18N
        getContentPane().add(txtImporteTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 730, 122, -1));

        jSeparator1.setName("jSeparator1"); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 690, 345, 10));

        VENTAS22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS22.setText("TOTAL");
        VENTAS22.setName("VENTAS22"); // NOI18N
        getContentPane().add(VENTAS22, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 700, 100, 20));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0.0");
        txtTotal.setToolTipText("");
        txtTotal.setName("txtTotal"); // NOI18N
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 700, 145, 20));

        txtNoM01.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoM01.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoM01.setText("0");
        txtNoM01.setToolTipText("");
        txtNoM01.setName("txtNoM01"); // NOI18N
        txtNoM01.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoM01FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoM01FocusLost(evt);
            }
        });
        txtNoM01.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoM01KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoM01KeyReleased(evt);
            }
        });
        getContentPane().add(txtNoM01, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, 120, -1));

        txtImporteM01.setEditable(false);
        txtImporteM01.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtImporteM01.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtImporteM01.setText("0.0");
        txtImporteM01.setToolTipText("");
        txtImporteM01.setName("txtImporteM01"); // NOI18N
        getContentPane().add(txtImporteM01, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 480, 122, -1));

        btnImprimirArqueo.setText("Imprimir");
        btnImprimirArqueo.setName("btnImprimirArqueo"); // NOI18N
        btnImprimirArqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirArqueoActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimirArqueo, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 730, 345, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Folio inicial");
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 80, -1));

        txtFolioInicial.setEditable(false);
        txtFolioInicial.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtFolioInicial.setName("txtFolioInicial"); // NOI18N
        getContentPane().add(txtFolioInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 143, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Folio final");
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 80, -1));

        txtFolioFinal.setEditable(false);
        txtFolioFinal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtFolioFinal.setName("txtFolioFinal"); // NOI18N
        getContentPane().add(txtFolioFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 143, -1));

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tblRetiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Progresivo", "Hora", "Monto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRetiros.setName("tblRetiros"); // NOI18N
        jScrollPane2.setViewportView(tblRetiros);
        if (tblRetiros.getColumnModel().getColumnCount() > 0) {
            tblRetiros.getColumnModel().getColumn(0).setResizable(false);
            tblRetiros.getColumnModel().getColumn(1).setResizable(false);
            tblRetiros.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 570, 345, 84));

        VENTAS23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS23.setText("Monedas");
        VENTAS23.setMaximumSize(null);
        VENTAS23.setName("VENTAS23"); // NOI18N
        getContentPane().add(VENTAS23, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 90, -1));

        VENTAS24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS24.setText("Subtotal");
        VENTAS24.setName("VENTAS24"); // NOI18N
        getContentPane().add(VENTAS24, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 120, -1));

        VENTAS25.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS25.setText("Boletos");
        VENTAS25.setName("VENTAS25"); // NOI18N
        getContentPane().add(VENTAS25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 730, -1, -1));

        txtNoBoletos.setEditable(false);
        txtNoBoletos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtNoBoletos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoBoletos.setToolTipText("");
        txtNoBoletos.setName("txtNoBoletos"); // NOI18N
        getContentPane().add(txtNoBoletos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 730, 111, -1));

        VENTAS20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        VENTAS20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VENTAS20.setText("Subtotal");
        VENTAS20.setName("VENTAS20"); // NOI18N
        getContentPane().add(VENTAS20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 660, 193, -1));

        txtRetirosSubtotal.setEditable(false);
        txtRetirosSubtotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtRetirosSubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRetirosSubtotal.setText("0.0");
        txtRetirosSubtotal.setToolTipText("");
        txtRetirosSubtotal.setName("txtRetirosSubtotal"); // NOI18N
        getContentPane().add(txtRetirosSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 660, 122, -1));

        jSeparator2.setName("jSeparator2"); // NOI18N
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 246, 345, 10));

        jSeparator3.setName("jSeparator3"); // NOI18N
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, 345, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoB500KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB500KeyTyped
        try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoB500KeyTyped

    private void txtNoB500KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB500KeyReleased
       if(!this.txtNoB500.getText().equals("")){
             this.txtImporteB500.setText(String.valueOf(Float.valueOf(this.txtNoB500.getText())*500)); 
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB500KeyReleased

    private void txtNoB500FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB500FocusLost
        if(this.txtNoB500.getText().equals("")){
            this.txtNoB500.setText("0");
            this.txtImporteB500.setText("0.0");
            subtotalB();
       }
    }//GEN-LAST:event_txtNoB500FocusLost

    private void txtNoB500FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB500FocusGained
        this.txtNoB500.setText("");
    }//GEN-LAST:event_txtNoB500FocusGained

    private void txtNoB200KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB200KeyTyped
        try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoB200KeyTyped

    private void txtNoB200KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB200KeyReleased
         if(!this.txtNoB200.getText().equals("")){
             this.txtImporteB200.setText(String.valueOf(Float.valueOf(this.txtNoB200.getText())*200)); 
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB200KeyReleased

    private void txtNoB200FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB200FocusLost
        if(this.txtNoB200.getText().equals("")){
        this.txtNoB200.setText("0");
            this.txtImporteB200.setText("0.0");
            subtotalB();
       }
    }//GEN-LAST:event_txtNoB200FocusLost

    private void txtNoB200FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB200FocusGained
       this.txtNoB200.setText("");
    }//GEN-LAST:event_txtNoB200FocusGained

    private void txtNoB100KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB100KeyTyped
          try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoB100KeyTyped

    private void txtNoB100KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB100KeyReleased
        if(!this.txtNoB100.getText().equals("")){
             this.txtImporteB100.setText(String.valueOf(Float.valueOf(this.txtNoB100.getText())*100)); 
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB100KeyReleased

    private void txtNoB100FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB100FocusLost
        if(this.txtNoB100.getText().equals("")){
            this.txtNoB100.setText("0");
            this.txtImporteB100.setText("0.0");
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB100FocusLost

    private void txtNoB100FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB100FocusGained
       this.txtNoB100.setText("");
    }//GEN-LAST:event_txtNoB100FocusGained

    private void txtNoB50KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB50KeyTyped
        try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoB50KeyTyped

    private void txtNoB50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB50KeyReleased
         if(!this.txtNoB50.getText().equals("")){
             this.txtImporteB50.setText(String.valueOf(Float.valueOf(this.txtNoB50.getText())*50)); 
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB50KeyReleased

    private void txtNoB50FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB50FocusLost
        if(this.txtNoB50.getText().equals("")){
            this.txtNoB50.setText("0");
            this.txtImporteB50.setText("0.0");
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB50FocusLost

    private void txtNoB50FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB50FocusGained
         this.txtNoB50.setText("");
    }//GEN-LAST:event_txtNoB50FocusGained

    private void txtNoB20KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB20KeyTyped
       try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoB20KeyTyped

    private void txtNoB20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoB20KeyReleased
        if(!this.txtNoB20.getText().equals("")){
             this.txtImporteB20.setText(String.valueOf(Float.valueOf(this.txtNoB20.getText())*20)); 
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB20KeyReleased

    private void txtNoB20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB20FocusLost
      if(this.txtNoB20.getText().equals("")){
            this.txtNoB20.setText("0");
            this.txtImporteB20.setText("0.0");
             subtotalB();
       }
    }//GEN-LAST:event_txtNoB20FocusLost

    private void txtNoB20FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoB20FocusGained
        this.txtNoB20.setText("");
    }//GEN-LAST:event_txtNoB20FocusGained

    private void txtNoM20KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM20KeyTyped
         try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM20KeyTyped

    private void txtNoM20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM20KeyReleased
        if(!this.txtNoM20.getText().equals("")){
             this.txtImporteM20.setText(String.valueOf(Float.valueOf(this.txtNoM20.getText())*20)); 
             subtotalM();
       }
    }//GEN-LAST:event_txtNoM20KeyReleased

    private void txtNoM20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM20FocusLost
         if(this.txtNoM20.getText().equals("")){
            this.txtNoM20.setText("0");
            this.txtImporteM20.setText("0.0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM20FocusLost

    private void txtNoM20FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM20FocusGained
        this.txtNoM20.setText("");
    }//GEN-LAST:event_txtNoM20FocusGained

    private void txtNoM10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM10KeyTyped
           try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM10KeyTyped

    private void txtNoM10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM10KeyReleased
         if(!this.txtNoM10.getText().equals("")){
             this.txtImporteM10.setText(String.valueOf(Float.valueOf(this.txtNoM10.getText())*10)); 
             subtotalM();
       }
    }//GEN-LAST:event_txtNoM10KeyReleased

    private void txtNoM10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM10FocusLost
        if(this.txtNoM10.getText().equals("")){
            this.txtNoM10.setText("0");
            this.txtImporteM10.setText("0.0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM10FocusLost

    private void txtNoM10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM10FocusGained
         this.txtNoM10.setText("");
    }//GEN-LAST:event_txtNoM10FocusGained

    private void txtNoM5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM5KeyTyped
        try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM5KeyTyped

    private void txtNoM5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM5KeyReleased
        if(!this.txtNoM5.getText().equals("")){
             this.txtImporteM5.setText(String.valueOf(Float.valueOf(this.txtNoM5.getText())*5)); 
             subtotalM();
       }
    }//GEN-LAST:event_txtNoM5KeyReleased

    private void txtNoM5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM5FocusLost
        if(this.txtNoM5.getText().equals("")){
            this.txtNoM5.setText("0");
            this.txtImporteM5.setText("0.0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM5FocusLost

    private void txtNoM5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM5FocusGained
         this.txtNoM5.setText("");
    }//GEN-LAST:event_txtNoM5FocusGained

    private void txtNoM2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM2KeyTyped
         try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM2KeyTyped

    private void txtNoM2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM2KeyReleased
        if(!this.txtNoM2.getText().equals("")){
             this.txtImporteM2.setText(String.valueOf(Float.valueOf(this.txtNoM2.getText())*2)); 
             subtotalM();
       }
    }//GEN-LAST:event_txtNoM2KeyReleased

    private void txtNoM2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM2FocusLost
        if(this.txtNoM2.getText().equals("")){
            this.txtNoM2.setText("0");
            this.txtImporteM2.setText("0.0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM2FocusLost

    private void txtNoM2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM2FocusGained
         this.txtNoM2.setText("");
    }//GEN-LAST:event_txtNoM2FocusGained

    private void txtNoM1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM1KeyTyped
          try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM1KeyTyped

    private void txtNoM1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM1KeyReleased
       if(!this.txtNoM1.getText().equals("")){
             this.txtImporteM1.setText(String.valueOf(Float.valueOf(this.txtNoM1.getText())*1)); 
             subtotalM();
       }  
    }//GEN-LAST:event_txtNoM1KeyReleased

    private void txtNoM1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM1FocusLost
         if(this.txtNoM1.getText().equals("")){
            this.txtNoM1.setText("0");
            this.txtImporteM1.setText("0.0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM1FocusLost

    private void txtNoM1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM1FocusGained
        this.txtNoM1.setText("");
    }//GEN-LAST:event_txtNoM1FocusGained

    private void txtNoM05KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM05KeyTyped
        try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM05KeyTyped

    private void txtNoM05KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM05KeyReleased
        if(!this.txtNoM05.getText().equals("")){
            this.txtImporteM05.setText(String.valueOf(Float.valueOf(this.txtNoM05.getText())*.5)); 
            subtotalM();
        }  
    }//GEN-LAST:event_txtNoM05KeyReleased

    private void txtNoM05FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM05FocusLost
         if(this.txtNoM05.getText().equals("")){
            this.txtNoM05.setText("0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM05FocusLost

    private void txtNoM05FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM05FocusGained
        this.txtNoM05.setText("");
        this.txtImporteM05.setText("0.0");
    }//GEN-LAST:event_txtNoM05FocusGained

    private void txtNoM02KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM02KeyTyped
         try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM02KeyTyped

    private void txtNoM02KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM02KeyReleased
       if(!this.txtNoM02.getText().equals("")){
            this.txtImporteM02.setText(String.valueOf(Float.valueOf(this.txtNoM02.getText())*.2)); 
            subtotalM();
        }  
    }//GEN-LAST:event_txtNoM02KeyReleased

    private void txtNoM02FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM02FocusLost
        if(this.txtNoM02.getText().equals("")){
            this.txtNoM02.setText("0");
            this.txtImporteM02.setText("0.0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM02FocusLost

    private void txtNoM02FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM02FocusGained
         this.txtNoM02.setText("");
    }//GEN-LAST:event_txtNoM02FocusGained

    private void txtNoM01KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM01KeyTyped
        try { 
            Float.parseFloat(String.valueOf(evt.getKeyChar())); 
        } catch(NumberFormatException e) { 
                evt.consume();
        }
    }//GEN-LAST:event_txtNoM01KeyTyped

    private void txtNoM01KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoM01KeyReleased
         if(!this.txtNoM01.getText().equals("")){
            this.txtImporteM01.setText(String.valueOf(Float.valueOf(this.txtNoM01.getText())*.1)); 
            subtotalM();
        }  
    }//GEN-LAST:event_txtNoM01KeyReleased

    private void txtNoM01FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM01FocusLost
        if(this.txtNoM01.getText().equals("")){
            this.txtNoM01.setText("0");
            this.txtImporteM01.setText("0.0");
              subtotalM();
       }
    }//GEN-LAST:event_txtNoM01FocusLost

    private void txtNoM01FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoM01FocusGained
        this.txtNoM01.setText("");
    }//GEN-LAST:event_txtNoM01FocusGained

    private void btnImprimirArqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirArqueoActionPerformed
       Arqueo arqueo = new Arqueo(Long.valueOf(txtFolioInicial.getText()),
                                Long.valueOf(txtFolioFinal.getText()),
                                Float.valueOf(this.txtImporteMSubtotal.getText()),
                                Float.valueOf(this.txtImporteBSubtotal.getText()),
                                Float.valueOf(this.txtRetirosSubtotal.getText()),
                                Float.valueOf(this.txtTotal.getText()),
                                Tiempo.getHora()+" "+Tiempo.getFecha()
       );
     arqueo.getDetallesArqueo().add(new DetallesArqueo(500,Integer.valueOf(this.txtNoB500.
             getText()),Float.valueOf(this.txtImporteB500.getText()),"Billetes" ));
     arqueo.getDetallesArqueo().add(new DetallesArqueo(200,Integer.valueOf(this.txtNoB200.
             getText()),Float.valueOf(this.txtImporteB200.getText()),"Billetes" ));
     arqueo.getDetallesArqueo().add(new DetallesArqueo(100,Integer.valueOf(this.txtNoB100.
             getText()),Float.valueOf(this.txtImporteB100.getText()),"Billetes" ));
     arqueo.getDetallesArqueo().add(new DetallesArqueo(50,Integer.valueOf(this.txtNoB50.
             getText()),Float.valueOf(this.txtImporteB50.getText()),"Billetes" ));
     arqueo.getDetallesArqueo().add(new DetallesArqueo(20,Integer.valueOf(this.txtNoB20.
             getText()),Float.valueOf(this.txtImporteB20.getText()),"Billetes" ));
     
    arqueo.getDetallesArqueo().add(new DetallesArqueo(20,Integer.valueOf(this.txtNoM20.
             getText()),Float.valueOf(this.txtImporteM20.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(10,Integer.valueOf(this.txtNoM10.
           getText()),Float.valueOf(this.txtImporteM10.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(5,Integer.valueOf(this.txtNoM5.
           getText()),Float.valueOf(this.txtImporteM5.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(2,Integer.valueOf(this.txtNoM2.
           getText()),Float.valueOf(this.txtImporteM2.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(1,Integer.valueOf(this.txtNoM1.
           getText()),Float.valueOf(this.txtImporteM1.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(0.5f,Integer.valueOf(this.txtNoM05.
             getText()),Float.valueOf(this.txtImporteM05.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(0.2f,Integer.valueOf(this.txtNoM02.
           getText()),Float.valueOf(this.txtImporteM02.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(0.1f,Integer.valueOf(this.txtNoM01.
             getText()),Float.valueOf(this.txtImporteM01.getText()),"Monedas" ));
    arqueo.getDetallesArqueo().add(new DetallesArqueo(0,0,0,"SubtotalMonedas" ));
    
    ArrayList<RetiroParcial> retirosParciales = turno.getRetirosParciales();
    Iterator<RetiroParcial> retiroIterator = retirosParciales.iterator();
    if (retirosParciales.size()>0){
        arqueo.getDetallesArqueo().add(new DetallesArqueo(0,0,0,"TituloRetiro" ));
        while(retiroIterator.hasNext()){
             RetiroParcial next = retiroIterator.next();
            arqueo.getDetallesArqueo().add(new DetallesArqueo(0,0,next.getMonto(),"Retiros" ));
        }
        arqueo.getDetallesArqueo().add(new DetallesArqueo(0,0,0,"SubtotalRetiros" ));
    }
    //turno.setDetallesMovimiento(DetallesMovimiento.generarDetalles(Auto.getAutosCobradosTurnoActual(turno),
    //            Auto.getAutosBoletoPerdidoTurnoActual(turno),Auto.getAutosBoletoCanceladoTurnoActual(turno),turno));
     
     new CorteTurno(turno, estacionamiento).generarReporte();

    new modeloReportes.ReporteArqueo(turno,arqueo,estacionamiento,serie).generarReporte();

    }//GEN-LAST:event_btnImprimirArqueoActionPerformed
    
    
    public void subtotalB(){
        this.txtImporteBSubtotal.setText(String.valueOf(
            Float.valueOf(this.txtImporteB500.getText())+
            Float.valueOf(this.txtImporteB200.getText())+
            Float.valueOf(this.txtImporteB100.getText())+
            Float.valueOf(this.txtImporteB50.getText())+
            Float.valueOf(this.txtImporteB20.getText())
            ));
        total();
    }
    
    private void subtotalM() {
        this.txtImporteMSubtotal.setText(String.valueOf(
            Float.valueOf(this.txtImporteM20.getText())+
            Float.valueOf(this.txtImporteM10.getText())+
            Float.valueOf(this.txtImporteM5.getText())+
            Float.valueOf(this.txtImporteM2.getText())+
            Float.valueOf(this.txtImporteM1.getText())+
            Float.valueOf(this.txtImporteM05.getText())+
            Float.valueOf(this.txtImporteM02.getText())+
            Float.valueOf(this.txtImporteM01.getText())
        ));
        total();
    }
    
    private void total() {
        this.txtTotal.setText(String.valueOf(
            Float.valueOf(this.txtImporteMSubtotal.getText())+
            Float.valueOf(this.txtImporteBSubtotal.getText())+
            Float.valueOf(this.txtRetirosSubtotal.getText())
        ));
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VENTAS;
    private javax.swing.JLabel VENTAS1;
    private javax.swing.JLabel VENTAS10;
    private javax.swing.JLabel VENTAS11;
    private javax.swing.JLabel VENTAS12;
    private javax.swing.JLabel VENTAS13;
    private javax.swing.JLabel VENTAS14;
    private javax.swing.JLabel VENTAS15;
    private javax.swing.JLabel VENTAS16;
    private javax.swing.JLabel VENTAS17;
    private javax.swing.JLabel VENTAS18;
    private javax.swing.JLabel VENTAS19;
    private javax.swing.JLabel VENTAS2;
    private javax.swing.JLabel VENTAS20;
    private javax.swing.JLabel VENTAS21;
    private javax.swing.JLabel VENTAS22;
    private javax.swing.JLabel VENTAS23;
    private javax.swing.JLabel VENTAS24;
    private javax.swing.JLabel VENTAS25;
    private javax.swing.JLabel VENTAS3;
    private javax.swing.JLabel VENTAS4;
    private javax.swing.JLabel VENTAS5;
    private javax.swing.JLabel VENTAS6;
    private javax.swing.JLabel VENTAS7;
    private javax.swing.JLabel VENTAS8;
    private javax.swing.JLabel VENTAS9;
    private javax.swing.JButton btnImprimirArqueo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tblRetiros;
    private javax.swing.JTable tblVenta;
    private javax.swing.JTextField txtFolioFinal;
    private javax.swing.JTextField txtFolioInicial;
    private javax.swing.JTextField txtImporteB100;
    private javax.swing.JTextField txtImporteB20;
    private javax.swing.JTextField txtImporteB200;
    private javax.swing.JTextField txtImporteB50;
    private javax.swing.JTextField txtImporteB500;
    private javax.swing.JTextField txtImporteBSubtotal;
    private javax.swing.JTextField txtImporteM01;
    private javax.swing.JTextField txtImporteM02;
    private javax.swing.JTextField txtImporteM05;
    private javax.swing.JTextField txtImporteM1;
    private javax.swing.JTextField txtImporteM10;
    private javax.swing.JTextField txtImporteM2;
    private javax.swing.JTextField txtImporteM20;
    private javax.swing.JTextField txtImporteM5;
    private javax.swing.JTextField txtImporteMSubtotal;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtNoB100;
    private javax.swing.JTextField txtNoB20;
    private javax.swing.JTextField txtNoB200;
    private javax.swing.JTextField txtNoB50;
    private javax.swing.JTextField txtNoB500;
    private javax.swing.JTextField txtNoBoletos;
    private javax.swing.JTextField txtNoM01;
    private javax.swing.JTextField txtNoM02;
    private javax.swing.JTextField txtNoM05;
    private javax.swing.JTextField txtNoM1;
    private javax.swing.JTextField txtNoM10;
    private javax.swing.JTextField txtNoM2;
    private javax.swing.JTextField txtNoM20;
    private javax.swing.JTextField txtNoM5;
    private javax.swing.JTextField txtRetirosSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

   
}
