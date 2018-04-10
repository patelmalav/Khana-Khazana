package due2do.mobile.com.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by malav on 4/10/2018.
 */

public class view_recipe extends AppCompatActivity {

    TextView name, discription, ingredients, steps;
    DatabaseReference database;
    Button back;
    details details = new details();
    details viewdata = new details();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe);
        database = FirebaseDatabase.getInstance().getReference();
        name = findViewById(R.id.dish_name_view);
        discription = findViewById(R.id.discription_view);
        ingredients = findViewById(R.id.ingredients_view);
        steps = findViewById(R.id.steps_view);
        back = findViewById(R.id.back_btn);

        viewdata = (details) getIntent().getSerializableExtra("View_Data");

        if(viewdata != null){
            name.setText(viewdata.getName());
            ingredients.setText(viewdata.getIngridents());
            discription.setText(viewdata.getDiscription());
            steps.setText(viewdata.getSteps());
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_recipe.this, homepage.class));
            }
        });


    }
}
