package plant.kz.aygolek.testmp3player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phrases);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("hello","sálem"));
        words.add(new Word("h a u?","qal qalay?"));
        words.add(new Word("ur name","atyng kim?"));

        WordAdapter arrayAdapter = new WordAdapter(this,words);

        ListView listView = findViewById(R.id.list_phrases);
        listView.setAdapter(arrayAdapter);

    }
}
