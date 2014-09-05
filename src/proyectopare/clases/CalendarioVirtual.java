package proyectopare.clases;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Calendar;
import org.freixas.jcalendar.*;


public class CalendarioVirtual extends JDialog {
    private JTextField txtFecha;
    public static final int INGLES = 1;
    public static final int ESPAÑOL = 2;
    public static final int INVERSO = 3;
    private int formato;

    /**
     * Constructor para crear un objeto de la clase CalendarioVirtual
     * @param invocador Componente JTextField que se encargará de crear
     * el calendario.
     * @param formato Tipo de formato que va a tener la fecha de salida.
     */
    public CalendarioVirtual(JTextField invocador, int formato){
        this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
        this.txtFecha = invocador;
        this.formato = formato;
        if(!invocador.isEditable() || !invocador.isEnabled())
            this.dispose();
        else
            iniciaComponentes();
    }

    private void iniciaComponentes(){
        /**
         * Se usa el Toolkit para obtener los valores del tamaño de pantalla.
         */
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension size = tk.getScreenSize();

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(txtFecha);
        Point punto = this.getLocation();
        
        JCalendar calendario = new JCalendar(JCalendarCombo.DISPLAY_DATE, false);
        calendario.setDayFont(new Font("Tahoma", Font.PLAIN, 9));
        calendario.setDayOfWeekFont(new Font("Tahoma", Font.PLAIN, 9));
        calendario.setTitleFont(new Font("Tahoma", Font.PLAIN, 9));
        calendario.setTodayFont(new Font("Tahoma", Font.PLAIN, 9));

        calendario.addDateListener(new DateListener(){
            public void dateChanged(DateEvent e){
                Calendar c = e.getSelectedDate();
                if (c != null) {
                    SimpleDateFormat esquema;
                    switch(formato){
                        case INGLES:
                            esquema = new SimpleDateFormat("MM-dd-yyyy");
                            break;
                        case ESPAÑOL:
                            esquema = new SimpleDateFormat("dd-MM-yyyy");
                            break;
                        case INVERSO:
                            esquema = new SimpleDateFormat("yyyy-MM-dd");
                            break;
                        default:
                            esquema = new SimpleDateFormat();
                    }
                    String fecha = esquema.format(c.getTime());
                    txtFecha.setText(fecha);
                    dispose();
                }
            }
        });

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(calendario, BorderLayout.NORTH);
        this.getContentPane().add(panel2);
        pack();

        /**
         * Una vez que se ha empacado la ventana, se realiza el calculo para
         * identificar si al pintarse queda fuera de la pantalla, reajustando
         * sus valores en X y Y antes de hacerla visible.
         */
        int x = size.width-(this.getWidth()+punto.x);
        if(x<0)
            punto.x+=x;

        int y = size.height-(this.getHeight()+punto.y);
        if(y<0)
            punto.y+=y;

        this.setLocation(punto);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

}
