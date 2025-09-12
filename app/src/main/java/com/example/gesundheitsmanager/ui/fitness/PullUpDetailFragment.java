package com.example.gesundheitsmanager.ui.fitness;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gesundheitsmanager.R;

public class PullUpDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pullup_detail, container, false);
        TextView textView = view.findViewById(R.id.textPullUpDetail);
        textView.setText("Zielmuskeln: Latissimus Dorsi (Breiter Rückenmuskel), Bizeps (Armbeuger), Trapezius (Kapuzenmuskel), Deltoideus Posterior (Hinterer Deltamuskel).\n\nAusführung:\n\n1) Startposition: Hänge dich mit einem Obergriff an eine Klimmzugstange, die Hände etwas breiter als schulterbreit auseinander. Die Arme sind vollständig gestreckt.\n\n2) Bewegung: Ziehe dich nach oben, indem du die Ellenbogen beugst und die Schulterblätter zusammenziehst. Versuche, das Kinn über die Stange zu bringen.\n\n3) Endposition: Senke dich kontrolliert wieder in die Startposition ab, bis die Arme vollständig gestreckt sind.");
        return view;
    }
}
