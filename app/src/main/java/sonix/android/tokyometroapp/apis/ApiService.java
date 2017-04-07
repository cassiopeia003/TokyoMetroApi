package sonix.android.tokyometroapp.apis;

import org.androidannotations.annotations.EBean;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sonix.android.tokyometroapp.entities.Operation;

/**
 * API通信を行う
 */

@EBean
public class ApiService {
    public void getOperation(String type, String consumerKey, Callback<List<Operation>> callback) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TokyoMetroApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        TokyoMetroApi tokyoMetroApi = retrofit.create(TokyoMetroApi.class);
        retrofit2.Call<List<Operation>> call = tokyoMetroApi.getOperation(type, consumerKey);
        call.enqueue(callback);
    }
}
