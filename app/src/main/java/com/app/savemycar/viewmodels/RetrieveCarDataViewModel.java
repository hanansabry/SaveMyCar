package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.RetrieveCarDataUseCase;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveCarDataViewModel extends ViewModel {

    private final RetrieveCarDataUseCase retrieveCarDataUseCase;
    private MutableLiveData<List<String>> categoriesListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<String>> modelListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<String>> typeListMutableLiveData  = new MutableLiveData<>();

    public RetrieveCarDataViewModel() {
        retrieveCarDataUseCase = Injection.getRetrieveCategoriesUseCase();
    }

    public void retrieveAllCategories() {
        retrieveCarDataUseCase.retrieveCategories(categoriesListMutableLiveData);
    }

    public void retrieveAllModels() {
        retrieveCarDataUseCase.retrieveModels(modelListMutableLiveData);
    }

    public void retrieveAllTypes() {
        retrieveCarDataUseCase.retrieveTypes(typeListMutableLiveData);
    }

    public MutableLiveData<List<String>> getCategoriesListMutableLiveData() {
        return categoriesListMutableLiveData;
    }

    public MutableLiveData<List<String>> getModelListMutableLiveData() {
        return modelListMutableLiveData;
    }

    public MutableLiveData<List<String>> getTypeListMutableLiveData() {
        return typeListMutableLiveData;
    }
}
