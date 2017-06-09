package com.brandcash.serverapi;

import com.brandcash.model.AccountData;
import com.brandcash.model.Document;
import com.brandcash.model.DocumentResponse;
import com.brandcash.model.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public interface ServerApiService {

    @GET("/getSpecials")
    Call<List<Offer>> getSpecials();

    @GET("/settings")
    Call<AccountData> getSettings();

    @GET("/checkQr")
    Call<DocumentResponse> checkQr();

}
