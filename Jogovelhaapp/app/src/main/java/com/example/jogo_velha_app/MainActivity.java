package com.example.jogo_velha_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText player1Input = findViewById(R.id.player_1_input);
        EditText player2Input = findViewById(R.id.player_2_input);
        Button startGameBtn = findViewById(R.id.submitBtn);

        Button creatorsBtn = findViewById(R.id.authorsBtn);
        creatorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatorsActivity.class );
                startActivity(intent);
            }
        });

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String player1 = player1Input.getText().toString();
                String player2 = player2Input.getText().toString();

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("PLAYER_1", player1);
                intent.putExtra("PLAYER_2", player2);
                startActivity(intent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backBtn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

            });

        }
    }
