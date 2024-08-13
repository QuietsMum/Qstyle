package org.q_style.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import org.q_style.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FrameLayout frame;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        MeowBottomNavigation bottomNavigation = root.findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_shop_card));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_shop));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_shoping_basket));
        bottomNavigation.show(1, true);

        final Fragment newFragment = new HomeShopsFragment();
        final Fragment newFragment2 = new HomeBuysFragment();
        final Fragment newFragment3 = new HomeBagFragment();
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