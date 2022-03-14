package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.AddWorkshopUseCase;
import com.app.savemycar.model.Workshop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddWorkshopViewModel extends ViewModel {

    private final AddWorkshopUseCase addWorkshopUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public AddWorkshopViewModel() {
        addWorkshopUseCase = Injection.getAddWorkshopUseCase();
    }

    public void addWorkshop(Workshop workshop) {
        if (validate(workshop)) {
            addWorkshopUseCase.execute(workshop, success);
        }
    }

    private boolean validate(Workshop workshop) {
        if (workshop.getName().isEmpty() || workshop.getAddress().isEmpty() || workshop.getCategory().isEmpty()
                || workshop.getPhone().isEmpty() || workshop.getRegion().isEmpty()) {
            error.setValue("You must enter all data");
            return false;
        } else {
            return true;
        }
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}
