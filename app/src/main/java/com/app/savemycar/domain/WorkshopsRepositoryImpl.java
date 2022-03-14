package com.app.savemycar.domain;

import com.app.savemycar.Constants;
import com.app.savemycar.data.WorkshopsRepository;
import com.app.savemycar.model.Workshop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        Workshop w1 = new Workshop();
        w1.setName("workshop 1");
        w1.setPhone("0546450520");
        w1.setCategory("cat1");

        Workshop w2 = new Workshop();
        w2.setName("workshop 2");
        w2.setPhone("0546450520");
        w2.setCategory("cat1");

        Workshop w3 = new Workshop();
        w3.setName("workshop 3");
        w3.setPhone("0546450520");
        w3.setCategory("cat2");

        List<Workshop> workshops = new ArrayList<>();
        workshops.add(w1);
        workshops.add(w2);
        workshops.add(w3);

        workshopsMutableLiveData.setValue(workshops);
    }
}
