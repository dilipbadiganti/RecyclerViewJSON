package com.example.recyclerviewjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;



import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private final Context myContext;
    private final ArrayList<ExamplePost> myPostList;

    public ExampleAdapter(Context myContext, ArrayList<ExamplePost> myPostList) {
        this.myContext = myContext;
        this.myPostList = myPostList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(myContext).inflate(R.layout.sample_post,parent,false);

        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExamplePost currentPost = myPostList.get(position);
        String imgUrl = currentPost.getImageUrl();
        String creatorName = currentPost.getCreator();
        int likesCount = currentPost.getLikeCount();

        holder.textViewCreator.setText(creatorName);
        //holder.textViewLikes.setText("Likes:"+likesCount);
        holder.textViewLikes.setText(myContext.getString(R.string.likes_count, likesCount));

        //Picasso.get(myContext).load(imgUrl).fit().centerInside().into(holder.imageView);
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return myPostList.size();
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textViewCreator;
        public TextView textViewLikes;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            textViewCreator = itemView.findViewById(R.id.tv_creator);
            textViewLikes = itemView.findViewById(R.id.tv_likes);
        }
    }
}
