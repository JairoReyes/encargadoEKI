package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.TrolebusPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class TrolebusViewModel extends ViewModel {
    public MutableLiveData<TrolebusPDO> trolebusResponse;

    public void setTrolebusResponse(MutableLiveData<TrolebusPDO> trolebusResponse) {
        this.trolebusResponse = trolebusResponse;
    }

    public MutableLiveData<TrolebusPDO> getTrolebusResponse() {
        if(trolebusResponse == null){
            trolebusResponse = new MutableLiveData<>();
            loadTrolebusResponse();
        }
        return trolebusResponse;
    }

    private void loadTrolebusResponse(){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        trolebusResponse = repositoryRetrofit.getTrolebusRequest();
    }
}
