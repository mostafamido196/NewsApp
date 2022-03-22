package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }

    public void loadData(){
        final ProgressBar progressBar = findViewById(R.id.pb);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CallableInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //لاحظ اننا استخدمنا inner class(builder)
        CallableInterface callable = retrofit.create(CallableInterface.class);
        Call<NewsModel> newsModelCall = callable.getNews();
        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                progressBar.setVisibility(View.GONE);//to destroy progress bar
                NewsModel newsModel = response.body();//to convert ArrayList of object to object
                ArrayList<Article> articles =  newsModel.getArticles();
                Log.d("Json","Data: "+articles.get(0).getTitle());

                showList(articles);

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);//to destroy progress bar
                Log.d("Json","Error: "+t.getLocalizedMessage());


            }
        });
    }



    private void showList(ArrayList<Article> articles){
        RecyclerView recyclerView = findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        CustomAdapter adapter = new CustomAdapter(this,articles);
        recyclerView.setAdapter(adapter);

    }

}