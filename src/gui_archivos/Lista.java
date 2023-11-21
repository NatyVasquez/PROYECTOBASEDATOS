package gui_archivos;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Andres Guevara
 * @author Camilo Suarez
 * @author Juan Estevan Santiago
 * @author Angie Cobo
 */
public class Lista extends JFrame{
    
    private BasicButton[] botones = new BasicButton[4];

    private JLabel titulo = new JLabel("Listados");

    private JPanel panel = new JPanel(null);
        
    private JScrollPane[] listas = new JScrollPane[3];
    
    private JTable[] tablas = new JTable[3];
    
    private Menu m;
    
    public Lista(Menu m){
        super("Listados");
        this.m = m;
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(panel);
        panel.setBackground(Color.white);
        panel.setLayout(null);
        titulo.setBounds(450, 25, 100, 50);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setVerticalAlignment(JLabel.CENTER);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        iniciarBotones();
        iniciarLista();
        add(titulo);
        setVisible(true);
    }
    
    public void iniciarBotones() {
        botones[0] = new BasicButton(350, 150, "Listado General") {
            @Override
            public void clickEvent() {
                botones[0].setVisible(false);
                botones[1].setVisible(false);
                botones[2].setVisible(false);
                listas[0].setVisible(true);
            }
        };
        botones[0].setLocation(325, 100);
        
        botones[1] = new BasicButton(350, 150, "Listado Genero/Edad") {
            @Override
            public void clickEvent() {
                botones[0].setVisible(false);
                botones[1].setVisible(false);
                botones[2].setVisible(false);
                listas[1].setVisible(true);
            }
        };
        botones[1].setLocation(325, 300);

        botones[2] = new BasicButton(350, 150, "<html><p align=center>Listado Genero de juego favorito/Juego favorito</p></html> ") {
            @Override
            public void clickEvent() {
                botones[0].setVisible(false);
                botones[1].setVisible(false);
                botones[2].setVisible(false);
                listas[2].setVisible(true);
            }
        };
        botones[2].setLocation(325, 500);

        botones[3] = new BasicButton(150, 50, "Atras") {
            @Override
            public void clickEvent() {
                
                boolean pass = false;
                for (int i = 0; i < 3; i++) {
                    if(listas[i].isVisible()){
                        listas[i].setVisible(false);
                        botones[0].setVisible(true);
                        botones[1].setVisible(true);
                        botones[2].setVisible(true);
                        pass = true;
                    }
                }
                if(!pass){
                    setVisible(false);
                    m.setVisible(true);
                    dispose();
                }
            }
        };
        botones[3].setLocation(425, 700);
        
        for (BasicButton boton : botones) {
            boton.setColor(new Color(0, 85, 255));
            boton.setLayout(null);
            boton.getText().setFont(new Font("Arial", Font.PLAIN, 18));
            boton.getText().setBounds(boton.getWidth()/2-100, boton.getHeight()/2-50, 200, 100);
            boton.getText().setVerticalAlignment(JLabel.CENTER);
            boton.getText().setHorizontalAlignment(JLabel.CENTER);
            panel.add(boton);
        }
        botones[3].setColor(new Color(255,0,40));
    }
    
    
    public void iniciarLista(){
        DefaultTableModel[] models = new DefaultTableModel[3];
        
        for (int i = 0; i < 3; i++) {
            String[] encabezados = {"Nickname", "Nombre", "Apellido", "Fecha de nacimiento", "Edad","Fecha de registro",
                                                                "Genero", "Juego Favorito", "Tipo de juego Favorito"};
    
            String[][] datos = {{"","","","","","","","",""}};
            models[i] = new DefaultTableModel(datos, encabezados);
        }
        
        
        for (int i = 0; i < 3; i++) {

            ArrayList<String[]> filas;
            
            tablas[i] = new JTable(models[i]);
            tablas[i].setSelectionBackground(Color.lightGray);
            tablas[i].setGridColor(Color.black);
            tablas[i].setGridColor(Color.black);
            
            JTableHeader th = tablas[i].getTableHeader();
            th.setBackground(Color.white);
            
            TableColumn tc = tablas[i].getColumn("Fecha de nacimiento");
            tc.setPreferredWidth(200);

            tc = tablas[i].getColumn("Fecha de registro");
            tc.setPreferredWidth(200);

            tc = tablas[i].getColumn("Juego Favorito");
            tc.setPreferredWidth(200);

            tc = tablas[i].getColumn("Tipo de juego Favorito");
            tc.setPreferredWidth(200);

            DefaultTableCellRenderer tc1 = new DefaultTableCellRenderer();
            tc1.setHorizontalAlignment(SwingConstants.CENTER);
            
            for (int j = 0; j < 9; j++) {
                tablas[i].getColumnModel().getColumn(j).setCellRenderer(tc1);
            }
            

            filas = getDatos();

            models[i].removeRow(0);
            for (int j = 0; j < filas.size(); j++) {
                models[i].addRow(filas.get(j));
            }
        }
        
        TableColumn tc = tablas[0].getColumn("Nickname");
        tc.setPreferredWidth(100);
        
        tablas[1].removeColumn(tablas[1].getColumn("Fecha de registro"));
        tablas[1].removeColumn(tablas[1].getColumn("Fecha de nacimiento"));
        tablas[1].removeColumn(tablas[1].getColumn("Juego Favorito"));
        tablas[1].removeColumn(tablas[1].getColumn("Tipo de juego Favorito"));
        
        tablas[2].removeColumn(tablas[2].getColumn("Fecha de registro"));
        tablas[2].removeColumn(tablas[2].getColumn("Fecha de nacimiento"));
        tablas[2].removeColumn(tablas[2].getColumn("Genero"));
        tablas[2].removeColumn(tablas[2].getColumn("Edad"));
        
        for (int i = 0; i < 3; i++) {
            listas[i] = new JScrollPane(tablas[i]);
            listas[i].getViewport().setBackground(Color.white);
            listas[i].setBorder(null);
            listas[i].setBounds(100, 80, 800, 600);
            listas[i].setVisible(false);
            add(listas[i]);
        }
    }
    
    public ArrayList<String[]> getDatos(){
        FileReader fr = null;
        boolean error = false;
        ArrayList<String[]> datos = new ArrayList<>();
        try {
            fr = new FileReader("datosJugadores.csv");            
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(this, 
                "Error al tratar de abrir el archivo");
        }
        if(!error){
            BufferedReader br = new BufferedReader(fr);
            String linea = "";            
            try {
                while ((linea = br.readLine()) != null) {                    
                    String tokens[] = linea.split(";");        
                    datos.add(tokens);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al tratar de leer el archivo");
            }
            try {
                fr.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al tratar de cerrar el archivo");
            }
        }
        return datos;
    }
    
}
