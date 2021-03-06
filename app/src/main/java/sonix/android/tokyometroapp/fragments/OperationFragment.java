package sonix.android.tokyometroapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sonix.android.tokyometroapp.R;
import sonix.android.tokyometroapp.activities.OperationActivity_;
import sonix.android.tokyometroapp.entities.Operation;

/**
 * 運行情報画面
 */

@EFragment(R.layout.fragment_operation)
public class OperationFragment extends BaseFragment {

    final String TYPE = "odpt:TrainInformation";

    @ViewById(R.id.operation_list)
    ListView listView;

    @AfterViews
    void afterViews() {
        apiService.getOperation(TYPE, pref.consumerKey().get(), new Callback<List<Operation>>() {
            @Override
            public void onResponse(Call<List<Operation>> call, Response<List<Operation>> response) {
                ArrayList<OperationItem> operationList = new ArrayList<>();

                operationList.add(new OperationItem.Builder().trainImage(R.drawable.ginza).trainName(getString(R.string.ginza)).trainInformation(response.body().get(0).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.tozai).trainName(getString(R.string.tozai)).trainInformation(response.body().get(1).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.chiyoda).trainName(getString(R.string.chiyoda)).trainInformation(response.body().get(2).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.nanboku).trainName(getString(R.string.nanboku)).trainInformation(response.body().get(3).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.marunouchi).trainName(getString(R.string.marunouchi)).trainInformation(response.body().get(4).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.hanzomon).trainName(getString(R.string.hanzomon)).trainInformation(response.body().get(5).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.yurakucho).trainName(getString(R.string.yurakucho)).trainInformation(response.body().get(6).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.fukutoshin).trainName(getString(R.string.fukutoshin)).trainInformation(response.body().get(7).trainInformationText).build());
                operationList.add(new OperationItem.Builder().trainImage(R.drawable.hibiya).trainName(getString(R.string.hibiya)).trainInformation(response.body().get(8).trainInformationText).build());

                OperationAdapter operationAdapter = new OperationAdapter(getActivity(), operationList);
                listView.setAdapter(operationAdapter);
            }

            @Override
            public void onFailure(Call<List<Operation>> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected static class OperationItem {
        int trainImage;
        String trainName;
        String trainInformation;

        protected static class Builder {
            private OperationItem operationItem;

            public Builder() {
                operationItem = new OperationItem();
            }

            public Builder trainImage(int trainImage) {
                operationItem.trainImage = trainImage;
                return this;
            }

            public Builder trainName(String trainName) {
                operationItem.trainName = trainName;
                return this;
            }

            public Builder trainInformation(String trainInformation) {
                operationItem.trainInformation = trainInformation;
                return this;
            }

            public OperationItem build() {
                return operationItem;
            }
        }
    }

    protected class OperationAdapter extends ArrayAdapter<OperationItem> {
        public OperationAdapter(Context context, List<OperationItem> operationItem) {
            super(context, 0, operationItem);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.operation_row, parent, false);
            }

            OperationItem operationItem = getItem(position);
            ImageView trainImage = (ImageView) convertView.findViewById(R.id.train_image);
            trainImage.setImageResource(operationItem.trainImage);
            TextView trainName = (TextView) convertView.findViewById(R.id.train_name);
            trainName.setText(operationItem.trainName);
            TextView trainInformation = (TextView) convertView.findViewById(R.id.train_information);
            trainInformation.setText(operationItem.trainInformation);
            return convertView;
        }
    }
}

