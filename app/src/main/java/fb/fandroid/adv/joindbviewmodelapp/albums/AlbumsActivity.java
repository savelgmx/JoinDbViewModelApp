package fb.fandroid.adv.joindbviewmodelapp.albums;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import fb.fandroid.adv.joindbviewmodelapp.R;
import fb.fandroid.adv.joindbviewmodelapp.SingleFragmentActivity;
import fb.fandroid.adv.joindbviewmodelapp.model.User;


public class AlbumsActivity extends SingleFragmentActivity {
    public static final String USER_KEY = "USER_KEY";
    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        mUser = (User) bundle.get(USER_KEY);


        super.onCreate(savedInstanceState);
    }

    @Override
    protected Fragment getFragment() {
        return AlbumsFragment.newInstance();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_comment, menu);
        menu.findItem(R.id.add_comment).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public User getCurrentUser(){
        return mUser;
    }
}
