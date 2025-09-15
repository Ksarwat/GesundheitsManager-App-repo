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

        binding.textHome.setOnClickListener(v -> {
            if (getActivity() != null) {
                androidx.drawerlayout.widget.DrawerLayout drawer = getActivity().findViewById(com.example.gesundheitsmanager.R.id.drawer_layout);
                if (drawer != null) drawer.openDrawer(androidx.core.view.GravityCompat.START);
            }
        });

        binding.buttonErnaehrung.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), com.example.gesundheitsmanager.R.id.nav_host_fragment_content_main);
            navController.navigate(com.example.gesundheitsmanager.R.id.nav_ernaehrung);
        });
        binding.buttonNotizen.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), com.example.gesundheitsmanager.R.id.nav_host_fragment_content_main);
            navController.navigate(com.example.gesundheitsmanager.R.id.nav_notizen);
        });
        binding.buttonFitness.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), com.example.gesundheitsmanager.R.id.nav_host_fragment_content_main);
            navController.navigate(com.example.gesundheitsmanager.R.id.nav_fitness);
        });
        binding.buttonMedikamente.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), com.example.gesundheitsmanager.R.id.nav_host_fragment_content_main);
            navController.navigate(com.example.gesundheitsmanager.R.id.nav_medikamente);
        });
        binding.buttonNotfallkontakte.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), com.example.gesundheitsmanager.R.id.nav_host_fragment_content_main);
            navController.navigate(com.example.gesundheitsmanager.R.id.nav_notfallkontakte);
        });

        binding.buttonErsteHilfe.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), com.example.gesundheitsmanager.R.id.nav_host_fragment_content_main);
            navController.navigate(com.example.gesundheitsmanager.R.id.nav_erste_hilfe);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}