package gui_archivos;

import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
/**
 *
 * @author Andres Guevara
 * @author Camilo Suarez
 * @author Juan Estevan Santiago
 * @author Angie Cobo
 */
public class Estadistica extends JFrame{
    private Menu m;
    BasicButton salir;
    
    public Estadistica(Menu m){
        super("Estadisticas");
        this.m=m;
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        DefaultCategoryDataset datasetJuegoFav = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetSexo = new DefaultCategoryDataset();
        DefaultCategoryDataset datasetEdades = new DefaultCategoryDataset();
        
        LeerFile(datasetSexo,datasetJuegoFav,datasetEdades);
        
        JFreeChart chartSexo = ChartFactory.createBarChart(
        "Sexo de los Jugadores",
        "Sexo",
        "Cantidad",
        datasetSexo,
        PlotOrientation.VERTICAL,
        true,
        true,
        true);
        
        JFreeChart chartJuegoFav = ChartFactory.createBarChart(
        "Juego favorito de los Jugadores",
        "Juego Favorito",
        "Cantidad",
        datasetJuegoFav,
        PlotOrientation.VERTICAL,
        true,
        true,
        true);
        
        JFreeChart chartEdades = ChartFactory.createBarChart(
        "Edad de los Jugadores",
        "Grupo de edades",
        "Cantidad",
        datasetEdades,
        PlotOrientation.VERTICAL,
        true,
        true,
        true);
        
        ChartPanel panelSexo = new ChartPanel(chartSexo,false);
        ChartPanel panelJuegoFav = new ChartPanel(chartJuegoFav,false);
        ChartPanel panelEdad = new ChartPanel(chartEdades,false);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Sexo",panelSexo);
        tabbedPane.addTab("Juego Favorito", panelJuegoFav);
        tabbedPane.addTab("Edades", panelEdad);
        tabbedPane.setSize(1000, 690);
        add(tabbedPane);
        
        salir = new BasicButton(150, 50, "Salir") {
            @Override
            public void clickEvent() {
                salir();
            }
        };
        salir.setLayout(null);
        salir.getText().setFont(new Font("Arial", Font.PLAIN, 18));
        salir.getText().setBounds(salir.getWidth()/2-75, salir.getHeight()/2-25, 150, 50);
        salir.getText().setVerticalAlignment(JLabel.CENTER);
        salir.getText().setHorizontalAlignment(JLabel.CENTER);
        salir.setLocation(425, 700);
        salir.setColor(new Color(255,0,40));
        add(salir);
        
        setVisible(true);
    }
    public void LeerFile(DefaultCategoryDataset datasetSexo,DefaultCategoryDataset datasetJuegoFav,DefaultCategoryDataset datasetEdades){
        FileReader fr= null;
        boolean error = false;
        try {
            fr = new FileReader("datosJugadores.csv");
        }catch(Exception e){
            error = true;
            JOptionPane.showMessageDialog(null, "error al abrir el archivo");
        }
        if(!error){
            BufferedReader br = new BufferedReader(fr);
            String linea= "",tokens[];
            int[] contadoresJuegoFav = new int[5];
            int[] contadoresSexo = new int[4];
            int[] contadoresEdades = new int[2];
            
            try {
                while((linea = br.readLine())!=null){
                    tokens = linea.split(";");
                    
                    int edad = Integer.parseInt(tokens[4]);
                    if(edad >= 18) contadoresEdades[0]++;
                    else contadoresEdades[1]++;
                    
                    switch(tokens[6]){
                        case "Ninguno":
                            contadoresSexo[0]++;
                            break;
                        case "Mujer":
                            contadoresSexo[1]++;
                            break;
                        case "Hombre":
                            contadoresSexo[2]++;
                            break;
                        case "Otro":
                            contadoresSexo[3]++;
                            break;
                    }
                    switch(tokens[7]){
                        case "Ninguno":
                            contadoresJuegoFav[0]++;
                            break;
                        case "Undertale":
                            contadoresJuegoFav[1]++;
                            break;
                        case "Call of Duty":
                            contadoresJuegoFav[2]++;
                            break;
                        case "World of warcraft":
                            contadoresJuegoFav[3]++;
                            break;
                        case "Minecraft":
                            contadoresJuegoFav[4]++;
                            break;
                    }
                    
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error al leer el archivo");
            }
            datasetSexo.setValue(contadoresSexo[0], "Sexo","Ninguno");
            datasetSexo.setValue(contadoresSexo[1], "Sexo", "Mujer");
            datasetSexo.setValue(contadoresSexo[2], "Sexo", "Hombre");
            datasetSexo.setValue(contadoresSexo[3], "Sexo", "Otro");
            
            datasetJuegoFav.setValue(contadoresJuegoFav[0], "Juego Favorito", "Ninguno");
            datasetJuegoFav.setValue(contadoresJuegoFav[1], "Juego Favorito", "Undertale");
            datasetJuegoFav.setValue(contadoresJuegoFav[2], "Juego Favorito", "Call of Duty");
            datasetJuegoFav.setValue(contadoresJuegoFav[3], "Juego Favorito", "World of warcraft");
            datasetJuegoFav.setValue(contadoresJuegoFav[4], "Juego Favorito", "Minecraft");
            
            datasetEdades.setValue(contadoresEdades[0], "Edades", "Mayores de 18");
            datasetEdades.setValue(contadoresEdades[1], "Edades", "Menores de 18");
            try{
                fr.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo");
            }
        }
    }
    
    public void salir() {
        setVisible(false);
        dispose();
    }
}