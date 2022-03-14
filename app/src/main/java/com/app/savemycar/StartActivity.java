package com.app.savemycar;

import android.content.Intent;
import android.os.Bundle;

import com.app.savemycar.presentation.admin.AdminHomeActivity;
import com.app.savemycar.presentation.admin.LoginActivity;
import com.app.savemycar.presentation.client.ClientHomeActivity;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnClient)
    public void onClientClicked() {
        startActivity(new Intent(this, ClientHomeActivity.class));
    }

    @OnClick(R.id.btnAdmin)
    public void onAdminClicked() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, AdminHomeActivity.class));
        }
    }
}