package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.AdminRepository;

import androidx.lifecycle.MutableLiveData;

public class LoginUseCase {

    private final AdminRepository adminRepository;

    public LoginUseCase(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void execute(String email, String password, MutableLiveData<Boolean> success) {
        adminRepository.loginAsAdmin(email, password, success);
    }
}
