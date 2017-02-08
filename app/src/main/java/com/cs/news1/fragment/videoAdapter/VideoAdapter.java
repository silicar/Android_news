package com.cs.news1.fragment.videoAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.cs.news1.R;
import com.cs.news1.entry.Video;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private Context context;
    private List<Video.V9LG4E6VRBean> mList;
    public VideoAdapter(Context context , List<Video.V9LG4E6VRBean> mList) {
        this.mList=mList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_videoview, parent, false));
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.jcVideoPlayer.setUp(
                mList.get(position).getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST,mList.get(position).getTitle());
        Glide.with(holder.jcVideoPlayer.getContext())
                .load(mList.get(position).getCover())
                .centerCrop()
                .into(holder.jcVideoPlayer.thumbImageView);
        holder.mCount.setText(mList.get(position).getPlayCount()+"");
        holder.mTvComeName.setText(mList.get(position).getVideosource());
       // Glide.with(context).load(mList.get(position).getTopicImg()).centerCrop().into(holder.mIcon);
        Glide.with(context).load(mList.get(position).getTopicImg()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.mIcon) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.mIcon.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JCVideoPlayerStandard jcVideoPlayer;
        ImageView mIcon;
        TextView mTvComeName;
        TextView mCount;
        public MyViewHolder(View itemView) {
            super(itemView);
            jcVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
            mIcon= (ImageView) itemView.findViewById(R.id.iv_item_videoicom);
            mTvComeName= (TextView) itemView.findViewById(R.id.tv_item_videoname);
            mCount= (TextView) itemView.findViewById(R.id.tv_item_videocount);


        }
    }

}
