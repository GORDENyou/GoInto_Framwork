package p.gordenyou.gointoframwork.into_retrofit.api;


import okhttp3.Call;
import p.gordenyou.gointoframwork.into_retrofit.annotation.Field;
import p.gordenyou.gointoframwork.into_retrofit.annotation.GET;
import p.gordenyou.gointoframwork.into_retrofit.annotation.POST;
import p.gordenyou.gointoframwork.into_retrofit.annotation.Query;

public interface MyRetrofitApi {
    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);

    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);
}
