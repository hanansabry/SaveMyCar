package com.app.savemycar.domain;

import com.app.savemycar.Constants;
import com.app.savemycar.data.DiagnosisRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class DiagnosisRepositoryImpl implements DiagnosisRepository {

    private final DatabaseReference issuesRef = FirebaseDatabase.getInstance().getReference(Constants.ISSUES_NODE);

    @Override
    public void retrieveDiagnosis(String issueId, String primaryId, String secondaryId, MutableLiveData<String> diagnosisMutableLiveData) {
        DatabaseReference secRef = issuesRef.child(issueId).child(Constants.PRIMARY_NODE).
                child(primaryId).child(Constants.SECONDARY_NODE).child(secondaryId);
        secRef.child(Constants.DIAGNOSIS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String diagnosis = snapshot.getValue(String.class);
                diagnosisMutableLiveData.setValue(diagnosis);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                diagnosisMutableLiveData.setValue(null);
            }
        });
    }

    @Override
    public void addNewDiagnosis(String issueId, String primaryId, String secondaryId, String diagnosis, MutableLiveData<Boolean> success) {
        DatabaseReference secRef = issuesRef.child(issueId).child(Constants.PRIMARY_NODE).
                child(primaryId).child(Constants.SECONDARY_NODE).child(secondaryId);
        secRef.child(Constants.DIAGNOSIS).setValue(diagnosis).addOnCompleteListener(task -> success.setValue(task.isSuccessful()));
    }
}
