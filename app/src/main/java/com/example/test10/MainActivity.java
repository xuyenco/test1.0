package com.example.test10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test10.Adapter.ThiSinhAdapter;
import com.example.test10.DataBase.Database;
import com.example.test10.Model.ThiSinhModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<ThiSinhModel> List = new ArrayList<>();
    Database db;
    FloatingActionButton addButton;
    ThiSinhAdapter thiSinhAdapter;
    EditText edtsearch;

    //ham xu ly khi nguoi dung chon them
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    //ham xu ly khi nguoi dung chon menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = ((ThiSinhAdapter) recyclerView.getAdapter()).getPosition();
        } catch (Exception e) {
            Log.d("Error", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        int itemId = item.getItemId();
        if (itemId == R.id.ctxSua) {
//            Toast.makeText(this, "Sua: " + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UpdateThiSinhActivity.class);
            intent.putExtra("id", List.get(position).getID());
            intent.putExtra("hoten", List.get(position).getHoTen());
            intent.putExtra("sbd", List.get(position).getSoBaoDanh());
            intent.putExtra("diemtoan", List.get(position).getDiemToan());
            intent.putExtra("diemly", List.get(position).getDiemLy());
            intent.putExtra("diemhoa", List.get(position).getDiemHoa());
            //dung intent de truyen du lieu, goi startActivityForResult thi phai co onActivityResult
            startActivityForResult(intent, 200);
        } else if (itemId == R.id.ctxXoa) {
            int id = List.get(position).getID();
            db.deleteData(id);
            getDataToList();
            thiSinhAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xoa: " + position, Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.floatingActionButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemThiSinhActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        recyclerView = findViewById(R.id.ListRecycleview);

        //khoi tao database
        db = new Database(this);
        getDataToList();

        //thiet lap recycleview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        thiSinhAdapter = new ThiSinhAdapter(List, this);
        recyclerView.setAdapter(thiSinhAdapter);

        //tìm kiếm
        edtsearch = findViewById(R.id.editTextText);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                thiSinhAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //ham lay du lieu tu database
    public void getDataToList(){
        List.clear();
        Cursor cr = db.getAllData();
        if(cr.getCount() == 0){
            Toast.makeText(this, "Database is empty", Toast.LENGTH_SHORT).show();
        }else {
            while (cr.moveToNext()){
                int id = cr.getInt(0);
                String name = cr.getString(1);
                String sbd = cr.getString(2);
                Double DiemToan = cr.getDouble(3);
                Double DiemLy = cr.getDouble(4);
                Double DiemHoa = cr.getDouble(5);
                Double DiemTb = cr.getDouble(6);
                List.add(new ThiSinhModel(id, name, sbd, DiemToan, DiemLy, DiemHoa, DiemTb));
            }
        }
    }

    //ham xu ly ket qua tra ve
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 150) {
            //khi nguoi dung them thi sinh thanh cong, du lieu duoc lay lai tu database
            getDataToList();
        }
        if(requestCode == 200 && resultCode == 250){
            getDataToList();
        }
        thiSinhAdapter.notifyDataSetChanged();
    }
}