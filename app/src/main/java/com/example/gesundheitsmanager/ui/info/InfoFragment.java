package com.example.gesundheitsmanager.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.gesundheitsmanager.R;
import androidx.navigation.Navigation;

public class InfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        Button buttonWeiter = view.findViewById(R.id.buttonWeiter);
        buttonWeiter.setOnClickListener(v -> {
            // Navigate to home fragment using Navigation Component
            Navigation.findNavController(v).navigate(R.id.action_infoFragment_to_homeFragment);
        });
        return view;
    }
}
