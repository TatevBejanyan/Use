package com.example.userapp.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.userapp.R;
import com.example.userapp.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>  {

    private Context mContext;
    private List<Result> resultList;
    private OnUserClickListener onUserClickListener;

    public UserAdapter(Context mContext,OnUserClickListener onUserClickListener) {
        this.mContext = mContext;
        resultList = new ArrayList<>();
        this.onUserClickListener = onUserClickListener;


    }
    public  void setData(List<Result> mlistresults){
        resultList.clear();
        resultList.addAll(mlistresults);
        notifyDataSetChanged();
    }

    public static String capitalize(String str)
    {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_users_recycler,viewGroup, false);
        return new UserViewHolder(view,onUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position){
     holder.txtName.setText(new StringBuilder(capitalize(String.valueOf(resultList.get(position).getName().getFirst()))).append(" ").
             append(capitalize(String.valueOf(resultList.get(position).getName().getLast()))));
     holder.txtStateCity.setText(new StringBuilder(capitalize(String.valueOf(resultList.get(position).getLocation().getCity()))).append(" ")
             .append(capitalize(String.valueOf(resultList.get(position).getLocation().getState()))));
     holder.txtPhone.setText(new StringBuilder(capitalize(String.valueOf(resultList.get(position).getCell()))));
     Picasso.get().load(String.valueOf(resultList.get(position).getPicture().getMedium())).into(holder.imgUser);
    }


    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgUser;
        private TextView txtName;
        private  TextView txtStateCity;
        private TextView txtPhone;
        OnUserClickListener onUserClickListener;
        public UserViewHolder(@NonNull View itemView, OnUserClickListener onUserClickListener) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            txtStateCity = itemView.findViewById(R.id.txt_state);
            txtPhone = itemView.findViewById(R.id.txt_phonumber);
            txtName = itemView.findViewById(R.id.txt_name);
            this.onUserClickListener = onUserClickListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            onUserClickListener.onUserClick(getAdapterPosition());

        }
    }
    public interface OnUserClickListener{
        void onUserClick(int position);

    }


}
