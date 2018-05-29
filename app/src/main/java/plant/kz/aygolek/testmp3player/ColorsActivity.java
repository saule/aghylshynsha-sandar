package plant.kz.aygolek.testmp3player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_colors);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("white","aq"));
        words.add(new Word("yellow","sary"));
        words.add(new Word("red","qyzyl"));
        words.add(new Word("pink","qyzghylt"));
        words.add(new Word("brown","qonyr"));
        words.add(new Word("green","jasyl"));
        words.add(new Word("blue","kók"));

        WordAdapter arrayAdapter = new WordAdapter(this,words);

        ListView listView = findViewById(R.id.list_colors);
        listView.setAdapter(arrayAdapter);

    }
}
