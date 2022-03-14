package com.app.savemycar.data;

import com.app.savemycar.model.Primary;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface PrimaryRepository {

    void addPrimary(String issueId, Primary primary, MutableLiveData<Primary> success);

    void retrieveAllPrimaries(String issueId, MutableLiveData<List<Primary>> primariesMutableLiveData);
}
