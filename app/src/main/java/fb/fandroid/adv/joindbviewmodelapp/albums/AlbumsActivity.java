package fb.fandroid.adv.joindbviewmodelapp.albums;

import android.support.v4.app.Fragment;

import fb.fandroid.adv.joindbviewmodelapp.SingleFragmentActivity;


public class AlbumsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return AlbumsFragment.newInstance();
    }
}
