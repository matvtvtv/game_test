package com.example.myapplication;

import static com.example.myapplication.LetterStatus.GRAY;
import static com.example.myapplication.LetterStatus.GREEN;
import static com.example.myapplication.LetterStatus.YELLOW;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.AttemptResult;
import com.example.myapplication.GameLogic;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLetters;
    private List<TextView> letterCells; // Список для всех клеток
    private int currentCellIndex = 0;
    private int currentAttemptIndex = 0;
    private final int WORD_LENGTH = 4;
    private final int MAX_ATTEMPTS = 6;

    private GameLogic gameLogic; // Логика игры

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация логики игры (6 попыток)
        gameLogic = new GameLogic(MAX_ATTEMPTS);
        // Пример: загадано слово "ВАГОН"
        gameLogic.startNewGame("АААА");

        gridLetters = findViewById(R.id.gridLetters);
        letterCells = new ArrayList<>();
        // Создаём 6 * 5 = 30 клеток для ввода
        for (int i = 0; i < MAX_ATTEMPTS * WORD_LENGTH; i++) {
            TextView cell = new TextView(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenHeight = displayMetrics.heightPixels;
            int screenWidth = displayMetrics.widthPixels;

            int cubeHeight = (int) ((screenHeight * 0.7f) / MAX_ATTEMPTS  - (screenWidth/40));
            int cubeWidth = (int) (screenWidth / WORD_LENGTH - (screenWidth/30));

            int cubeSize = Math.min(cubeWidth, cubeHeight);

            params.width = cubeSize;
            params.height = cubeSize;
            params.setMargins(8, 8, 8, 8);
            cell.setLayoutParams(params);
            cell.setGravity(Gravity.CENTER);
            cell.setTextSize(24);
            // Устанавливаем фон для клетки (создайте drawable cell_background.xml)
            cell.setBackgroundResource(R.drawable.cell_background);
            gridLetters.addView(cell);
            letterCells.add(cell);
        }

        // Обработчик кликов для кнопок клавиатуры (пример для кнопок "А", "Б", "В", "Г")
        Button btnA = findViewById(R.id.btnA);
        Button btnБ = findViewById(R.id.btnБ);
        Button btnВ = findViewById(R.id.btnВ);
        Button btnГ = findViewById(R.id.btnГ);
        GridLayout layout = findViewById(R.id.gridLetters);

        layout.setColumnCount(WORD_LENGTH);

        View.OnClickListener letterClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCellIndex < (currentAttemptIndex + 1) * WORD_LENGTH) {
                    Button btn = (Button) v;
                    String letter = btn.getText().toString();
                    letterCells.get(currentCellIndex).setText(letter);
                    currentCellIndex++;
                }
            }
        };

        btnA.setOnClickListener(letterClickListener);
        btnБ.setOnClickListener(letterClickListener);
        btnВ.setOnClickListener(letterClickListener);
        btnГ.setOnClickListener(letterClickListener);

        // Добавьте остальные кнопки аналогичным образом

        // Кнопка удаления
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCellIndex > currentAttemptIndex * WORD_LENGTH) {
                    currentCellIndex--;
                    letterCells.get(currentCellIndex).setText("");
                }
            }
        });

        // Кнопка проверки
        Button btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Собираем слово из текущей строки
                int start = currentAttemptIndex * WORD_LENGTH;
                int end = start + WORD_LENGTH;
                StringBuilder guessBuilder = new StringBuilder();
                for (int i = start; i < end; i++) {
                    guessBuilder.append(letterCells.get(i).getText());
                }
                String guess = guessBuilder.toString().trim();

                if (guess.length() != WORD_LENGTH) {
                    Toast.makeText(MainActivity.this, "Введите слово из " + WORD_LENGTH + " букв!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    AttemptResult result = gameLogic.checkWord(guess);
                    // Изменяем цвет клеток в зависимости от результата
                    for (int i = 0; i < WORD_LENGTH; i++) {
                        TextView cell = letterCells.get(start + i);
                        switch (result.getStatuses()[i]) {
                            case GREEN:
                                cell.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.green));
                                break;
                            case YELLOW:
                                cell.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.yellow));
                                break;
                            case GRAY:
                                cell.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
                                break;
                        }
                    }

                    if (gameLogic.isGameWon()) {
                        Toast.makeText(MainActivity.this, "Поздравляем, вы выиграли!", Toast.LENGTH_LONG).show();
                    } else if (gameLogic.isGameOver()) {
                        Toast.makeText(MainActivity.this, "Попытки закончились! Загаданное слово: " + gameLogic.getHiddenWord(), Toast.LENGTH_LONG).show();
                    } else {
                        // Переход к следующей попытке
                        currentAttemptIndex++;
                    }
                } catch (IllegalStateException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
