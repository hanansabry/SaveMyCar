package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.WorkshopsRepository;
import com.app.savemycar.model.Workshop;

import androidx.lifecycle.MutableLiveData;

public class AddWorkshopUseCase {

    private final WorkshopsRepository workshopsRepository;

    public AddWorkshopUseCase(WorkshopsRepository workshopsRepository) {
        this.workshopsRepository = workshopsRepository;
    }

    public void execute (Workshop workshop, MutableLiveData<Boolean> success) {
        workshopsRepository.addNewWorkshop(workshop, success);
    }
}
