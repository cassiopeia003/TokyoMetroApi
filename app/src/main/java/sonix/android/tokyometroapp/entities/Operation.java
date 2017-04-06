package sonix.android.tokyometroapp.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hiroki.ishikawa on 2017/04/06.
 */

public class Operation {

    @SerializedName("dc:date")
    public String date;

    @SerializedName("dc:valid")
    public String valid;

    @SerializedName("odpt:operator")
    public String operator;

    @SerializedName("odpt:railway")
    public String railway;

    @SerializedName("odpt:timeOfOrigin")
    public String timeOfOrigin;

    @SerializedName("odpt:trainInformationText")
    public String trainInformationText;
}
