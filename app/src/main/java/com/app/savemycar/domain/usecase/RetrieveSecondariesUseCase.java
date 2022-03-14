package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.SecondaryRepository;
import com.app.savemycar.model.Secondary;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveSecondariesUseCase {

    private final SecondaryRepository secondaryRepository;

    public RetrieveSecondariesUseCase(SecondaryRepository secondaryRepository) {
        this.secondaryRepository = secondaryRepository;
    }

    public void execute(String issueId, String primaryId, MutableLiveData<List<Secondary>> secondaryListMutableLiveData) {
        secondaryRepository.retrieveAllSecondaries(issueId, primaryId, secondaryListMutableLiveData);
    }
}
