package org.q_style.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import org.q_style.R;
import org.q_style.ui.home.HomeBagFragment;
import org.q_style.ui.home.HomeBuysFragment;
import org.q_style.ui.home.HomeShopsFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        MeowBottomNavigation bottomNavigation = root.findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_library_books));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_downloaded_books));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_reader));
        bottomNavigation.show(1, true);

        final Fragment newFragment = new ToolsBooksFragment();
        final Fragment newFragment2 = new ToolsSavedFragment();
        final Fragment newFragment3 = new ToolsReaderFragment();
        final FragmentTransaction[] transaction = {getFragmentManager().beginTransaction()};
        transaction[0].replace(R.id.frame_home, newFragment);
        transaction[0].addToBackStack(null);
        transaction[0].commit();
        // Set Menu Click Listener
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model p1) {
                int i = p1.getId();
                switch (i) {
                    case 1:

                        transaction[0] = getFragmentManager().beginTransaction();
                        transaction[0].replace(R.id.frame_home, newFragment);
                        transaction[0].addToBackStack(null).commit();
                        break;
                    case 2:
                        transaction[0] = getFragmentManager().beginTransaction();
                        transaction[0].replace(R.id.frame_home, newFragment2);
                        transaction[0].addToBackStack(null).commit();
                        break;
                    case 3:
                        transaction[0] = getFragmentManager().beginTransaction();
                        transaction[0].replace(R.id.frame_home, newFragment3);
                        transaction[0].addToBackStack(null).commit();
                        break;
                }

                return Unit.INSTANCE;
            }
        });


        return root;
    }
}