package controllers.session;

import core.Controller;
import entities.User;
import models.UserModel;
import views.session.LoginView;

public class LoginController extends Controller {
    private LoginView loginView;

    private final UserModel userModel;

    public LoginController(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void run() {
        this.loginView = new LoginView(this);
    }

    public boolean verifyUser(String username, String password) {
        try {
            return userModel.loginUser(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void loadMenuView() {
        destroyLoginView();
        loadView("HomeView");
    }

    public void loadRegisterView() {
        loadLoginView("RegisterView");
    }

    public LoginView getView() {
        return loginView;
    }
}
