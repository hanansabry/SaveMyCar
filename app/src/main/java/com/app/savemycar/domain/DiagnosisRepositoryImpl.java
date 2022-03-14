package com.app.savemycar.domain;

import com.app.savemycar.data.DiagnosisRepository;

import androidx.lifecycle.MutableLiveData;

public class DiagnosisRepositoryImpl implements DiagnosisRepository {
    @Override
    public void getDiagnosis(String issueId, String primaryId, String secondaryId, MutableLiveData<String> diagnosisMutableLiveData) {
        if (issueId.equals("issue1") && primaryId.equals("p1") && secondaryId.equals("s2")) {
            diagnosisMutableLiveData.setValue("diagnosis");
        }
    }
}
