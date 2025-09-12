package com.example.gesundheitsmanager.ui.fitness;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gesundheitsmanager.R;

public class SquatDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_squat_detail, container, false);
        TextView textView = view.findViewById(R.id.textSquatDetail);
        textView.setText("Zielmuskeln: Quadrizeps (Oberschenkelvorderseite), Gluteus Maximus (Großer Gesäßmuskel), Adduktoren (Adductors), Hamstrings (Oberschenkelrückseite).\n\nAusführung:\n1) Startposition: Stehe aufrecht, Füße schulterbreit auseinander, Zehen leicht nach außen gedreht. Arme können nach vorne gestreckt werden, um das Gleichgewicht zu halten.\n2) Bewegung: Beuge Knie und Hüfte, als ob du dich auf einen Stuhl setzen würdest. Halte den Rücken gerade und die Brust hoch. Gehe so tief wie möglich, idealerweise bis die Oberschenkel parallel zum Boden sind.\n3) Endposition: Drücke dich durch die Fersen zurück in die Startposition.");
        return view;
    }
}
