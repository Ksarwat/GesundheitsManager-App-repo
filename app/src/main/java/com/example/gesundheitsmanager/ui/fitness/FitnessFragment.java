package com.example.gesundheitsmanager.ui.fitness;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.gesundheitsmanager.R;

public class FitnessFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fitness, container, false);

        Button buttonSquat = view.findViewById(R.id.buttonSquat);
        Button buttonPushUp = view.findViewById(R.id.buttonPushUp);
        Button buttonLunge = view.findViewById(R.id.buttonLunge);
        Button buttonDeadlift = view.findViewById(R.id.buttonDeadlift);
        Button buttonPullUp = view.findViewById(R.id.buttonPullUp);

        buttonSquat.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_squat_detail);
        });
        buttonPushUp.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_pushup_detail);
        });
        buttonLunge.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_lunge_detail);
        });
        buttonDeadlift.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_deadlift_detail);
        });
        buttonPullUp.setOnClickListener(v -> {
            androidx.navigation.NavController navController = androidx.navigation.Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_pullup_detail);
        });

        return view;
    }
}
