package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.PrimaryRepository;
import com.app.savemycar.model.Primary;

import androidx.lifecycle.MutableLiveData;

public class AddPrimaryUseCase {

    private final PrimaryRepository primaryRepository;

    public AddPrimaryUseCase(PrimaryRepository primaryRepository) {
        this.primaryRepository = primaryRepository;
    }

    public void execute(String issueId, Primary primary, MutableLiveData<Primary> primaryMutableLiveData) {
        primaryRepository.addPrimary(issueId, primary, primaryMutableLiveData);
    }
}
