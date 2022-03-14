package com.app.savemycar;

import com.app.savemycar.data.AdminRepository;
import com.app.savemycar.data.CarDataRepository;
import com.app.savemycar.data.IssuesRepository;
import com.app.savemycar.data.PrimaryRepository;
import com.app.savemycar.data.SecondaryRepository;
import com.app.savemycar.data.WorkshopsRepository;
import com.app.savemycar.domain.AdminRepositoryImpl;
import com.app.savemycar.domain.CarDataRepositoryImpl;
import com.app.savemycar.domain.IssuesRepositoryImpl;
import com.app.savemycar.domain.PrimaryRepositoryImpl;
import com.app.savemycar.domain.SecondaryRepositoryImpl;
import com.app.savemycar.domain.WorkshopsRepositoryImpl;
import com.app.savemycar.domain.usecase.AddIssueUseCase;
import com.app.savemycar.domain.usecase.AddPrimaryUseCase;
import com.app.savemycar.domain.usecase.AddSecondaryUseCase;
import com.app.savemycar.domain.usecase.AddWorkshopUseCase;
import com.app.savemycar.domain.usecase.LoginUseCase;
import com.app.savemycar.domain.usecase.RetrieveCarDataUseCase;
import com.app.savemycar.domain.usecase.RetrieveIssuesUseCase;
import com.app.savemycar.domain.usecase.RetrieveSecondariesUseCase;

public class Injection {

    public static LoginUseCase getLoginUseCase() {
        return new LoginUseCase(getAdminRepository());
    }

    private static AdminRepository getAdminRepository() {
        return new AdminRepositoryImpl();
    }

    public static AddWorkshopUseCase getAddWorkshopUseCase() {
        return new AddWorkshopUseCase(getWorkshopRepository());
    }

    private static WorkshopsRepository getWorkshopRepository() {
        return new WorkshopsRepositoryImpl();
    }

    public static RetrieveCarDataUseCase getRetrieveCategoriesUseCase() {
        return new RetrieveCarDataUseCase(getCarDataRepository());
    }

    private static CarDataRepository getCarDataRepository() {
        return new CarDataRepositoryImpl();
    }

    public static AddIssueUseCase getAddIssueUseCase() {
        return new AddIssueUseCase(getIssuesRepository());
    }

    private static IssuesRepository getIssuesRepository() {
        return new IssuesRepositoryImpl();
    }

    public static RetrieveIssuesUseCase getRetrieveIssuesUseCase() {
        return new RetrieveIssuesUseCase(getIssuesRepository());
    }

    public static AddPrimaryUseCase getAddPrimaryUseCase() {
        return new AddPrimaryUseCase(getPrimaryRepository());
    }

    private static PrimaryRepository getPrimaryRepository() {
        return new PrimaryRepositoryImpl();
    }

    public static AddSecondaryUseCase getAddSecondaryUseCase() {
        return new AddSecondaryUseCase(getSecondaryRepository());
    }

    private static SecondaryRepository getSecondaryRepository() {
        return new SecondaryRepositoryImpl();
    }

    public static RetrieveSecondariesUseCase getRetrieveSecondariesUseCase() {
        return new RetrieveSecondariesUseCase(getSecondaryRepository());
    }
}
