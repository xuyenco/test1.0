package com.example.test10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test10.DataBase.Database;

public class ThemThiSinhActivity extends AppCompatActivity {

    //khi them activity nho them trong manifest

    EditText edtHoVaTen, edtSbd, edtDiemToan, edtDiemLy, edtDiemHoa;
    Button btnThem, btnBack;

    Database db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_thisinh);
        init();


        btnThem.setOnClickListener(v -> {
            String HoVaTen = edtHoVaTen.getText().toString();
            String Sbd = edtSbd.getText().toString();
            Double DiemToan = Double.parseDouble(edtDiemToan.getText().toString());
            Double DiemLy = Double.parseDouble(edtDiemLy.getText().toString());
            Double DiemHoa = Double.parseDouble(edtDiemHoa.getText().toString());
            int id = db.getAllData().getCount() + 1;
            db.addThiSinh(id,HoVaTen, Sbd, DiemToan, DiemLy, DiemHoa);
            Toast.makeText(getApplicationContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();

            //intent chả để làm gì đâu, setResult(150) de so voi ben kia
            Intent intent = new Intent();
            setResult(150);
            finish();

        });
        btnBack.setOnClickListener(v ->{
            setResult(100);
            finish();
        });
    }

    public  void init(){
        btnThem = findViewById(R.id.btnThem);
        btnBack = findViewById(R.id.btnBack);
        edtHoVaTen = findViewById(R.id.edtHoVaTen);
        edtSbd = findViewById(R.id.edtSbd);
        edtDiemToan = findViewById(R.id.edtDiemToan);
        edtDiemLy = findViewById(R.id.edtDiemLy);
        edtDiemHoa = findViewById(R.id.edtDiemHoa);

        db = new Database(this);
    }
}
