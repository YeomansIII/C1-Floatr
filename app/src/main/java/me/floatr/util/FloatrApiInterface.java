package me.floatr.util;

import android.content.IntentFilter;

import me.floatr.models.Auth;
import me.floatr.models.Loan;
import me.floatr.models.LoanOffer;
import me.floatr.models.Login;
import me.floatr.models.Payment;
import me.floatr.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by pedrod on 10/30/16.
 */

public interface FloatrApiInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("user")
    Call<User> getUser();

    @PUT("user/{id}")
    Call<User> updateUser(@Path("id") String groupId, @Body User user);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") String userId);

    @POST("loans")
    Call<Loan> createLoan(@Body Loan loan);

    @PUT("loans/{id}")
    Call<Loan> updateLoan(@Path("id") String loanId, @Body Loan loan);

    @GET("loans/{id}")
    Call<Loan> getLoan(@Path("id") String loanId);

    @GET("loan-offer")
    Call<LoanOffer> getLoanOffer();

    @POST("loan-offer")
    Call<LoanOffer> createLoadOffer(@Body LoanOffer loanOffer);

    @PUT("loan-offer/{id}")
    Call<LoanOffer> updateLoadOffer(@Path("id") String offerId, @Body LoanOffer loanOffer);

    @GET("loan-offer/{id}")
    Call<LoanOffer> getLoadOffer(@Path("id") String groupId);

    @DELETE("loan-offer/{id}")
    Call<LoanOffer> deleteLoadOffer(@Path("id") String offerId);

    @POST("payments")
    Call<Payment> createPayment(@Body Payment payment);

    @GET("payments/{id}")
    Call<Payment> getPayment(@Path("id") String paymentId);

    @POST("auth/register")
    Call<Auth> register(@Body User user);

    @POST("auth/login")
    Call<Auth> login(@Body Login login);


//    @GET("group/{id}/users")
//        Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
//
//        @POST("users/new")
//        Call<User> createUser(@Body User user);

}
