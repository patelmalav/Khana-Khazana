package due2do.mobile.com.a4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by malav on 4/5/2018.
 */

public class add_recipe extends AppCompatActivity {

    EditText D_name,D_discription, D_ingridents, D_steps, key;
    TextView rate;
    RatingBar rb;
    ImageView D_image;
    Button submit;
    DatabaseReference database;
    details details = new details();
    details update = new details();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);
        database = FirebaseDatabase.getInstance().getReference();


        D_name= findViewById(R.id.dish_name);
        D_discription = findViewById(R.id.discription);
        D_ingridents = findViewById(R.id.ingredients);
        D_steps = findViewById(R.id.steps);
        D_image = findViewById(R.id.image);
        submit = findViewById(R.id.add_btn);
        rb = findViewById(R.id.rating);
        rate = findViewById(R.id.rate_value);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate.setText(""+ rating);
            }
        });

        update = (details) getIntent().getSerializableExtra("Data_on_click");
        if (update != null){
            D_name.setText(update.getName());
            D_discription.setText(update.getDiscription());
            D_steps.setText(update.getSteps());
            D_ingridents.setText(update.getIngridents());
            rb.setRating(update.getRate());

        }



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (update!= null) {
                    update.setName(String.valueOf(D_name.getText()));
                    update.setSteps(String .valueOf(D_steps.getText()));
                    update.setIngridents(String .valueOf(D_ingridents.getText()));
                    update.setDiscription(String.valueOf(D_discription.getText()));
                    update.setRate(rb.getRating());
                    database.child("Recipe").child(update.getKey()).setValue(update);

                }
                else {

                    String name = D_name.getText().toString();
                    String discription = D_discription.getText().toString();
                    String ingridents = D_ingridents.getText().toString();
                    String steps = D_steps.getText().toString();

                    details.setName(name);
                    details.setDiscription(discription);
                    details.setIngridents(ingridents);
                    details.setSteps(steps);
                    details.setRate(rb.getRating());
                    database.child("Recipe").push().setValue(details);


                }
                startActivity(new Intent(add_recipe.this, homepage.class));
            }

        });


    }

}
