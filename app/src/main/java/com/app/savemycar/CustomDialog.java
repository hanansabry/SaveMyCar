package com.app.savemycar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialog extends Dialog {

    private DialogListener dialogListener;
    private Context context;
    @BindView(R.id.primaryEditText)
    EditText primaryEditText;
    @BindView(R.id.dialog_title)
    TextView dialog_title;

    public CustomDialog(@NonNull Context context, DialogListener dialogListener) {
        super(context);
        this.context = context;
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = View.inflate(getContext(), R.layout.add_value_dialog, null);
        setContentView(view);
        ButterKnife.bind(this, view);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

    }

    @OnClick(R.id.btn_yes)
    public void onAddClicked() {
        String value = primaryEditText.getText().toString();
        if (value.isEmpty()) {
            Toast.makeText(context, "You must add value details", Toast.LENGTH_SHORT).show();
        } else {
            dialogListener.onAddClicked(value);
            dismiss();
        }
    }

    @OnClick(R.id.btn_no)
    public void onCancelClicked() {
        dismiss();
    }

    public interface DialogListener {
        void onAddClicked(String value);
    }

    public enum ValueType {
        CONFLICT, CONTRADICTORY
    }
}

