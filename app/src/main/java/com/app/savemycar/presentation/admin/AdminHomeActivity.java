package com.app.savemycar.presentation.admin;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.savemycar.R;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.workshops_cardview)
    public void onWorkshopsClicked() {
        startActivity(new Intent(this, CarWorkshopsActivity.class));
    }

    @OnClick(R.id.issues_cardview)
    public void onIssuesClicked() {
        startActivity(new Intent(this, IssuesActivity.class));
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}