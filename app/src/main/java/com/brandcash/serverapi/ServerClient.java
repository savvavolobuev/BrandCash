package com.brandcash.serverapi;

import android.content.pm.PackageManager;
import android.os.Build;

import com.brandcash.BrandcashApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class ServerClient {

    private static final String SERVER_URL = "http://brand.cash/";
    private static Retrofit retrofit;
    private static OkHttpClient client;
    private static String version;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        try {
            version = BrandcashApp.getAppContext().getPackageManager()
                    .getPackageInfo(BrandcashApp.getAppContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            version = "1.0";
            e.printStackTrace();
        }
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originRequest = chain.request();
                Request requestWithUserAgent = originRequest.newBuilder()
                        .header("User-Agent", "BrandCash-android/" + version)
                        .header("Content-Type", "application/json")
                        .build();
                return chain.proceed(requestWithUserAgent);
            }
        }).addInterceptor(logging).hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        }).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public static ServerApiService getServerApiService() {

        return retrofit.create(ServerApiService.class);
    }
}
