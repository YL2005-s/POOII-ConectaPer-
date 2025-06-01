package core;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Controller {
    protected static final JFrame mainFrame = new JFrame("ConectaPerú");
    private static final JPanel viewsLogin = new JPanel(new CardLayout());
    private static final JPanel viewsViewer = new JPanel(new CardLayout());
    private static final Map<String, Component> mainFrameComponents = new HashMap<>();

    static {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 700);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.add(viewsLogin);
    }

    public abstract void run();

    public static void addView(String viewName, Component view) {
        viewsViewer.add(view, viewName);
    }

    public static void addLoginView(String viewName, Component view) {
        viewsLogin.add(view, viewName);
    }

    public static void destroyLoginView() {
        mainFrame.remove(viewsLogin);
        mainFrame.add(viewsViewer);
    }

    public static void loadView(String viewName) {
        CardLayout cl = (CardLayout) viewsViewer.getLayout();
        cl.show(viewsViewer, viewName);
    }

    public static void loadLoginView(String viewName) {
        CardLayout cl = (CardLayout) viewsLogin.getLayout();
        cl.show(viewsLogin, viewName);
    }

    public static void addComponent(String name, Component component) {
        mainFrameComponents.put(name, component);
    }

    public static void removeComponent(String name) {
        mainFrameComponents.remove(name);
    }

    public static Component getComponent(String name) {
        return mainFrameComponents.get(name);
    }
}
