package com.example.gesundheitsmanager.ui.notizen;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.navigation.Navigation;
import com.example.gesundheitsmanager.R;

public class NotizenFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notizen, container, false);
        Button buttonAddReminder = view.findViewById(R.id.button_add_reminder);
        buttonAddReminder.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.fragment_add_reminder));
        return view;
    }
}
