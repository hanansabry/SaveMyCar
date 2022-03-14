package com.app.savemycar.domain;

import com.app.savemycar.data.SecondaryRepository;
import com.app.savemycar.model.Secondary;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class SecondaryRepositoryImpl implements SecondaryRepository {
    @Override
    public void addSecondary(String primaryId, Secondary secondary, MutableLiveData<Boolean> success) {
        if (secondary.getName().equals("secondary1")) {
            success.setValue(true);
        } else {
            success.setValue(false);
        }
    }

    @Override
    public void retrieveAllSecondaries(String primaryId, MutableLiveData<List<Secondary>> secondariesMutableLiveData) {
        Secondary s1 = new Secondary();
        Secondary s2 = new Secondary();

        s1.setName("Secondary1");
        s2.setName("Secondary2");

        List<Secondary> secondaries = new ArrayList<>();
        secondaries.add(s1);
        secondaries.add(s2);

        secondariesMutableLiveData.setValue(secondaries);
    }
}
