package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class RecActivity extends AppCompatActivity {

    MyDBManager MyDBManager;
    ArrayList<MyData> myDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);

        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        MyDBManager = new MyDBManager(this);
        myDataArrayList = MyDBManager.getAllData();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Random rnd = new Random();
                int num = rnd.nextInt(myDataArrayList.size());
                String result = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일의 추천 도서는 " + myDataArrayList.get(num).getBook_title() + "입니다.";
                Toast.makeText(RecActivity.this, result, Toast.LENGTH_SHORT).show();
            }

        });

    }
}




