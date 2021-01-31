package com.example.chat.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chat.R;
import com.example.chat.UI.SettingUi.chatting.Message_Activity;
import com.example.chat.model.Users;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    private Context mContext;
    private List<Users> mUsers;
    private Boolean ischat;

    public UserAdapter (Context mContext ,List<Users> mUsers , boolean ischat ){
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat = ischat ;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final Users users = mUsers.get(position);
        holder.username.setText(users.getUsername());

//        if(users.getImageURL().equals("default")){
        String url = users.getImageURL();
        if (url != null && url.equals("default")){
            holder.profileImage.setImageResource(R.drawable.ic_add_alert_black_24dp);
        }else {
            Glide.with(mContext).load(users.getImageURL()).into(holder.profileImage);
//            Picasso.with(mContext).load(users.getImageURL()).into(holder.profileImage);
        }
        if (ischat){
            if (users.getStatus().equals("online")){
                    holder.img_on.setVisibility(View.VISIBLE);
                    holder.img_off.setVisibility(View.GONE);
            }else {
                    holder.img_on.setVisibility(View.GONE);
                    holder.img_off.setVisibility(View.VISIBLE);
            }
            }else {
                     holder.img_on.setVisibility(View.GONE);
                     holder.img_off.setVisibility(View.GONE);

            }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Message_Activity.class);
                intent.putExtra("userid",users.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profileImage;
        private CircularImageView img_on;
        private CircularImageView img_off;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.userText);
            profileImage =itemView.findViewById(R.id.user_Image);
            img_on =itemView.findViewById(R.id.image_online);
            img_off =itemView.findViewById(R.id.image_offline);

        }
    }
}
