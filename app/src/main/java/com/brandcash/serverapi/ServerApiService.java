package com.brandcash.serverapi;

import com.brandcash.model.AccountData;
import com.brandcash.model.AccountMain;
import com.brandcash.model.CashListResponseData;
import com.brandcash.model.Code;
import com.brandcash.model.PhoneNum;
import com.brandcash.model.ReceiptListResponseData;
import com.brandcash.model.ReceiptResponseData;
import com.brandcash.model.Offer;
import com.brandcash.model.Session;
import com.brandcash.model.User;
import com.brandcash.model.UserRegestrated;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public interface ServerApiService {

    @POST("v1/users")
    Call<UserRegestrated> regUser(@Body User user);

    @POST("v1/users/restore_phone_code")
    Call<Code> restorePhoneCode(@Body PhoneNum phnum);

    @POST("v1/sessions")
    Call<Session> getSession(@Query("phone") String phone, @Query("code") String code);

    @GET("v1/users/{id}/accounts")
    Call<AccountMain> getAccountData(@Path("id") int userId, @Query("sid") String sid);


    @GET("getSpecials")
    Call<List<Offer>> getSpecials();

    @GET("v1/settings")
    Call<AccountData> getSettings();

    @GET("v1/receipts/add")
    Call<ReceiptResponseData> add(@Query("n") String n, @Query("t") String t,
                                  @Query("s") String s, @Query("fn") String fn,
                                  @Query("i") String i, @Query("fp") String fp, @Query("sid") String sid);

    @GET("v1/receipts")
    Call<ReceiptListResponseData> listReceipts(@Query("sid") String sid);

    @GET("v1/users/{id}/accounts/cash")
    Call<CashListResponseData> listCash(@Path("id") int userId, @Query("sid") String sid);

}
