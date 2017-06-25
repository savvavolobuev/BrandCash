package com.brandcash.serverapi;

import com.brandcash.model.AccountData;
import com.brandcash.model.ReceiptListResponseData;
import com.brandcash.model.ReceiptResponseData;
import com.brandcash.model.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public interface ServerApiService {

    @GET("getSpecials")
    Call<List<Offer>> getSpecials();

    @GET("settings")
    Call<AccountData> getSettings();

    @GET("receipts/add")
    Call<ReceiptResponseData> add(@Query("n") String n, @Query("t") String t,
                                  @Query("s") String s, @Query("fn") String fn,
                                  @Query("i") String i, @Query("fp") String fp, @Query("sid") String sid);

    @GET("receipts")
    Call<ReceiptListResponseData> list(@Query("sid") String sid);

}
