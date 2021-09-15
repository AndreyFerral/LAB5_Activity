package com.example.lab5_activity;

import static android.telephony.AvailableNetworkInfo.PRIORITY_LOW;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        /// Конструируем диалоговое окно
        AlertDialog.Builder alert = new AlertDialog.Builder(Activity3.this);

        // Заголовок и текст диалогового окна
        alert.setTitle("Информация о пользователе");
        alert.setMessage("Введите вашу фамилию");

        // Устанавливаем EditText для ввода текста
        final EditText input = new EditText(Activity3.this);
        alert.setView(input);

        // Создаем кнопку "Подтвердить"
        alert.setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                // Возвращаем значение во второе активити
                Intent intent = new Intent();
                intent.putExtra("Surname",input.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

                // Отправляем подсказку
                Toast.makeText(Activity3.this, "Вы перешли во второе активити",
                        Toast.LENGTH_LONG).show();

            }
        });

        alert.setCancelable(false); // Запрещаем закрытие диалогового окна

        // Показываем диалоговое окно
        alert.show();
    }
}