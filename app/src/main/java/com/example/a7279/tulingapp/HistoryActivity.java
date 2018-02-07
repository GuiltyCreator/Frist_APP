package com.example.a7279.tulingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity {
    List<ChatModel> list = new ArrayList<>();
    List<ListBean> listBeans=new ArrayList<>();
    List<ChatModel> list1=new ArrayList<>();
    RecyclerView recyclerView;
    static ChatList_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        list = DataSupport.findAll(ChatModel.class);
        ChatModel chatModel=new ChatModel();
        for(ChatModel chatModel1:list)
        {
            chatModel=chatModel1;
            listBeans.addAll(DataSupport.select("article","source","name","icon","info","detailurl","time").where("time=?",chatModel.getTime()).find(ListBean.class));
            /*chatModel.setList(DataSupport.select("article","source","name","icon","info","detailurl","time").where("time=?",chatModel.getTime()).find(ChatModel.ListBean.class));*/
            for(ListBean listBean:listBeans)
            {
                ChatModel.ListBean listBean1=new ChatModel.ListBean();
                    listBean1.setArticle(listBean.getArticle());
                    listBean1.setDetailurl(listBean.getDetailurl());
                    listBean1.setIcon(listBean.getIcon());
                    listBean1.setInfo(listBean.getInfo());
                    listBean1.setName(listBean.getName());
                    listBean1.setSource(listBean.getSource());
                    listBean1.setTime(listBean.getTime());
                    chatModel.getList().add(listBean1);
            }
            list1.add(chatModel);
            listBeans.clear();
        }
        recyclerView = findViewById(R.id.history_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ChatList_Adapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(HistoryActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("是否要清空历史记录？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataSupport.deleteAll(ChatModel.class);
                        DataSupport.deleteAll(ListBean.class);
                        list.clear();
                        adapter = new ChatList_Adapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
                break;
            case R.id.back_toolbar:
                finish();
                break;
            default:
        }
        return true;
    }

}