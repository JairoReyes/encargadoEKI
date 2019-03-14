package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.UbicacionPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class UbicacionViewModel extends ViewModel {
    public MutableLiveData<UbicacionPDO> ubicacionResponse;

    public void setUbicacionResponse(MutableLiveData<UbicacionPDO> ubicacionResponse) {
        this.ubicacionResponse = ubicacionResponse;
    }

    public MutableLiveData<UbicacionPDO> getUbicacionResponse() {
        if(ubicacionResponse == null){
            ubicacionResponse = new MutableLiveData<>();
            loadUbicacionResponse();
        }
        return ubicacionResponse;
    }

    private void loadUbicacionResponse(){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        ubicacionResponse = repositoryRetrofit.getUbicacionRequest();
    }
}












