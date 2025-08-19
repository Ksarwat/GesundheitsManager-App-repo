package com.example.gesundheitsmanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.gesundheitsmanager.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Open navigation drawer when clicking the text
        binding.textHome.setOnClickListener(v -> {
            if (getActivity() != null) {
                androidx.drawerlayout.widget.DrawerLayout drawer = getActivity().findViewById(com.example.gesundheitsmanager.R.id.drawer_layout);
                if (drawer != null) drawer.openDrawer(androidx.core.view.GravityCompat.START);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}