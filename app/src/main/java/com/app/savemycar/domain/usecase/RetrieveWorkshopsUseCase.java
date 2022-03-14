package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.WorkshopsRepository;
import com.app.savemycar.model.Workshop;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveWorkshopsUseCase {

    private final WorkshopsRepository workshopsRepository;

    public RetrieveWorkshopsUseCase(WorkshopsRepository workshopsRepository) {
        this.workshopsRepository = workshopsRepository;
    }

    public void execute(String categoryName, MutableLiveData<List<Workshop>> workshopsMutableLiveData) {
        workshopsRepository.retrieveWorkshopsByCategory(categoryName, workshopsMutableLiveData);
    }
}
