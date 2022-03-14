package com.app.savemycar.domain;

import com.app.savemycar.Constants;
import com.app.savemycar.data.PrimaryRepository;
import com.app.savemycar.model.Primary;
import com.app.savemycar.model.Secondary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class PrimaryRepositoryImpl implements PrimaryRepository {

    private final DatabaseReference issuesReference = FirebaseDatabase.getInstance().getReference(Constants.ISSUES_NODE);

    @Override
    public void addPrimary(String issueId, Primary primary, MutableLiveData<Primary> primaryMutableLiveData) {
        String primaryKey = issuesReference.child(issueId).child(Constants.PRIMARY_NODE).push().getKey();
        issuesReference.child(issueId).child(Constants.PRIMARY_NODE).child(primaryKey).setValue(primary).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                primary.setId(primaryKey);
                if (task.isSuccessful()) {
                    primaryMutableLiveData.setValue(primary);
                } else {
                    primaryMutableLiveData.setValue(null);
                }
            }
        });
    }

    @Override
    public void retrieveAllPrimaries(String issueId, MutableLiveData<List<Primary>> primariesMutableLiveData) {
        Primary p1 = new Primary();
        p1.setName("primary1");

        Secondary s1 = new Secondary();
        s1.setName("secondary1");
        Secondary s2 = new Secondary();
        s2.setName("secondary2");

        HashMap<String, Secondary> secondaries = new HashMap<>();
        secondaries.put(p1.getName(), s1);
        secondaries.put(p1.getName(), s2);

        p1.setSecondaries(secondaries);
        List<Primary> primaries = new ArrayList<>();
        primaries.add(p1);
        primariesMutableLiveData.setValue(primaries);
    }
}
