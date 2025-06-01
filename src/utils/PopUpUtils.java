package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopUpUtils {

    public static void showMessageDialog(Component parent, String message, boolean success) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), success ? "Éxito" : "Error", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.setSize(350, 260);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        dialog.setContentPane(mainPanel);

        JPanel header = new JPanel();
        header.setBackground(new Color(240, 240, 240));
        header.setPreferredSize(new Dimension(350, 35));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8));
        header.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        header.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JLabel headerLabel = new JLabel(success ? "✅ Éxito" : "❗ Error");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        headerLabel.setForeground(Color.DARK_GRAY);
        header.add(headerLabel);

        final Point[] mouseDownCompCoords = {null};
        header.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords[0] = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords[0] = null;
            }
        });

        header.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                dialog.setLocation(currCoords.x - mouseDownCompCoords[0].x, currCoords.y - mouseDownCompCoords[0].y);
            }
        });

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        JLabel lbl_logo = new JLabel(ImageUtils.loadIcon("img/logo_cpr.png", 160, 90));
        lbl_logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lbl_message = new JLabel("<html><div style='text-align: center;'>" + message + "</div></html>");
        lbl_message.setFont(new Font("SansSerif", Font.PLAIN, 15));
        lbl_message.setForeground(Color.DARK_GRAY);
        lbl_message.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl_message.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btn_ok = new JButton("Entendido");
        btn_ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_ok.setBackground(success ? new Color(210, 255, 210) : new Color(240, 240, 240));
        btn_ok.setForeground(Color.DARK_GRAY);
        btn_ok.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn_ok.setFocusPainted(false);
        btn_ok.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        btn_ok.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn_ok.addActionListener(e -> dialog.dispose());

        content.add(lbl_logo);
        content.add(Box.createVerticalStrut(10));
        content.add(lbl_message);
        content.add(Box.createVerticalStrut(20));
        content.add(btn_ok);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(content, BorderLayout.CENTER);

        dialog.setVisible(true);
    }

}
