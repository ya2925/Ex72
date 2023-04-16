package com.example.ex72;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText a,b,c;
    TextView results;
    Intent Solver;
    Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.avar);
        b = findViewById(R.id.bvar);
        c = findViewById(R.id.cvar);
        results = findViewById(R.id.results);

        rand = new Random();
    }
    //send a b and c to the solver activity and get the result
    public void solve(View view){
        if(!isInt(a.getText().toString()) || !isInt(b.getText().toString()) || !isInt(c.getText().toString())){
            results.setText("Please enter valid numbers");
            return;
        }
        Solver = new Intent(this, Solver.class);
        Solver.putExtra("a", a.getText().toString());
        Solver.putExtra("b", b.getText().toString());
        Solver.putExtra("c", c.getText().toString());
        startActivityForResult(Solver, 1);
    }

    //randomize a b and c with click of a button
    public void randomize(View view){
        a.setText(String.valueOf(rand.nextInt(100)));
        b.setText(String.valueOf(rand.nextInt(100)));
        c.setText(String.valueOf(rand.nextInt(100)));
    }

    //get the result from the solver activity
    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);
        if(good == RESULT_OK){
            if(data_back.getStringExtra("x1").equals("No real roots")){
                results.setText("No real roots");
            }
            else if (data_back.getStringExtra("x1").equals(data_back.getStringExtra("x2"))){
                results.setText("x = " + data_back.getStringExtra("x1"));
            }
            else {
                results.setText("x1 = " + data_back.getStringExtra("x1") + " x2 = " + data_back.getStringExtra("x2"));
            }
        }
    }


    public static boolean isInt(String str) {
        try {
            int x = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}