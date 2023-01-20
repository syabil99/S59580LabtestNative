package com.example.mindsharpener;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int firstNumber;
    private int secondNumber;
    private String operator;
    private int point;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.easy:
                    generateNumbers(1);
                    level = 1;
                    break;
                case R.id.medium:
                    generateNumbers(2);
                    level = 2;
                    break;
                case R.id.hard:
                    generateNumbers(3);
                    level = 3;
                    break;
            }
        });


        findViewById(R.id.button).setOnClickListener(view -> {
            String userAnswerString = ((TextView)findViewById(R.id.userInputAnswer)).getText().toString();
            if (!userAnswerString.isEmpty()) {
                int userAnswer = Integer.parseInt(userAnswerString);
                int correctAnswer;
                if(operator.equals("/") && secondNumber == 0) {
                    correctAnswer = 0;
                } else {
                    correctAnswer = calculateAnswer();
                }
                if (userAnswer == correctAnswer) {
                    point++;
                    Toast.makeText(this, "Correct! Points: " + point, Toast.LENGTH_SHORT).show();
                    ((TextView)findViewById(R.id.textView14)).setText("POINT:" + point);
                } else {
                    point--;
                    Toast.makeText(this, "Incorrect! Points: " + point, Toast.LENGTH_SHORT).show();
                    ((TextView)findViewById(R.id.textView14)).setText("POINT:" + point);
                }
                generateQuestion();
                ((EditText)findViewById(R.id.userInputAnswer)).setText("");
                switch (level) {
                    case 1:
                        generateNumbers(1);
                        break;
                    case 2:
                        generateNumbers(2);
                        break;
                    case 3:
                        generateNumbers(3);
                        break;
                }
            }
        });
    }
    private void generateNumbers(int digit) {
        Random random = new Random();
        firstNumber = random.nextInt((int) Math.pow(10, digit) - 1) + 1;
        do {
            secondNumber = random.nextInt((int) Math.pow(10, digit) - 1) + 1;
        } while (secondNumber == 0 && digit != 1);
        operator = random.nextInt(4) == 0 ? "+" : random.nextInt(4) == 1 ? "-" : random.nextInt(4) == 2 ? "*" : "/";
        generateQuestion();
    }

    private void generateQuestion() {
        ((TextView)findViewById(R.id.textView4)).setText(String.valueOf(firstNumber));
        ((TextView)findViewById(R.id.textView5)).setText(String.valueOf(operator));
        ((TextView)findViewById(R.id.textView6)).setText(String.valueOf(secondNumber));
    }

    private int calculateAnswer() {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber == 0) {
                    return 0;
                }
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }
}

