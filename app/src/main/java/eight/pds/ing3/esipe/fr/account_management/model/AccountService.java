package eight.pds.ing3.esipe.fr.account_management.model;




import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;

import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


/**
 * Created by antho on 12/11/2017.
 */

public interface AccountService {

    String ENDPOINT = "http://192.168.1.13:8181/";


    @POST("auth")
    Observable<ResponseBody> authenticate(@Body Credential credential);


    @GET("accounts")
    Observable<List<Account>> getAccounts(@Header("Authorization") String authHeader);

    class Creator {

        //static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        public static AccountService newAccountService(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AccountService.ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(AccountService.class);
        }

    }





}
