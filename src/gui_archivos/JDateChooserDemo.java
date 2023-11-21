package gui_archivos;


import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class JDateChooserDemo extends JFrame{

    JDateChooser dateChooser;
    JButton jbSeleccFecha;
    
    public JDateChooserDemo(){
        super("Ejemplo de JDateChooser");
        setSize(320, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        crearGUI();
        
        setVisible(true);
    }
    
    private void crearGUI() {
        JLabel jl = new JLabel("Selecciona una fecha:");
        jl.setBounds(30, 25, 150, 25);
        add(jl);
        
        dateChooser = new JDateChooser(new Date());// crear el dateChooser con la fecha actual
        //dateChooser = new JDateChooser(new Date(1979-1900, 2, 7));// crear el dateChooser con la fecha inicial de 7 de marzo de 1979
                
        dateChooser.setBounds(160, 25, 100, 25);
        add(dateChooser);
        
        jbSeleccFecha = new JButton("Obtener fecha seleccionada");
        jbSeleccFecha.setBounds(30, 65, 232, 25);        
        jbSeleccFecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evento_jbSeleccFecha();
            }
        });
        add(jbSeleccFecha); 
    }
    
    
    private void evento_jbSeleccFecha() {
        Calendar calendario = dateChooser.getCalendar();
        System.out.println("----------- Fecha seleccionada ------------");
        int dia = calendario.get(Calendar.DATE); 
        int mes = calendario.get(Calendar.MONTH) + 1;
        int year = calendario.get(Calendar.YEAR);
        System.out.println("dia = " + dia);
        System.out.println("mes = " + mes);
        System.out.println("año = " + year);
    }
    
    public static void main(String[] args) {
        JDateChooserDemo demo = new JDateChooserDemo();
    }
    
}
