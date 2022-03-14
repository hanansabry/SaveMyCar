package com.app.savemycar.domain;

import com.app.savemycar.Constants;
import com.app.savemycar.data.SecondaryRepository;
import com.app.savemycar.model.Secondary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class SecondaryRepositoryImpl implements SecondaryRepository {

    private final DatabaseReference issuesRef = FirebaseDatabase.getInstance().getReference(Constants.ISSUES_NODE);

    @Override
    public void addSecondary(String issueId, String primaryId, Secondary secondary, MutableLiveData<Boolean> success) {
        DatabaseReference secondaryRef = issuesRef.child(issueId).child(Constants.PRIMARY_NODE)
                .child(primaryId).child(Constants.SECONDARY_NODE);
        String secondaryKey = secondaryRef.push().getKey();
        secondaryRef.child(secondaryKey).setValue(secondary).addOnCompleteListener(task -> success.setValue(Boolean.valueOf(task.isSuccessful())));
    }

    @Override
    public void retrieveAllSecondaries(String issueId, String primaryId, MutableLiveData<List<Secondary>> secondariesMutableLiveData) {
        DatabaseReference secondariesRef = issuesRef.child(issueId).child(Constants.PRIMARY_NODE)
                .child(primaryId).child(Constants.SECONDARY_NODE);
        secondariesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Secondary> secondaries = new ArrayList<>();
                for (DataSnapshot secSnapshot : snapshot.getChildren()) {
                    Secondary secondary = secSnapshot.getValue(Secondary.class);
                    secondary.setId(secSnapshot.getKey());
                    secondaries.add(secondary);
                }
                secondariesMutableLiveData.setValue(secondaries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                secondariesMutableLiveData.setValue(null);
            }
        });
    }
}
