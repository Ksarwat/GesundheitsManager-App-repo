package com.example.gesundheitsmanager.ui.notizen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.gesundheitsmanager.R;
import java.util.ArrayList;

public class EditDeleteNotificationsFragment extends Fragment {
    private ArrayList<String> notificationList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private Button buttonDelete, buttonEdit, buttonDeleteAll;
    private int selectedIndex = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_delete_notifications, container, false);
        listView = view.findViewById(R.id.listViewNotifications);
        buttonDelete = view.findViewById(R.id.buttonDeleteNotification);
        buttonEdit = view.findViewById(R.id.buttonEditNotification);
        buttonDeleteAll = view.findViewById(R.id.buttonDeleteAllNotifications);

        // TODO: Load notifications from storage
        // For now, dummy data
        notificationList.add("Medikament: Aspirin, um 08:00, alle 1 Tage, ab 15.09.2025");
        notificationList.add("Arzttermin: Dr. Müller, am 20.09.2025, um 10:00");
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_single_choice, notificationList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        buttonDelete.setEnabled(false);
        buttonEdit.setEnabled(false);

        listView.setOnItemClickListener((parent, v, position, id) -> {
            selectedIndex = position;
            buttonDelete.setEnabled(true);
            buttonEdit.setEnabled(true);
        });

        buttonDelete.setOnClickListener(v -> {
            if (selectedIndex >= 0 && selectedIndex < notificationList.size()) {
                notificationList.remove(selectedIndex);
                adapter.notifyDataSetChanged();
                buttonDelete.setEnabled(false);
                buttonEdit.setEnabled(false);
                selectedIndex = -1;
                Toast.makeText(getContext(), "Benachrichtigung gelöscht", Toast.LENGTH_SHORT).show();
            }
        });

        buttonEdit.setOnClickListener(v -> {
            if (selectedIndex >= 0 && selectedIndex < notificationList.size()) {
                // TODO: Implement edit logic (e.g., open dialog to edit)
                Toast.makeText(getContext(), "Bearbeiten nicht implementiert", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeleteAll.setOnClickListener(v -> {
            notificationList.clear();
            adapter.notifyDataSetChanged();
            buttonDelete.setEnabled(false);
            buttonEdit.setEnabled(false);
            selectedIndex = -1;
            // Remove all reminders from both files
            try {
                requireContext().openFileOutput("medicine_reminders.txt", android.content.Context.MODE_PRIVATE).close();
                requireContext().openFileOutput("doctor_reminders.txt", android.content.Context.MODE_PRIVATE).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(), "Alle Benachrichtigungen gelöscht", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
