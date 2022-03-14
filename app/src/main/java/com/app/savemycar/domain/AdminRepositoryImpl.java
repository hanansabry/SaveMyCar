package com.app.savemycar.domain;

import com.app.savemycar.data.AdminRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class AdminRepositoryImpl implements AdminRepository {

    private final FirebaseAuth auth;

    public AdminRepositoryImpl() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void loginAsAdmin(String email, String password, MutableLiveData<Boolean> success) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }
}
