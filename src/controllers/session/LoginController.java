package controllers.session;

import core.Controller;
import views.session.LoginView;

public class LoginController extends Controller {
    private LoginView loginView;

    @Override
    public void run() {
        this.loginView = new LoginView(this);
    }

    public void loadRegisterView() {
        loadLoginView("RegisterView");
    }

    public LoginView getView() {
        return loginView;
    }
}
