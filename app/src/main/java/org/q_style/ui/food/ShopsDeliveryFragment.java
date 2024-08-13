package org.q_style.ui.food;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.q_style.R;
import org.q_style.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopsDeliveryFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView lvSimple;
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
        View root = inflater.inflate(R.layout.fragment_delivery_shops, container, false);
        lvSimple = root.findViewById(R.id.lvSimple);
        // массивы данных
        String[] texts1 = {"Shipudim", "Starbucks", "Пинта",
                "Пинта", "Банка'", "Медведь", "Узбечка", "Сухой закон"};
        String[] texts2 = {"шашлык из баранины", "капучино", "джин бамбей + тоник",
                "пиво", "коктейл пина калада'", "стйек рибай", "лагман", "виски"};
        int[] img = new int[]{R.drawable.shipudim_shash, R.drawable.coffe_shop, R.drawable.shot6,
                R.drawable.pivo4, R.drawable.napitok6, R.drawable.steak3, R.drawable.lagman, R.drawable.shot5};

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


        adapter = new SimpleAdapter(getActivity(), data, R.layout.item_shops_delivery, from, to);

        lvSimple.setAdapter(adapter);

        return root;
    }

}