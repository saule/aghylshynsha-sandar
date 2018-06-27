package qz.aygolek.englishwordsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import qz.aygolek.englilshwordsapp.R;

public class main_activity extends AppCompatActivity {

    private TextView numbers_category;
    private TextView family_category;
    private TextView colors_category;
    private TextView phrases_category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3play);

        numbers_category = findViewById(R.id.category_numbers);
        family_category = findViewById(R.id.category_family);
        colors_category = findViewById(R.id.category_colors);
        phrases_category = findViewById(R.id.category_phrasses);

        numbers_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_activity.this, NumbersActivity.class);
                startActivity(intent);

            }
        });

        family_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_activity.this, FamilyMembersActivity.class);
                startActivity(intent);

            }
        });

        colors_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_activity.this, ColorsActivity.class);
                startActivity(intent);

            }
        });

        phrases_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_activity.this, PhrasesActivity.class);
                startActivity(intent);

            }
        });


    }
}
