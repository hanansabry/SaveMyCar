package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.DiagnosisRepository;

import androidx.lifecycle.MutableLiveData;

public class AddDiagnosisUseCase {

    private final DiagnosisRepository diagnosisRepository;

    public AddDiagnosisUseCase(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public void execute(String issueId, String primaryId, String secondaryId, String diagnosis, MutableLiveData<Boolean> success) {
        diagnosisRepository.addNewDiagnosis(issueId, primaryId, secondaryId, diagnosis, success);
    }
}
