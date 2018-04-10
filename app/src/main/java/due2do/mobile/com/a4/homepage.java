package due2do.mobile.com.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malav on 4/5/2018.
 */

public class homepage extends AppCompatActivity {

//    SearchView searchView;

    RecyclerView recyclerView;
    List<details> items = new ArrayList<>();
    ImageButton new_recipe;
    DatabaseReference database;
    Adapter recipe_adapter;
    EditText editText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        new_recipe = findViewById(R.id.add_recipe);
        recyclerView = findViewById(R.id.recipe_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this, items));
         editText = findViewById(R.id.search);

       // https://www.youtube.com/watch?v=OWwOSLfWboY&feature=youtu.be (to implement search)

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        database = FirebaseDatabase.getInstance().getReference().child("Recipe");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    details storeData = ds.getValue(details.class);
                    storeData.setKey(ds.getKey());
                    items.add(storeData);
                }
                recipe_adapter = new Adapter(homepage.this, items);
                recyclerView.setAdapter(recipe_adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        new_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, add_recipe.class));
            }
        });



    }

    private void filter(String text){
        ArrayList<details> filteredlist = new ArrayList<>();

        for (details item : items){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }
        recipe_adapter.filterlist(filteredlist);
    }

}

