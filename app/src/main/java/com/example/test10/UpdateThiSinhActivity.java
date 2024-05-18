package com.example.test10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test10.DataBase.Database;

public class UpdateThiSinhActivity extends AppCompatActivity {
    EditText edtHoVaTen, edtSbd, edtDiemToan, edtDiemLy, edtDiemHoa;
    Button btnSua, btnBack;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_thisinh);

        init();
        //intent nay tao ra de lay du lieu tu main activity truyen vao
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String hoten = intent.getStringExtra("hoten");
        String sbd = intent.getStringExtra("sbd");
        double diemtoan = intent.getDoubleExtra("diemtoan", 0.0);
        double diemly = intent.getDoubleExtra("diemly", 0.0);
        double diemhoa = intent.getDoubleExtra("diemhoa", 0.0);

        edtHoVaTen.setText(hoten);
        edtSbd.setText(sbd);
        edtDiemToan.setText(String.valueOf(diemtoan));
        edtDiemLy.setText(String.valueOf(diemly));
        edtDiemHoa.setText(String.valueOf(diemhoa));
        btnSua.setOnClickListener(view -> {
            //
            db.updateData(id, edtHoVaTen.getText().toString(), edtSbd.getText().toString(), Double.parseDouble(edtDiemToan.getText().toString()), Double.parseDouble(edtDiemLy.getText().toString()), Double.parseDouble(edtDiemHoa.getText().toString()));
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            setResult(250);
            finish();
        });
        btnBack.setOnClickListener(view -> {
            setResult(250);
            finish();
        });
    }
    public void init(){
        btnSua = findViewById(R.id.btnSua);
        btnBack = findViewById(R.id.btnBack);
        edtHoVaTen = findViewById(R.id.edtHoVaTen);
        edtSbd = findViewById(R.id.edtSbd);
        edtDiemToan = findViewById(R.id.edtDiemToan);
        edtDiemLy = findViewById(R.id.edtDiemLy);
        edtDiemHoa = findViewById(R.id.edtDiemHoa);

        db = new Database(this);
    }
}