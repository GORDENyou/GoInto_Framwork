package p.gordenyou.gointoframwork.into_retrofit;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import p.gordenyou.gointoframwork.R;
import p.gordenyou.gointoframwork.into_retrofit.api.MyRetrofitApi;
import p.gordenyou.gointoframwork.into_retrofit.api.RetrofitApi;
import p.gordenyou.gointoframwork.into_retrofit.myretrofit.MyRetrofit;
import retrofit2.Retrofit;

public class MyRetrofitActivity extends AppCompatActivity {

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
    }
}