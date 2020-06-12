package p.gordenyou.gointoframwork.into_retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.ResponseBody;
import p.gordenyou.gointoframwork.R;
import p.gordenyou.gointoframwork.into_retrofit.api.MyRetrofitApi;
import p.gordenyou.gointoframwork.into_retrofit.api.RetrofitApi;
import p.gordenyou.gointoframwork.into_retrofit.myretrofit.MyRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyRetrofitActivity extends AppCompatActivity {

    private static final String TAG = MyRetrofitActivity.class.getName();
    @BindView(R.id.rf_post)
    Button post;
    @BindView(R.id.rf_get)
    Button get;
    @BindView(R.id.rf_my_post)
    Button myPost;
    @BindView(R.id.rf_my_get)
    Button myGet;

    private RetrofitApi retrofitApi;
    private MyRetrofitApi myRetrofitApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_retrofit);
        //我们分别构建retrofit 和我们自己的框架接口。

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://restapi.amap.com").build();
        retrofitApi = retrofit.create(RetrofitApi.class);

        MyRetrofit myRetrofit = new MyRetrofit.Builder().baseUrl("https://restapi.amap.com").build();
//        MyRetrofit myRetrofit1 = new MyRetrofit();// 实验证明，不是同一个 Package 就不可以使用。
        myRetrofitApi = myRetrofit.create(MyRetrofitApi.class);
    }

    public void post(View view) {
        Call<ResponseBody> call = retrofitApi.postWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                try {
                    String string = body.string();
                    Log.i(TAG, "onResponse post: " + string);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    body.close();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void get(View view) {
        Call<ResponseBody> call = retrofitApi.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    ResponseBody body = response.body();
                    try {
                        String string = body.string();
                        Log.i(TAG, "onResponse get: " + string);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        body.close();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void myPost(View view) {
        okhttp3.Call call = myRetrofitApi.postWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.i(TAG, "onResponse myPost: " + response.body().string());
                response.close();
            }
        });
    }

    public void myGet(View view) {
        okhttp3.Call call = myRetrofitApi.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.i(TAG, "onResponse myGet: " + response.body().string());
                response.close();
            }
        });
    }
}