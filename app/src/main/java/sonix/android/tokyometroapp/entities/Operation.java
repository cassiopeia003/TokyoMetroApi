package sonix.android.tokyometroapp.entities;

import com.google.gson.annotations.SerializedName;

/**
 * 運行情報のAPIに含まれている要素
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
