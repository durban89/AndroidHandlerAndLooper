package com.gowhich.androidhandlerandlooper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Activity中有一个默认的Looper对象,来处理子线程的发送的消息
        button = (Button) this.findViewById(R.id.button);
        textView = (TextView) this.findViewById(R.id.textView);

        Looper looper = Looper.myLooper();

        handler = new MyHandler(looper);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new MyThread()).start();
            }
        });
    }

    public class MyThread implements Runnable{
        @Override
        public void run() {
            Message message = Message.obtain();
            message.obj = "tom";
            handler.sendMessage(message);
        }
    }

    public class MyHandler extends Handler{
        public MyHandler(){

        }

        public MyHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText("接收消息 ==> "+msg.obj);
        }
    }
}
