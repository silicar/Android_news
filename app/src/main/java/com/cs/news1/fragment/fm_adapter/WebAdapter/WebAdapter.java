package com.cs.news1.fragment.fm_adapter.WebAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.Web;
import com.cs.news1.utils.GlideUtils;

import java.util.List;

/**
 * Created by chenshuai on 2016/11/7.
 */

public class WebAdapter extends RecyclerView.Adapter<WebAdapter.WebHolder> {
    private Context context;
    private List<Web.ResultBean.DataBean> list;

    public WebAdapter(Context context, List<Web.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public WebHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_web,parent,false);
        return new WebHolder(view);
    }

    @Override
    public void onBindViewHolder(WebHolder holder, int position) {
        holder.title.setText(list.get(position).getAuthor_name());
        holder.content.setText(list.get(position).getTitle());
       /* Glide.with(context)
                .load(list.get(position).getThumbnail_pic_s())
                .override(100,100)
                .placeholder(R.mipmap.noloading)
                .error(R.mipmap.nosccess)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.imageView);*/
        GlideUtils.loadImagewithDisk(context,list.get(position).getThumbnail_pic_s(),100,100,holder.imageView);

    }
    public void clearCache(){
        new GlideUtils().clearCache(context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WebHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView content;
        public WebHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_web_ima);
            title= (TextView) itemView.findViewById(R.id.tv_web_title);
            content= (TextView) itemView.findViewById(R.id.tv_web_content);

        }
    }
}
