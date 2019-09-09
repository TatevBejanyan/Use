package com.example.userapp.network;
import com.example.userapp.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UserApi {

  @GET("api/")
  Call<User> getUser(@Query("page") int page, @Query("results") int results);

}
