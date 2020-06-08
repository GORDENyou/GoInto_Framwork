package p.gordenyou.gointoframwork.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import p.gordenyou.gointoframwork.R;

public class HandlerActivity extends AppCompatActivity {

    TextView textView;

    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    textView.setText("HandlerActivity");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = findViewById(R.id.handler);

        myHandler.post(new Runnable() {
            @Override
            public void run() {
                //更新UI
                Message message = Message.obtain();
                message.what = 1;
            }
        });
    }

}
