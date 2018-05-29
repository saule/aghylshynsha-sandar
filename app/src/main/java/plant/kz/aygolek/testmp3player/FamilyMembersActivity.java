package plant.kz.aygolek.testmp3player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_familymembers);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("mom","ana"));
        words.add(new Word("dad","áke"));
        words.add(new Word("brother","agha"));
        words.add(new Word("sister","ápke"));
        words.add(new Word("grandma","áje"));
        words.add(new Word("grandpa","ata"));
        words.add(new Word("baby","bópe"));

        WordAdapter arrayAdapter = new WordAdapter(this,words);

        ListView listView = findViewById(R.id.list_fammembers);
        listView.setAdapter(arrayAdapter);

    }
}
