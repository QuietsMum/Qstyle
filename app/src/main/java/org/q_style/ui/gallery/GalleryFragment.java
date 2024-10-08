package org.q_style.ui.gallery;

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

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        MeowBottomNavigation bottomNavigation = root.findViewById(R.id.bottom_nav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_trends));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_podpis));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_videolibrary));
        bottomNavigation.show(1, true);

        final Fragment newFragment = new GalleryListFragment();
        final Fragment newFragment2 = new GalleryTrendsFragment();
        final Fragment newFragment3 = new GalleryChannelsFragment();
        final Fragment newFragment4 = new GalleryLibraryFragment();
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
                    case 4:
                        transaction[0] = getFragmentManager().beginTransaction();
                        transaction[0].replace(R.id.frame_home, newFragment4);
                        transaction[0].addToBackStack(null).commit();
                        break;
                }

                return Unit.INSTANCE;
            }
        });

        return root;
    }
}