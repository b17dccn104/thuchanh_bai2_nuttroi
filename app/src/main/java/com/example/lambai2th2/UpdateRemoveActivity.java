package com.example.lambai2th2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UpdateRemoveActivity extends AppCompatActivity {
    private Button btUpdate, btDelete, btBack;
    private EditText txtId, txtName, txtDate ,txtAmount, txtPrice;
    private SQLiteOrderOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_remove);
        initView();
        db = new SQLiteOrderOpenHelper(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Order order = db.getById(id);
        txtId.setText(String.valueOf(id));
        txtName.setText(order.getName());
        txtDate.setText(order.getDate());
        txtAmount.setText(String.valueOf(order.getAmount()));
        txtPrice.setText(String.valueOf(order.getPrice()));

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = txtName.getText().toString();
                    String date = txtDate.getText().toString();

                    int amount = Integer.parseInt(txtAmount.getText().toString());
                    double price = Double.parseDouble(txtPrice.getText().toString());
                    Order order = new Order(id, name,date, amount, price);
                    db.updateOrder(order);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteOrder(id);
                    finish();
                } catch (Exception e) {

                }
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
        btUpdate=findViewById(R.id.btnUp);
        btDelete=findViewById(R.id.btDelete);
        btBack=findViewById(R.id.upbtBack);
        txtId=findViewById(R.id.upID);
        txtName=findViewById(R.id.upName);
        txtDate = findViewById(R.id.upDateUp);
        txtAmount=findViewById(R.id.upAmount);
        txtPrice=findViewById(R.id.upPrice);
//        System.exit(0);
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