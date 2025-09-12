package com.example.gesundheitsmanager.ui.fitness;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gesundheitsmanager.R;

public class PushUpDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pushup_detail, container, false);
        TextView textView = view.findViewById(R.id.textPushUpDetail);
        textView.setText("Zielmuskeln: Pectoralis Major (Großer Brustmuskel), Trizeps (Armstrecker), Deltoideus Anterior (Vorderer Deltamuskel).\n\nAusführung:\n1) Startposition: Gehe in die Plank-Position, Hände etwas breiter als schulterbreit unter den Schultern platziert. Der Körper bildet eine gerade Linie von Kopf bis zu den Fersen.\n2) Bewegung: Senke den Körper, indem du die Ellenbogen beugst, bis die Brust fast den Boden berührt. Halte den Rumpf angespannt.\n3) Endposition: Drücke dich kraftvoll zurück in die Startposition.");
        return view;
    }
}
