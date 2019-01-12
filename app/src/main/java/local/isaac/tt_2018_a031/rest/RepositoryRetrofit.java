package local.isaac.tt_2018_a031.rest;

import android.arch.lifecycle.MutableLiveData;

import local.isaac.tt_2018_a031.PDO.ConductorPDO;
import local.isaac.tt_2018_a031.PDO.LoginPDO;
import local.isaac.tt_2018_a031.PDO.LoginRequest;
import local.isaac.tt_2018_a031.PDO.Error;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepositoryRetrofit {

    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    public RepositoryRetrofit() {
    }

    public MutableLiveData<LoginPDO> getLoginRequest(String numero, String cont){

        final MutableLiveData<LoginPDO> loginResponse = new MutableLiveData<>();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setExpediente(numero);
        loginRequest.setContrasena(cont);

        Call<LoginPDO> call = apiInterface.loginTerminal(loginRequest);
        call.enqueue(new Callback<LoginPDO>() {
            @Override
            public void onResponse(Call<LoginPDO> call, Response<LoginPDO> response) {
                if(response.isSuccessful()){
                    loginResponse.postValue(response.body());
                }else{
                    Error httpError = new Error();
                    httpError.setText(response.code() + ": " + response.message());
                    LoginPDO loginPDOHttpError = new LoginPDO();
                    loginPDOHttpError.setError(httpError);
                    loginResponse.postValue(loginPDOHttpError);
                }
            }

            @Override
            public void onFailure(Call<LoginPDO> call, Throwable t) {
                //Errores tales como TimeOut, se le indica al usuario que vuelva a insertar sus datos y lo vuelva a intentar
                Error networkError = new Error();
                networkError.setText("Fallo en la conexión, vuelva a intentarlo.");
                LoginPDO loginPDONetworkError = new LoginPDO();
                loginPDONetworkError.setError(networkError);
                loginResponse.postValue(loginPDONetworkError);
            }
        });

        return loginResponse;

    }



    public MutableLiveData<ConductorPDO> getConductorRequest(){

        final MutableLiveData<ConductorPDO> conductorResponse = new MutableLiveData<>();

        //LoginRequest loginRequest = new LoginRequest();
        //loginRequest.setExpediente(numero);
        //loginRequest.setContrasena(cont);

        Call<ConductorPDO> call = apiInterface.condcutores(); // duda!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        call.enqueue(new Callback<ConductorPDO>() {
            @Override
            public void onResponse(Call<ConductorPDO> call, Response<ConductorPDO> response) {
                if(response.isSuccessful()){
                    conductorResponse.postValue(response.body());
                }else{
                    Error httpError = new Error();
                    httpError.setText(response.code() + ": " + response.message());
                    ConductorPDO conductorPDOHttpError = new ConductorPDO();
                    conductorPDOHttpError.setError(httpError);
                    conductorResponse.postValue(conductorPDOHttpError);
                }
            }

            @Override
            public void onFailure(Call<ConductorPDO> call, Throwable t) {
                //Errores tales como TimeOut, se le indica al usuario que vuelva a insertar sus datos y lo vuelva a intentar
                Error networkError = new Error();
                networkError.setText("Fallo en la conexión, vuelva a intentarlo.");
                ConductorPDO conductorPDONetworkError = new ConductorPDO();
                conductorPDONetworkError.setError(networkError);
                conductorResponse.postValue(conductorPDONetworkError);
            }
        });

        return conductorResponse;

    }
}

