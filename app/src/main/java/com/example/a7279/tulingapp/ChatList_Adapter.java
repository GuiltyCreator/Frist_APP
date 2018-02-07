package com.example.a7279.tulingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by a7279 on 2017/11/19.
 */

public class ChatList_Adapter extends RecyclerView.Adapter<ChatList_Adapter.ViewHolder> {
    private List<ChatModel> mlist;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chatlist_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.tuling_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatModel chatModel=mlist.get(holder.getAdapterPosition());
                if(chatModel.getUrl()!=null)
                {
                    Intent intent=new Intent(view.getContext(),WebActivity.class);
                    intent.putExtra("Url",chatModel.getUrl());
                    view.getContext().startActivity(intent);
                }
            }
        });
        holder.user_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                if(HistoryActivity.adapter!=null)
                {
                    final ChatModel chatModel=mlist.get(holder.getAdapterPosition());
                    AlertDialog.Builder dialog=new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("警告");
                    dialog.setMessage("是否要删除此条记录？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DataSupport.deleteAll(ChatModel.class,"id=?",chatModel.getId());
                            mlist.remove(holder.getAdapterPosition());
                            HistoryActivity.adapter.notifyDataSetChanged();
                            Toast.makeText(view.getContext(),"已删除一条记录",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                }
                return true;
            }
        });
        holder.tuling_text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                if(HistoryActivity.adapter!=null)
                {
                    final ChatModel chatModel=mlist.get(holder.getAdapterPosition());
                    AlertDialog.Builder dialog=new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("警告");
                    dialog.setMessage("是否要删除此条记录？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DataSupport.deleteAll(ChatModel.class,"id=?",chatModel.getId());
                            mlist.remove(holder.getAdapterPosition());
                            HistoryActivity.adapter.notifyDataSetChanged();
                            Toast.makeText(view.getContext(),"已删除一条记录",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                }

                return true;
            }
        });//长按删除
        return  holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatModel chatModel=mlist.get(position);
        if (chatModel.getIsreceived())
        {
            holder.leftLinearLayout.setVisibility(View.VISIBLE);
            holder.rightLinearLayout.setVisibility(View.GONE);
            holder.chattime_text.setVisibility(View.GONE);
            holder.tuling_text.setText(chatModel.getText());
            holder.chattime_text.setText(chatModel.getTime());
            if(chatModel.getList()!=null)
            {
                LinearLayoutManager layoutManager=new LinearLayoutManager(holder.item_view.getContext());
                holder.recyclerView.setLayoutManager(layoutManager);
                ContentList_Adapter adapter=new ContentList_Adapter(chatModel.getList());
                holder.recyclerView.setAdapter(adapter);
            }
        }
        else
        {
            holder.leftLinearLayout.setVisibility(View.GONE);
            holder.rightLinearLayout.setVisibility(View.VISIBLE);
            holder.user_text.setText(chatModel.getText());
            holder.chattime_text.setText(chatModel.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLinearLayout;
        LinearLayout rightLinearLayout;
        TextView chattime_text;
        TextView tuling_text;
        TextView user_text;
        RecyclerView recyclerView;
        View item_view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.item_view=itemView;
            this.leftLinearLayout=itemView.findViewById(R.id.left_layout);
            this.rightLinearLayout=itemView.findViewById(R.id.right_layout);
            this.chattime_text=itemView.findViewById(R.id.chattime_text);
            this.tuling_text=itemView.findViewById(R.id.tuling_text);
            this.user_text=itemView.findViewById(R.id.user_text);
            this.recyclerView=itemView.findViewById(R.id.content_RV);
        }
    }

    public ChatList_Adapter(List<ChatModel> list)
    {
        mlist=list;
    }
}
