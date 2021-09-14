package com.example.lab5_activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setTitle("Выберите один из вариантов");
                a_builder.setMessage("Ваше име - ")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int whichButton) {
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int whichButton) {
                                dialogInterface.cancel();
                            }
                        });

                a_builder.show();
            }
        });

        // Обрабатываем нажатие на кнопку
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаем диалоговое окно
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                // Заголовок и текст активити
                alert.setTitle("Информация о пользователе");
                alert.setMessage("Введите ваше име");

                // Устанавливаем EditText для ввода текста
                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);

                // Создаем кнопку "Подтвердить"
                alert.setPositiveButton("Подтвердить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();

                        // Do something with value!

                        // Переход во второе активити
                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        startActivity(intent);


                    }
                });

                // Показываем диалоговое окно
                alert.show();
            }
        });

    }
}