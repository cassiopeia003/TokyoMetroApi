package sonix.android.tokyometroapp.apis;

import android.content.Context;
import android.support.compat.BuildConfig;

import org.androidannotations.annotations.Bean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hiroki.ishikawa on 2017/04/06.
 */
public class ApiService {
    private OkHttpClient client;
    protected final Context context;
    private final static int READ_TIME_OUT = 60;
    private final static int WRITE_TIME_OUT = 60;

    // ログ出力レベル
    // debugビルド時は全て出力する
    private static final HttpLoggingInterceptor.Level LOG_LEVEL = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;

    public ApiService(Context context) {
        this.context = context;
    }


    private void initClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(LOG_LEVEL);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        client = builder.addInterceptor(loggingInterceptor)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    protected Retrofit getRestAdapter() {
        return new Retrofit.Builder()
                .baseUrl(TokyoMetroApi.BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * API実行クラスを作成する
     */
    public TokyoMetroApi create() {
        return getRestAdapter().create(TokyoMetroApi.class);
    }

    /**
     * APIの送信に成功したか
     *
     * @param response
     * @return true 正常な処理結果を取得 false エラー情報を取得
     */
    public static boolean isSuccess(Response response) {
        return response.body() != null;
    }
}
