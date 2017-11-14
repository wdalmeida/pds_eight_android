package eight.pds.ing3.esipe.fr.account_management.model;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


/**
 * Created by antho on 12/11/2017.
 */

public interface AccountService {

    String ENDPOINT = "http://192.168.1.44:8080/";

    @GET("accounts")
    Observable<List<Account>> getAccounts();

    class Creator {

        public static AccountService newAccountService(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AccountService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(AccountService.class);
        }

    }





}
