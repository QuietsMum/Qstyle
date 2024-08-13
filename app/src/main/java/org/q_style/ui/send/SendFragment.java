package org.q_style.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import org.q_style.R;

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        MeowBottomNavigation bottomNavigation = root.findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_shop));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_video));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_music));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_book));
        return root;
    }
}