package com.example.lab5_activity;

import static android.telephony.AvailableNetworkInfo.PRIORITY_LOW;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final int NOYIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /// Конструируем диалоговое окно
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        // Заголовок и текст диалогового окна
        alert.setTitle("Информация о пользователе");
        alert.setMessage("Введите ваше име");

        // Устанавливаем EditText для ввода текста
        final EditText input = new EditText(MainActivity.this);
        alert.setView(input);

        // Создаем кнопку "Подтвердить"
        alert.setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                // Переход во второе активити с передачей имени
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("name", input.getText().toString());
                startActivity(intent);

                // Отправляем подсказку
                Toast.makeText(MainActivity.this, "Вы перешли во второе активити",
                        Toast.LENGTH_LONG).show();

                // работа с уведомлением
                notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // флаги для работы с уведомлением
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT); // отложенный интент

                // конструируем уведомление
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(false)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Приветствие пользователя")
                        .setContentText("Привет, " + input.getText().toString())
                        .setPriority(PRIORITY_LOW);

                createChannelIfNeeded(notificationManager);
                notificationManager.notify(NOYIFY_ID, notificationBuilder.build());

            }
        });

        alert.setCancelable(false); // Запрещаем закрытие диалогового окна

        // Показываем диалоговое окно
        alert.show();

    }

    // Проверка для android oreo и выше
    public static void createChannelIfNeeded(NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }

    }
}