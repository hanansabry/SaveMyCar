package com.app.savemycar.presentation.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.app.savemycar.CustomDialog;
import com.app.savemycar.R;

public class PrimaryActivity extends AppCompatActivity {

    @BindView(R.id.secondariesRecyclerView)
    RecyclerView secondariesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnAddSecondary)
    public void onAddSecondaryClicked() {
        startActivity(new Intent(this, DiagnosisActivity.class));
    }
}