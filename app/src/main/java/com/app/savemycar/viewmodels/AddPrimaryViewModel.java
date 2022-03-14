package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.AddPrimaryUseCase;
import com.app.savemycar.model.Primary;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddPrimaryViewModel extends ViewModel {

    private final AddPrimaryUseCase addPrimaryUseCase;
    private MutableLiveData<Primary> primaryMutableLiveData = new MutableLiveData<>();

    public AddPrimaryViewModel() {
        addPrimaryUseCase = Injection.getAddPrimaryUseCase();
    }

    public void addNewPrimary(String issueId, Primary primary) {
        addPrimaryUseCase.execute(issueId, primary, primaryMutableLiveData);
    }

    public MutableLiveData<Primary> getPrimaryMutableLiveData() {
        return primaryMutableLiveData;
    }
}
