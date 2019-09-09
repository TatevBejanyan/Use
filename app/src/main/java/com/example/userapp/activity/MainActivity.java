package com.example.userapp.activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.userapp.R;
import com.example.userapp.adapter.UserAdapter;
import com.example.userapp.model.Result;
import com.example.userapp.model.User;
import com.example.userapp.network.UserApi;
import com.example.userapp.network.UserInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener {


    RecyclerView userRecycler;
    RecyclerView.LayoutManager layoutManager;
    List<Result> resultArrayList = new ArrayList<>();
    UserAdapter userAdapter;
    private int position;
    private int page = 1;
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getUsersData();
        userRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLastItemDisplaying(userRecycler)){
                    getUsersData();
                }
            }
        });
    }

    public void initViews() {
        progressBar = findViewById(R.id.progress_bar);
        userRecycler = findViewById(R.id.user_recycler);
        layoutManager = new LinearLayoutManager(this);
        userRecycler.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(MainActivity.this, MainActivity.this);
        userRecycler.setAdapter(userAdapter);


    }

        public void getUsersData() {
            UserApi userApi = UserInstance.getInstance().create(UserApi.class);
            Call<User> call;
            call = userApi.getUser(page++ , 10);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.body()!=null){
                        resultArrayList.addAll(response.body().getResults());

                    }
                            userAdapter = new UserAdapter(MainActivity.this, MainActivity.this);
                            userRecycler.setAdapter(userAdapter);
                            userAdapter.setData(resultArrayList);
                            progressBar.setVisibility(View.GONE);
                        }


                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        }


        private boolean isLastItemDisplaying(RecyclerView mRecycler) {
        if (mRecycler.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPos = ((LinearLayoutManager) mRecycler.getLayoutManager())
                    .findLastCompletelyVisibleItemPosition();
            return lastVisibleItemPos != RecyclerView.NO_POSITION && lastVisibleItemPos == mRecycler.getAdapter().getItemCount() - 1;
        }
        return false;
    }

    @Override
    public void onUserClick(int position) {
        this.position = position;
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Result result = resultArrayList.get(position);
        intent.putExtra("name", (Serializable) new StringBuilder(UserAdapter.capitalize(result.getName().getFirst())).append(" ").append(UserAdapter.capitalize(result.getName().getLast())));
        intent.putExtra("gender", result.getGender());
        intent.putExtra("age", String.valueOf(result.getDob().getAge()));
        intent.putExtra("image",String.valueOf( result.getPicture().getLarge()));
        intent.putExtra("email", String.valueOf(result.getEmail()));
        intent.putExtra("location", (Serializable) new StringBuilder(UserAdapter.capitalize(String.valueOf(result.getLocation().
                getState()))).append(UserAdapter.capitalize(result.getLocation().getState())).append(UserAdapter.capitalize(result.getLocation().getStreet())));

        startActivity(intent);


    }

}

