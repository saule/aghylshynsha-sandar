package plant.kz.aygolek.testmp3player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

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

        setContentView(R.layout.activity_familymembers);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("mom","ana", R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("dad","áke", R.drawable.family_father,R.raw.family_father));
        words.add(new Word("brother","agha", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("sister","ápke", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("grandma","áje", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandpa","ata", R.drawable.family_grandfather,R.raw.family_grandfather));
        words.add(new Word("baby","bópe", R.drawable.family_younger_brother,R.raw.family_younger_brother));

        WordAdapter arrayAdapter = new WordAdapter(this,words,R.color.category_family);

        ListView listView = findViewById(R.id.list_fammembers);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                long itemIdAtPosition = parent.getItemIdAtPosition(position);
                System.out.println("rrrrrrrrrr   "+itemIdAtPosition);
                int audioResourceId = words.get((int) itemIdAtPosition).getAudioResourceId();

                mediaPlayer=MediaPlayer.create(FamilyMembersActivity.this,audioResourceId);
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
