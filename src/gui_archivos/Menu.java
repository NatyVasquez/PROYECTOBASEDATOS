package gui_archivos;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andres Guevara
 * @author Camilo Suarez
 * @author Juan Estevan Santiago
 * @author Angie Cobo
 */class Menu extends JFrame {

    private BasicButton[] botones = new BasicButton[7];

    private JLabel titulo = new JLabel("Menu"),warning = new JLabel();

    private JPanel panel = new JPanel(null);

    public Menu() {
        super("Desarrolladora de videojuegos");
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
        warning.setBounds(250, 650, 500, 50);
        warning.setFont(new Font("Arial", Font.PLAIN, 18));
        warning.setForeground(Color.red);
        warning.setVerticalAlignment(JLabel.CENTER);
        warning.setHorizontalAlignment(JLabel.CENTER);
        //warning.setVisible(false);
        iniciarBotones();
        add(warning);
        add(titulo);
        setVisible(true);
    }

    public void iniciarBotones() {
        botones[0] = new BasicButton(350, 150, "Añadir Jugador") {
            @Override
            public void clickEvent() {
                Menu.this.setVisible(false);
                new Formulario(Menu.this).setVisible(true);
            }
        };
        botones[0].setLocation(100, 90);
        
        botones[1] = new BasicButton(350, 150, "Consultar") {
            @Override
            public void clickEvent() {
                Menu.this.setVisible(false);
                Formulario f = new Formulario(Menu.this);
                f.modoConsulta();
            }
        };
        botones[1].setLocation(550, 90);

        botones[2] = new BasicButton(350, 150, "Listar") {
            @Override
            public void clickEvent() {
                Menu.this.setVisible(false);
                new Lista(Menu.this);
            }
        };
        botones[2].setLocation(100, 290);

        botones[3] = new BasicButton(350, 150, "Estadisticas") {
            @Override
            public void clickEvent() {
                Menu.this.setVisible(false);
                new Estadistica(Menu.this);
            }
        };
        botones[3].setLocation(550, 290);

        botones[4] = new BasicButton(350, 150, "Enviar Datos") {
            @Override
            public void clickEvent() {
                    EnviarCorreo.enviar(Menu.this);
            }
        };
        botones[4].setLocation(100, 490);

        botones[5] = new BasicButton(350, 150, "Informacion") {
            @Override
            public void clickEvent() {
                Menu.this.setVisible(false);
                    Acerca a = new Acerca();
                    a.setVisible(true);
            }
        };
        botones[5].setLocation(550, 490);

        botones[6] = new BasicButton(150, 50, "Salir") {
            @Override
            public void clickEvent() {
                System.exit(0);
            }
        };
        botones[6].setLocation(425, 700);

        for (BasicButton boton : botones) {
            boton.setColor(new Color(0, 85, 255));
            boton.setLayout(null);
            boton.getText().setFont(new Font("Arial", Font.PLAIN, 18));
            boton.getText().setBounds(boton.getWidth()/2-75, boton.getHeight()/2-25, 150, 50);
            boton.getText().setVerticalAlignment(JLabel.CENTER);
            boton.getText().setHorizontalAlignment(JLabel.CENTER);
            panel.add(boton);
        }
        botones[6].setColor(new Color(255,0,40));
    }

    public JLabel getWarning() {
        return warning;
    }
}
