package views.home;

import controllers.HomeController;
import core.Model;
import core.View;

import javax.swing.*;

public class HomeView extends JPanel implements View {
    private final HomeController homeController;

    public HomeView(HomeController homeController) {
        this.homeController = homeController;

    }


    @Override
    public void update(Model model, Object data) {

    }
}
