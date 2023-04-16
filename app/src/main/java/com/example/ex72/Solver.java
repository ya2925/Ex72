package com.example.ex72;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Solver extends AppCompatActivity {

    int a,b,c;
    WebView Web;

    Intent MainPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        MainPage = getIntent();

        Web = findViewById(R.id.web);
        Web.getSettings().setJavaScriptEnabled(true);
        Web.setWebViewClient(new MyWebViewClient());



        a  = Integer.parseInt(MainPage.getStringExtra("a"));
        b  = Integer.parseInt(MainPage.getStringExtra("b"));
        c  = Integer.parseInt(MainPage.getStringExtra("c"));
        
        Web.loadUrl(getExpression(a,b,c));
    }


    public String getExpression(int a, int b, int c){
        return "https://cameramath.com/mathsolver?q="+a+"x%5E2%2B"+b+"x%2B"+c+"%3D0";
    }

    public double getx1(int a, int b, int c){
        return (-b+Math.sqrt(b*b-4*a*c))/(2*a);
    }
    public double getx2(int a, int b, int c){
        return (-b-Math.sqrt(b*b-4*a*c))/(2*a);
    }

    public void back(View view){
        System.out.println(b*b - 4*a*c);
        System.out.println(getx1(a,b,c));
        System.out.println(getx2(a,b,c));
        if(b*b - 4*a*c < 0){
            MainPage.putExtra("x1", "No real roots");
            MainPage.putExtra("x2", "No real roots");
            setResult(RESULT_OK, MainPage);
            finish();
        }
        else {
            MainPage.putExtra("x1", Double.toString(getx1(a,b,c)));
            MainPage.putExtra("x2", Double.toString(getx2(a,b,c)));
            setResult(RESULT_OK, MainPage);
            finish();
        }
    }


    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}