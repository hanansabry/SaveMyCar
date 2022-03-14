package com.app.savemycar.data;

import com.app.savemycar.model.Workshop;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface WorkshopsRepository {

    void addNewWorkshop(Workshop workshop, MutableLiveData<Boolean> success);

    void retrieveWorkshopsByCategory(String categoryName, MutableLiveData<List<Workshop>> workshopsMutableLiveData);
}
