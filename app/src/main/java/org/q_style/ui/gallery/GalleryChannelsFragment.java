package org.q_style.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.exoplayer2.ExoPlayer;

import org.q_style.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GalleryChannelsFragment extends Fragment implements ExoPlayer.EventListener{

    ListView lvSimple;
    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT1 = "text1";
    final String ATTRIBUTE_NAME_TEXT2 = "text2";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery_channels, container, false);

        // массивы данных
        String[] texts1 = {"Big Buck Bunny.channel", "Elephant Dream.channel", "For Bigger Blazes.channel",
                "For Bigger Escape.channel", "For Bigger Fun.channel'", "For Bigger Joyrides.channel", "For Bigger Meltdowns.channel", "Sintel.channel"};
        String[] texts2 = {"Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... ",
                "The first Blender Open Movie from 2006",
                "HBO GO now works with Chromecast ",
                "Introducing Chromecast. The easiest way to enjoy online video and music on your TV",
                "Introducing Chromecast. The easiest way to enjoy online video and music on your TV",
                "Introducing Chromecast. The easiest way to enjoy online video and music on your TV",
                "Introducing Chromecast. The easiest way to enjoy online video and music on your TV",
                "Sintel is an independently produced short film, initiated by the Blender Foundation"};
        int[] img = new int[]{R.drawable.bigbuckbunny, R.drawable.elephantdream, R.drawable.biggerblazeazes,
                R.drawable.biggerbigger, R.drawable.joyfriends, R.drawable.joyfriends, R.drawable.meltdowns, R.drawable.sintel};

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
        String[] from = {ATTRIBUTE_NAME_TEXT1, ATTRIBUTE_NAME_TEXT2,
                ATTRIBUTE_NAME_IMAGE};
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = {R.id.videTitle, R.id.videDisc, R.id.image_video_view};

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.item_list_channels,
                from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) root.findViewById(R.id.videolist);
        lvSimple.setAdapter(sAdapter);
//        lvSimple.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), VideoFragment.class);
//                intent.putExtra("id", position );
//                startActivity(intent);
//            }
//        });
        return root;

    }



}
