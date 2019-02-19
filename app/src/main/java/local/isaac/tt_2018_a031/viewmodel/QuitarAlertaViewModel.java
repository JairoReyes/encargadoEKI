package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.QuitarAlertaPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class QuitarAlertaViewModel extends ViewModel {
    public MutableLiveData<QuitarAlertaPDO> quitarAlertaResponse;

    public void setQuitarAlertaResponse(MutableLiveData<QuitarAlertaPDO> quitarAlertaResponse) {
        this.quitarAlertaResponse = quitarAlertaResponse;
    }

    public MutableLiveData<QuitarAlertaPDO> getQuitarAlertaResponse(String estado, String id_alerta) {
        if(quitarAlertaResponse == null){
            quitarAlertaResponse = new MutableLiveData<>();
            loadQuitarAlertaResponse(estado,id_alerta);
        }
        return quitarAlertaResponse;
    }

    private void loadQuitarAlertaResponse(String estado, String id_alerta){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        quitarAlertaResponse = repositoryRetrofit.getQuitarAlertaRequest(estado,id_alerta);
    }
}
