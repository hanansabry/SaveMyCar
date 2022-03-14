package com.app.savemycar.domain;

import com.app.savemycar.Constants;
import com.app.savemycar.data.WorkshopsRepository;
import com.app.savemycar.model.Workshop;
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

public class WorkshopsRepositoryImpl implements WorkshopsRepository {

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.WORKSHOPS_NODE);
    @Override
    public void addNewWorkshop(Workshop workshop, MutableLiveData<Boolean> success) {
        databaseReference.push().setValue(workshop).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                success.setValue(task.isSuccessful());
            }
        });
    }

    @Override
    public void retrieveWorkshopsByCategory(String categoryName, MutableLiveData<List<Workshop>> workshopsMutableLiveData) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Workshop> workshops = new ArrayList<>();
                for (DataSnapshot workshopSnapshot: snapshot.getChildren()) {
                    Workshop workshop = workshopSnapshot.getValue(Workshop.class);
                    if (workshop.getCategory().equalsIgnoreCase(categoryName)) {
                        workshops.add(workshop);
                    }
                }
                workshopsMutableLiveData.setValue(workshops);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                workshopsMutableLiveData.setValue(null);
            }
        });
    }
}
