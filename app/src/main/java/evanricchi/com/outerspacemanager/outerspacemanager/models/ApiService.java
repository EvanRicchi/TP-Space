package evanricchi.com.outerspacemanager.outerspacemanager.models;

import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiBuilding;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiToken;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiUser;
import evanricchi.com.outerspacemanager.outerspacemanager.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/api/v1/auth/create")
    Call<ApiToken> createUser(@Body User user);

    @POST("/api/v1/auth/login")
    Call<ApiToken> login(@Body User user);

    @GET("/api/v1/users/get")
    Call<ApiUser> currentuser(@Header("x-access-token") String token);

    @POST("/api/v1/buildings/create/{buildingId}")
    Call<ApiBuilding> createBuilding(@Header("x-access-token") String token, @Path("buildingId") int buildingID);

    @GET("/api/v1/buildings/list")
    Call<ListBuilding> getBuilding(@Header("x-access-token") String token);

    @GET("/api/v1/buildings/{buildId}")
    Call<ApiBuilding> getBuildingById(@Header("x-access-token") String token, @Path("buildId") int buildId);

    @GET("/api/v1/users/{from}/{limit}")
    Call<ListUser> getRank(@Header("x-access-token") String token, @Path("from") int from, @Path("limit") int limit);

    @GET("/api/v1/ships")
    Call<ListFleet> getFleet(@Header("x-access-token") String token);
}
