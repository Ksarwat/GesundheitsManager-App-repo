package com.example.gesundheitsmanager.ui.medikamente;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.navigation.Navigation;
import com.example.gesundheitsmanager.R;

public class MedikamenteFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medikamente, container, false);

        Button buttonAspirin = view.findViewById(R.id.button_aspirin);
        Button buttonIbuprofen = view.findViewById(R.id.button_ibuprofen);
        Button buttonParacetamol = view.findViewById(R.id.button_paracetamol);
        Button buttonAmoxicillin = view.findViewById(R.id.button_amoxicillin);
        Button buttonLisinopril = view.findViewById(R.id.button_lisinopril);

        buttonAspirin.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_aspirin));
        buttonIbuprofen.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_ibuprofen));
        buttonParacetamol.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_paracetamol));
        buttonAmoxicillin.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_amoxicillin));
        buttonLisinopril.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_lisinopril));

        return view;
    }
}
