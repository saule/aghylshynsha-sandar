package plant.kz.aygolek.testmp3player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one","bir"));
        words.add(new Word("two","eki"));
        words.add(new Word("three","ýsh"));
        words.add(new Word("four","tórt"));
        words.add(new Word("five","bes"));
        words.add(new Word("six","alty"));
        words.add(new Word("seven","jeti"));
        words.add(new Word("eight","segiz"));
        words.add(new Word("nine","toghyz"));
        words.add(new Word("ten","on"));

        WordAdapter arrayAdapter = new WordAdapter(this,words);

        ListView listView = findViewById(R.id.list_numbers);
        listView.setAdapter(arrayAdapter);

    }
}
