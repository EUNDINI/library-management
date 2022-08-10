package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDBManager {

    MyDBHelper MyDBHelper = null;
    Cursor cursor = null;
    int images[]= {R.mipmap.book1, R.mipmap.book2, R.mipmap.book3, R.mipmap.book4, R.mipmap.book5, R.mipmap.momo};

    public MyDBManager(Context context) {
        MyDBHelper = new MyDBHelper(context);
    }

    public ArrayList<MyData> getAllData() {
        ArrayList MyDataList = new ArrayList();
        SQLiteDatabase db = MyDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MyDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_IMAGE));
            String title = cursor.getString(cursor.getColumnIndex(MyDBHelper.COL_BOOK_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(MyDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(MyDBHelper.COL_PUBLISHER));
            int price = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_PRICE));
            int pages = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_NUMBER_OF_PAGES));
            MyDataList.add ( new MyData (id, images[image], title, author, publisher, price, pages) );
        }

        cursor.close();
        MyDBHelper.close();
        return MyDataList;
    }

    public boolean addNewData(MyData newData) {
        SQLiteDatabase db = MyDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MyDBHelper.COL_IMAGE, "5");
        value.put(MyDBHelper.COL_BOOK_TITLE, newData.getBook_title());
        value.put(MyDBHelper.COL_AUTHOR, newData.getAuthor());
        value.put(MyDBHelper.COL_PUBLISHER, newData.getPublisher());
        value.put(MyDBHelper.COL_PRICE, newData.getPrice());
        value.put(MyDBHelper.COL_NUMBER_OF_PAGES, 100);

        long count = db.insert(MyDBHelper.TABLE_NAME, null, value);

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean modifyData(MyData myData) {
        SQLiteDatabase sqLiteDatabase = MyDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(MyDBHelper.COL_BOOK_TITLE, myData.getBook_title());
        row.put(MyDBHelper.COL_AUTHOR, myData.getAuthor());
        row.put(MyDBHelper.COL_PUBLISHER, myData.getPublisher());
        row.put(MyDBHelper.COL_PRICE, myData.getPrice());

        String whereclause = MyDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(myData.get_id())};

        int result = sqLiteDatabase.update(MyDBHelper.TABLE_NAME, row, whereclause, whereArgs);
        MyDBHelper.close();

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeData(long id) {
        SQLiteDatabase sqLiteDatabase = MyDBHelper.getWritableDatabase();
        String whereClause = MyDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = sqLiteDatabase.delete(MyDBHelper.TABLE_NAME, whereClause,whereArgs);
        MyDBHelper.close();
        if (result > 0) return true;
        return false;
    }

    public ArrayList<MyData> getDataByName(String title) {
        ArrayList<MyData> myDataArrayList = new  ArrayList<MyData>();
        SQLiteDatabase sqLiteDatabase = MyDBHelper.getReadableDatabase();

        String selection= MyDBHelper.COL_BOOK_TITLE + "=?";
        String[] selectArgs = new String[] { title };

        cursor = sqLiteDatabase.query(MyDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);
        while (cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_IMAGE));
            String titles = cursor.getString(cursor.getColumnIndex(MyDBHelper.COL_BOOK_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(MyDBHelper.COL_AUTHOR));
            String publisher = cursor.getString(cursor.getColumnIndex(MyDBHelper.COL_PUBLISHER));
            int price = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_PRICE));
            int pages = cursor.getInt(cursor.getColumnIndex(MyDBHelper.COL_NUMBER_OF_PAGES));
            myDataArrayList.add ( new MyData (id, image, titles, author, publisher, price, pages) );
        }

        MyDBHelper.close();
        cursor.close();
        return myDataArrayList;
    }
}

