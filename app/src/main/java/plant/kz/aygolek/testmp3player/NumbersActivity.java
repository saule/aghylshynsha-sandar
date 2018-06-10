package plant.kz.aygolek.testmp3player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener myCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_numbers);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one","bir", R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","eki", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three","ýsh", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four","tórt", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five","bes", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six","alty", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven","jeti", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight","segiz", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine","toghyz", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten","on", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter arrayAdapter = new WordAdapter(this,words, R.color.category_numbers);

        ListView listView = findViewById(R.id.list_numbers);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();

                int audioResourceId = words.get(position).getAudioResourceId();


                mediaPlayer=MediaPlayer.create(NumbersActivity.this,audioResourceId);

                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(myCompletionListener);
            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
