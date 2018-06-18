package plant.kz.aygolek.testmp3player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

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

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(getResources().getString(R.string.color_white), "white", R.drawable.color_white, R.raw.color_white));
        words.add(new Word(getResources().getString(R.string.color_yellow), "yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word(getResources().getString(R.string.color_red), "red", R.drawable.color_red, R.raw.color_red));
        words.add(new Word(getResources().getString(R.string.color_pink), "pink", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word(getResources().getString(R.string.color_green), "green", R.drawable.color_green, R.raw.color_green));
        words.add(new Word(getResources().getString(R.string.color_blue), "blue", R.drawable.color_black, R.raw.color_black));
        words.add(new Word(getResources().getString(R.string.color_brown), "brown", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word(getResources().getString(R.string.color_grey), "gray", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word(getResources().getString(R.string.color_black), "black", R.drawable.color_black, R.raw.color_black));

        WordAdapter arrayAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = findViewById(R.id.list_colors);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                long itemIdAtPosition = parent.getItemIdAtPosition(position);
                Log.v("audio_focus", "fouces is granted");
                int audioResourceId = words.get((int) itemIdAtPosition).getAudioResourceId();

                int result = audioManager.requestAudioFocus(
                        ColorsActivity.this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    //audioManager.registerMediaButtonEventReceiver();
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, audioResourceId);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(myCompletionListener);
                } else {
                    Toast.makeText(ColorsActivity.this, "Cannot gain audio focus", Toast.LENGTH_SHORT).show();
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

            audioManager.abandonAudioFocus(ColorsActivity.this);
        }
    }

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

}
