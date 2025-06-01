package controllers.session;

import core.Controller;
import entities.User;
import models.UserModel;
import views.session.RegisterView;

public class RegisterController extends Controller {
    private RegisterView registerView;

    private final UserModel userModel;

    public RegisterController(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void run() {
        this.registerView = new RegisterView(this);
    }

    public boolean registerUser(User user) {
        try {
            userModel.registerUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void loadLoginView() {
        loadLoginView("LoginView");
    }

    public RegisterView getView() {
        return registerView;
    }
}

