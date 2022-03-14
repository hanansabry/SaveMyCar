package com.app.savemycar.domain;

import com.app.savemycar.Constants;
import com.app.savemycar.data.CarDataRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class CarDataRepositoryImpl implements CarDataRepository {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    public void retrieveModels(MutableLiveData<List<String>> modelsMutableLiveData) {
        DatabaseReference modelsReference = database.getReference(Constants.MODELS_NODE);
        modelsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> models = new ArrayList<>();
                for (DataSnapshot modelSnapshot : snapshot.getChildren()) {
                    models.add(modelSnapshot.getValue(String.class));
                }
                modelsMutableLiveData.setValue(models);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                modelsMutableLiveData.setValue(null);
            }
        });
    }

    @Override
    public void retrieveTypes(MutableLiveData<List<String>> typesMutableLiveData) {
        DatabaseReference typesReference = database.getReference(Constants.TYPES_NODE);
        typesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> types = new ArrayList<>();
                for (DataSnapshot typeSnapshot : snapshot.getChildren()) {
                    types.add(typeSnapshot.getValue(String.class));
                }
                typesMutableLiveData.setValue(types);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                typesMutableLiveData.setValue(null);
            }
        });
    }

    @Override
    public void retrieveCategories(MutableLiveData<List<String>> categoriesMutableLiveData) {
        DatabaseReference categoriesReference = database.getReference(Constants.CATEGORIES_NODE);
        categoriesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> categories = new ArrayList<>();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    categories.add(categorySnapshot.getValue(String.class));
                }
                categoriesMutableLiveData.setValue(categories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                categoriesMutableLiveData.setValue(null);
            }
        });
    }
}
