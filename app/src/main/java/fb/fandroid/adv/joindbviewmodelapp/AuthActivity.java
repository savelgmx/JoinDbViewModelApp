package fb.fandroid.adv.joindbviewmodelapp;

import android.support.v4.app.Fragment;

/**
 * Created by andrew on 18.05.2019.
 */

public class AuthActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return AuthFragment.newInstance();
    }
}
