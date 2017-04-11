package sonix.android.tokyometroapp.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.Pref;

import sonix.android.tokyometroapp.apis.ApiService;
import sonix.android.tokyometroapp.preferences.Preference_;

/**
 * 基底となるクラス
 */

@EFragment
abstract class BaseFragment extends Fragment {
    @Pref
    Preference_ pref;

    @Bean
    ApiService apiService;
}
