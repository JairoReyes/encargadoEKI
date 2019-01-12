package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.ConductorPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class ConductorViewModel extends ViewModel {

    public MutableLiveData<ConductorPDO> conductorResponse;

    public void setLoginResponse(MutableLiveData<ConductorPDO> conductorResponse) {
        this.conductorResponse = conductorResponse;
    }

    public MutableLiveData<ConductorPDO> getConductorResponse() {
        if(conductorResponse == null){
            conductorResponse = new MutableLiveData<>();
            loadLoginResponse();
        }
        return conductorResponse;
    }

    private void loadLoginResponse(){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        conductorResponse = repositoryRetrofit.getConductorRequest();
    }
}
