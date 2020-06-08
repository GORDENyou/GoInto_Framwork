package p.gordenyou.gointoframwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<String> myRecyclerList;
    private View.OnClickListener listener;

    public MyRecyclerViewAdapter(List<String> myRecyclerList) {
        this.myRecyclerList = myRecyclerList;
    }

    public void setListener(View.OnClickListener listener) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myrecycler, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * 这个是可以再次利用的 view ， 我们可以直接绑定值
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(myRecyclerList.get(position));
    }

    @Override
    public int getItemCount() {
        return myRecyclerList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_title);
        }
    }
}
