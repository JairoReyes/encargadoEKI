package local.isaac.tt_2018_a031.rest;

import local.isaac.tt_2018_a031.PDO.ConductorPDO;
import local.isaac.tt_2018_a031.PDO.LoginPDO;
import local.isaac.tt_2018_a031.PDO.LoginRequest;
import local.isaac.tt_2018_a031.PDO.TrolebusPDO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("iniciarSesion")
    Call<LoginPDO> loginTerminal(@Body LoginRequest loginRequest);

    @GET("conductores")
    Call<ConductorPDO> condcutores();

    @GET("trolebuses")
    Call<TrolebusPDO> trolebuses();

}