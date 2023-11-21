/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_archivos;

/**
 *
 * @author david
 * @author Camilo Suarez
 * @author Juan Estevan Santiago
 * @author Angie Cobo
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Acerca extends JDialog {

    JLabel[] codigos = new JLabel[4], nombres = new JLabel[4],fotos = new JLabel[4];
    String[] noms = {"Andres Guevara","Juan Santiago", "Juan Suarez", "Angie Cobo"},
            cods = {"230231022","230231009", "230231027", "230222011"};
    BasicButton salir;

    public Acerca() {
        setTitle("Acerca de desarrolladores");
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(Color.white);
        salir();
        datos();
        JLabel titulo = new JLabel("Acerca de desarrolladores");
        titulo.setFont(new Font("Arial", Font.PLAIN, 18));
        add(titulo);
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
        salir.setLocation(325, 400);
        salir.setColor(new Color(255,0,40));
        add(salir);
        setModal(true);
        setVisible(false);
    }

    public void datos() {
        int j = 0;
        for (int i = 0; i < 4; i++, j += 180) {
            fotos[i] = new JLabel(imagen("src/images/foto"+(i+1)+".jpg"));
            fotos[i].setBounds(40 + j, 60, 150, 200);
            add(fotos[i]);
            
            nombres[i] = new JLabel("Nombre: " + noms[i]);
            nombres[i].setBounds(40 + j, 310, 150, 30);
            add(nombres[i]);

            codigos[i] = new JLabel("codigo: "+ cods[i]);
            codigos[i].setBounds(40 + j, 350, 150, 30);
            add(codigos[i]);
        }
    }

    public void salir() {
        setVisible(false);
        dispose();
    }
    
    public Icon imagen(String source){
        ImageIcon imagen = new ImageIcon(source);
        float scale = (float)  200/imagen.getIconHeight();
        Icon icon = new ImageIcon(
                imagen.getImage().getScaledInstance((int) (imagen.getIconWidth()*scale), (int) (imagen.getIconHeight()*scale), Image.SCALE_DEFAULT));
        return icon;
    }
}