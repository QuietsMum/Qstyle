package org.q_style.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import org.q_style.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeShopsFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView lvSimple;
    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT1 = "text1";
    final String ATTRIBUTE_NAME_TEXT2 = "text2";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_shops, container, false);

        // массивы данных
        String[] texts1 = { "lamoda", "starbucks", "coca-cola",
                "shipudim", "medved'", "technodom", "sulpak", "xiaomi" };
        String[] texts2 = { "магазин одежды", "лучший кофе", "годовой запас кока-колы",
                "шашлычная номер 1", "ресторан русской кухни'" , "магазин техники", "магазин техники", "магазин техники"};
        int[] img = new int[]{R.drawable.lamoda, R.drawable.starbucks, R.drawable.coca_cola,
                R.drawable.shipudim, R.drawable.medved, R.drawable.technodom, R.drawable.sulpak, R.drawable.xiaomi};

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
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
        String[] from = { ATTRIBUTE_NAME_TEXT1, ATTRIBUTE_NAME_TEXT2,
                ATTRIBUTE_NAME_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText1, R.id.tvText2, R.id.imageViewShop };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.item_shop_main,
                from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) root.findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);

        return root;
    }
}
