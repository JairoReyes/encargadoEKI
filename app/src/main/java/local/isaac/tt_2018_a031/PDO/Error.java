package local.isaac.tt_2018_a031.PDO;

import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("text")
    private String text;

    public Error() {
    }

    public Error(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

