package controllers;

import controllers.session.LoginController;
import controllers.session.RegisterController;
import core.Controller;
import entities.User;
import models.UserModel;
import views.home.HomeView;
import views.session.LoginView;
import views.session.RegisterView;

public class HomeController extends Controller {
    private final HomeView homeView = new HomeView(this);
    private final UserModel userModel = new UserModel();

    private final LoginController loginController = new LoginController(userModel);
    private final RegisterController registerController = new RegisterController(userModel);

    @Override
    public void run() {
        loginController.run();
        registerController.run();

        addLoginView("LoginView", getLoginView());
        addLoginView("RegisterView", getRegisterView());

        addView("HomeView", homeView);

        mainFrame.setVisible(true);
    }

    public LoginView getLoginView() {
        return loginController.getView();
    }

    public RegisterView getRegisterView() {
        return registerController.getView();
    }
}
