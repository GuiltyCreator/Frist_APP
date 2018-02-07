package com.example.a7279.tulingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity{
    List<ChatModel> list;
    EditText editText;
    RecyclerView recyclerView;
    ChatList_Adapter adapter;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList<>();
        editText=findViewById(R.id.send_text);
        recyclerView=findViewById(R.id.chat_recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new ChatList_Adapter(list);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String data=editText.getText().toString();
            if(!"".equals(data))
            {
                ChatModel chatModel=new ChatModel();
                chatModel.setIsreceived(false);
                chatModel.setText(data);
                chatModel.setTime(time_set());
                chatModel.save();
                list.add(chatModel);
                Post_Okhttp(data);
                editText.setText("");
            }
            }
        });
    }

    private String time_set()
{
    Calendar calendar=Calendar.getInstance();
    int year=calendar.get(Calendar.YEAR);
    int month=calendar.get(Calendar.MONTH)+1;
    int day=calendar.get(Calendar.DAY_OF_MONTH);
    int hour=calendar.get(Calendar.HOUR_OF_DAY);
    int min=calendar.get(Calendar.MINUTE);
    int scend=calendar.get(Calendar.SECOND);
    String time=year+"/"+month+"/"+day+"--"+hour+":"+min+":"+scend;
    return time;
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.history:
                Intent intent=new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.close:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("是否关闭程序？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivitiyCollector.finishAll();
                    }
                });
                dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
                default:
        }
        return true;
    }

    void Post_Okhttp(final String text)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RequestBody requestBody=new FormBody.Builder().add("key","268be5a907a24140ae5c68c1229665eb")
                            .add("info",text)
                            .add("userid","GuiltyCreator")
                            .build();
                    Request request=new Request.Builder().url("http://www.tuling123.com/openapi/api").post(requestBody).build();
                    OkHttpClient client=new OkHttpClient();
                    Response response=client.newCall(request).execute();
                    String data=response.body().string();
                    Log.d(TAG, "run: "+data);
                    parseJSONWithGSON(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"好像没网哦~~",Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

     private void parseJSONWithGSON(String jsonData)
    {
        Gson gson=new Gson();
        ChatModel  chatModel=gson.fromJson(jsonData,new TypeToken<ChatModel>(){}.getType());
        chatModel.save();
        for(ChatModel.ListBean listBean:chatModel.getList())
        {
            ListBean listBean1=new ListBean();
            listBean1.setArticle(listBean.getArticle());
            listBean1.setDetailurl(listBean.getDetailurl());
            listBean1.setIcon(listBean.getIcon());
            listBean1.setInfo(listBean.getInfo());
            listBean1.setName(listBean.getName());
            listBean1.setSource(listBean.getSource());
            listBean1.setTime(listBean.getTime());
            listBean1.save();
        }
        list.add(chatModel);
        show();
    }
  private  void show()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyItemInserted(list.size()-1);
                recyclerView.scrollToPosition(list.size()-1);
            }
        });
    }
}
