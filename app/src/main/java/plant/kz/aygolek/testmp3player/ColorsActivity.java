package plant.kz.aygolek.testmp3player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

    private MediaPlayer.OnCompletionListener myCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_colors);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("white","aq", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("yellow","sary", R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Word("red","qyzyl", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("pink","qyzghylt", R.drawable.color_dusty_yellow,  R.raw.color_dusty_yellow));
        words.add(new Word("brown","qonyr", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("green","jasyl", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("blue","kók", R.drawable.color_gray, R.raw.color_gray));

        WordAdapter arrayAdapter = new WordAdapter(this,words,R.color.category_colors);

        ListView listView = findViewById(R.id.list_colors);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                long itemIdAtPosition = parent.getItemIdAtPosition(position);
                System.out.println("rrrrrrrrrr   "+itemIdAtPosition);
                int audioResourceId = words.get((int) itemIdAtPosition).getAudioResourceId();

                mediaPlayer= MediaPlayer.create(ColorsActivity.this,audioResourceId);
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
