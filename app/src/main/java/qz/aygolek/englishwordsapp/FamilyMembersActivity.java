package qz.aygolek.englishwordsapp;

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

import qz.aygolek.englilshwordsapp.R;

public class FamilyMembersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // Pause playback because your Audio Focus was
                // temporarily stolen, but will be back soon.
                // i.e. for a phone call
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // Stop playback, because you lost the Audio Focus.
                // i.e. the user started some other playback app
                // Remember to unregister your controls/buttons here.
                // And release the kra — Audio Focus!
                // You’re done.
                releaseMediaPlayer();


            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // Resume playback, because you hold the Audio Focus
                // again!
                // i.e. the phone call ended or the nav directions
                // are finished
                // If you implement ducking and lower the volume, be
                // sure to return it to normal here, as well.
                mediaPlayer.start();
            }
        }
    };

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

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(getResources().getString(R.string.fam_mom), "mother", R.drawable.family_mother, R.raw.family_mom));
        words.add(new Word(getResources().getString(R.string.fam_dad), "father", R.drawable.family_father, R.raw.family_dad));
        words.add(new Word(getResources().getString(R.string.fam_younger_brother), "younger brother", R.drawable.family_younger_brother, R.raw.family_younger_bro));
        words.add(new Word(getResources().getString(R.string.fam_younger_sister), "younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sis));
        words.add(new Word(getResources().getString(R.string.fam_older_brother), "elder brother", R.drawable.family_older_brother, R.raw.family_elder_bro));
        words.add(new Word(getResources().getString(R.string.fam_older_sister), "elder sister", R.drawable.family_older_sister, R.raw.family_elder_sister));
        words.add(new Word(getResources().getString(R.string.fam_daughter), "daughter", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(getResources().getString(R.string.fam_son), "son", R.drawable.family_son, R.raw.family_son));
        words.add(new Word(getResources().getString(R.string.fam_grandma), "grandma", R.drawable.family_grandmother, R.raw.family_grandma));
        words.add(new Word(getResources().getString(R.string.fam_grandpa), "grandpa", R.drawable.family_grandfather, R.raw.family_grandpa));

        WordAdapter arrayAdapter = new WordAdapter(this, words, R.color.category_family);

        ListView listView = findViewById(R.id.list_fammembers);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                long itemIdAtPosition = parent.getItemIdAtPosition(position);
                System.out.println("rrrrrrrrrr   " + itemIdAtPosition);
                int audioResourceId = words.get((int) itemIdAtPosition).getAudioResourceId();

                int result = audioManager.requestAudioFocus(
                        mAudioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, audioResourceId);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(myCompletionListener);
                }
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

            audioManager.abandonAudioFocus(mAudioFocusListener);
        }
    }


}
