package due2do.mobile.com.a4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malav on 4/5/2018.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<StoreData> items = new ArrayList<>();
    public Adapter(Context context, List<StoreData> items){
    this.context = context;
    this.items = items;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.recycler_row, parent, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StoreData data = new StoreData();
        data = items.get(position);

        ((Item)holder).d_name.setText(data.getD_name());
        ((Item)holder).d_discription.setText(data.getD_discription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView d_name, d_discription;
        public Item(View itemView) {
            super(itemView);
            d_name = (TextView) itemView.findViewById(R.id.dish_name);
            d_discription = (TextView) itemView.findViewById(R.id.discription);
        }
    }

    public class StoreData{
        private String d_name, d_discription;

        public String getD_name() {
            return d_name;
        }

        public void setD_name(String d_name) {
            this.d_name = d_name;
        }

        public String getD_discription() {
            return d_discription;
        }

        public void setD_discription(String d_discription) {
            this.d_discription = d_discription;
        }
    }
}


