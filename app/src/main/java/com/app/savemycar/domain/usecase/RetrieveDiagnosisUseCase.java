package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.DiagnosisRepository;

import androidx.lifecycle.MutableLiveData;

public class RetrieveDiagnosisUseCase {

    private final DiagnosisRepository diagnosisRepository;

    public RetrieveDiagnosisUseCase(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public void execute(String issueId, String primaryId, String secondaryId, MutableLiveData<String> diagnosisMutableLiveData) {
        diagnosisRepository.retrieveDiagnosis(issueId, primaryId, secondaryId, diagnosisMutableLiveData);
    }
}
