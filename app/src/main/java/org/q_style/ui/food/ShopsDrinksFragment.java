package org.q_style.ui.food;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ShopsDrinksFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView lvSimple;
    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT1 = "text1";
    final String ATTRIBUTE_NAME_TEXT2 = "text2";
    final String ATTRIBUTE_NAME_IMAGE1 = "image1";
    final String ATTRIBUTE_NAME_IMAGE2 = "image2";
    final String ATTRIBUTE_NAME_IMAGE3 = "image3";
    final String ATTRIBUTE_NAME_IMAGE4 = "image4";
    final String ATTRIBUTE_NAME_IMAGE5 = "image5";
    final String ATTRIBUTE_NAME_IMAGE6 = "image6";
    final String ATTRIBUTE_NAME_IMAGEBACK = "back";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_drinks_shops, container, false);

        // массивы данных
        String[] texts1 = { "Пинта", "Банка", "Сухой закон",
                "Чечел"};
        String[] texts2 = { "Бар и Гриль", "Бар и Гриль", "Бар",
                "Бар"};
        int[] img1 = new int[]{R.drawable.pivo1, R.drawable.shot, R.drawable.napitok1,
                R.drawable.shot6};
        int[] img2 = new int[]{R.drawable.shot2, R.drawable.pivo2, R.drawable.nap2,
                R.drawable.pivo4};
        int[] img3 = new int[]{R.drawable.pivo3, R.drawable.shot3, R.drawable.napitok3,
                R.drawable.napitok6};
        int[] img4 = new int[]{R.drawable.napitok4, R.drawable.pivo4, R.drawable.shot3,
                R.drawable.shot4};
        int[] img5 = new int[]{R.drawable.shot5, R.drawable.pivo5, R.drawable.napitok5,
                R.drawable.napitok3};
        int[] img6 = new int[]{R.drawable.pivo6, R.drawable.shot6, R.drawable.napitok6,
                R.drawable.pivo2};

        int[] imgback = new int[]{R.drawable.logopinta, R.drawable.logobanka, R.drawable.logosuhoi,
                R.drawable.logochechel};

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                texts1.length);
        Map<String, Object> m;
        for (int i = 0; i < texts1.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT1, texts1[i]);
            m.put(ATTRIBUTE_NAME_TEXT2, texts2[i]);
            m.put(ATTRIBUTE_NAME_IMAGE1, img1[i]);
            m.put(ATTRIBUTE_NAME_IMAGE2, img2[i]);
            m.put(ATTRIBUTE_NAME_IMAGE3, img3[i]);
            m.put(ATTRIBUTE_NAME_IMAGE4, img4[i]);
            m.put(ATTRIBUTE_NAME_IMAGE5, img5[i]);
            m.put(ATTRIBUTE_NAME_IMAGE6, img6[i]);
            m.put(ATTRIBUTE_NAME_IMAGEBACK, imgback[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT1, ATTRIBUTE_NAME_TEXT2,
                ATTRIBUTE_NAME_IMAGE1, ATTRIBUTE_NAME_IMAGE2, ATTRIBUTE_NAME_IMAGE3, ATTRIBUTE_NAME_IMAGE4, ATTRIBUTE_NAME_IMAGE5, ATTRIBUTE_NAME_IMAGE6, ATTRIBUTE_NAME_IMAGEBACK };
        // массив ID View-компонентов, в которые будут вставлять данные,
        int[] to = { R.id.tvText1, R.id.tvText2, R.id.imageViewShop1 , R.id.imageViewShop2, R.id.imageViewShop3, R.id.imageViewShop4, R.id.imageViewShop5, R.id.imageViewShop6, R.id.background_shops};

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.item_shops_drinks,
                from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) root.findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);

        return root;
    }
}
