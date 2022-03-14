package com.app.savemycar.data;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface CarDataRepository {

    void retrieveModels(MutableLiveData<List<String>> modelMutableLiveData);

    void retrieveTypes(MutableLiveData<List<String>> typesMutableLiveData);

    void retrieveCategories(MutableLiveData<List<String>> categoriesMutableLiveData);
}
