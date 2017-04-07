package sonix.android.tokyometroapp.fragments;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sonix.android.tokyometroapp.R;
import sonix.android.tokyometroapp.entities.Operation;

/**
 * Created by hiroki.ishikawa on 2017/04/06.
 */

@EFragment(R.layout.fragment_operation)
public class OperationFragment extends BaseFragment {

    @ViewById
    TextView textView;

    @AfterViews
    void afterViews() {
        apiService.getOperation("odpt:TrainInformation", "e0b630fa1a98bfa67af9d8162fc626d49e5ea605b7611d45673125a196893afb", new Callback<List<Operation>>() {
            @Override
            public void onResponse(Call<List<Operation>> call, Response<List<Operation>> response) {
                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                textView.setText(response.body().get(0).trainInformationText);
            }

            @Override
            public void onFailure(Call<List<Operation>> call, Throwable t) {
                Log.e("", "",t);
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
