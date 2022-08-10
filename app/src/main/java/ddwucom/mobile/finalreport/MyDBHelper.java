package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    final static String TAG = "MyDBHelper";

    final static String DB_NAME = "books.db";
    public final static String TABLE_NAME = "book_table";

    public final static String COL_ID = "_id";
    public final static String COL_IMAGE = "image";
    public final static String COL_BOOK_TITLE = "book_title";
    public final static String COL_AUTHOR = "author";
    public final static String COL_PUBLISHER = "publisher";
    public final static String COL_PRICE = "price";
    public final static String COL_NUMBER_OF_PAGES = "number_of_pages";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, "
                + COL_IMAGE + " TEXT, "
                + COL_BOOK_TITLE + " TEXT, "
                + COL_AUTHOR + " TEXT, "
                + COL_PUBLISHER + " TEXT, "
                + COL_PRICE + " INTEGER, "
                + COL_NUMBER_OF_PAGES + " INTEGER)";

        Log.d(TAG, sql);
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, '0', '22똥괭이네', '이삼 집사', '위즈덤하우스', 16000, 284);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '1', '지적 대화를 위한 넓고 얕은 지식', '채사장', '웨일북', 19800, 556);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '2', '보통의 언어들', '김이나', '위즈덤하우스', 14500, 268);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '3', '인간을 키우는 고양이', 'haha ha', '다독임북스', 15000, 192);");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '4', '코로나 이후의 세계', '제이슨 셍커', '미디어숲', 14800, 200);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
