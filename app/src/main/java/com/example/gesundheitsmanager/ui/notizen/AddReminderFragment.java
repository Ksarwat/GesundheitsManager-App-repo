package com.example.gesundheitsmanager.ui.notizen;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.gesundheitsmanager.R;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddReminderFragment extends Fragment {
    private ArrayList<String> reminderList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private static final String FILE_MEDICINE = "medicine_reminders.txt";
    private static final String FILE_DOCTOR = "doctor_reminders.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_reminder, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroupType);
        EditText editTextTitle = view.findViewById(R.id.editTextTitle);
        EditText editTextDate = view.findViewById(R.id.editTextDate);
        EditText editTextTime = view.findViewById(R.id.editTextTime);
        Spinner spinnerFrequencyType = view.findViewById(R.id.spinnerFrequencyType);
        Spinner spinnerFrequencyValue = view.findViewById(R.id.spinnerFrequencyValue);
    // Spinner spinnerFrequencyMinutes entfernt
        EditText editTextStartDate = view.findViewById(R.id.editTextStartDate);
        Button buttonSave = view.findViewById(R.id.buttonSaveReminder);
        ListView listViewReminders = view.findViewById(R.id.listViewReminders);
        Button buttonEditDeleteNotifications = view.findViewById(R.id.buttonEditDeleteNotifications);
        TextView textViewFrequencyLabel = view.findViewById(R.id.textViewFrequencyLabel);
        Button buttonPickDate = view.findViewById(R.id.buttonPickDate);

        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, reminderList);
        listViewReminders.setAdapter(adapter);

        // Lade gespeicherte Erinnerungen aus beiden Dateien
        loadRemindersFromFile(FILE_MEDICINE);
        loadRemindersFromFile(FILE_DOCTOR);
        adapter.notifyDataSetChanged();

        // Spinner für Häufigkeitstyp (Stunden/Tage)
        ArrayAdapter<String> freqTypeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, new String[]{"Stunden", "Tage"});
        freqTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrequencyType.setAdapter(freqTypeAdapter);

        // Spinner für Werte (1-12 für Stunden, 1-7 für Tage)
        ArrayAdapter<String> freqHourAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, getNumberStrings(1, 12));
        freqHourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> freqDayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, getNumberStrings(1, 7));
        freqDayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buttonPickDate.setVisibility(View.GONE);
        // Minuten-Adapter (1-30)
    // Minuten-Adapter entfernt

        spinnerFrequencyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if (position == 0) { // Stunden
                    spinnerFrequencyValue.setAdapter(freqHourAdapter);
                    editTextStartDate.setVisibility(View.GONE);
                } else { // Tage
                    spinnerFrequencyValue.setAdapter(freqDayAdapter);
                    editTextStartDate.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinnerFrequencyValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if (spinnerFrequencyType.getSelectedItemPosition() == 1) {
                    editTextStartDate.setVisibility(View.VISIBLE);
                } else {
                    editTextStartDate.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        // Sichtbarkeit der Felder für Medikamente/Arzttermin
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            boolean isMedical = checkedId == R.id.radio_medical;
            editTextTime.setVisibility(View.VISIBLE); // Arzttermin und Medizin immer Zeitfeld
            spinnerFrequencyType.setVisibility(isMedical ? View.VISIBLE : View.GONE);
            spinnerFrequencyValue.setVisibility(isMedical ? View.VISIBLE : View.GONE);
            // spinnerFrequencyMinutes entfernt
            textViewFrequencyLabel.setVisibility(isMedical ? View.VISIBLE : View.GONE);
            editTextStartDate.setVisibility(isMedical && spinnerFrequencyType.getSelectedItemPosition() == 1 ? View.VISIBLE : View.GONE);
            editTextDate.setVisibility(isMedical ? View.GONE : View.VISIBLE);
            buttonPickDate.setVisibility(isMedical ? View.GONE : View.VISIBLE);
        });
        listViewReminders.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        buttonEditDeleteNotifications.setEnabled(false);
        listViewReminders.setOnItemClickListener((parent, v, position, id) -> {
            listViewReminders.setItemChecked(position, true);
            buttonEditDeleteNotifications.setEnabled(true);
        });
        // Initiale Sichtbarkeit
        boolean isMedical = radioGroup.getCheckedRadioButtonId() == R.id.radio_medical;
        editTextTime.setVisibility(View.VISIBLE);
        spinnerFrequencyType.setVisibility(isMedical ? View.VISIBLE : View.GONE);
        spinnerFrequencyValue.setVisibility(isMedical ? View.VISIBLE : View.GONE);
    // spinnerFrequencyMinutes entfernt
        textViewFrequencyLabel.setVisibility(isMedical ? View.VISIBLE : View.GONE);
        editTextStartDate.setVisibility(isMedical && spinnerFrequencyType.getSelectedItemPosition() == 1 ? View.VISIBLE : View.GONE);
        editTextDate.setVisibility(isMedical ? View.GONE : View.VISIBLE);

        // Set default date to today
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
        String todayDate = sdf.format(new java.util.Date());
        editTextDate.setText(todayDate);

        // Open calendar picker on button click
        buttonPickDate.setOnClickListener(v -> {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            String currentDate = editTextDate.getText().toString();
            try {
                java.util.Date parsedDate = sdf.parse(currentDate);
                calendar.setTime(parsedDate);
            } catch (Exception ignored) {}
            int year = calendar.get(java.util.Calendar.YEAR);
            int month = calendar.get(java.util.Calendar.MONTH);
            int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
            new android.app.DatePickerDialog(requireContext(), (view1, y, m, d) -> {
                java.util.Calendar picked = java.util.Calendar.getInstance();
                picked.set(y, m, d);
                editTextDate.setText(sdf.format(picked.getTime()));
            }, year, month, day).show();
        });

        buttonEditDeleteNotifications.setOnClickListener(v -> {
            reminderList.clear();
            adapter.notifyDataSetChanged();
            buttonEditDeleteNotifications.setEnabled(false);
            try {
            // Remove all reminders from both files
            requireContext().openFileOutput("medicine_reminders.txt", android.content.Context.MODE_PRIVATE).close();
                requireContext().openFileOutput("doctor_reminders.txt", android.content.Context.MODE_PRIVATE).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Cancel all scheduled notifications
            cancelAllScheduledNotifications(requireContext());
            Toast.makeText(getContext(), "Alle Benachrichtigungen gelöscht", Toast.LENGTH_SHORT).show();
        });


        ReminderNotificationHelper.createNotificationChannel(requireContext());

        buttonSave.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            String type = checkedId == R.id.radio_medical ? "Medikament" : "Arzttermin";
            String title = editTextTitle.getText().toString();
            String date = editTextDate.getText().toString();
            String time = editTextTime.getText().toString();
            String freqType = spinnerFrequencyType.getSelectedItem() != null ? spinnerFrequencyType.getSelectedItem().toString() : "";
            String freqValue = spinnerFrequencyValue.getSelectedItem() != null ? spinnerFrequencyValue.getSelectedItem().toString() : "";
            // freqMinutes entfernt
            String startDate = editTextStartDate.getText().toString();
            if (title.isEmpty() || (type.equals("Arzttermin") && date.isEmpty())) {
                Toast.makeText(getContext(), "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
                return;
            }
            if (type.equals("Medikament")) {
                if (time.isEmpty() || freqType.isEmpty() || freqValue.isEmpty() || (freqType.equals("Tage") && startDate.isEmpty())) {
                    Toast.makeText(getContext(), "Bitte alle Felder ausfüllen", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (type.equals("Arzttermin") && time.isEmpty()) {
                time = "08:00";
            }
            StringBuilder entry = new StringBuilder();
            entry.append(type).append(": ").append(title);
            if (type.equals("Arzttermin")) {
                entry.append(", am ").append(date).append(", um ").append(time);
            } else {
                entry.append(", um ").append(time).append(", alle ").append(freqValue).append(" ").append(freqType.toLowerCase());
                if (freqType.equals("Tage")) {
                    entry.append(", ab ").append(startDate);
                }
            }
            reminderList.add(entry.toString());
            adapter.notifyDataSetChanged();
            saveReminderToFile(type, title, date, time, freqType, freqValue, "", startDate);
            // Benachrichtigung planen
            boolean validDoctorFormat = true;
            if (type.equals("Arzttermin")) {
                // Validate date format dd.MM.yyyy
                validDoctorFormat = date.matches("\\d{2}\\.\\d{2}\\.\\d{4}") && time.matches("\\d{2}:\\d{2}");
                if (!validDoctorFormat) {
                    Toast.makeText(getContext(), "Ungültiges Format! Datum: TT.MM.JJJJ, Uhrzeit: HH:MM", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            long triggerMillis = parseReminderTime(type, date, time, startDate);
            if (type.equals("Arzttermin") && triggerMillis <= 0) {
                Toast.makeText(getContext(), "Ungültiges Datum oder Uhrzeit für Arzttermin! Bitte korrekt eingeben (z.B. 09.09.2025 und 08:00)", Toast.LENGTH_LONG).show();
                return;
            }
            if (triggerMillis > 0) {
                String msg = type.equals("Medikament") ? "Zeit für dein Medikament: " + title : "Arzttermin: " + title;
                ReminderNotificationHelper.scheduleNotification(requireContext(), triggerMillis, "Erinnerung", msg);
            }
            editTextTitle.setText("");
            editTextDate.setText("");
            editTextTime.setText("");
            editTextStartDate.setText("");
            spinnerFrequencyType.setSelection(0);
            spinnerFrequencyValue.setSelection(0);
            // spinnerFrequencyMinutes entfernt
            Toast.makeText(getContext(), "Erinnerung gespeichert", Toast.LENGTH_SHORT).show();
        });

        // Replace manual time entry for Arzttermin with a TimePickerDialog (spinner style)
        editTextTime.setFocusable(false);
        editTextTime.setClickable(true);
        editTextTime.setOnClickListener(v -> {
            java.util.Calendar now = java.util.Calendar.getInstance();
            int hour = now.get(java.util.Calendar.HOUR_OF_DAY);
            int minute = now.get(java.util.Calendar.MINUTE);
            android.app.TimePickerDialog timePicker = new android.app.TimePickerDialog(requireContext(), android.app.TimePickerDialog.THEME_HOLO_LIGHT,
                (view1, selectedHour, selectedMinute) -> {
                    String formatted = String.format("%02d:%02d", selectedHour, selectedMinute);
                    editTextTime.setText(formatted);
                }, hour, minute, true);
            timePicker.show();
        });

        return view;
    }
    private void cancelAllScheduledNotifications(Context context) {
        try {
            android.app.AlarmManager alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            for (int i = 0; i < 100; i++) { // Try to cancel up to 100 notifications
                android.content.Intent intent = new android.content.Intent(context, ReminderNotificationHelper.ReminderReceiver.class);
                android.app.PendingIntent pendingIntent = android.app.PendingIntent.getBroadcast(context, i, intent, android.app.PendingIntent.FLAG_NO_CREATE | android.app.PendingIntent.FLAG_IMMUTABLE);
                if (pendingIntent != null && alarmManager != null) {
                    alarmManager.cancel(pendingIntent);
                    pendingIntent.cancel();
                }
            } } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] getNumberStrings(int from, int to) {
        String[] arr = new String[to - from + 1];
        for (int i = from; i <= to; i++) {
            arr[i - from] = String.valueOf(i);
        }
        return arr;
    }

    private void saveReminderToFile(String type, String title, String date, String time, String freqType, String freqValue, String freqMinutes, String startDate) {
        String filename = type.equals("Medikament") ? FILE_MEDICINE : FILE_DOCTOR;
        StringBuilder sb = new StringBuilder();
        sb.append("Titel: ").append(title);
        if (type.equals("Arzttermin")) {
            sb.append(", Datum: ").append(date).append(", Zeit: ").append(time);
        } else {
            sb.append(", Uhrzeit: ").append(time)
              .append(", Intervall: ").append(freqValue).append(" ").append(freqType);
            if (freqType.equals("Tage")) {
                sb.append(", Start: ").append(startDate);
            }
        }
        sb.append("\n");
        try (java.io.FileOutputStream fos = requireContext().openFileOutput(filename, android.content.Context.MODE_APPEND)) {
            fos.write(sb.toString().getBytes());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRemindersFromFile(String filename) {
        try (FileInputStream fis = requireContext().openFileInput(filename);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    reminderList.add(line);
                }
            }
        } catch (IOException e) {
            // Datei existiert evtl. noch nicht, ignoriere
        }
    }

    private long parseReminderTime(String type, String date, String time, String startDate) {
        // Versucht, das nächste Erinnerungsdatum als Zeitstempel zu berechnen
        try {
            String dateTimeStr;
            if (type.equals("Arzttermin")) {
                if (date == null || date.isEmpty() || time == null || time.isEmpty()) {
                    return -1;
                }
                dateTimeStr = date + " " + time;
            } else {
                // Für Medikamente: Startdatum + Zeit, sonst heute + Zeit
                dateTimeStr = (startDate.isEmpty() ? new java.text.SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date()) : startDate) + " " + time;
            }
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm");
            java.util.Date dt = sdf.parse(dateTimeStr);
            long millis = dt.getTime();
            android.util.Log.i("ReminderParse", "Parsed dateTimeStr: '" + dateTimeStr + "' -> millis: " + millis + ", now: " + System.currentTimeMillis());
            android.widget.Toast.makeText(requireContext(), "Parsed: " + dateTimeStr + " (" + millis + ")", android.widget.Toast.LENGTH_SHORT).show();
            return millis;
        } catch (Exception e) {
            android.util.Log.e("ReminderParse", "Fehler beim Parsen von Datum/Uhrzeit: " + e.getMessage());
            android.widget.Toast.makeText(requireContext(), "Fehler beim Parsen: " + e.getMessage(), android.widget.Toast.LENGTH_LONG).show();
            return -1;
        }
    }
}

// ReminderNotificationHelper als eigene Klasse außerhalb von AddReminderFragment
class ReminderNotificationHelper {
    private static final String CHANNEL_ID = "reminder_channel";
    private static final int NOTIFICATION_ID = 1001;

    public static void scheduleNotification(Context context, long triggerAtMillis, String title, String message) {
        if (context == null) {
            android.util.Log.e("ReminderNotification", "Context is null, cannot schedule notification.");
            return;
        }
        // Check for SCHEDULE_EXACT_ALARM permission on Android 12+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                intent.setData(android.net.Uri.parse("package:" + context.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                android.widget.Toast.makeText(context, "Bitte erlauben Sie 'Genaues Wecken' in den Einstellungen und versuchen Sie es erneut.", android.widget.Toast.LENGTH_LONG).show();
                android.util.Log.e("ReminderNotification", "App does not have permission to schedule exact alarms. Prompting user.");
                return;
            }
        }
        // Check if notifications are enabled for this app
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!notificationManager.areNotificationsEnabled()) {
                android.widget.Toast.makeText(context, "Benachrichtigungen sind deaktiviert. Bitte aktivieren Sie sie in den Einstellungen.", android.widget.Toast.LENGTH_LONG).show();
                Intent intent = new Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                android.util.Log.e("ReminderNotification", "Notifications are disabled. Prompting user to enable.");
                return;
            }
        }
        // Do not overwrite triggerAtMillis! Use the value passed in.
        if (triggerAtMillis <= System.currentTimeMillis()) {
            String msg = "Trigger time is not in the future: " + triggerAtMillis + " current time " + System.currentTimeMillis();
            android.util.Log.e("ReminderNotification", msg);
            android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_LONG).show();
            triggerAtMillis = System.currentTimeMillis() + 60000; // Set to 1 minute in the future as fallback
        }
        try {
            Intent intent = new Intent(context, ReminderReceiver.class);
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
                android.util.Log.i("ReminderNotification", "Notification scheduled for: " + triggerAtMillis);
            } else {
                android.util.Log.e("ReminderNotification", "AlarmManager is null, cannot schedule notification.");
            }
        } catch (Exception e) {
            android.util.Log.e("ReminderNotification", "Exception scheduling notification: " + e.getMessage());
        }
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Erinnerungen", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Benachrichtigungen für Erinnerungen");
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public static class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        android.util.Log.i("ReminderReceiver", "onReceive called: notification should be shown");
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
    }
}
