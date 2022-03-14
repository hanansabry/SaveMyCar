package com.app.savemycar.data;

import com.app.savemycar.model.Secondary;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface SecondaryRepository {

    void addSecondary(String primaryId, Secondary secondary, MutableLiveData<Boolean> success);

    void retrieveAllSecondaries(String primaryId, MutableLiveData<List<Secondary>> secondariesMutableLiveData);
}
