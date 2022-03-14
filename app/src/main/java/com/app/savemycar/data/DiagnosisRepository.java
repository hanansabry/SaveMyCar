package com.app.savemycar.data;

import androidx.lifecycle.MutableLiveData;

public interface DiagnosisRepository {

    void retrieveDiagnosis(String issueId, String primaryId, String secondaryId, MutableLiveData<String> diagnosisMutableLiveData);

    void addNewDiagnosis(String issueId, String primaryId, String secondaryId, String diagnosis, MutableLiveData<Boolean> success);
}
