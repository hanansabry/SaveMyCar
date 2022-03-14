package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.RetrieveWorkshopsUseCase;
import com.app.savemycar.model.Workshop;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveWorkshopsViewModel extends ViewModel {

    private final RetrieveWorkshopsUseCase retrieveWorkshopsUseCase;
    private MutableLiveData<List<Workshop>> workshopsMutableLiveData = new MutableLiveData<>();

    public RetrieveWorkshopsViewModel() {
        retrieveWorkshopsUseCase = Injection.getRetrieveWorkshopsUseCase();
    }

    public void retrieveWorkshops(String categoryName) {
        retrieveWorkshopsUseCase.execute(categoryName, workshopsMutableLiveData);
    }

    public MutableLiveData<List<Workshop>> getWorkshopsMutableLiveData() {
        return workshopsMutableLiveData;
    }
}
