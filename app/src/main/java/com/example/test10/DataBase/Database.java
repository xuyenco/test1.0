package com.example.test10.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//khi gọi db thì phải extends SQLiteOpenHelper
public class Database extends SQLiteOpenHelper {
    //khi gọi db thì phải có context
    private Context context;

    //khởi tạo tên các bảng, cột,.....
    public static final String DATABASE_NAME = "ThiSinh.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "ThiSinh";
    public static final String COLLUM_ID = "ID";
    public static final String COLLUM_HO_TEN = "HoTen";
    public static final String COLLUM_SO_BAO_DANH = "SoBaoDanh";
    public static final String COLLUM_DIEM_TOAN = "DiemToan";
    public static final String COLLUM_DIEM_LY = "DiemLy";
    public static final String COLLUM_DIEM_HOA = "DiemHoa";
    public static final String COLLUM_DIEM_TB = "DiemTb";

    //hàm tạo db, đây luôn được gọi đầu
    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //hàm tạo db (sử dụng query)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COLLUM_ID + " INTEGER, "
                + COLLUM_HO_TEN + " TEXT, "
                + COLLUM_SO_BAO_DANH + " TEXT, "
                + COLLUM_DIEM_TOAN + " DOUBLE, "
                + COLLUM_DIEM_LY + " DOUBLE, "
                + COLLUM_DIEM_HOA + " DOUBLE, "
                + COLLUM_DIEM_TB + " DOUBLE)";

        db.execSQL(query);
    }

    //hàm thay đổi db, drop cho nhanh
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //hàm thêm dữ liệu
    public void addThiSinh(int id, String Hoten, String SBD, double DiemToan, double DiemLy, double DiemHoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Double DTB = (DiemToan + DiemLy + DiemHoa) / 3;

        cv.put(COLLUM_ID,id);
        cv.put(COLLUM_HO_TEN,Hoten);
        cv.put(COLLUM_SO_BAO_DANH,SBD);
        cv.put(COLLUM_DIEM_TOAN,DiemToan);
        cv.put(COLLUM_DIEM_LY,DiemLy);
        cv.put(COLLUM_DIEM_HOA,DiemHoa);
        cv.put(COLLUM_DIEM_TB,DTB);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
    }

    //hàm lấy dữ liệu
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    //hàm xóa dữ liệu
    public void deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLLUM_ID + " = " + id;
        db.execSQL(query);
    }

    //hàm thay đổi dữ liệu
    public void updateData(int id, String Hoten, String SBD, double DiemToan, double DiemLy, double DiemHoa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Double DTB = (DiemToan + DiemLy + DiemHoa) / 3;
        cv.put(COLLUM_HO_TEN,Hoten);
        cv.put(COLLUM_SO_BAO_DANH,SBD);
        cv.put(COLLUM_DIEM_TOAN,DiemToan);
        cv.put(COLLUM_DIEM_LY,DiemLy);
        cv.put(COLLUM_DIEM_HOA,DiemHoa);
        cv.put(COLLUM_DIEM_TB,DTB);
        db.update(TABLE_NAME,cv,"ID = ?",new String[]{String.valueOf(id)});
    }


}
