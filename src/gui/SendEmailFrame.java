package gui;
/*
 * @author HongAnh
 * @created 13 / 02 / 2024 - 3:58 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */

import domain.Event;
import domain.User;
import service.UserService;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailFrame extends javax.swing.JFrame {

    /**
     * Creates new form SendEmailFrame
     */
    public SendEmailFrame(User user, Event event) {
        userService = new UserService();
        initComponents(user,event);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents(User user,Event event) {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtToEmail = new javax.swing.JTextField();
        txtFromEmail = new javax.swing.JTextField();
        txtSubject = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        accpectBtn = new javax.swing.JButton();
        rejectBtn = new javax.swing.JButton();
        sendBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtFromEmail.setText("hle646698@gmail.com");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("TO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("FROM");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Subject");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Message");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        accpectBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        accpectBtn.setText("Accept");

        rejectBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rejectBtn.setText("Reject");

        sendBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sendBtn.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(24, 24, 24)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtToEmail)
                                                        .addComponent(txtFromEmail)
                                                        .addComponent(txtSubject)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(accpectBtn)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                                                                .addComponent(sendBtn)
                                                                .addGap(106, 106, 106)
                                                                .addComponent(rejectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(51, 51, 51))
                                                        .addComponent(jScrollPane1))))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(txtToEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(txtFromEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(accpectBtn)
                                        .addComponent(rejectBtn)
                                        .addComponent(sendBtn))
                                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    public void sendBtnActionPerformed(User user,Event event) {
        User toUser = userService.getUserById(user.getUserId());
        // TODO add your handling code here:
        String toEmail = toUser.getEmail();
        String fromEmail = txtFromEmail.getText();
        String fromEmailPassword = "glkm bbyr cffo lqlp";
        String subjectText = "Registered Event " + event.getName();
        String subject = subjectText;
        String eventDescription = "Thank you for registering for our event. We are excited to have you join us on " + event.getStartDateTime() + " in " + event.getPlace() +" and you must transfer " +event.getPrice() +"$"+ ". Please feel free to reach out if you have any questions or concerns. We look forward to seeing you there!";
        String messageContent = eventDescription;

        String emailBody = "<html><body><p>" + messageContent + "</p>"
                + "<button onclick=\"window.location.href='http://example.com/accept'\">Accept</button>"
                + "<button onclick=\"window.location.href='http://example.com/reject'\">Reject</button>"
                + "</body></html>";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail,fromEmailPassword);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setContent(emailBody, "text/html");

            Transport.send(message);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton accpectBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton rejectBtn;
    private javax.swing.JButton sendBtn;
    private javax.swing.JTextField txtFromEmail;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JTextField txtToEmail;
    private final UserService userService;
    // End of variables declaration
}
