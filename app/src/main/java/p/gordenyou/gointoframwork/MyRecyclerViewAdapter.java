package p.gordenyou.gointoframwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {

    private List<String> myRecyclerList;
    private MyItemClickListener listener;

    public MyRecyclerViewAdapter(List<String> myRecyclerList) {
        this.myRecyclerList = myRecyclerList;
    }

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myrecycler, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    /**
     * 这个是可以再次利用的 view ， 我们可以直接绑定值
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(myRecyclerList.get(position));
        holder.itemView.setTag(position); // 我们这里需要保存位置。
    }

    @Override
    public int getItemCount() {
        return myRecyclerList.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onItemClick(view, (int) view.getTag()); // 这里通过 view 的 getTag() 方法获取项目位置。
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_title);
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }
}
