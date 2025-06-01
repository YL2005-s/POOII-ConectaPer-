package views.session;

import controllers.session.LoginController;
import utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JPanel {
    private final LoginController loginController;

    public LoginView(LoginController loginController) {
        this.loginController = loginController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        make_logoPanel();
        make_separator(20);
        make_lbl_welcome();
        make_separator(15);
        make_user_field();
        make_separator(10);
        make_password_field();
        make_separator(35);
        make_btn_login();
        make_separator(10);
        make_register_text();
    }

    private void make_separator(int height) {
        add(Box.createRigidArea(new Dimension(0, height)));
    }

    private void make_logoPanel() {
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 210));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_logo = new JLabel();
        lbl_logo.setIcon(ImageUtils.loadIcon("img/logo_cpr.png", 410, 210));
        lbl_logo.setHorizontalAlignment(SwingConstants.CENTER);

        logoPanel.add(lbl_logo, BorderLayout.CENTER);
        add(logoPanel);
    }

    private void make_lbl_welcome() {
        JLabel lbl_welcome = new JLabel("Bienvenido", SwingConstants.CENTER);
        lbl_welcome.setFont(new Font("SansSerif", Font.BOLD, 26));
        lbl_welcome.setForeground(Color.DARK_GRAY);
        lbl_welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lbl_welcome);
    }

    private void make_user_field() {
        JPanel textPanel = new JPanel(new BorderLayout(0, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 65));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel("Correo electrónico");
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        JTextField tf_user = new JTextField();
        tf_user.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_user.setMargin(new Insets(8, 10, 8, 10));
        tf_user.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
        tf_user.setBackground(new Color(245, 245, 245));
        textPanel.add(tf_user, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 5)));
        add(textPanel);
    }

    private void make_password_field() {
        JPanel textPanel = new JPanel(new BorderLayout(0, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 75));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel("Contraseña");
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        JPanel layeredPanel = new JPanel();
        layeredPanel.setLayout(new OverlayLayout(layeredPanel));
        layeredPanel.setBackground(Color.WHITE);
        layeredPanel.setMaximumSize(new Dimension(400, 45));
        layeredPanel.setPreferredSize(new Dimension(400, 45));
        textPanel.add(layeredPanel, BorderLayout.CENTER);

        JPasswordField pf_password = new JPasswordField();
        pf_password.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pf_password.setMargin(new Insets(8, 10, 8, 35));
        pf_password.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
        pf_password.setBackground(new Color(245, 245, 245));
        pf_password.setEchoChar('•');
        pf_password.setAlignmentX(Component.LEFT_ALIGNMENT);
        layeredPanel.add(pf_password);

        JPanel eyePanel = new JPanel();
        eyePanel.setOpaque(false);
        eyePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        eyePanel.setMaximumSize(new Dimension(400, 45));
        eyePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btn_eye = new JButton(ImageUtils.loadIcon("icon/ic_openEyes.png", 20, 20));
        btn_eye.setBorderPainted(false);
        btn_eye.setContentAreaFilled(false);
        btn_eye.setFocusPainted(false);
        btn_eye.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eyePanel.add(btn_eye);
        layeredPanel.add(eyePanel);

        add(Box.createRigidArea(new Dimension(0, 5)));
        add(textPanel);

        btn_eye.addActionListener(e -> {
            if (pf_password.getEchoChar() != 0) {
                pf_password.setEchoChar((char) 0);
                btn_eye.setIcon(ImageUtils.loadIcon("icon/ic_closeEyes.png", 20, 20));
            } else {
                pf_password.setEchoChar('•');
                btn_eye.setIcon(ImageUtils.loadIcon("icon/ic_openEyes.png", 20, 20));
            }
        });
    }

    private void make_btn_login() {
        JButton btn_login = new JButton("Ingresar");
        btn_login.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn_login.setForeground(Color.WHITE);
        btn_login.setBackground(new Color(200, 0, 0));
        btn_login.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn_login.setFocusPainted(false);
        btn_login.setContentAreaFilled(false);
        btn_login.setOpaque(true);
        btn_login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_login.setMaximumSize(new Dimension(300, 45));
        btn_login.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(btn_login);

        btn_login.getModel().addChangeListener(e -> {
            ButtonModel model = btn_login.getModel();
            if (model.isPressed()) {
                btn_login.setBackground(new Color(160, 0, 0));
            } else {
                btn_login.setBackground(new Color(200, 0, 0));
            }
        });
    }

    private void make_register_text() {
        JLabel lbl_register = new JLabel("¿No tienes una cuenta?", SwingConstants.CENTER);
        lbl_register.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lbl_register.setForeground(Color.GRAY);
        lbl_register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl_register.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lbl_register);

        lbl_register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginController.loadRegisterView();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lbl_register.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl_register.setForeground(Color.GRAY);
            }
        });
    }

}
