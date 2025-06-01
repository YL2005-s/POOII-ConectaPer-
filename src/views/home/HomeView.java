package views.home;

import controllers.HomeController;
import core.Model;
import core.View;
import utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomeView extends JPanel implements View {
    private final HomeController homeController;

    public HomeView(HomeController homeController) {
        this.homeController = homeController;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        make_top_bar();
        make_main_content();
        make_bottom_nav();
    }

    private void make_top_bar() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btn_menu = new JButton("☰");
        styleIconButton(btn_menu);
        topPanel.add(btn_menu, BorderLayout.WEST);

        JLabel logo = new JLabel(ImageUtils.loadIcon("img/logo_cpr.png", 150, 130), JLabel.CENTER);
        logo.setFont(new Font("SansSerif", Font.BOLD, 18));
        logo.setForeground(new Color(200, 0, 0));
        topPanel.add(logo, BorderLayout.CENTER);

        JButton btn_bell = new JButton("🔔");
        styleIconButton(btn_bell);
        topPanel.add(btn_bell, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void make_main_content() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel lbl_welcome = new JLabel("Hola, xxxxx");
        lbl_welcome.setFont(new Font("SansSerif", Font.BOLD, 22));
        lbl_welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl_welcome.setForeground(Color.BLACK);
        centerPanel.add(lbl_welcome);

        centerPanel.add(Box.createVerticalStrut(30));

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setMaximumSize(new Dimension(300, 40));
        searchPanel.setBackground(new Color(230, 230, 230));
        searchPanel.setBorder(new LineBorder(Color.GRAY));

        JLabel icon = new JLabel("🔍");
        icon.setBorder(new EmptyBorder(0, 10, 0, 10));
        searchPanel.add(icon, BorderLayout.WEST);

        JTextField tf_search = new JTextField("Buscar trabajo o empleo");
        tf_search.setBorder(null);
        tf_search.setBackground(new Color(230, 230, 230));
        tf_search.setForeground(Color.GRAY);
        tf_search.setEditable(false);
        searchPanel.add(tf_search, BorderLayout.CENTER);

        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(searchPanel);

        centerPanel.add(Box.createVerticalStrut(20));

        JButton btn_empleo = new JButton("Empleo disponible");
        btn_empleo.setBackground(new Color(200, 255, 200));
        btn_empleo.setForeground(Color.BLACK);
        btn_empleo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btn_empleo.setFocusPainted(false);
        btn_empleo.setMaximumSize(new Dimension(300, 40));
        btn_empleo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(btn_empleo);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void make_bottom_nav() {
        JPanel navPanel = new JPanel(new GridLayout(1, 4));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

        navPanel.add(createNavItem("🏠", "Inicio"));
        navPanel.add(createNavItem("📋", "Empleos"));
        navPanel.add(createNavItem("🎯", "Metas"));
        navPanel.add(createNavItem("👤", "Perfil"));

        add(navPanel, BorderLayout.SOUTH);
    }

    private JPanel createNavItem(String emoji, String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel icon = new JLabel(emoji, JLabel.CENTER);
        icon.setFont(new Font("SansSerif", Font.PLAIN, 20));

        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));

        panel.add(icon, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);

        return panel;
    }

    private void styleIconButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void update(Model model, Object data) {
        // Implementación futura si es necesario
    }
}
