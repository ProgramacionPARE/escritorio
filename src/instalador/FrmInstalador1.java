/*
 *  Formulario que funcionara, como instalador
    Esto es una prueba de concepto no esta terminado
    Pantalla 2
 */
package instalador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import modelos.ConexionDatos;

/**
 *
 * @author sistema
 */
public class FrmInstalador1 extends javax.swing.JFrame {

    /**
     * Creates new form FrmInstalador
     */
    public FrmInstalador1() {
        super("Instalador");
        initComponents();
         setLocationRelativeTo(null);
        installMyqsl();
        
    }

    public void installMyqsl(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = null;
                try {

                    Process p = Runtime.getRuntime().exec("sudo apt-get install -y mysql-server mysql-client"); 
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                    
                    BufferedWriter write = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                    // read the output from the command
                    write.append("#parePROGRAMACIONdb");
                    System.out.println("M-\n");
                    while ((s = stdInput.readLine()) != null) {
                        txtLogs.setText(txtLogs.getText() + s +"\n");
                        System.out.println(s);
                    }

                    // read any errors from the attempted command
                    System.out.println("E-\n");
                    while ((s = stdError.readLine()) != null) {
                        System.out.println(s);
                    }
                    txtLogs.setText(txtLogs.getText() +"Cargando bases de datos\n");
                    ConexionDatos.getInstance().checkDB();
                    txtLogs.setText(txtLogs.getText() +"Se cargaron los datos correctamente\n");
                    btnSiguiente.setEnabled(true);
                }
                catch (IOException e) {
                    System.out.println("exception happened - here's what I know: ");
                    e.printStackTrace();
                }
            }
        }).start();
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLogs = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        jLabel1.setBackground(java.awt.Color.white);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectopare/pantallas/resources/pare valet parking 1.jpg"))); // NOI18N
        jLabel1.setText("            ");
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 48)); // NOI18N
        jLabel2.setText("Parking PARE ");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Se va a instalar mysql.");
        jLabel3.setName("jLabel3"); // NOI18N

        btnSiguiente.setText("Siguiente");
        btnSiguiente.setEnabled(false);
        btnSiguiente.setName("btnSiguiente"); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtLogs.setEditable(false);
        txtLogs.setColumns(20);
        txtLogs.setRows(5);
        txtLogs.setName("txtLogs"); // NOI18N
        jScrollPane1.setViewportView(txtLogs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(82, 82, 82)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente)
                .addGap(26, 26, 26))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        FrmInstalador2 frmInstalador2 = new FrmInstalador2 ();
        frmInstalador2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSiguienteActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtLogs;
    // End of variables declaration//GEN-END:variables
}
