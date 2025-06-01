package views.session;

import controllers.session.RegisterController;
import entities.User;
import utils.ImageUtils;
import utils.PopUpUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RegisterView extends JPanel {
    private final RegisterController registerController;

    private JTextField tf_nombre;
    private JTextField tf_dni;
    private JTextField tf_usuario;
    private JPasswordField pf_password;
    private JPasswordField pf_confirm;

    public RegisterView(RegisterController registerController) {
        this.registerController = registerController;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        make_logoPanel();
        make_separator(20);
        make_lbl_title();
        make_separator(15);
        make_text_fields();
        make_password_fields();
        make_separator(25);
        make_btn_submit();
    }

    private void make_separator(int height) {
        add(Box.createRigidArea(new Dimension(0, height)));
    }

    private void cleanFields() {
        tf_nombre.setText("");
        tf_dni.setText("");
        tf_usuario.setText("");
        pf_password.setText("");
        pf_confirm.setText("");
    }

    private void make_logoPanel() {
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.X_AXIS));
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setBorder(new LineBorder(new Color(200, 200, 200), 1, false));

        JButton btn_back = new JButton(ImageUtils.loadIcon("icon/ic_redArrow.png", 70, 70));
        btn_back.setContentAreaFilled(false);
        btn_back.setFocusPainted(false);
        btn_back.setBorderPainted(false);
        btn_back.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logoPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        logoPanel.add(btn_back);
        logoPanel.add(Box.createHorizontalGlue());

        JLabel lbl_logo = new JLabel(ImageUtils.loadIcon("img/logo_cpr.png", 200, 100));
        lbl_logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoPanel.add(lbl_logo);

        logoPanel.add(Box.createHorizontalGlue());
        logoPanel.add(Box.createRigidArea(new Dimension(90, 0)));

        add(logoPanel);

        btn_back.addActionListener(e -> registerController.loadLoginView());
    }


    private void make_lbl_title() {
        JLabel lbl_title = new JLabel("Registro de Usuario", SwingConstants.CENTER);
        lbl_title.setFont(new Font("SansSerif", Font.BOLD, 26));
        lbl_title.setForeground(Color.DARK_GRAY);

        lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lbl_title);
    }

    private void make_text_fields() {
        tf_nombre = setupTextField("Nombre Completo");
        tf_dni = setupTextField("DNI");
        tf_usuario = setupTextField("Correo electrónico");
    }

    private void make_password_fields() {
        pf_password = setupPasswordField("Contraseña");
        pf_confirm = setupPasswordField("Confirmar Contraseña");
    }

    private void make_btn_submit() {
        JButton btn_register = new JButton("Listo");
        btn_register.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn_register.setForeground(Color.WHITE);
        btn_register.setBackground(new Color(200, 0, 0));
        btn_register.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn_register.setFocusPainted(false);
        btn_register.setContentAreaFilled(false);
        btn_register.setOpaque(true);
        btn_register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_register.setMaximumSize(new Dimension(300, 45));
        btn_register.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(btn_register);

        btn_register.getModel().addChangeListener(e -> {
            ButtonModel model = btn_register.getModel();
            if (model.isPressed()) {
                btn_register.setBackground(new Color(160, 0, 0));
            } else {
                btn_register.setBackground(new Color(200, 0, 0));
            }
        });

        btn_register.addActionListener(e -> {
            String nombre = tf_nombre.getText().trim();
            String dni = tf_dni.getText().trim();
            String correo = tf_usuario.getText().trim();
            String password = new String(pf_password.getPassword());
            String confirm = new String(pf_confirm.getPassword());

            if (nombre.isEmpty() || dni.isEmpty() || correo.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                PopUpUtils.showMessageDialog(this, "Por favor, completa todos los campos.", false);
                return;
            }

            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                PopUpUtils.showMessageDialog(this, "El nombre no puede contener números ni caracteres especiales.", false);
                return;
            }

            if (!dni.matches("\\d+")) {
                PopUpUtils.showMessageDialog(this, "El DNI debe contener solo números.", false);
                return;
            }

            if (!password.equals(confirm)) {
                PopUpUtils.showMessageDialog(this, "Las contraseñas no coinciden.", false);
                return;
            }

            User user = new User(nombre, correo, password);
            if (registerController.registerUser(user)) {
                PopUpUtils.showMessageDialog(this, "✅ Registro exitoso.", true);
                registerController.loadLoginView();
                cleanFields();
            } else {
                PopUpUtils.showMessageDialog(this, "Ocurrió un error al registrar el usuario.", false);
            }
        });
    }

    private JTextField setupTextField(String text) {
        JPanel textPanel = new JPanel(new BorderLayout(0, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 65));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel(text);
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        JTextField tf_text = new JTextField();
        tf_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf_text.setMargin(new Insets(8, 12, 8, 12));
        tf_text.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
        tf_text.setBackground(new Color(245, 245, 245));
        textPanel.add(tf_text, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 5)));
        add(textPanel);
        return tf_text;
    }

    private JPasswordField setupPasswordField(String text) {
        JPanel textPanel = new JPanel(new BorderLayout(0, 5));
        textPanel.setBackground(Color.WHITE);
        textPanel.setMaximumSize(new Dimension(400, 65));
        textPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl_text = new JLabel(text);
        lbl_text.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl_text.setForeground(Color.DARK_GRAY);
        textPanel.add(lbl_text, BorderLayout.NORTH);

        JPasswordField pf_pass = new JPasswordField();
        pf_pass.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pf_pass.setMargin(new Insets(8, 10, 8, 10));
        pf_pass.setBorder(new LineBorder(new Color(180, 180, 180), 1, true));
        pf_pass.setBackground(new Color(245, 245, 245));
        pf_pass.setEchoChar('•');

        textPanel.add(pf_pass, BorderLayout.CENTER);

        add(Box.createRigidArea(new Dimension(0, 5)));
        add(textPanel);
        return pf_pass;
    }

}
