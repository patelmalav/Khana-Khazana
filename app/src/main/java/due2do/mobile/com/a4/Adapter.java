package due2do.mobile.com.a4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malav on 4/5/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Item> {
    Context context;
    List<details> items = new ArrayList<>();
    private DatabaseReference database;
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
      //  holder.rb.setRating(data.getRb());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView d_name, d_discription;
        ImageButton delete, edit, view;
        //RatingBar rb;
        public Item(final View itemView) {
            super(itemView);
            d_name =  itemView.findViewById(R.id.dish_name);
            d_discription = itemView.findViewById(R.id.discription);
            delete = itemView.findViewById(R.id.delete_btn);
            edit = itemView.findViewById(R.id.edit_btn);
            view = itemView.findViewById(R.id.view_btn);
          //  rb = (RatingBar) itemView.findViewById(R.id.rating);



            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database = FirebaseDatabase.getInstance().getReference();
                    details delete_recipe = new details();
                    delete_recipe = items.get(getAdapterPosition());
                    items.remove(delete_recipe);

                    DatabaseReference database1 = database.child("Recipe").child(delete_recipe.getKey());
                    database1.setValue(null);
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    details details = items.get(getAdapterPosition());
                    Context context = v.getContext();
                    Intent EditActivity = new Intent(context,add_recipe.class);
                    EditActivity.putExtra("Data_on_click",items.get(getAdapterPosition()));
                    context.startActivity(EditActivity);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    details details = items.get(getAdapterPosition());
                    Context context = v.getContext();
                    Intent ViewActivity = new Intent(context,view_recipe.class);
                    ViewActivity.putExtra("View_Data",items.get(getAdapterPosition()));
                    context.startActivity(ViewActivity);

                }
            });
        }
    }

}


