package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.AddSecondaryUseCase;
import com.app.savemycar.model.Secondary;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddSecondaryViewModel extends ViewModel {

    private final AddSecondaryUseCase addSecondaryUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddSecondaryViewModel() {
        addSecondaryUseCase = Injection.getAddSecondaryUseCase();
    }

    public void addNewSecondary(String primaryId, Secondary secondary) {
        addSecondaryUseCase.execute(primaryId, secondary, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
