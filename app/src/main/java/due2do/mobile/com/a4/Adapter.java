package due2do.mobile.com.a4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malav on 4/5/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Item> {
    Context context;
    List<details> items = new ArrayList<>();
    public Adapter(Context context, List<details> items){
    this.context = context;
    this.items = items;
    }
    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.recycler_row, parent, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(Item holder, int position) {
        details data = new details();
        data = items.get(position);

        holder.d_name.setText(data.getName());
        holder.d_discription.setText(data.getDiscription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView d_name, d_discription;
        public Item(View itemView) {
            super(itemView);
            d_name =  itemView.findViewById(R.id.dish_name);
            d_discription = itemView.findViewById(R.id.discription);
        }
    }

}


