package plant.kz.aygolek.testmp3player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();
    private int bg_colorResourceId;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of Word objects to display in a list
     */
    public WordAdapter(@NonNull Context context, ArrayList<Word> words, int bg_colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,words);
        this.bg_colorResourceId =bg_colorResourceId;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        LinearLayout textContainer = listItemView.findViewById(R.id.textContainer);

        int color = ContextCompat.getColor(getContext(),bg_colorResourceId);
        textContainer.setBackgroundColor(color);
        // Find the TextView in the list_iteml layout with the ID english number
        TextView eng_number_TextView = (TextView) listItemView.findViewById(R.id.english_number_textview);
        // Get the english name of number from the current Word object and
        // set this text on the name eng_number_TextView
        eng_number_TextView.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_iteml layout with the ID qazaq_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.qazaq_number_textview);
        // Get the qazaq number from the current Word object and
        // set this text on the number TextView
        numberTextView.setText(currentWord.getMiwokTranslation());


        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.word_imageview);
        // Get the image resource ID from the current Word object and
        // set the image to iconView
        if (currentWord.getImageResourceId()!=-1)
            iconView.setImageResource(currentWord.getImageResourceId());
        else
            iconView.setVisibility(View.GONE);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
