package com.example.lambai2th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private Button btAdd, btBack;
    private EditText txtName, txtDate, txtAmount, txtPrice;
    private SQLiteOrderOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        db = new SQLiteOrderOpenHelper(this);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String date = txtDate.getText().toString();
                int amount = Integer.parseInt(txtAmount.getText().toString());
                double price = Double.parseDouble(txtPrice.getText().toString());
                Order order = new Order(name,date, amount, price);
                db.addOrder(order);
                finish();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        btAdd=findViewById(R.id.btAdd);
        btBack=findViewById(R.id.btBack);
        txtName=findViewById(R.id.stName);
        txtDate = findViewById(R.id.stDate);
        txtAmount=findViewById(R.id.stAmount);
        txtPrice=findViewById(R.id.stPrice);
    }

    public void chonngay(){
        final Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}