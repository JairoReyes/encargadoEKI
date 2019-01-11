package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;
public class LoginPDO {
    @SerializedName("loginResponse")
    public LoginResponse loginResponse;

    @SerializedName("error")
    public Error error;


    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public Error getError() {
        return error;
    }


}