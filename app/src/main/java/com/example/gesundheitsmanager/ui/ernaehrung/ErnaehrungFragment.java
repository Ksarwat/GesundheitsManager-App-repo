package com.example.gesundheitsmanager.ui.ernaehrung;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.navigation.Navigation;
import com.example.gesundheitsmanager.R;

public class ErnaehrungFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ernaehrung, container, false);

        Button buttonBanane = view.findViewById(R.id.button_banane);
        Button buttonApfel = view.findViewById(R.id.button_apfel);
        Button buttonWassermelone = view.findViewById(R.id.button_wassermelone);
        Button buttonOrange = view.findViewById(R.id.button_orange);
        Button buttonErdbeere = view.findViewById(R.id.button_erdbeere);

        buttonBanane.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_banane));
        buttonApfel.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_apfel));
        buttonWassermelone.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_wassermelone));
        buttonOrange.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_orange));
        buttonErdbeere.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_erdbeere));

        return view;
    }
}
