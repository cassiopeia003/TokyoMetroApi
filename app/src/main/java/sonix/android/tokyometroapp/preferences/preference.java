package sonix.android.tokyometroapp.preferences;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by hiroki.ishikawa on 2017/04/10.
 */
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface Preference {

    String consumerKey();
}
