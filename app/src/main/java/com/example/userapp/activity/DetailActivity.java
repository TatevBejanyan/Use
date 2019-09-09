package com.example.userapp.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.userapp.R;
import com.example.userapp.adapter.UserAdapter;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgItem;
    private TextView   txtNameItem;
    private TextView   txtGenderItem;
    private TextView    txtAgeItem;
    private TextView    txtEmailItem;
    private TextView txtItemLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        getData();



    }
    public void initView(){
        imgItem = findViewById(R.id.img_item);
        txtNameItem = findViewById(R.id.txt_item_name);
        txtAgeItem = findViewById(R.id.txt_item_age);
        txtEmailItem = findViewById(R.id.txt_item_email);
        txtGenderItem = findViewById(R.id.txt_item_gender);
        txtItemLocation = findViewById(R.id.txt_item_location);

    }
    public void  getData(){
        Intent intent = getIntent();
        String name = UserAdapter.capitalize(intent.getStringExtra("name"));
        txtNameItem.setText(new StringBuilder("Name: ").append(name));
        txtGenderItem.setText(new StringBuilder("Gender: ").append(intent.getStringExtra("gender")));
        txtEmailItem.setText(new StringBuilder("E-mail: ").append(intent.getStringExtra("email")));
        txtAgeItem.setText(new StringBuilder("Age: ").append(intent.getStringExtra("age")).append(" years old."));
        txtItemLocation.setText(new StringBuilder("Location: ").append(intent.getStringExtra("location")));
        Picasso.get().load(intent.getStringExtra("image")).into(imgItem);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
