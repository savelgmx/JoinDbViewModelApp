package fb.fandroid.adv.joindbviewmodelapp.comments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fb.fandroid.adv.joindbviewmodelapp.R;
import fb.fandroid.adv.joindbviewmodelapp.model.Comment;

/**
 * @author Azret Magometov
 */

public class CommentsHolder extends RecyclerView.ViewHolder {

    private TextView mAuthor;
    private TextView mText;
    private TextView mDate;

    public CommentsHolder(View itemView) {
        super(itemView);
        mAuthor = itemView.findViewById(R.id.tv_author);
        mText = itemView.findViewById(R.id.tv_text);
        mDate = itemView.findViewById(R.id.tv_date);
    }

    public void bind(Comment item) {
        mAuthor.setText(item.getAuthor());
        mText.setText(item.getText());

        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            date = format.parse(item.getTimestamp());
            Date today = Calendar.getInstance().getTime();

            int days = (int) ((today.getTime() - date.getTime()) / (24 * 60 * 60 * 1000));

            if (days > 1){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                mDate.setText(dateFormat.format(date));
            } else{
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                mDate.setText(timeFormat.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}