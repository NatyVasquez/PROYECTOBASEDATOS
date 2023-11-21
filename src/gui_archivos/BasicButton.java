/*Boton con bordes redondeados para proposito general
se hizo a la clase abstracto para definir al momento de
instanciar que va a hacer el boton
 */

package gui_archivos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public abstract class BasicButton extends JPanel {

    private boolean clicked = false, pressed = false;

    private JLabel text = new JLabel("");

    private Color color = new Color(77, 77, 77);

    private int arcW = 20;//redondeo horizontal

    private int arcH = 20;//redondeo vertical
    
    private Shape forma;
        
    public BasicButton(int w, int h, String texto){
        setSize(w, h);
        setBackground(color);
        text.setText(texto);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.PLAIN, 32));
        forma = new RoundRectangle2D.Double(3,3,getWidth()-6,getHeight()-6, arcW, arcH);
        setOpaque(false);
        add(text);
        accionMouse();
        setVisible(true);
    }

    public BasicButton(){
        setBounds(457, 650, 160, 54);
        setBackground(color);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.PLAIN, 32));
        forma = new RoundRectangle2D.Double(3,3,getWidth()-6,getHeight()-6, arcW, arcH);
        setOpaque(false);
        add(text);
        accionMouse();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Shape aux = new RoundRectangle2D.Double(getLocationOnScreen().x + 3,getLocationOnScreen().y + 3,getWidth()-6,getHeight()-6, arcW, arcH);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Point p = MouseInfo.getPointerInfo().getLocation();
        g2.setColor(getBackground());
        g2.fill(forma);
        if(aux.contains(p)) incrmentRGB();
        else setBackground(color);
        super.paint(g);
        repaint();
    }

    public void accionMouse(){
        MouseListener m = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(forma.contains(e.getPoint())) clickEvent();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(forma.contains(e.getPoint())) incrmentRGB();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                decrementRGB();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        
        addMouseListener(m);
    }

    public abstract void clickEvent();//funcion abstracta para definirle al boton que funcion va a realizar

    public JLabel getText() {
        return text;
    }

    public void setText(JLabel text) {
        this.text = text;
    }

    public void setText(String texto){
        text.setText(texto);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public int getArcW() {
        return arcW;
    }

    public void setArcW(int arcW) {
        this.arcW = arcW;
    }

    public int getArcH() {
        return arcH;
    }

    public void setArcH(int arcH) {
        this.arcH = arcH;
    }

    public void setRound(int arcW, int arcH){
        this.arcW = arcW;
        this.arcH = arcH;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
   
    
    public void incrmentRGB(){
        int r,g,b;
                    if( color.getRed() > 14) r = color.getRed() - 15;
                    else r = color.getRed();

                    if( color.getGreen()> 14) g = color.getGreen()- 15;
                    else g = color.getGreen();

                    if( color.getBlue()> 14) b = color.getBlue()- 15;
                    else b = color.getBlue();

                    setBackground(new Color(r, g, b));
    }
    
    public void decrementRGB(){
        int r,g,b;
        if(getBackground() != color){

            if( getBackground().getRed() < 241) r = getBackground().getRed() + 15;
            else r = getBackground().getRed();

            if( getBackground().getGreen()< 241) g = getBackground().getGreen()+ 15;
            else g = getBackground().getGreen();

            if( getBackground().getBlue()< 241) b = getBackground().getBlue()+ 15;
            else b = getBackground().getBlue();

            setBackground(new Color(r, g, b));
        }
    }
}
