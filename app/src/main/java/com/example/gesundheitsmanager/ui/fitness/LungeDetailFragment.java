package com.example.gesundheitsmanager.ui.fitness;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gesundheitsmanager.R;

public class LungeDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lunge_detail, container, false);
    TextView textView = view.findViewById(R.id.textLungeDetail);
    textView.setText("Zielmuskeln: Quadrizeps, Gluteus Maximus, Hamstrings, Adduktoren.\n\nAusführung:\n\n1) Startposition: Stehe aufrecht, Füße hüftbreit auseinander.\n\n2) Bewegung: Mache einen großen Schritt nach vorne mit einem Bein. Senke die Hüfte, bis das vordere Knie über dem Knöchel ist und das hintere Knie fast den Boden berührt. Der vordere Oberschenkel sollte parallel zum Boden sein.\n\n3) Endposition: Drücke dich mit dem vorderen Fuß zurück in die Startposition. Wechsle das Bein.");
        return view;
    }
}
