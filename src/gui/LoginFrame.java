
package gui;

import domain.User;
import service.UserService;

/**
 *
 * @author PC
 */
public class LoginFrame extends javax.swing.JFrame {

    private UserService userService;

    /**
     * Creates new form Login
     */
    public LoginFrame() {
        userService = new UserService();
        initComponents();
        setLocationRelativeTo(null);
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        EmailInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        LoginBtn = new javax.swing.JButton();
        RegisterBtn = new javax.swing.JLabel();
        PasswordInput = new javax.swing.JPasswordField();
        showPassword = new javax.swing.JCheckBox();

        jLabel1.setText("jLabel1");


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LOGIN");
        jLabel2.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel4.setText("Email: ");

        EmailInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailInputActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel5.setText("Password:");

        LoginBtn.setBackground(new java.awt.Color(51, 153, 255));
        LoginBtn.setFont(new java.awt.Font("Segoe UI", 0, 36));
        LoginBtn.setText("LOGIN");
        LoginBtn.setToolTipText("");
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });

        RegisterBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        RegisterBtn.setForeground(new java.awt.Color(255, 51, 51));
        RegisterBtn.setText("Register");
        RegisterBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterBtnMouseClicked(evt);
            }
        });

        PasswordInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordInputActionPerformed(evt);
            }
        });

        showPassword.setText("Show Password");
        showPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(showPassword)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(RegisterBtn))
                                        .addComponent(LoginBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(EmailInput, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 5, Short.MAX_VALUE))
                                        .addComponent(PasswordInput, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(EmailInput, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5)
                                        .addComponent(PasswordInput, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(RegisterBtn)
                                        .addComponent(showPassword))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(LoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
        );

        pack();
    }

    private void EmailInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String email = EmailInput.getText();
        String password = new String(PasswordInput.getPassword());

        boolean result = userService.loginUser(email,password);
        if(result == false){
            return;
        }
        else {
            user.setUserId(userService.getUserId(email,password));
            openMainScreenFrame();
        }
    }

    private void openMainScreenFrame() {
        MainScreen mainScreen =new MainScreen();
        mainScreen.setVisible(true);
        dispose();
    }

    private void RegisterBtnMouseClicked(java.awt.event.MouseEvent evt) {
        RegisterFrame registerFrame = new RegisterFrame();
        registerFrame.setVisible(true);
        dispose();
    }

    private void PasswordInputActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (showPassword.isSelected()) {
            PasswordInput.setEchoChar('\u0000');
        } else {
            PasswordInput.setEchoChar('*');
        }
    }

    private javax.swing.JTextField EmailInput;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JPasswordField PasswordInput;
    private javax.swing.JLabel RegisterBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JCheckBox showPassword;
    public static User user = new User();
}
