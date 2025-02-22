package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Keyboard.Keyboard;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private GridLayout gridLetters;
    private List<TextView> letterCells; // Список для всех клеток
    private int currentCellIndex = 0;
    private int currentAttemptIndex = 0;
    private final int WORD_LENGTH = 5;
    private final int MAX_ATTEMPTS = 6;

    private GameLogic gameLogic; // Логика игры

    private Keyboard.Key key = new Keyboard.Key("", new Size(12,56));
    private List<Keyboard.Key> list = new ArrayList<>();
    private Keyboard x  = new Keyboard(list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add(key);

        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Инициализация логики игры (6 попыток)
        gameLogic = new GameLogic(MAX_ATTEMPTS);
        String[] words = {"птица", // п, т, и, ц, а
                "домик", // д, о, м, и, к
                "котик", // к, о, т, и, к
                "волки", // в, о, л, к, и
                "света", // с, в, е, т, а
                "ягода", // я, г, о, д, а
                "снега", // с, н, е, г, а
                "супер", // с, у, п, е, р
                "белка", // б, е, л, к, а
                "мышка", // м, ы, ш, к, а
                "рыбак", // р, ы, б, а, к
                "песик", // п, е, с, и, к
                "корка", // к, о, р, к, а
                "зайка", // з, а, й, к, а
                "груша", // г, р, у, ш, а
                "финик", // ф, и, н, и, к
                "рубеж", // р, у, б, е, ж
                "нитка", // н, и, т, к, а
                "блоки", // б, л, о, к, и
                "фронт"  // ф, р, о, н, т
                };
        // Пример: загадано слово "ВАГОН"
        gameLogic.startNewGame(words[(int)(Math.random() * words.length)]);

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
            cell.setBackgroundResource(R.drawable.ic_launcher_background);
            gridLetters.addView(cell);
            letterCells.add(cell);
        }

        // Обработчик кликов для кнопок клавиатуры (пример для кнопок "А", "Б", "В", "Г")
        Button btnА = findViewById(R.id.btnА);
        Button btnБ = findViewById(R.id.btnБ);
        Button btnВ = findViewById(R.id.btnВ);
        Button btnГ = findViewById(R.id.btnГ);
        Button btnД = findViewById(R.id.btnД);
        Button btnЕ = findViewById(R.id.btnЕ);
        Button btnЖ = findViewById(R.id.btnЖ);
        Button btnЗ = findViewById(R.id.btnЗ);
        Button btnИ = findViewById(R.id.btnИ);
        Button btnЙ = findViewById(R.id.btnЙ);
        Button btnК = findViewById(R.id.btnК);
        Button btnЛ = findViewById(R.id.btnЛ);
        Button btnМ = findViewById(R.id.btnМ);
        Button btnН = findViewById(R.id.btnН);
        Button btnО = findViewById(R.id.btnО);
        Button btnП = findViewById(R.id.btnП);
        Button btnР = findViewById(R.id.btnР);
        Button btnС = findViewById(R.id.btnС);
        Button btnТ = findViewById(R.id.btnТ);
        Button btnУ = findViewById(R.id.btnУ);
        Button btnФ = findViewById(R.id.btnФ);
        Button btnХ = findViewById(R.id.btnХ);
        Button btnЦ = findViewById(R.id.btnЦ);
        Button btnЧ = findViewById(R.id.btnЧ);
        Button btnШ = findViewById(R.id.btnШ);
        Button btnЩ = findViewById(R.id.btnЩ);
        Button btnЪ = findViewById(R.id.btnЪ);
        Button btnЫ = findViewById(R.id.btnЫ);
        Button btnЬ = findViewById(R.id.btnЬ);
        Button btnЭ = findViewById(R.id.btnЭ);
        Button btnЮ = findViewById(R.id.btnЮ);
        Button btnЯ = findViewById(R.id.btnЯ);
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


        btnА.setOnClickListener(letterClickListener);
        btnБ.setOnClickListener(letterClickListener);
        btnВ.setOnClickListener(letterClickListener);
        btnГ.setOnClickListener(letterClickListener);
        btnД.setOnClickListener(letterClickListener);
        btnЕ.setOnClickListener(letterClickListener);
        btnЖ.setOnClickListener(letterClickListener);
        btnЗ.setOnClickListener(letterClickListener);
        btnИ.setOnClickListener(letterClickListener);
        btnЙ.setOnClickListener(letterClickListener);
        btnК.setOnClickListener(letterClickListener);
        btnЛ.setOnClickListener(letterClickListener);
        btnМ.setOnClickListener(letterClickListener);
        btnН.setOnClickListener(letterClickListener);
        btnО.setOnClickListener(letterClickListener);
        btnП.setOnClickListener(letterClickListener);
        btnР.setOnClickListener(letterClickListener);
        btnС.setOnClickListener(letterClickListener);
        btnТ.setOnClickListener(letterClickListener);
        btnУ.setOnClickListener(letterClickListener);
        btnФ.setOnClickListener(letterClickListener);
        btnХ.setOnClickListener(letterClickListener);
        btnЦ.setOnClickListener(letterClickListener);
        btnЧ.setOnClickListener(letterClickListener);
        btnШ.setOnClickListener(letterClickListener);
        btnЩ.setOnClickListener(letterClickListener);
        btnЪ.setOnClickListener(letterClickListener);
        btnЫ.setOnClickListener(letterClickListener);
        btnЬ.setOnClickListener(letterClickListener);
        btnЭ.setOnClickListener(letterClickListener);
        btnЮ.setOnClickListener(letterClickListener);
        btnЯ.setOnClickListener(letterClickListener);






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
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.e377e9b8d135e68);
                        mediaPlayer.start();
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
