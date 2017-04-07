package sonix.android.tokyometroapp.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import sonix.android.tokyometroapp.apis.ApiService;

/**
 * Created by hiroki.ishikawa on 2017/04/05.
 */
@EFragment
abstract class BaseFragment extends Fragment {
    @Bean
    ApiService apiService;
}
