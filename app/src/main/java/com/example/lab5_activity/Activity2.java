package com.example.lab5_activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button button3 = (Button) findViewById(R.id.button3);

        // Получаем данные с первого активити
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        // Конструируем диалоговое окно
        AlertDialog.Builder a_builder = new AlertDialog.Builder(Activity2.this);

        // Заголовок и текст диалогового окна
        a_builder.setTitle("Подтверждение введенного имени");
        a_builder.setMessage("Ваше име - " + name)
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        //dialogInterface.cancel(); // закрываем диалоговое окно

                        // Переход в третье активити с получением информации
                        Intent intent = new Intent(Activity2.this, Activity3.class);
                        startActivityForResult(intent, 1);

                        // Отправляем подсказку
                        Toast.makeText(Activity2.this, "Вы перешли в третье активити",
                                Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whichButton) {
                        // Возвращаемся в первое активити
                        Intent intent = new Intent(Activity2.this, MainActivity.class);
                        startActivity(intent);

                        // Отправляем подсказку
                        Toast.makeText(Activity2.this, "Вы вернулись в первое активити",
                                Toast.LENGTH_LONG).show();
                    }
                });

        // Показываем диалоговое окно
        a_builder.show();

        // Обрабатываем нажатие на кнопку
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Показываем диалоговое окно
                a_builder.show();
                // Отправляем подсказку
                Toast.makeText(Activity2.this, "Вы вызвали диалоговое сообщение на втором активити",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String Surname = data.getStringExtra("Surname");

        // Отправляем подсказку
        Toast.makeText(Activity2.this, "Ваша фамилия - "+ Surname,
                Toast.LENGTH_LONG).show();
    }

}