package gui_archivos;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;

/**
 *
 * @author Andres Guevara
 * @author Camilo Suarez
 * @author Juan Estevan Santiago
 * @author Angie Cobo
 */
public class Formulario extends JFrame {

    private BasicButton[] botones = new BasicButton[4];

    private RoundTextField[] campos = new RoundTextField[6];

    private JLabel[] tagCampos = new JLabel[7], tagBoxes = new JLabel[3];

    private JLabel titulo = new JLabel("Añadir jugador"), warning = new JLabel();
    
    private RoundComboBox[] boxes = new RoundComboBox[3];

    private JPanel panel = new JPanel(null);
    private JDateChooser dateChooser;
    private JButton btnMostrarCalendario;
    
    private Menu m;

    public Formulario(Menu m) {
        super("Nuevo jugador");
        this.m = m;
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(panel);
        panel.setBackground(Color.white);
        panel.setLayout(null);
        titulo.setBounds(400, 25, 250, 50);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setVerticalAlignment(JLabel.CENTER);
        titulo.setHorizontalAlignment(JLabel.CENTER);
            dateChooser = new JDateChooser(new Date());
    dateChooser.setBounds(150, 400, 100, 25); // Ajusta la posición según tus necesidades
    dateChooser.setVisible(false); // Inicialmente oculto
    panel.add(dateChooser);
        warning.setBounds(450, 400, 500, 50);
        warning.setFont(new Font("Arial", Font.PLAIN, 18));
        warning.setForeground(Color.red);
        warning.setVerticalAlignment(JLabel.CENTER);
        warning.setHorizontalAlignment(JLabel.CENTER);
        warning.setVisible(false);
        iniciarBotones();
        iniciarTextFields();
        iniciarComboBoxes();
        add(warning);
        add(titulo);
        setVisible(true);
    }

    public void iniciarTextFields() {
        for (int i = 0; i < 3; i++) {
            campos[i] = new RoundTextField(330, 50);
            tagCampos[i] = new JLabel();
            tagCampos[i].setSize(100, 50);
            panel.add(campos[i]);
            panel.add(tagCampos[i]);
        }
        
        for (int i = 3; i < 6; i++) {
            campos[i] = new RoundTextField(50, 50);
            tagCampos[i] = new JLabel();
            tagCampos[i].setSize(50, 50);
            panel.add(campos[i]);
            panel.add(tagCampos[i]);
        }
        
        campos[0].setLocation(150, 100);
        tagCampos[0].setText("Nombre");
        tagCampos[0].setHorizontalAlignment(JLabel.CENTER);
        tagCampos[0].setFont(new Font("Arial", Font.PLAIN,18));
        tagCampos[0].setLocation(25, 100);
        campos[0].getTextField().requestFocus();
        
        campos[1].setLocation(150, 200);
        tagCampos[1].setText("Apellido");
        tagCampos[1].setHorizontalAlignment(JLabel.CENTER);
        tagCampos[1].setFont(new Font("Arial", Font.PLAIN,18));
        tagCampos[1].setLocation(25, 200);
        
        campos[2].setLocation(150, 300);
        tagCampos[2].setText("NickName");
        tagCampos[2].setHorizontalAlignment(JLabel.CENTER);
        tagCampos[2].setFont(new Font("Arial", Font.PLAIN,18));
        tagCampos[2].setLocation(25, 300);
        
       /* campos[3].setLocation(200, 400);
        tagCampos[3].setText("Dia");
        tagCampos[3].setHorizontalAlignment(JLabel.CENTER);
        tagCampos[3].setFont(new Font("Arial", Font.PLAIN,18));
        tagCampos[3].setLocation(200, 350);
        
        campos[4].setLocation(290, 400);
        tagCampos[4].setText("Mes");
        tagCampos[4].setHorizontalAlignment(JLabel.CENTER);
        tagCampos[4].setFont(new Font("Arial", Font.PLAIN,18));
        tagCampos[4].setLocation(290, 350);
        
        campos[5].setTam(100, 50);
        campos[5].setLocation(380, 400);
        tagCampos[5].setText("Año");
        tagCampos[5].setHorizontalAlignment(JLabel.CENTER);
        tagCampos[5].setFont(new Font("Arial", Font.PLAIN,18));
        tagCampos[5].setLocation(405, 350);
        */
        tagCampos[6] = new JLabel();
        tagCampos[6].setSize(200, 50);
        panel.add(tagCampos[6]);
        tagCampos[6].setText("Fecha de nacimiento");
        tagCampos[6].setFont(new Font("Arial", Font.PLAIN,18));
        tagCampos[6].setLocation(25, 350);
        
        
    }

    public void iniciarBotones() {
        btnMostrarCalendario = new JButton("Mostrar Calendario");
    btnMostrarCalendario.setBounds(220, 370, 150, 25); // Ajusta la posición según tus necesidades
    btnMostrarCalendario.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dateChooser.setVisible(true);
        }
    });
    panel.add(btnMostrarCalendario);
        botones[0] = new BasicButton(130, 50, "Guardar") {
            @Override
            public void clickEvent() {
                warning.setVisible(false);
                guardar();
                JOptionPane.showMessageDialog(null, "Se ha guardado su registro con exito");
            }
        };
        botones[0].setLocation(285, 475);

        botones[1] = new BasicButton(130, 50, "Limpiar") {
            @Override
            public void clickEvent() {
                limpiar();
            }
        };
        botones[1].setLocation(435, 475);

        botones[2] = new BasicButton(130, 50, "Atras") {
            @Override
            public void clickEvent() {
                setVisible(false);
                m.setVisible(true);
                dispose();
            }
        };
        botones[2].setLocation(585, 475);
        
        botones[3] = new BasicButton(130, 50, "Consultar") {
            @Override
            public void clickEvent() {
                consultar(campos[2].getText());
            }
        };
        botones[3].setLocation(285, 475);
        botones[3].setVisible(false);
        
        for (BasicButton boton : botones) {
            boton.setColor(new Color(0, 85, 255));
            boton.setLayout(null);
            boton.getText().setFont(new Font("Arial", Font.PLAIN, 18));
            boton.getText().setBounds(boton.getWidth() / 2 - 75, boton.getHeight() / 2 - 25, 150, 50);
            boton.getText().setVerticalAlignment(JLabel.CENTER);
            boton.getText().setHorizontalAlignment(JLabel.CENTER);
            panel.add(boton);
        }
        botones[2].setColor(new Color(255, 0, 40));
    }
    
    public void iniciarComboBoxes(){
        for (int i = 0; i < 3; i++) {
            boxes[i] = new RoundComboBox();
            tagBoxes[i] = new JLabel();
            add(tagBoxes[i]);
            add(boxes[i]);
        }
        
        boxes[0].setLocation(720,100);
        boxes[0].addItem("Ninguno");
        boxes[0].addItem("RPG");
        boxes[0].addItem("Shooter");
        boxes[0].addItem("MMO");
        tagBoxes[0].setBounds(500, 100, 200, 50);
        tagBoxes[0].setText("Genero de juego favorito");
        tagBoxes[0].setHorizontalAlignment(JLabel.CENTER);
        tagBoxes[0].setFont(new Font("Arial", Font.PLAIN,18));
        boxes[1].setLocation(720,200);
        boxes[1].addItem("Ninguno");
        boxes[1].addItem("Undertale");
        boxes[1].addItem("Call of Duty");
        boxes[1].addItem("World of warcraft");
        boxes[1].addItem("Minecraft");
        tagBoxes[1].setBounds(500, 200, 200, 50);
        tagBoxes[1].setText("Juego favorito");
        tagBoxes[1].setHorizontalAlignment(JLabel.CENTER);
        tagBoxes[1].setFont(new Font("Arial", Font.PLAIN,18));
        boxes[2].setLocation(720,300);
        boxes[2].addItem("Ninguno");
        boxes[2].addItem("Mujer");
        boxes[2].addItem("Hombre");
        boxes[2].addItem("Otro");
        tagBoxes[2].setBounds(500, 300, 200, 50);
        tagBoxes[2].setText("Genero");
        tagBoxes[2].setHorizontalAlignment(JLabel.CENTER);
        tagBoxes[2].setFont(new Font("Arial", Font.PLAIN,18));
    }
    
    public void guardar(){
        FileWriter w = null;
        Date fechaSeleccionada = dateChooser.getDate();
        boolean error = false;
        try {
            w = new FileWriter("datosJugadores.csv", true);
        } catch (IOException e) {
            error = true;
            warning.setText("Error al crear/abrir el archivo");
            warning.setVisible(true);
        }
        
        Composicion fechas = new Composicion();
        

        
        if (!error && !exits(campos[2].getText())) {
            
            try {
                w.write(campos[2].getText()+";"+campos[0].getText()+";"+campos[1].getText()+";"
                        +fechas.toString()+";"+boxes[2].getSelectedItem()+";"+
                        boxes[1].getSelectedItem()+";"+boxes[0].getSelectedItem()+";"+"\r\n");
            } catch (IOException e) {
                warning.setText("Error al guardar en el archivo");
                warning.setVisible(true);
            }
            
            try {
                w.close();
                limpiar();
            } catch (IOException e) {
                warning.setText( "Error al cerrar el archivo");
                warning.setVisible(true);
            }
        }
    }
    
    public void limpiar(){
        for (RoundTextField c : campos) {
            c.setText("");
        }
        campos[0].setRequestFocusEnabled(true);
        for (RoundComboBox b : boxes) {
            b.setItemSelected(0);
        }
        warning.setVisible(false);
    }
    
    public boolean exits(String PK){
        boolean pass = false, error = false;
        FileReader r = null;
        
        try {
            r = new FileReader("datosJugadores.csv");
        } catch (Exception e) {
            error = true;
            warning.setText("Error al abrir el archivo");
            warning.setVisible(true);
        }
        
        if(!error){
            BufferedReader br = new BufferedReader(r);
            String line = "";
            
            try {
                while ((line = br.readLine()) != null) {
                    if(line.split(";")[0].equals(PK)){
                        warning.setText("Ya existe este jugador");
                        warning.setVisible(true);
                        return true;
                    }
                }
            } catch (Exception e) {
                warning.setText("Error al tratar de leer el archivo");
                warning.setVisible(true);
            }
            
            try {
                r.close();
            } catch (Exception e) {
                warning.setText("Error al tratar de cerrar el archivo");
                warning.setVisible(true);
            }
        }
        return pass;
    }
    
    public void consultar(String PK){
        boolean error = false, existe = false;
        FileReader r = null;
        warning.setVisible(false);
        
        try {
            r = new FileReader("datosJugadores.csv");
        } catch (Exception e) {
            error = true;
            warning.setText("Error al abrir el archivo");
            warning.setVisible(true);
        }
        
        if(!error){
            BufferedReader br = new BufferedReader(r);
            String line = "";
            
            try {
                while ((line = br.readLine()) != null) {
                    if(line.split(";")[0].equals(PK)){
                        campos[0].setText(line.split(";")[1]);
                        campos[1].setText(line.split(";")[2]);
                        campos[3].setText(line.split(";")[3].split("/")[0]);
                        campos[4].setText(line.split(";")[3].split("/")[1]);
                        campos[5].setText(line.split(";")[3].split("/")[2]);
                        boxes[2].getItems().get(0).setDato(line.split(";")[6]);
                        boxes[1].getItems().get(0).setDato(line.split(";")[7]);
                        boxes[0].getItems().get(0).setDato(line.split(";")[8]);
                        existe = true;
                        break;
                    }
                }
                
                if(!existe){
                    warning.setText("No existe el Jugador");
                    warning.setVisible(true);
                }
            } catch (Exception e) {
                warning.setText("Error al tratar de leer el archivo");
                warning.setVisible(true);
            }
            
            try {
                r.close();
            } catch (Exception e) {
                warning.setText("Error al tratar de cerrar el archivo");
                warning.setVisible(true);
            }
        }
    }
    
    public void modoConsulta(){
        setTitle("Consultar Jugador");
        titulo.setText("Consultar Jugador");
        for (RoundComboBox box : boxes) {
            box.setEnable(false);
        }
        for (RoundTextField campo : campos) {
            campo.getTextField().setFocusable(false);
        }
        campos[2].getTextField().setFocusable(true);
        campos[2].getTextField().requestFocus();
        botones[0].setVisible(false);
        botones[3].setVisible(true);
    }
}
