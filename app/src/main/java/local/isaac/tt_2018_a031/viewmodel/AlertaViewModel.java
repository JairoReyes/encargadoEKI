package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Parcel;
import android.os.Parcelable;

import local.isaac.tt_2018_a031.PDO.AlertaPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class AlertaViewModel extends ViewModel{
    public MutableLiveData<AlertaPDO> alertaResponse;


    public void setAlertaResponse(MutableLiveData<AlertaPDO> alertaResponse) {
        this.alertaResponse = alertaResponse;
    }

    public MutableLiveData<AlertaPDO> getAlertaResponse() {
        if(alertaResponse == null){
            alertaResponse = new MutableLiveData<>();
            loadAlertaResponse();
        }
        return alertaResponse;
    }

    private void loadAlertaResponse(){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        alertaResponse = repositoryRetrofit.getAlertaRequest();
    }

}
