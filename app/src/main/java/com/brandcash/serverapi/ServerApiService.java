package com.brandcash.serverapi;

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

}
