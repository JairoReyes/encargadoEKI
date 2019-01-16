package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.RegistroTrolebusPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class RegistroTrolebusViewModel extends ViewModel {

    public MutableLiveData<RegistroTrolebusPDO> registroTrolebusResponse;

    public void setRegistroTrolebusResponse(MutableLiveData<RegistroTrolebusPDO> registroTrolebusResponse) {
        this.registroTrolebusResponse = registroTrolebusResponse;
    }

    public MutableLiveData<RegistroTrolebusPDO> getRegistroTrolebusResponse(String id_trolebus, String inicio,String fin) {
        if(registroTrolebusResponse == null){
            registroTrolebusResponse = new MutableLiveData<>();
            loadRegistroTrolebusResponse(id_trolebus,inicio,fin);
        }
        return registroTrolebusResponse;
    }

    private void loadRegistroTrolebusResponse(String id_trolebus, String inicio, String fin){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        registroTrolebusResponse = repositoryRetrofit.getRegistroTrolebusRequest(id_trolebus, inicio, fin);
    }
}
