package sonix.android.tokyometroapp.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import sonix.android.tokyometroapp.apis.ApiService;

/**
 * 基底となるクラス
 */

@EFragment
abstract class BaseFragment extends Fragment {
    @Bean
    ApiService apiService;
}
