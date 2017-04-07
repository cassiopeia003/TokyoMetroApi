package sonix.android.tokyometroapp.apis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sonix.android.tokyometroapp.entities.Operation;

/**
 * API
 */

public interface TokyoMetroApi {

    String BASE_URL = "https://api.tokyometroapp.jp/";

    @GET("api/v2/datapoints")
    Call<List<Operation>> getOperation(@Query("rdf:type") String type, @Query("acl:consumerKey") String consumerKey);
}
