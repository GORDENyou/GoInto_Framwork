package p.gordenyou.gointoframwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import p.gordenyou.gointoframwork.annotation.AnnotationActivity;

public class MainActivity extends Activity {

    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1.注解实现自动获取 Intent 携带值");
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(linkedList);

        adapter.setListener((view, position) -> {
            itemDetial(position);
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerItemDecoration());
        recyclerView.setAdapter(adapter);
    }

    private void itemDetial(int position) {
        switch (position){
            case 0:
                startActivity(new Intent(MainActivity.this, AnnotationActivity.class).putExtra("testString", "TEST!!!").putExtra("intTests", new int[]{233, 666,7788}));
                break;
        }
    }
}
