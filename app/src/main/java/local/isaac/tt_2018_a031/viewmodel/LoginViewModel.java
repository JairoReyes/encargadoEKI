package local.isaac.tt_2018_a031.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import local.isaac.tt_2018_a031.PDO.LoginPDO;
import local.isaac.tt_2018_a031.rest.RepositoryRetrofit;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<LoginPDO> loginResponse;

    public void setLoginResponse(MutableLiveData<LoginPDO> loginResponse) {
        this.loginResponse = loginResponse;
    }

    public MutableLiveData<LoginPDO> getLoginResponse(String numero, String cont) {
        if(loginResponse == null){
            loginResponse = new MutableLiveData<>();
            loadLoginResponse(numero,cont);
        }
        return loginResponse;
    }

    private void loadLoginResponse(String numero, String cont){
        RepositoryRetrofit repositoryRetrofit = new RepositoryRetrofit();
        loginResponse = repositoryRetrofit.getLoginRequest(numero,cont);
    }
}