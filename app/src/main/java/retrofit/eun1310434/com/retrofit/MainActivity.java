package retrofit.eun1310434.com.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit
                .Builder()
                .baseUrl(RetrofitService.URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .build();
        // Retrofit 클래스로 인터페이스 구현

        retrofitService = retrofit.create(RetrofitService.class);
        Call<ResponseBody> comment =
                retrofitService.getComment(1);//RetrofitService에서 구현한 메소드
        comment.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //데이터가 받아지면 호출되는 메소드
                try {
                    Log.e("TEST",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //데이터가 받는것이 실패할 경우 호출되는 메소드

            }
        });

        //프레그먼트를 코드에서 추가
        /*RetrofitFragment fragment = new RetrofitFragment ();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();//트렌젝션 안에서의 실행이므로 commit을 꼭해줘야 함*/
    }

}
