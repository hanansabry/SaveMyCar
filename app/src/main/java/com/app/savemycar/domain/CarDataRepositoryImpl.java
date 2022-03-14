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
        List<String> models = new ArrayList<>();
        models.add("model1");
        models.add("model2");
        models.add("model3");
        modelsMutableLiveData.setValue(models);
    }

    @Override
    public void retrieveTypes(MutableLiveData<List<String>> typesMutableLiveData) {
        List<String> types = new ArrayList<>();
        types.add("type1");
        types.add("type2");
        types.add("type3");
        typesMutableLiveData.setValue(types);
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
//        List<String> categories = new ArrayList<>();
//        categories.add("category1");
//        categories.add("category2");
//        categories.add("category3");
//        categoriesMutableLiveData.setValue(categories);
    }
}
