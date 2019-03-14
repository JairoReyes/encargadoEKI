package local.isaac.tt_2018_a031.rest;

import android.arch.lifecycle.MutableLiveData;

import local.isaac.tt_2018_a031.PDO.AlertaPDO;
import local.isaac.tt_2018_a031.PDO.ConductorPDO;
import local.isaac.tt_2018_a031.PDO.Error;
import local.isaac.tt_2018_a031.PDO.LoginPDO;
import local.isaac.tt_2018_a031.PDO.LoginRequest;
import local.isaac.tt_2018_a031.PDO.QuitarAlertaPDO;
import local.isaac.tt_2018_a031.PDO.QuitarAlertaRequest;
import local.isaac.tt_2018_a031.PDO.RegistroConductorPDO;
import local.isaac.tt_2018_a031.PDO.RegistroConductorRequest;
import local.isaac.tt_2018_a031.PDO.RegistroTrolebusPDO;
import local.isaac.tt_2018_a031.PDO.RegistroTrolebusRequest;
import local.isaac.tt_2018_a031.PDO.TrolebusPDO;
import local.isaac.tt_2018_a031.PDO.UbicacionPDO;
import local.isaac.tt_2018_a031.PDO.ZonasRojasPDO;
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


    ////CONDUCTORES ORDENAR SPINNER
    public MutableLiveData<ConductorPDO> getConductorRequest(){

        final MutableLiveData<ConductorPDO> conductorResponse = new MutableLiveData<>();

        Call<ConductorPDO> call = apiInterface.condcutores();
        call.enqueue(new Callback<ConductorPDO>() {
            @Override
            public void onResponse(Call<ConductorPDO> call, Response<ConductorPDO> response) {
                if(response.isSuccessful()){
                    conductorResponse.postValue(response.body());
                }else{
                    System.out.println("No fue successful");
                }
            }

            @Override
            public void onFailure(Call<ConductorPDO> call, Throwable t) {
                System.out.println("Error al recibir lista de conductores");
            }
        });

        return conductorResponse;
    }




    ////TROLEBUS ORDENAR SPINNER
    public MutableLiveData<TrolebusPDO> getTrolebusRequest(){

        final MutableLiveData<TrolebusPDO> trolebusResponse = new MutableLiveData<>();

        Call<TrolebusPDO> call = apiInterface.trolebuses();
        call.enqueue(new Callback<TrolebusPDO>() {
            @Override
            public void onResponse(Call<TrolebusPDO> call, Response<TrolebusPDO> response) {
                if(response.isSuccessful()){
                    trolebusResponse.postValue(response.body());
                }else{
                    System.out.println("No fue successful");
                }
            }

            @Override
            public void onFailure(Call<TrolebusPDO> call, Throwable t) {
                System.out.println("Error al recibir lista de conductores");
            }
        });

        return trolebusResponse;
    }



    ///REGISTROS CONDUCTORES POR ID
    public MutableLiveData<RegistroConductorPDO> getRegistroConductorRequest(String id_usuario, String inicio, String fin){

        final MutableLiveData<RegistroConductorPDO> registroConductorResponse = new MutableLiveData<>();

        RegistroConductorRequest registroConductorRequest = new RegistroConductorRequest();
        registroConductorRequest.setIdusuario(id_usuario);
        registroConductorRequest.setInicio(inicio);
        registroConductorRequest.setFin(fin);

        Call<RegistroConductorPDO> call = apiInterface.registrosConductores(registroConductorRequest);
        call.enqueue(new Callback<RegistroConductorPDO>() {
            @Override
            public void onResponse(Call<RegistroConductorPDO> call, Response<RegistroConductorPDO> response) {
                if(response.isSuccessful()){
                    registroConductorResponse.postValue(response.body());
                }else{
                    System.out.println("No fue succesfull");
                }
            }

            @Override
            public void onFailure(Call<RegistroConductorPDO> call, Throwable t) {
                //Errores tales como TimeOut, se le indica al usuario que vuelva a insertar sus datos y lo vuelva a intentar
                System.out.println("Error al recibir lista de registros del conductor");
            }
        });

        return registroConductorResponse;

    }


    ///REGISTROS TROLEBUS POR ID
    public MutableLiveData<RegistroTrolebusPDO> getRegistroTrolebusRequest(String id_trolebus, String inicio, String fin){

        final MutableLiveData<RegistroTrolebusPDO> registroTrolebusResponse = new MutableLiveData<>();

        RegistroTrolebusRequest registroTrolebusRequest = new RegistroTrolebusRequest();
        registroTrolebusRequest.setIdtrolebus(id_trolebus);
        registroTrolebusRequest.setInicio(inicio);
        registroTrolebusRequest.setFin(fin);

        Call<RegistroTrolebusPDO> call = apiInterface.registrosTrolebuses(registroTrolebusRequest);
        call.enqueue(new Callback<RegistroTrolebusPDO>() {
            @Override
            public void onResponse(Call<RegistroTrolebusPDO> call, Response<RegistroTrolebusPDO> response) {
                if(response.isSuccessful()){
                    registroTrolebusResponse.postValue(response.body());
                }else{
                    System.out.println("No fue succesfull");
                }
            }

            @Override
            public void onFailure(Call<RegistroTrolebusPDO> call, Throwable t) {
                //Errores tales como TimeOut, se le indica al usuario que vuelva a insertar sus datos y lo vuelva a intentar
                System.out.println("Error al recibir lista de registros del conductor");
            }
        });

        return registroTrolebusResponse;

    }

    public MutableLiveData<ZonasRojasPDO> getZonasRojasRequest (){

        final MutableLiveData<ZonasRojasPDO> zonasRojasResponse = new MutableLiveData<>();

        Call<ZonasRojasPDO> call = apiInterface.zonasRojas();

        call.enqueue(new Callback<ZonasRojasPDO>() {
            @Override
            public void onResponse(Call<ZonasRojasPDO> call, Response<ZonasRojasPDO> response) {
                if(response.isSuccessful())
                    zonasRojasResponse.postValue(response.body());
                else{
                    Error httpError = new Error();
                    httpError.setText(response.code() + ": " + response.message());
                    ZonasRojasPDO zonasRojashttpError = new ZonasRojasPDO();
                    zonasRojashttpError.setError(httpError);
                    zonasRojasResponse.postValue(zonasRojashttpError);
                }
            }

            @Override
            public void onFailure(Call<ZonasRojasPDO> call, Throwable t) {
                Error networkError = new Error();
                networkError.setText("Fallo en la conexión, vualeva a intentarlo");
                ZonasRojasPDO zonasRojasNetworkError = new ZonasRojasPDO();
                zonasRojasNetworkError.setError(networkError);
                zonasRojasResponse.postValue(zonasRojasNetworkError);
            }
        });

        return zonasRojasResponse;
    }

    public MutableLiveData<UbicacionPDO> getUbicacionRequest (){

        final MutableLiveData<UbicacionPDO> ubicacionResponse = new MutableLiveData<>();

        Call<UbicacionPDO> call = apiInterface.ubicacionPasajero();

        call.enqueue(new Callback<UbicacionPDO>() {
            @Override
            public void onResponse(Call<UbicacionPDO> call, Response<UbicacionPDO> response) {
                if(response.isSuccessful())
                    ubicacionResponse.postValue(response.body());
                else{
                    Error httpError = new Error();
                    httpError.setText(response.code() + ": " + response.message());
                    UbicacionPDO ubicacionPDOhttpError = new UbicacionPDO();
                    ubicacionPDOhttpError.setError(httpError);
                    ubicacionResponse.postValue(ubicacionPDOhttpError);
                }
            }

            @Override
            public void onFailure(Call<UbicacionPDO> call, Throwable t) {
                Error networkError = new Error();
                networkError.setText("Fallo en la conexión, vualeva a intentarlo");
                UbicacionPDO ubicacionPDONetworkError = new UbicacionPDO();
                ubicacionPDONetworkError.setError(networkError);
                ubicacionResponse.postValue(ubicacionPDONetworkError);
            }
        });

        return ubicacionResponse;
    }




    ///ALERTAS!!!!
    public MutableLiveData<AlertaPDO> getAlertaRequest(){

        final MutableLiveData<AlertaPDO> alertaResponse = new MutableLiveData<>();

        Call<AlertaPDO> call = apiInterface.alertas();
        call.enqueue(new Callback<AlertaPDO>() {
            @Override
            public void onResponse(Call<AlertaPDO> call, Response<AlertaPDO> response) {
                if(response.isSuccessful()){
                    alertaResponse.postValue(response.body());
                }else{
                    System.out.println("No fue successful");
                }
            }

            @Override
            public void onFailure(Call<AlertaPDO> call, Throwable t) {
                System.out.println("Error al recibir lista de conductores");
            }
        });

        return alertaResponse;
    }


    ////CAMBIAR LA ALERTA DE ESTADO
    public MutableLiveData<QuitarAlertaPDO> getQuitarAlertaRequest(String estado, String id_alerta){

        final MutableLiveData<QuitarAlertaPDO> quitarAlertaResponse = new MutableLiveData<>();

        QuitarAlertaRequest quitarAlertaRequest = new QuitarAlertaRequest();
        quitarAlertaRequest.setEstado(estado);
        quitarAlertaRequest.setId_alerta(id_alerta);

        Call<QuitarAlertaPDO> call = apiInterface.quitarAlerta(quitarAlertaRequest);
        call.enqueue(new Callback<QuitarAlertaPDO>() {
            @Override
            public void onResponse(Call<QuitarAlertaPDO> call, Response<QuitarAlertaPDO> response) {
                if(response.isSuccessful()){
                    quitarAlertaResponse.postValue(response.body());
                }else{
                    Error httpError = new Error();
                    httpError.setText(response.code() + ": " + response.message());
                    QuitarAlertaPDO loginPDOHttpError = new QuitarAlertaPDO();
                    loginPDOHttpError.setError(httpError);
                    quitarAlertaResponse.postValue(loginPDOHttpError);
                }
            }

            @Override
            public void onFailure(Call<QuitarAlertaPDO> call, Throwable t) {
                //Errores tales como TimeOut, se le indica al usuario que vuelva a insertar sus datos y lo vuelva a intentar
                Error networkError = new Error();
                networkError.setText("Fallo en la conexión, vuelva a intentarlo.");
                QuitarAlertaPDO quitarAlertaPDONetworkError = new QuitarAlertaPDO();
                quitarAlertaPDONetworkError.setError(networkError);
                quitarAlertaResponse.postValue(quitarAlertaPDONetworkError);
            }
        });

        return quitarAlertaResponse;

    }
}

