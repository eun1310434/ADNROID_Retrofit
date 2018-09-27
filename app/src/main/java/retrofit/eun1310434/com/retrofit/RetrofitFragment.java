package retrofit.eun1310434.com.retrofit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ddil on 25-Sep-18.
 */

public class RetrofitFragment extends Fragment {

    View mView;
    TextView textViewIndex;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_retrofit, container, false);
        textViewIndex = (TextView) mView.findViewById(R.id.txtRetrofitTest);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(RetrofitService.URL)
                //.addConverterFactory(GsonConverterFactory.create())
                .build();
        // Retrofit 클래스로 인터페이스 구현

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //Synchronous(retrofit,retrofitService);
        Asynchronous(retrofit,retrofitService);
        return mView;
    }

    public void Synchronous(Retrofit retrofit , final RetrofitService retrofitService){
        
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                Call<List<Item>> call = retrofitService.repoContributors("square", "retrofit");
                //Call은 서비스 인터페이스를 통해서 HTTP 요청을 원격 웹서버로 보냄

                try {
                    return call.execute().body().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textViewIndex.setText(s);
            }
        }.execute();
    }

    public void Asynchronous(Retrofit retrofit , final RetrofitService retrofitService){
        Call<List<Item>> call = retrofitService.repoContributors("square", "retrofit");
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                textViewIndex.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }
}
