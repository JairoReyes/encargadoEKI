package local.isaac.tt_2018_a031.rest;

import local.isaac.tt_2018_a031.PDO.AlertaPDO;
import local.isaac.tt_2018_a031.PDO.ConductorPDO;
import local.isaac.tt_2018_a031.PDO.LoginPDO;
import local.isaac.tt_2018_a031.PDO.LoginRequest;
import local.isaac.tt_2018_a031.PDO.QuitarAlertaPDO;
import local.isaac.tt_2018_a031.PDO.QuitarAlertaRequest;
import local.isaac.tt_2018_a031.PDO.RegistroConductorPDO;
import local.isaac.tt_2018_a031.PDO.RegistroConductorRequest;
import local.isaac.tt_2018_a031.PDO.RegistroTrolebusPDO;
import local.isaac.tt_2018_a031.PDO.RegistroTrolebusRequest;
import local.isaac.tt_2018_a031.PDO.TrolebusPDO;
import local.isaac.tt_2018_a031.PDO.ZonasRojasPDO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIInterface {

    @POST("iniciarSesion")
    Call<LoginPDO> loginTerminal(@Body LoginRequest loginRequest);

    @GET("conductores")
    Call<ConductorPDO> condcutores();

    @GET("trolebuses")
    Call<TrolebusPDO> trolebuses();

    @POST("conductorRegistros")
    Call<RegistroConductorPDO> registrosConductores(@Body RegistroConductorRequest registroConductorRequest);

    @POST("trolebusRegistros")
    Call<RegistroTrolebusPDO> registrosTrolebuses(@Body RegistroTrolebusRequest registroTrolebusRequest);

    @GET("alertas")
    Call<AlertaPDO> alertas();

    @PUT("estadoAlerta")
    Call<QuitarAlertaPDO> quitarAlerta(@Body QuitarAlertaRequest quitarAlertaRequest);

    @GET("zonasRojas")
    Call<ZonasRojasPDO> zonasRojas();

}