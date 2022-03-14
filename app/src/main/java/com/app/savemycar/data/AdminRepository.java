package com.app.savemycar.data;

import androidx.lifecycle.MutableLiveData;

public interface AdminRepository {

    void loginAsAdmin(String email, String password, MutableLiveData<Boolean> success);
}
