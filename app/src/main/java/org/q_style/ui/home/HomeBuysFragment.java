package org.q_style.ui.home;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.q_style.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeBuysFragment extends Fragment {

    private HomeViewModel homeViewModel;
    GridView lvSimple;
    ArrayList<Map<String, Object>> data;
    SimpleAdapter adapter;
    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT1 = "text1";
    final String ATTRIBUTE_NAME_TEXT2 = "text2";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_buys, container, false);
        lvSimple = (GridView) root.findViewById(R.id.tablelayout);
        // массивы данных
        String[] texts1 = {"Xiaomi", "Starbucks", "iQos",
                "iQos", "Овощи'", "Xiaomi", "Starbucks", "iQos",
                "iQos", "Овощи'"};
        String[] texts2 = {"Redmi note 5", "капучино", "стики iqos",
                "IQOS 3 DUO Kit", "овощи'", "Redmi note 5", "капучино", "стики iqos",
                "IQOS 3 DUO Kit", "овощи'"};
        int[] img = new int[]{R.drawable.phone_xiaomi, R.drawable.coffe_shop, R.drawable.iqos_shop,
                R.drawable.iqos_vape_shop, R.drawable.vegetables, R.drawable.phone_xiaomi, R.drawable.coffe_shop, R.drawable.iqos_shop,
                R.drawable.iqos_vape_shop, R.drawable.vegetables};

        // упаковываем данные в понятную для адаптера структуру
        data = new ArrayList<Map<String, Object>>(
                texts1.length);
        Map<String, Object> m;
        for (int i = 0; i < texts1.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT1, texts1[i]);
            m.put(ATTRIBUTE_NAME_TEXT2, texts2[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_TEXT1, ATTRIBUTE_NAME_TEXT2,
                ATTRIBUTE_NAME_IMAGE};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.tvText1, R.id.tvText2, R.id.imageViewShop};


        adapter = new SimpleAdapter(getActivity(), data, R.layout.item_buys_main, from, to);

        lvSimple.setAdapter(adapter);
        adjustGridView();

        return root;
    }

    private void adjustGridView() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int height = displaymetrics.heightPixels;
        if (width >= 2000) {
            lvSimple.setNumColumns(3);
        } else if (width >= 1400) {
            lvSimple.setNumColumns(3);
        } else lvSimple.setNumColumns(2);
    }

}
