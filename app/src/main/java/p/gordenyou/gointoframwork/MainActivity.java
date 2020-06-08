package p.gordenyou.gointoframwork;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.main_recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("注解实现自动获取 Intent 携带值");
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(linkedList);
    }
}
