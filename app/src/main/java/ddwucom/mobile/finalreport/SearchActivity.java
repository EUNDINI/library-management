package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    MyDBManager myDBManager;
    EditText title;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        title = findViewById(R.id.search_title);
        button = findViewById(R.id.search_button);
        myDBManager = new MyDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.search_button:
                ArrayList<MyData> myDataArrayList;
                String search_title = title.getText().toString();
                myDataArrayList = myDBManager.getDataByName(search_title);

                if (myDataArrayList.size() == 0) {
                    Toast.makeText(this, search_title + "라는 제목을 가진 책이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, search_title + "라는 제목을 가진 책이 존재합니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
