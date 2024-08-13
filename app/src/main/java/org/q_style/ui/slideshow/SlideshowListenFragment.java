package org.q_style.ui.slideshow;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.q_style.R;
import org.q_style.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlideshowListenFragment extends Fragment {

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
        View root = inflater.inflate(R.layout.fragment_slideshow_listen, container, false);
        lvSimple = (GridView) root.findViewById(R.id.tablelayout);
        // массивы данных
        String[] texts1 = {"Ninety One", "Мадина Садвакасова", "Алишер КАРИМОВ",
                "Роза Рымбаева", "Luina'", "Кайрат Нуртас", "Ninety One", "Айқын Төлепберген"};
        String[] texts2 = {"Men Emes", "Любовь без преград", "Juregim",
                "Любовь Любовь", "Hey Yo'", "Что-то поет", "man true", "тоже что-то поет"};
        int[] img = new int[]{R.drawable.ninetyone, R.drawable.madina, R.drawable.alisher,
                R.drawable.roza, R.drawable.luina, R.drawable.nurtas, R.drawable.ninetyone, R.drawable.aykin};

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


        adapter = new SimpleAdapter(getActivity(), data, R.layout.item_music_listen, from, to);

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
