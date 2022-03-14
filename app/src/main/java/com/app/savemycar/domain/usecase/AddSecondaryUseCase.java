package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.SecondaryRepository;
import com.app.savemycar.model.Secondary;

import androidx.lifecycle.MutableLiveData;

public class AddSecondaryUseCase {

    private final SecondaryRepository secondaryRepository;

    public AddSecondaryUseCase(SecondaryRepository secondaryRepository) {
        this.secondaryRepository = secondaryRepository;
    }

    public void execute(String issueId, String primaryId, Secondary secondary, MutableLiveData<Boolean> success) {
        secondaryRepository.addSecondary(issueId, primaryId, secondary, success);
    }
}
