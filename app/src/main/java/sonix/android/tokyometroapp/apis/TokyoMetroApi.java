package sonix.android.tokyometroapp.apis;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sonix.android.tokyometroapp.entities.Operation;

/**
 * Created by hiroki.ishikawa on 2017/04/06.
 */

public interface TokyoMetroApi {

    String BASE_URL = "https://api.tokyometroapp.jp/api/v2/datapoints";


    @GET(BASE_URL)
    void getOperation(@Query("rdf:type") String type, @Query("dc:title") String title, @Query("acl:consumerKey") String consumerKey, Callback<Operation> cb);
}
