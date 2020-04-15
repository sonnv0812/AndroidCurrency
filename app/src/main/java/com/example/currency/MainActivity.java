package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<String> listView;
    ArrayAdapter<String> adapter;
    Spinner spinner1, spinner2;
    TextView dv1, dv2, input, result, quydoi;

    Money vnd = new Money("₫",1.0);
    Money dola = new Money("$",23000.0);
    Money pound = new Money("£", 25731.0);
    Money rup = new Money("₽", 318.69);
    Money yen = new Money("¥", 218.0);

    Money from = dola;
    Money to = vnd;

    String textview1 = "0";
    String textview2 = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dv1 = (TextView) findViewById(R.id.tv_donvi1);
        dv2 = (TextView) findViewById(R.id.tv_donvi2);
        input = (TextView) findViewById(R.id.textinput);
        result = (TextView) findViewById(R.id.tvResult);
        quydoi = (TextView) findViewById(R.id.tv_quy_doi);

        findViewById(R.id.So0).setOnClickListener(this);
        findViewById(R.id.So1).setOnClickListener(this);
        findViewById(R.id.So2).setOnClickListener(this);
        findViewById(R.id.So3).setOnClickListener(this);
        findViewById(R.id.So4).setOnClickListener(this);
        findViewById(R.id.So5).setOnClickListener(this);
        findViewById(R.id.So6).setOnClickListener(this);
        findViewById(R.id.So7).setOnClickListener(this);
        findViewById(R.id.So8).setOnClickListener(this);
        findViewById(R.id.So9).setOnClickListener(this);
        findViewById(R.id.dauCham).setOnClickListener(this);
        findViewById(R.id.nutBS).setOnClickListener(this);
        findViewById(R.id.CE).setOnClickListener(this);

        listView = new ArrayList<>();
        listView.add("VietNam - vnd");
        listView.add("United States - dollar");
        listView.add("Japan - yen");
        listView.add("Russia - rup");
        listView.add("England - pound");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listView);

        spinner1 = findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);
        spinner2 = findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        from = vnd;
                        break;
                    case 1:
                        from = dola;
                        break;
                    case 2:
                        from = yen;
                        break;
                    case 3:
                        from = rup;
                        break;
                    default:
                        from = pound;
                        break;
                }
                dv1.setText(from.symbol);
                quydoi.setText("1 " + from.symbol + "=" + from.getRate(to) + to.symbol);
                textview2 = String.valueOf((double)Math.round(Double.parseDouble(textview1)*from.getRate(to)*100)/100);
                result.setText(textview2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        to = vnd;
                        break;
                    case 1:
                        to = dola;
                        break;
                    case 2:
                        to = yen;
                        break;
                    case 3:
                        to = rup;
                        break;
                    default:
                        to = pound;
                        break;
                }
                dv2.setText(to.symbol);
                quydoi.setText("1 " + from.symbol + "=" + from.getRate(to) + to.symbol);
                textview2 = String.valueOf((double)Math.round(Double.parseDouble(textview1)*from.getRate(to)*100)/100);
                result.setText(textview2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CE:
                textview1 = "0";
                textview2 = "0";
                break;

            case R.id.nutBS:
                if (textview1.length() == 1)
                    textview1 = "0";
                else {
                    textview1 = (String) textview1.substring(0, textview1.length()-1);
                }
                break;

            default:
                if (textview1.equals("0"))
                    textview1 = "";
                textview1 += ((Button)v).getText().toString();
                break;
        }
        textview2 = String.valueOf((double)Math.round(Double.parseDouble(textview1)*from.getRate(to)*100)/100);

        input.setText(textview1);
        result.setText(textview2);
    }
}
