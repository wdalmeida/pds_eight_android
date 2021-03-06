package fr.esipe.ing3.pds.eight.bem.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class API {

    //public static final String URL = "http://10.0.2.2:8080";
	public  static  final String URL= "http://int.eight.inside.esiag.info:8080";
	// public static final String URL = "http://192.168.42.116:8080";
    private static API instance;
    private APIService service;


    public static API get() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }

    public APIService getRetrofitService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(APIService.class);
        }
        return service;
    }

}
