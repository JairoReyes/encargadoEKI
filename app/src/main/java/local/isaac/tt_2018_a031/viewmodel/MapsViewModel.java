package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.ZonasRojasPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class MapsViewModel extends ViewModel {

    public MutableLiveData<ZonasRojasPDO> zonaRojaResponse;

    public void setZonaRojaResponse(MutableLiveData<ZonasRojasPDO> zonaRojaResponse) {
        this.zonaRojaResponse = zonaRojaResponse;
    }

    public MutableLiveData<ZonasRojasPDO> getZonaRojaResponse() {
        if(zonaRojaResponse == null){
            zonaRojaResponse = new MutableLiveData<>();
            loadLoginResponse();
        }
        return zonaRojaResponse;
    }

    private void loadLoginResponse(){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        zonaRojaResponse = repositoryRetrofit.getZonasRojasRequest();
    }

}
