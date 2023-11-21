/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_archivos;

/**
 *
 * @author Andres Guevara
 * @author Camilo Suarez
 * @author Juan Estevan Santiago
 * @author Angie Cobo
 */
import java.awt.Color;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarCorreo {
     
     public static void enviar(Menu m){
         
         boolean error = false;
         String parametros[] =  { "ANGIE NATALIA COBO VASQUEZ",      // remitente
                                "cobovasquezangienatalia@gmail.com",     // correoRemitente
                                "ncki nskw jkez tbhu",                 // contrasena de aplicaciones de Gmail
                                "Envío de correo desde Java",      // asunto
                                "Buenas noches.<br>Este es el envío del correo enviado desde <b><u>Java</u></b><br><br>chao" // mensaje
                                }; 
         
         String correosDestinatarios[] = {  "llasso@uceva.edu.co",
                                            "adrianlassocardona@yahoo.es"};
         
         String archivos[] = {"datosJugadores.csv"};
        try{
            // --------------------------------------------------------------------------------
            //1) Configurar propiedades de la conexion             
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", parametros[1]); // correoRemitente
            props.setProperty("mail.smtp.auth", "true");

            // Preparar la sesion
            Session session = Session.getDefaultInstance(props);

            // Construir el mensaje
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress( parametros[1],      // correoRemitente
                                            parametros[0]));    // remitente
            
            InternetAddress toList[] = new InternetAddress[correosDestinatarios.length];
            for (int i = 0; i < correosDestinatarios.length; i++) { // recorrer el arreglo correosDestinatarios
                toList[i] = new InternetAddress(correosDestinatarios[i]);
            }
            mm.addRecipients(Message.RecipientType.TO, toList); // adicionar la lista de correosDestinatarios
            
            mm.setSubject(parametros[3]); // asunto
            
            // --------------------------------------------------------------------------------
            // 2) Crear el contenido del mensaje a enviar
            MimeBodyPart mime_mensage = new MimeBodyPart();
            mime_mensage.setContent(parametros[4],  // texto del mensage
                                    "text/html");   // tipo de codificacion del mensage
            
            //Crear un objeto de la Clase Multipart y adicionar las partes es este
            Multipart mp = new MimeMultipart();            
            mp.addBodyPart(mime_mensage); // adicionar el texto  
                        
            for (String nf : archivos) {
                MimeBodyPart attach = new MimeBodyPart();
                attach.attachFile(nf);
                mp.addBodyPart(attach); // adicionar el archivo 
            }
            
            mm.setContent(mp);// adicionar la informacion del objeto Multipart al mensaje
                        
            // --------------------------------------------------------------------------------
            // 3) Enviar el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(  parametros[1],      // correoRemitente
                        parametros[2]);     // contrasena de aplicaciones de Gmail
            t.sendMessage(mm, mm.getAllRecipients());
            t.close();
            
        }catch (Exception e) {
            error = true;
        }
        if(!error){
            m.getWarning().setForeground(Color.green);
            m.getWarning().setText("Se ha enviado el correo");
            m.getWarning().setVisible(true);
            Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                m.getWarning().setVisible(false);
            }
        });
        run.start();
        }else{
            m.setForeground(Color.red);
            m.getWarning().setText("Error al enviar el correo");
            m.getWarning().setVisible(true);
            Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                m.getWarning().setVisible(false);
            }
        });
        run.start();
        }
    }
}
