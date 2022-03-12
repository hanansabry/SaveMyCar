package com.app.savemycar.presentation.client;

import android.content.Intent;
import android.os.Bundle;

import com.app.savemycar.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnNext)
    public void onNextClicked() {
        startActivity(new Intent(this, CheckIssueActivity.class));
    }
}