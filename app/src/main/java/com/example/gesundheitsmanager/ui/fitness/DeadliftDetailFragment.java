package com.example.gesundheitsmanager.ui.fitness;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gesundheitsmanager.R;

public class DeadliftDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deadlift_detail, container, false);
        TextView textView = view.findViewById(R.id.textDeadliftDetail);
        textView.setText("Zielmuskeln: Quadrizeps, Gluteus Maximus, Hamstrings, Erector Spinae (Rückenstrecker), Trapezius (Kapuzenmuskel), Latissimus Dorsi (Breiter Rückenmuskel).\n\nAusführung:\n\n1) Startposition: Stehe mit den Füßen hüftbreit auseinander, die Langhantel liegt vor dir über dem Mittelfuß. Beuge dich an der Hüfte und den Knien, um die Hantel mit einem Obergriff (oder Kreuzgriff) zu fassen, die Hände etwas breiter als schulterbreit. Der Rücken ist gerade, die Brust ist oben.\n\n2) Bewegung: Hebe die Hantel vom Boden, indem du gleichzeitig Knie und Hüfte streckst. Halte die Hantel nah am Körper und den Rücken gerade. Ziehe die Schultern leicht nach hinten, wenn du aufrecht stehst.\n\n3) Endposition: Senke die Hantel kontrolliert wieder zum Boden, indem du die Bewegung umkehrst, zuerst die Hüfte beugst und dann die Knie.");
        return view;
    }
}
