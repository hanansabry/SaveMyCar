package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.PrimaryRepository;
import com.app.savemycar.model.Primary;

import androidx.lifecycle.MutableLiveData;

public class RetrievePrimaryUseCase {

    private final PrimaryRepository primaryRepository;

    public RetrievePrimaryUseCase(PrimaryRepository primaryRepository) {
        this.primaryRepository = primaryRepository;
    }

    public void execute(String issueId, String primaryId, MutableLiveData<Primary> primaryMutableLiveData) {
        primaryRepository.retrievePrimaryByIds(issueId, primaryId, primaryMutableLiveData);
    }
}
