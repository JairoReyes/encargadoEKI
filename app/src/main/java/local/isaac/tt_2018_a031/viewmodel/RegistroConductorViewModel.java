package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.RegistroConductorPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class RegistroConductorViewModel extends ViewModel {

    public MutableLiveData<RegistroConductorPDO> registroConductorResponse;

    public void setRegistroConductorResponse(MutableLiveData<RegistroConductorPDO> registroConductorResponse) {
        this.registroConductorResponse = registroConductorResponse;
    }

    public MutableLiveData<RegistroConductorPDO> getRegistroConductorResponse(String id_usuario, String inicio,String fin) {
        if(registroConductorResponse == null){
            registroConductorResponse = new MutableLiveData<>();
            loadRegistroConductorResponse(id_usuario,inicio,fin);
        }
        return registroConductorResponse;
    }

    private void loadRegistroConductorResponse(String id_usuario, String inicio, String fin){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        registroConductorResponse = repositoryRetrofit.getRegistroConductorRequest(id_usuario, inicio, fin);
    }
}
