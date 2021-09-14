package com.example.lab5_activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
                Toast.makeText(MainActivity.this, "Вы перешли во второе активити",
                        Toast.LENGTH_LONG).show();
            }
        });

        alert.setCancelable(false); // Запрещаем закрытие диалогового окна

        // Показываем диалоговое окно
        alert.show();

    }
}