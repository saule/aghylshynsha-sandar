package plant.kz.aygolek.testmp3player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener myCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phrases);

        final    ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("hello","sálem",-1,R.raw.phrase_are_you_coming));
        words.add(new Word("h a u?","qal qalay?",-1,R.raw.phrase_come_here));
        words.add(new Word("ur name","atyng kim?",-1,R.raw.phrase_how_are_you_feeling));

        WordAdapter arrayAdapter = new WordAdapter(this,words,R.color.category_phrases);

        ListView listView = findViewById(R.id.list_phrases);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                long itemIdAtPosition = parent.getItemIdAtPosition(position);
                System.out.println("rrrrrrrrrr   "+itemIdAtPosition);
                int audioResourceId = words.get((int) itemIdAtPosition).getAudioResourceId();

                mediaPlayer=MediaPlayer.create(PhrasesActivity.this,audioResourceId);
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(myCompletionListener);
            }
        });
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
