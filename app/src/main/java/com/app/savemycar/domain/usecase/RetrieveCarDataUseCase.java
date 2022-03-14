package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.CarDataRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveCarDataUseCase {

    private final CarDataRepository carDataRepository;

    public RetrieveCarDataUseCase(CarDataRepository carDataRepository) {
        this.carDataRepository = carDataRepository;
    }

    public void retrieveCategories(MutableLiveData<List<String>> categoriesMutableLiveData) {
        carDataRepository.retrieveCategories(categoriesMutableLiveData);
    }

    public void retrieveModels(MutableLiveData<List<String>> modelsMutableLiveData) {
        carDataRepository.retrieveModels(modelsMutableLiveData);
    }

    public void retrieveTypes(MutableLiveData<List<String>> typesMutableLiveData) {
        carDataRepository.retrieveTypes(typesMutableLiveData);
    }
}
