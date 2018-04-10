package due2do.mobile.com.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    RecyclerView recyclerView;
    List<details> items = new ArrayList<>();
    ImageButton new_recipe;
    DatabaseReference database;
    Adapter recipe_adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        new_recipe = findViewById(R.id.add_recipe);
        recyclerView = findViewById(R.id.recipe_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this, items));

        database = FirebaseDatabase.getInstance().getReference().child("Recipe");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    details storeData = ds.getValue(details.class);
                    items.add(storeData);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recipe_adapter = new Adapter(homepage.this, items);
        recyclerView.setAdapter(recipe_adapter);

        new_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, add_recipe.class));
            }
        });


    }


}
