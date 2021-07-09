package com.example.physics;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class TestFormula extends AppCompatActivity {
    public int randomAnswer, randomPlace;
    public int a1, a2, a3, a4;
    Array array = new Array();
    Random random = new Random();
    public int count = 0; //счетчик правильных ответов

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_formula);

        final TextView question = (TextView)findViewById(R.id.question);
        final TextView answer1 = (TextView)findViewById(R.id.answer1);
        final TextView answer2 = (TextView)findViewById(R.id.answer2);
        final TextView answer3 = (TextView)findViewById(R.id.answer3);
        final TextView answer4 = (TextView)findViewById(R.id.answer4);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //_____________________________
        //Вызов диалогового окна в начале приложения
        dialog = new Dialog(this); //создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.dialogend); //путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой назад

        //Кнопка которая закрывает диалоговое окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try {
                    Intent intent = new Intent(TestFormula.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss(); //Закрываем диалоговое окно
                //обрабатываем нажатие кнопки - конец
            }
        });
        //Кнопка которая закрывает диалоговое окно - конец
        //_____________________________

        //Кнопка для перехода - начало
        ImageView img_back = (ImageView) findViewById(R.id.img_back_test);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(TestFormula.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //Кнопка для перехода - конец

        //Массив для прогресса игры
        final int[] progerss = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
        };

        //Подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(TestFormula.this, R.anim.alpha);

        randomAnswer = random.nextInt(22);
        a1 = random.nextInt(22);
        while(a1 == randomAnswer){
            a1 = random.nextInt(22);
        }
        answer1.setText(array.formulaTest[a1]);
        a2 = random.nextInt(22);
        while(a2 == randomAnswer || a2 == a1){
            a2 = random.nextInt(22);
        }
        answer2.setText(array.formulaTest[a2]);
        a3 = random.nextInt(22);
        while(a3 == randomAnswer || a3 == a1 || a3 == a2){
            a3 = random.nextInt(22);
        }
        answer3.setText(array.formulaTest[a3]);
        a4 = random.nextInt(22);
        while(a4 == randomAnswer || a4 == a1 || a4 == a2 || a4 == a3){
            a4 = random.nextInt(22);
        }
        answer4.setText(array.formulaTest[a4]);

        randomPlace = random.nextInt(4);
        question.setText(array.textTest[randomAnswer]);
        switch (randomPlace){
            case 0:
                answer1.setText(array.formulaTest[randomAnswer]);
                break;
            case 1:
                answer2.setText(array.formulaTest[randomAnswer]);
                break;
            case 2:
                answer3.setText(array.formulaTest[randomAnswer]);
                break;
            case 3:
                answer4.setText(array.formulaTest[randomAnswer]);
                break;
        }
        //обрабатываем нажатие на кнопку - начало
        answer1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //если коснулись кнопку
                    answer2.setEnabled(false);
                    answer3.setEnabled(false);
                    answer4.setEnabled(false);
                    if(randomPlace == 0){
                        answer1.setBackground(getDrawable(R.drawable.rigth_answer_rec));
                    }else{
                        answer1.setBackground(getDrawable(R.drawable.wrong_answer_rec));
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //если отпустил кнопку
                    if(randomPlace == 0){
                        if(count < 10){
                            count++;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 10; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }else{
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count -= 2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 9; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }
                    if(count == 10){
                        //Вывод из уровня
                        dialog.show(); //показать диалоговое окно
                    }else {
                        randomAnswer = random.nextInt(22);
                        a1 = random.nextInt(22);
                        while(a1 == randomAnswer){
                            a1 = random.nextInt(22);
                        }
                        answer1.setText(array.formulaTest[a1]);
                        a2 = random.nextInt(22);
                        while(a2 == randomAnswer || a2 == a1){
                            a2 = random.nextInt(22);
                        }
                        answer2.setText(array.formulaTest[a2]);
                        a3 = random.nextInt(22);
                        while(a3 == randomAnswer || a3 == a1 || a3 == a2){
                            a3 = random.nextInt(22);
                        }
                        answer3.setText(array.formulaTest[a3]);
                        a4 = random.nextInt(22);
                        while(a4 == randomAnswer || a4 == a1 || a4 == a2 || a4 == a3){
                            a4 = random.nextInt(22);
                        }
                        answer4.setText(array.formulaTest[a4]);
                        randomPlace = random.nextInt(4);
                        question.setText(array.textTest[randomAnswer]);
                        switch (randomPlace){
                            case 0:
                                answer1.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 1:
                                answer2.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 2:
                                answer3.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 3:
                                answer4.setText(array.formulaTest[randomAnswer]);
                                break;
                        }
                        answer1.setBackground(getDrawable(R.drawable.menu_rec));
                        answer2.setEnabled(true);
                        answer3.setEnabled(true);
                        answer4.setEnabled(true);
                    }
                }
                return true;
            }
        });
        //обрабатываем нажатие на кнопку - конец

        //обрабатываем нажатие на кнопку - начало
        answer2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //если коснулись кнопку
                    answer1.setEnabled(false);
                    answer3.setEnabled(false);
                    answer4.setEnabled(false);
                    if(randomPlace == 1){
                        answer2.setBackground(getDrawable(R.drawable.rigth_answer_rec));
                    }else{
                        //answer1.setBackgroundColor(getResources().getColor(R.color.red));
                        answer2.setBackground(getDrawable(R.drawable.wrong_answer_rec));
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //если отпустил кнопку
                    if(randomPlace == 1){
                        if(count < 10){
                            count++;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 10; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }else{
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count -= 2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 9; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }
                    if(count == 10){
                        //Вывод из уровня
                        dialog.show(); //показать диалоговое окно
                    }else {
                        randomAnswer = random.nextInt(22);
                        a1 = random.nextInt(22);
                        while(a1 == randomAnswer){
                            a1 = random.nextInt(22);
                        }
                        answer1.setText(array.formulaTest[a1]);
                        a2 = random.nextInt(22);
                        while(a2 == randomAnswer || a2 == a1){
                            a2 = random.nextInt(22);
                        }
                        answer2.setText(array.formulaTest[a2]);
                        a3 = random.nextInt(22);
                        while(a3 == randomAnswer || a3 == a1 || a3 == a2){
                            a3 = random.nextInt(22);
                        }
                        answer3.setText(array.formulaTest[a3]);
                        a4 = random.nextInt(22);
                        while(a4 == randomAnswer || a4 == a1 || a4 == a2 || a4 == a3){
                            a4 = random.nextInt(22);
                        }
                        answer4.setText(array.formulaTest[a4]);
                        randomPlace = random.nextInt(4);
                        question.setText(array.textTest[randomAnswer]);
                        switch (randomPlace){
                            case 0:
                                answer1.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 1:
                                answer2.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 2:
                                answer3.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 3:
                                answer4.setText(array.formulaTest[randomAnswer]);
                                break;
                        }
                        answer2.setBackground(getDrawable(R.drawable.menu_rec));
                        answer1.setEnabled(true);
                        answer3.setEnabled(true);
                        answer4.setEnabled(true);
                    }
                }
                return true;
            }
        });
        //обрабатываем нажатие на кнопку - конец

        //обрабатываем нажатие на кнопку - начало
        answer3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //если коснулись кнопку
                    answer1.setEnabled(false);
                    answer2.setEnabled(false);
                    answer4.setEnabled(false);
                    if(randomPlace == 2){
                        answer3.setBackground(getDrawable(R.drawable.rigth_answer_rec));
                    }else{
                        //answer1.setBackgroundColor(getResources().getColor(R.color.red));
                        answer3.setBackground(getDrawable(R.drawable.wrong_answer_rec));
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //если отпустил кнопку
                    if(randomPlace == 2){
                        if(count < 10){
                            count++;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 10; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }else{
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count -= 2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 9; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }
                    if(count == 10){
                        //Вывод из уровня
                        dialog.show(); //показать диалоговое окно
                    }else {
                        randomAnswer = random.nextInt(22);
                        a1 = random.nextInt(22);
                        while(a1 == randomAnswer){
                            a1 = random.nextInt(22);
                        }
                        answer1.setText(array.formulaTest[a1]);
                        a2 = random.nextInt(22);
                        while(a2 == randomAnswer || a2 == a1){
                            a2 = random.nextInt(22);
                        }
                        answer2.setText(array.formulaTest[a2]);
                        a3 = random.nextInt(22);
                        while(a3 == randomAnswer || a3 == a1 || a3 == a2){
                            a3 = random.nextInt(22);
                        }
                        answer3.setText(array.formulaTest[a3]);
                        a4 = random.nextInt(22);
                        while(a4 == randomAnswer || a4 == a1 || a4 == a2 || a4 == a3){
                            a4 = random.nextInt(22);
                        }
                        answer4.setText(array.formulaTest[a4]);
                        randomPlace = random.nextInt(4);
                        question.setText(array.textTest[randomAnswer]);
                        switch (randomPlace){
                            case 0:
                                answer1.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 1:
                                answer2.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 2:
                                answer3.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 3:
                                answer4.setText(array.formulaTest[randomAnswer]);
                                break;
                        }
                        answer3.setBackground(getDrawable(R.drawable.menu_rec));
                        answer1.setEnabled(true);
                        answer2.setEnabled(true);
                        answer4.setEnabled(true);
                    }
                }
                return true;
            }
        });
        //обрабатываем нажатие на кнопку - конец

        //обрабатываем нажатие на кнопку - начало
        answer4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //если коснулись кнопку
                    answer1.setEnabled(false);
                    answer2.setEnabled(false);
                    answer3.setEnabled(false);
                    if(randomPlace == 3){
                        answer4.setBackground(getDrawable(R.drawable.rigth_answer_rec));
                    }else{
                        //answer1.setBackgroundColor(getResources().getColor(R.color.red));
                        answer4.setBackground(getDrawable(R.drawable.wrong_answer_rec));
                    }
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    //если отпустил кнопку
                    if(randomPlace == 3){
                        if(count < 10){
                            count++;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 10; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }else{
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            }else{
                                count -= 2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for(int i = 0; i < 9; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Закрашиваем прогресс зеленым - начало
                        for (int i = 0; i < count; i++){
                            TextView tv = findViewById(progerss[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашиваем прогресс зеленым - конец
                    }
                    if(count == 10){
                        //Вывод из уровня
                        dialog.show(); //показать диалоговое окно
                    }else {
                        randomAnswer = random.nextInt(22);
                        a1 = random.nextInt(22);
                        while(a1 == randomAnswer){
                            a1 = random.nextInt(22);
                        }
                        answer1.setText(array.formulaTest[a1]);
                        a2 = random.nextInt(22);
                        while(a2 == randomAnswer || a2 == a1){
                            a2 = random.nextInt(22);
                        }
                        answer2.setText(array.formulaTest[a2]);
                        a3 = random.nextInt(22);
                        while(a3 == randomAnswer || a3 == a1 || a3 == a2){
                            a3 = random.nextInt(22);
                        }
                        answer3.setText(array.formulaTest[a3]);
                        a4 = random.nextInt(22);
                        while(a4 == randomAnswer || a4 == a1 || a4 == a2 || a4 == a3){
                            a4 = random.nextInt(22);
                        }
                        answer4.setText(array.formulaTest[a4]);
                        randomPlace = random.nextInt(4);
                        question.setText(array.textTest[randomAnswer]);
                        switch (randomPlace){
                            case 0:
                                answer1.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 1:
                                answer2.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 2:
                                answer3.setText(array.formulaTest[randomAnswer]);
                                break;
                            case 3:
                                answer4.setText(array.formulaTest[randomAnswer]);
                                break;
                        }
                        answer4.setBackground(getDrawable(R.drawable.menu_rec));
                        answer1.setEnabled(true);
                        answer2.setEnabled(true);
                        answer3.setEnabled(true);
                    }
                }
                return true;
            }
        });
        //обрабатываем нажатие на кнопку - конец
    }


    //Системная кнопка "назад" начало
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(TestFormula.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Системная кнопка "назад" конец
}
