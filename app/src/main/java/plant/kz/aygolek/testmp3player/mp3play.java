package plant.kz.aygolek.testmp3player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class mp3play extends AppCompatActivity {

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
                Intent intent = new Intent(mp3play.this, NumbersActivity.class);
                startActivity(intent);

            }
        });

        family_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mp3play.this, FamilyMembersActivity.class);
                startActivity(intent);

            }
        });

        colors_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mp3play.this, ColorsActivity.class);
                startActivity(intent);

            }
        });

        phrases_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mp3play.this, PhrasesActivity.class);
                startActivity(intent);

            }
        });

    }
}
