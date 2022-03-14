package com.app.savemycar.data;

import androidx.lifecycle.MutableLiveData;

public interface DiagnosisRepository {

    void getDiagnosis(String issueId, String primaryId, String secondaryId, MutableLiveData<String> diagnosisMutableLiveData);
}
