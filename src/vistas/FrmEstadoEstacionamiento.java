/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import ModelosAux.Tiempo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modeloReportes.ReporteFolios;
import modelos.Auto;
import modelos.Estacionamiento;
import modelos.IUseCalendar;
import modelos.RetiroParcial;
import modelos.Turno;
import vistas.formatos.FrmReciboPago;

/**
 *
 * @author sistema
 */
public class FrmEstadoEstacionamiento extends javax.swing.JDialog implements IUseCalendar {
    Frame parent;
    Turno turno;
    Estacionamiento estacionamiento;
    List<Auto> autosReporte;
    /**
     * Creates new form FrmEstadoEstacionamiento
     */
    public FrmEstadoEstacionamiento(java.awt.Frame parent, boolean modal,Turno turno,Estacionamiento estacionamiento) {
        super(parent, modal);
        initComponents();
        personalizarTablas();
        this.parent = parent;
        this.estacionamiento =  estacionamiento;
        setLocationRelativeTo(parent);
        cargarDatos(Tiempo.getFecha());
        this.txtFecha.setText(Tiempo.getFecha());
        this.turno = turno;
        setVisible(true);
    }
    
    
    private void personalizarTablas(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );     
        tblRetiros.setDefaultRenderer(String.class, centerRenderer);
        
        tblAutos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(table.getModel().getValueAt(row,0).equals("COBRADO"))
                    c.setBackground(new Color(255,255,102));
                if(table.getModel().getValueAt(row,0).equals("PENDIENTE"))
                    c.setBackground(new Color(153,255,102));
                if(table.getModel().getValueAt(row,0).equals("PERDIDO"))
                    c.setBackground(new Color(255,102,102));
                if(table.getModel().getValueAt(row,0).equals("CANCELADO"))
                    c.setBackground(new Color(153,153,153));
                this.setHorizontalAlignment( SwingConstants.CENTER);
                return c;
            }
        });
        
        tblTurnos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(table.getModel().getValueAt(row,0).equals("ABIERTO"))
                    c.setBackground(new Color(153,255,102));
                if(table.getModel().getValueAt(row,0).equals("CERRADO"))
                    c.setBackground(new Color(153,153,153));
                this.setHorizontalAlignment( SwingConstants.CENTER);
                return c;
            }
        });

    }
    
    private void cargarDatos(String fecha) {
        deshablitarCbx();
        autosReporte = new ArrayList<Auto>();
        //Habilitar cbx de turnos disponibles
        ArrayList<Turno> turnos = Turno.getTurnosByFechaAbierto(fecha);
        Iterator<Turno> turnosIterator = turnos.iterator();
        while(turnosIterator.hasNext()){
            Turno turnoTemp = turnosIterator.next();
            switch (turnoTemp.getTipoTurno()) {
                case "Primer turno":
                    cbxPrimer.setVisible(true);
                    //cbxPrimer.setSelected(true);
                    break;
                case "Segundo turno":
                    cbxSegundo.setVisible(true);
//                    cbxSegundo.setSelected(true);
                    break;
                case "Tercer turno":
                    cbxTercer.setVisible(true);
//                    cbxTercer.setSelected(true);
                    break;
            }
                
        }

        // Turnos
        float importeTurnosTabla=0;
        DefaultTableModel modelTurno = (DefaultTableModel) this.tblTurnos.getModel();
        modelTurno.getDataVector().removeAllElements();
        modelTurno.fireTableDataChanged();
        Iterator<Turno> iterator = turnos.iterator();
        while(iterator.hasNext()){
            Turno turnoAux = iterator.next();
            if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
            modelTurno.addRow(new Object[]{turnoAux.getHoraCierre()!=null?"CERRADO":"ABIERTO",turnoAux.getTipoTurno(),
                turnoAux.getHoraApertura(),turnoAux.getHoraCierre(),turnoAux.getEmpleado().getNombre(),
                turnoAux.getFolioInicial(),turnoAux.getFolioFinal(),turnoAux.getTotal()});
            importeTurnosTabla+=turnoAux.getTotal();
            }
        }
        this.tblTurnos.setModel(modelTurno);
        
        //Retiros parciales
         float importeRetirosTabla=0;
        DefaultTableModel modelRetiros = (DefaultTableModel) this.tblRetiros.getModel();
        modelRetiros.getDataVector().removeAllElements();
        modelRetiros.fireTableDataChanged();
        iterator = turnos.iterator();
        while(iterator.hasNext()){
            Turno turnoAux = iterator.next();
            
            ArrayList<RetiroParcial> retirosParciales = turnoAux.getRetirosParciales();
            Iterator<RetiroParcial> iteratorRetiros = retirosParciales.iterator();
            while(iteratorRetiros.hasNext()){
                RetiroParcial retiro = iteratorRetiros.next();
               
                if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                    modelRetiros.addRow(new Object[]{retiro.getProgresivo(), Turno.getById(retiro.getIdTurno()).getTipoTurno(),
                    retiro.getHora(),retiro.getMontoReal(),retiro.getMonto(),retiro.getMontoReal()-retiro.getMonto()});
                     importeRetirosTabla+=retiro.getMontoReal();
                }
            }
            
        }
        this.tblRetiros.setModel(modelRetiros);
        
        //Autos
        int noAutosTabla=0;
        float importeAutosTabla=0;
        DefaultTableModel modelAutos = (DefaultTableModel) this.tblAutos.getModel();
        modelAutos.getDataVector().removeAllElements();
        modelAutos.fireTableDataChanged();
        iterator = turnos.iterator();
        while(iterator.hasNext()){
            Turno turnoAux = iterator.next();
            //Autos cobrados
            if(this.cbxCobrado.isSelected()){
                List<Auto> autosCobradosTurnoActual = Auto.getAutosCobradosTurnoActual(turnoAux); 
                
                Iterator<Auto> iteratorAutosCobrados = autosCobradosTurnoActual.iterator();
                 if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                        noAutosTabla += autosCobradosTurnoActual.size();
                    }
                while(iteratorAutosCobrados.hasNext()){
                    Auto auto = iteratorAutosCobrados.next();
                    
                    if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                        modelAutos.addRow(new Object[]{"COBRADO",auto.getProgresivo(),turnoAux.getTipoTurno(),auto.getMatricula(),
                            auto.getHoraEntrada(),auto.getHoraSalida(),String.format("%02d",auto.getHorasTangibles())+" : "+String.format("%02d",auto.getMinutosTangibles()),
                            auto.getMontoTangible()});
                        importeAutosTabla += auto.getMontoTangible();
                        autosReporte.add(auto);
                    }
                }
            }
            //Autos pendientes
            if(this.cbxDentro.isSelected()){
                List<Auto> autosPendientesTurnoActual = Auto.getAutosPendientes(turnoAux);

                Iterator<Auto> iteratorAutosPendientes = autosPendientesTurnoActual.iterator();
                if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                        noAutosTabla += autosPendientesTurnoActual.size();    
                }
                while(iteratorAutosPendientes.hasNext()){
                    Auto auto = iteratorAutosPendientes.next();
                    
                    if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                        modelAutos.addRow(new Object[]{"PENDIENTE",auto.getProgresivo(),turnoAux.getTipoTurno(),auto.getMatricula(),
                        auto.getHoraEntrada(),"-","-","-"});
                        autosReporte.add(auto);
                    }
                }
            }
            //Autos Cancelados
            if(this.cbxCancelado.isSelected()){
                List<Auto> autosCanceladosTurnoActual = Auto.getAutosBoletoCanceladoTurnoActual(turnoAux);
                Iterator<Auto> iteratorAutosCancelados = autosCanceladosTurnoActual.iterator();
                if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                        noAutosTabla += autosCanceladosTurnoActual.size();  
                }
                while(iteratorAutosCancelados.hasNext()){
                    Auto auto = iteratorAutosCancelados.next();
                    if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) )
                        modelAutos.addRow(new Object[]{"CANCELADO",auto.getProgresivo(),turnoAux.getTipoTurno(),auto.getMatricula(),
                            auto.getHoraEntrada(),auto.getHoraSalida(),String.format("%02d",auto.getHorasTangibles())+" : "+String.format("%02d",auto.getMinutosTangibles()),
                            auto.getMontoTangible()}); 
                            autosReporte.add(auto);
                }
            }
            //Autos boleto perdido
            if(this.cbxPerdido.isSelected()){
                List<Auto> autosPerdidosTurnoActual = Auto.getAutosBoletoPerdidoTurnoActual(turnoAux);
             
                Iterator<Auto> iteratorAutosPerdidos = autosPerdidosTurnoActual.iterator();
                 if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                        noAutosTabla += autosPerdidosTurnoActual.size();
                }                  
               
                while(iteratorAutosPerdidos.hasNext()){
                    Auto auto = iteratorAutosPerdidos.next();
                     
                    if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                    ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                    ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                        modelAutos.addRow(new Object[]{"PERDIDO",auto.getProgresivo(),turnoAux.getTipoTurno(),auto.getMatricula(),
                            auto.getHoraEntrada(),auto.getHoraSalida(),String.format("%02d",auto.getHorasTangibles())+" : "+String.format("%02d",auto.getMinutosTangibles()),
                            auto.getMontoTangible()});
                        importeAutosTabla += auto.getMontoTangible();
                        autosReporte.add(auto);
                    }
                }
            }
        }
        this.tblAutos.setModel(modelAutos);
          
        //Datos estadisticos
        Iterator<Turno> iteratorTurnos = turnos.iterator();
        int boletosEmitidos=0;
        int boletosPendientes=0;
        int boletosCobrados=0;
        int boletosPerdidos=0;
        int boletosCancelados=0; 
        int boletosTurnoA=0;
        while(iteratorTurnos.hasNext()){
            Turno turnoAux = iteratorTurnos.next();
            if( ( turnoAux.getTipoTurno().equals("Primer turno") && this.cbxPrimer.isSelected() && this.cbxPrimer.isVisible() ) ||
                ( turnoAux.getTipoTurno().equals("Segundo turno") && this.cbxSegundo.isSelected() && this.cbxSegundo.isVisible()) ||
                ( turnoAux.getTipoTurno().equals("Tercer turno")&& this.cbxTercer.isSelected()&& this.cbxTercer.isVisible() ) ){
                    boletosEmitidos += turnoAux.getNoBol();
                    boletosTurnoA += turnoAux.getNoBolTurnoA();
                    boletosPendientes+= turnoAux.getNoBol() + turnoAux.getNoBolTurnoA() - (turnoAux.getNoBolCobrados() + turnoAux.getNoBolCancelados() +turnoAux.getNoBolPerdidos() );
                    boletosCobrados += turnoAux.getNoBolCobrados();
                    boletosPerdidos += turnoAux.getNoBolPerdidos();
                    boletosCancelados += turnoAux.getNoBolCancelados(); 
            }
                
        }
        this.txtTotalTurnos.setText(String.valueOf(importeTurnosTabla));
         
        this.txtNoBoletosAutos.setText(String.valueOf(noAutosTabla));
        this.txtTotalAutos.setText(String.valueOf(importeAutosTabla));
        
        this.txtTotalRetiros.setText(String.valueOf(importeRetirosTabla));
        
        this.txtBoletosTurnoA.setText(String.valueOf(boletosTurnoA));
        this.txtBoletosEmitidos.setText(String.valueOf(boletosEmitidos));
        this.txtBoletosCobrados.setText(String.valueOf(boletosCobrados));
        this.txtBoletosPorCobrar.setText(String.valueOf(boletosPendientes));
        this.txtBoletosPerdidos.setText(String.valueOf(boletosPerdidos));
        this.txtBoletosCancelados.setText(String.valueOf(boletosCancelados));
             
    }
    
    @Override
    public void updateFecha(String fecha) {
        this.txtFecha.setText(fecha);
       cargarDatos(fecha);
    }
    
    @Override
    public String getFechaActual() {
        return this.txtFecha.getText();
    }
    
    private void deshablitarCbx(){
        cbxPrimer.setVisible(false);
//        cbxPrimer.setSelected(false);
        cbxSegundo.setVisible(false);
//        cbxSegundo.setSelected(false);
        cbxTercer.setVisible(false);
//        cbxTercer.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBoletosPorCobrar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBoletosCobrados = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTurnos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtBoletosEmitidos = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBoletosCancelados = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtBoletosPerdidos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRetiros = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAutos = new javax.swing.JTable();
        cbxPrimer = new javax.swing.JCheckBox();
        cbxSegundo = new javax.swing.JCheckBox();
        cbxTercer = new javax.swing.JCheckBox();
        btnSetFecha = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTotalRetiros = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTotalAutos = new javax.swing.JTextField();
        cbxDentro = new javax.swing.JCheckBox();
        cbxCobrado = new javax.swing.JCheckBox();
        cbxPerdido = new javax.swing.JCheckBox();
        cbxCancelado = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        txtNoBoletosAutos = new javax.swing.JTextField();
        txtTotalTurnos = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        btnImprimir1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtBoletosTurnoA = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Boletos emitidos");

        jLabel3.setText("Boletos por cobrar");

        txtBoletosPorCobrar.setEditable(false);
        txtBoletosPorCobrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setText("Boletos fuera");

        txtBoletosCobrados.setEditable(false);
        txtBoletosCobrados.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tblTurnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Turno", "Entrada", "Salida", "Cajero", "F. Inicial", "F. Final", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTurnos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblTurnos);
        if (tblTurnos.getColumnModel().getColumnCount() > 0) {
            tblTurnos.getColumnModel().getColumn(0).setMinWidth(0);
            tblTurnos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblTurnos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblTurnos.getColumnModel().getColumn(1).setResizable(false);
            tblTurnos.getColumnModel().getColumn(2).setResizable(false);
            tblTurnos.getColumnModel().getColumn(3).setResizable(false);
            tblTurnos.getColumnModel().getColumn(4).setResizable(false);
            tblTurnos.getColumnModel().getColumn(5).setResizable(false);
            tblTurnos.getColumnModel().getColumn(6).setResizable(false);
            tblTurnos.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Fecha");

        txtBoletosEmitidos.setEditable(false);
        txtBoletosEmitidos.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtBoletosEmitidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("Autos dentro");

        jLabel7.setText("Boletos cobrados");

        jLabel8.setText("Boletos cancelados");

        txtBoletosCancelados.setEditable(false);
        txtBoletosCancelados.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setText("Boletos perdidos");

        txtBoletosPerdidos.setEditable(false);
        txtBoletosPerdidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Turnos");

        tblRetiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Progresivo", "Turno", "Hora", "Monto real", "Monto retirado", "Diferencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRetiros.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblRetiros);
        if (tblRetiros.getColumnModel().getColumnCount() > 0) {
            tblRetiros.getColumnModel().getColumn(0).setResizable(false);
            tblRetiros.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblRetiros.getColumnModel().getColumn(1).setResizable(false);
            tblRetiros.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblRetiros.getColumnModel().getColumn(2).setResizable(false);
            tblRetiros.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblRetiros.getColumnModel().getColumn(3).setResizable(false);
            tblRetiros.getColumnModel().getColumn(3).setPreferredWidth(90);
            tblRetiros.getColumnModel().getColumn(4).setResizable(false);
            tblRetiros.getColumnModel().getColumn(4).setPreferredWidth(90);
            tblRetiros.getColumnModel().getColumn(5).setResizable(false);
            tblRetiros.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("Retiros parciales");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Autos");

        tblAutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Progresivo", "Turno", "Matricula", "Entrada", "Salida", "Estadia", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAutos);
        if (tblAutos.getColumnModel().getColumnCount() > 0) {
            tblAutos.getColumnModel().getColumn(0).setMinWidth(0);
            tblAutos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblAutos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblAutos.getColumnModel().getColumn(1).setResizable(false);
            tblAutos.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblAutos.getColumnModel().getColumn(2).setResizable(false);
            tblAutos.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblAutos.getColumnModel().getColumn(3).setResizable(false);
            tblAutos.getColumnModel().getColumn(3).setPreferredWidth(60);
            tblAutos.getColumnModel().getColumn(4).setResizable(false);
            tblAutos.getColumnModel().getColumn(4).setPreferredWidth(90);
            tblAutos.getColumnModel().getColumn(5).setPreferredWidth(90);
            tblAutos.getColumnModel().getColumn(7).setResizable(false);
            tblAutos.getColumnModel().getColumn(7).setPreferredWidth(60);
        }

        cbxPrimer.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbxPrimer.setSelected(true);
        cbxPrimer.setText("Primer turno");
        cbxPrimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPrimerActionPerformed(evt);
            }
        });

        cbxSegundo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbxSegundo.setSelected(true);
        cbxSegundo.setText("Segundo turno");
        cbxSegundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSegundoActionPerformed(evt);
            }
        });

        cbxTercer.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbxTercer.setSelected(true);
        cbxTercer.setText("Tercer turno");
        cbxTercer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTercerActionPerformed(evt);
            }
        });

        btnSetFecha.setText("Elegir fecha");
        btnSetFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetFechaActionPerformed(evt);
            }
        });

        jLabel17.setText("Total");

        jLabel18.setText("Total");

        txtTotalRetiros.setEditable(false);
        txtTotalRetiros.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel19.setText("Total");

        txtTotalAutos.setEditable(false);
        txtTotalAutos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cbxDentro.setBackground(new java.awt.Color(153, 255, 102));
        cbxDentro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxDentro.setSelected(true);
        cbxDentro.setText("Dentro");
        cbxDentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDentroActionPerformed(evt);
            }
        });

        cbxCobrado.setBackground(new java.awt.Color(255, 255, 102));
        cbxCobrado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxCobrado.setSelected(true);
        cbxCobrado.setText("Cobrado");
        cbxCobrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCobradoActionPerformed(evt);
            }
        });

        cbxPerdido.setBackground(new java.awt.Color(255, 102, 102));
        cbxPerdido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxPerdido.setSelected(true);
        cbxPerdido.setText("Perdido");
        cbxPerdido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPerdidoActionPerformed(evt);
            }
        });

        cbxCancelado.setBackground(new java.awt.Color(153, 153, 153));
        cbxCancelado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxCancelado.setSelected(true);
        cbxCancelado.setText("Cancelado");
        cbxCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCanceladoActionPerformed(evt);
            }
        });

        jLabel20.setText("No. Boletos");

        txtNoBoletosAutos.setEditable(false);
        txtNoBoletosAutos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtTotalTurnos.setEditable(false);
        txtTotalTurnos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setBackground(new java.awt.Color(153, 255, 102));
        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("Turno abierto");
        jLabel14.setOpaque(true);

        jLabel15.setBackground(new java.awt.Color(153, 153, 153));
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("Turno cerrado");
        jLabel15.setOpaque(true);

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnImprimir.setText("Imprimir folios");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnImprimir1.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnImprimir1.setText("Realizar arqueo");
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });

        jLabel13.setText("Boletos turno A.");

        txtBoletosTurnoA.setEditable(false);
        txtBoletosTurnoA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel14)
                            .addGap(82, 82, 82)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSetFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalRetiros, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxDentro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxCobrado, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxPerdido, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNoBoletosAutos, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalAutos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxSegundo)
                            .addComponent(cbxPrimer)
                            .addComponent(cbxTercer)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                                    .addComponent(txtBoletosPorCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBoletosCobrados)
                                    .addComponent(txtBoletosCancelados)
                                    .addComponent(txtBoletosPerdidos, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBoletosEmitidos, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(jLabel6)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBoletosTurnoA, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSetFecha)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5)
                    .addComponent(txtFecha)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxCobrado)
                                .addComponent(cbxPerdido)
                                .addComponent(cbxCancelado)
                                .addComponent(cbxDentro))
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTotalAutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNoBoletosAutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtTotalTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(cbxPrimer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSegundo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTercer)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtBoletosEmitidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtBoletosTurnoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtBoletosPorCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtBoletosCobrados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8))
                            .addComponent(txtBoletosCancelados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtBoletosPerdidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnImprimir)
                        .addComponent(btnImprimir1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTotalRetiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetFechaActionPerformed
        new FrmCalendario(parent,true,this);
    }//GEN-LAST:event_btnSetFechaActionPerformed

    private void cbxPrimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPrimerActionPerformed
       cargarDatos(this.txtFecha.getText());
    }//GEN-LAST:event_cbxPrimerActionPerformed

    private void cbxSegundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSegundoActionPerformed
        cargarDatos(this.txtFecha.getText());
    }//GEN-LAST:event_cbxSegundoActionPerformed

    private void cbxTercerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTercerActionPerformed
        cargarDatos(this.txtFecha.getText());
    }//GEN-LAST:event_cbxTercerActionPerformed

    private void cbxDentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDentroActionPerformed
         cargarDatos(this.txtFecha.getText());
    }//GEN-LAST:event_cbxDentroActionPerformed

    private void cbxCobradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCobradoActionPerformed
        cargarDatos(this.txtFecha.getText());
    }//GEN-LAST:event_cbxCobradoActionPerformed

    private void cbxPerdidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPerdidoActionPerformed
       cargarDatos(this.txtFecha.getText());
    }//GEN-LAST:event_cbxPerdidoActionPerformed

    private void cbxCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCanceladoActionPerformed
        cargarDatos(this.txtFecha.getText());
    }//GEN-LAST:event_cbxCanceladoActionPerformed

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
     new FrmArqueo(this.parent,true,turno, estacionamiento);
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "¿Quieres ordenar el reporte por NO. de folio?", "Folios",JOptionPane.YES_NO_OPTION);
        if(showConfirmDialog == JOptionPane.YES_OPTION)
            new ReporteFolios(autosReporte,estacionamiento,true).generarReporte();
        else
            new ReporteFolios(autosReporte,estacionamiento,false).generarReporte();
    }//GEN-LAST:event_btnImprimirActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnImprimir1;
    private javax.swing.JButton btnSetFecha;
    private javax.swing.JCheckBox cbxCancelado;
    private javax.swing.JCheckBox cbxCobrado;
    private javax.swing.JCheckBox cbxDentro;
    private javax.swing.JCheckBox cbxPerdido;
    private javax.swing.JCheckBox cbxPrimer;
    private javax.swing.JCheckBox cbxSegundo;
    private javax.swing.JCheckBox cbxTercer;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblAutos;
    private javax.swing.JTable tblRetiros;
    private javax.swing.JTable tblTurnos;
    private javax.swing.JTextField txtBoletosCancelados;
    private javax.swing.JTextField txtBoletosCobrados;
    private javax.swing.JTextField txtBoletosEmitidos;
    private javax.swing.JTextField txtBoletosPerdidos;
    private javax.swing.JTextField txtBoletosPorCobrar;
    private javax.swing.JTextField txtBoletosTurnoA;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNoBoletosAutos;
    private javax.swing.JTextField txtTotalAutos;
    private javax.swing.JTextField txtTotalRetiros;
    private javax.swing.JTextField txtTotalTurnos;
    // End of variables declaration//GEN-END:variables




}
