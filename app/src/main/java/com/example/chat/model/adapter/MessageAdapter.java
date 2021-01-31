package com.example.chat.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.R;
import com.example.chat.model.ChatM;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.viewHolder> {

    public static final  int MSG_TYPE_LEFT = 0;
    public static final  int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<ChatM> mChat;
    private String imageurl;
    FirebaseUser fuser;


    public MessageAdapter (Context mContext ,List<ChatM> mChat ,String imageurl){
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT ) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.viewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.viewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.viewHolder holder, int position) {
        final ChatM chat = mChat.get(position);
        holder.show_message.setText(chat.getMessage());
//        if (imageurl.equals("default")){
//            holder.profileImage.setImageResource(R.mipmap.ic_launcher);
//        }else {
//            Glide.with(mContext).load(imageurl).into(holder.profileImage);
//        }

        if (position == mChat.size()-1){
            if (chat.isIsseen()){
                holder.txt_seen.setText("seen");
            }else {
                holder.txt_seen.setText("delivered");
            }
        }else {
            holder.txt_seen.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        public TextView  show_message  ;
        public ImageView profileImage;
        public  TextView txt_seen;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profileImage =itemView.findViewById(R.id.profile_Image_Chat);
            txt_seen = itemView.findViewById(R.id.Txt_Seen);
        }
    }
@Override
    public  int getItemViewType(int position){

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
  }
}
