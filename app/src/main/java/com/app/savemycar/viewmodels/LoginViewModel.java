package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.LoginUseCase;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private final LoginUseCase loginUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public LoginViewModel() {
        loginUseCase = Injection.getLoginUseCase();
    }

    public void loginAsAdmin(String email, String password) {
        if (validate(email, password)) {
            loginUseCase.execute(email, password, success);
        }
    }

    private boolean validate(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            error.setValue("You must enter email and password");
            return false;
        } else {
            return true;
        }
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}
