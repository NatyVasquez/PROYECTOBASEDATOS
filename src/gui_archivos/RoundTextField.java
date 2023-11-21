/*Clase que imita el funcionamiento del textField
con una modificacion de diseño para este ser de
bordes redondeados
 */

package gui_archivos;

import javax.swing.*;
import java.awt.*;

public class RoundTextField extends JPanel {

    private RoundBorder border;//borde del textField

    private JTextField textField = new JTextField();
    public RoundTextField(int w, int h){
        setSize(w, h);
        setBackground(new Color(46,46,46));
        setLayout(null);
        setOpaque(false);
        textField.setBounds(7, 7,w - 14, h - 14);
        textField.setFont(new Font("Arial",Font.PLAIN,18));
        textField.setFocusable(true);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setHorizontalAlignment(JTextField.LEFT);
        border = new RoundBorder(w, h, 10, 10, 2);
        border.setBackground(Color.BLACK);
        add(border);
        add(textField);
        setVisible(true);
    }

    public double getNumText(){
        return Double.parseDouble(textField.getText());
    }

    public void setText(String text){
        textField.setText(text);
    }
    
    public void setEditable(Boolean valor){
        textField.setEditable(valor);
    }

    public String getText(){
        return textField.getText();
    }

    public RoundBorder getRoundBorder(){
        return border;
    }

    public JTextField getTextField() {
        return textField;
    }
    
    public void setTam(int w, int h){
        setSize(w, h);
        textField.setSize(w-12, h-12);
        border.setW(w);
        border.setH(h);
    }
}
