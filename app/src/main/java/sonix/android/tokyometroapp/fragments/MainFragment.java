package sonix.android.tokyometroapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import sonix.android.tokyometroapp.R;
import sonix.android.tokyometroapp.activities.OperationActivity_;
import sonix.android.tokyometroapp.activities.TimeTableActivity_;

/**
 * メイン画面
 */

@EFragment(R.layout.fragment_main)
public class MainFragment extends BaseFragment {

    @ViewById
    ListView listView;

    @AfterViews
    void afterViews() {
        ArrayList<MenuItem> menuList = new ArrayList<>();

        menuList.add(new MenuItem.Builder().label(getString(R.string.location)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.operation)).intent(OperationActivity_.intent(this).get()).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.timetable)).intent(TimeTableActivity_.intent(this).get()).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.equipment)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.station)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.charge)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.gateway)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.MLIT_station)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.MLIT_rail)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.metro_station)).build());
        menuList.add(new MenuItem.Builder().label(getString(R.string.metro_rail)).build());

        MenuAdapter menuAdapter = new MenuAdapter(getActivity(), menuList);
        listView.setAdapter(menuAdapter);

        if (!pref.consumerKey().get().equals(getString(R.string.consumer_key))) {
            pref.consumerKey().put(getString(R.string.consumer_key));
        }
    }

    protected static class MenuItem {
        String label;
        Intent intent;

        protected static class Builder {
            private MenuItem menuItem;

            public Builder() {
                menuItem = new MenuItem();
            }

            public Builder label(String label) {
                menuItem.label = label;
                return this;
            }

            public Builder intent(Intent intent) {
                menuItem.intent = intent;
                return this;
            }

            public MenuItem build() {
                return menuItem;
            }
        }
    }

    protected class MenuAdapter extends ArrayAdapter<MenuItem> {
        public MenuAdapter(Context context, List<MenuItem> menuItem) {
            super(context, 0, menuItem);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
            }

            MenuItem menuItem = getItem(position);
            TextView label = (TextView) convertView.findViewById(R.id.label);
            label.setText(menuItem.label);
            return convertView;
        }
    }

    @ItemClick(R.id.list_view)
    void tapMenuItem(MenuItem menuItem) {
        if (menuItem.intent == null) {
            Toast.makeText(getContext(), menuItem.label + " tap", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(menuItem.intent);
    }
}
