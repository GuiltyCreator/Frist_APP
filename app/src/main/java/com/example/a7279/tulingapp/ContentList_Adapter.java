package com.example.a7279.tulingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by a7279 on 2017/12/1.
 */

public class ContentList_Adapter extends RecyclerView.Adapter<ContentList_Adapter.ViewHolder> {
    private List<ChatModel.ListBean> mlist;
    ContentList_Adapter(List<ChatModel.ListBean> list)
    {
        this.mlist=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contentlist_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatModel.ListBean listBean=mlist.get(holder.getAdapterPosition());
                Intent intent=new Intent(view.getContext(),WebActivity.class);
                intent.putExtra("Url",listBean.getDetailurl());
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       ChatModel.ListBean listBean=mlist.get(position);
       if(listBean.getArticle()!=null) {
           holder.imageView.setImageURI(Uri.parse(listBean.getIcon()));
           holder.textView.setText(listBean.getArticle() + "\n" + "-来自:" + listBean.getSource());

       }
       else{
           holder.imageView.setImageURI(Uri.parse(listBean.getIcon()));
           holder.textView.setText(listBean.getName()+"\n"+listBean.getInfo());
       }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView1;
        View item_view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.item_view=itemView;
            this.imageView=itemView.findViewById(R.id.content_ImageView);
            this.textView=itemView.findViewById(R.id.content_TextView);
            this.textView1=itemView.findViewById(R.id.content_TextView1);
        }
    }


}
