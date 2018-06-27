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

public class PhrasesActivity extends AppCompatActivity {

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
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phrases);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(getResources().getString(R.string.prases_hello), "hello", -1, R.raw.phrases_hello));
        words.add(new Word(getResources().getString(R.string.prases_what_is_your_name), "What is your name?", -1, R.raw.phrases_whatisyourname));
        words.add(new Word(getResources().getString(R.string.prases_my_name_is), "My name is ...", -1, R.raw.phrases_mynameis));
        words.add(new Word(getResources().getString(R.string.prases_how_are_you), "How are you?", -1, R.raw.phrases_howareyou));
        words.add(new Word(getResources().getString(R.string.prases_i_am_good), "I am good", -1, R.raw.phrases_iamgood));
        words.add(new Word(getResources().getString(R.string.prases_where_are_u_going), "Where are you going?", -1, R.raw.phrases_whereareyougoing));
        words.add(new Word(getResources().getString(R.string.prases_lets_go), "Let's go", -1, R.raw.phrases_letsgo));
        words.add(new Word(getResources().getString(R.string.prases_will_u_come), "Will you come?", -1, R.raw.phrases_willyoucome));
        words.add(new Word(getResources().getString(R.string.prases_yes), "Yes", -1, R.raw.phrases_yes));
        words.add(new Word(getResources().getString(R.string.prases_no), "No", -1, R.raw.phrases_no));
        words.add(new Word(getResources().getString(R.string.prases_come_here), "Come here", -1, R.raw.phrases_comehere));

        WordAdapter arrayAdapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = findViewById(R.id.list_phrases);
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
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, audioResourceId);
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(myCompletionListener);

                }
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
            audioManager.abandonAudioFocus(mAudioFocusListener);
        }
    }
}
