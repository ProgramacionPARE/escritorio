
package vistas;

import modelos.Turno;
import ModelosAux.Tiempo;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import modeloReportes.CorteDiario;
import modeloReportes.CorteTurno;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendar;
import org.jdesktop.application.Action;


public class FrmReportes extends javax.swing.JDialog {
    String reporteDiarioFecha; 

    public FrmReportes(Frame parent, boolean modal) {
        super(parent,"Reportes", modal);
        initComponents();
        reporteDiario();
        this.pack();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void reporteDiario(){
        calendar = new JCalendar();
        calendar.setSize(50,50);
        panelCalendar.setLayout(new  FlowLayout());
        panelCalendar.add(calendar);
        reporteDiarioFecha =  Tiempo.getFecha();
        lblFecha.setText(Tiempo.getFechaLarga());
        calendar.addDateListener(new DateListener() {
            @Override
            public void dateChanged(DateEvent de) {
                reporteDiarioFecha =  Tiempo.getFecha(de.getSelectedDate());
                lblFecha.setText(Tiempo.getFechaLarga(de.getSelectedDate())); 
            }
        });
        
    }
      @Action
    public void onReporteDiarioImprimir() {
        ArrayList<Turno> turnosByFecha = Turno.getTurnosByFecha(reporteDiarioFecha);
        if(ckbReporteDiarioDetelleTurno.isSelected()){
            Iterator<Turno> iterator = turnosByFecha.iterator();
            while (iterator.hasNext())
                new CorteTurno(iterator.next()).generarReporte();
        }
        if(turnosByFecha.size()>0)
            new CorteDiario(turnosByFecha,reporteDiarioFecha).generarReporte();
        else
            JOptionPane.showMessageDialog(this, "No hay registros de esta fecha", "Vacio", JOptionPane.WARNING_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        ckbReporteDiarioDetelleTurno = new javax.swing.JCheckBox();
        btnReporteDiarioImprimir = new javax.swing.JButton();
        panelCalendar = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Fecha");
        jLabel1.setName("jLabel1"); // NOI18N

        lblFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setName("lblFecha"); // NOI18N

        ckbReporteDiarioDetelleTurno.setSelected(true);
        ckbReporteDiarioDetelleTurno.setText("Imprimir Detalle por turno");
        ckbReporteDiarioDetelleTurno.setName("ckbReporteDiarioDetelleTurno"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmReportes.class, this);
        btnReporteDiarioImprimir.setAction(actionMap.get("onReporteDiarioImprimir")); // NOI18N
        btnReporteDiarioImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnReporteDiarioImprimir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReporteDiarioImprimir.setText("Imprimir");
        btnReporteDiarioImprimir.setName("btnReporteDiarioImprimir"); // NOI18N

        panelCalendar.setName("panelCalendar"); // NOI18N

        javax.swing.GroupLayout panelCalendarLayout = new javax.swing.GroupLayout(panelCalendar);
        panelCalendar.setLayout(panelCalendarLayout);
        panelCalendarLayout.setHorizontalGroup(
            panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );
        panelCalendarLayout.setVerticalGroup(
            panelCalendarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(ckbReporteDiarioDetelleTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReporteDiarioImprimir)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ckbReporteDiarioDetelleTurno)
                        .addGap(18, 18, 18)
                        .addComponent(btnReporteDiarioImprimir))
                    .addComponent(panelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reporte diario", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporteDiarioImprimir;
    private javax.swing.JCheckBox ckbReporteDiarioDetelleTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JPanel panelCalendar;
    // End of variables declaration//GEN-END:variables
    private JCalendar calendar;
}
