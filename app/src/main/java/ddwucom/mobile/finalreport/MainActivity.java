// 과제명: 책 정보 관리 앱
// 분반: 01분반
// 학번: 20180955 성명: 김은진
// 제출일: 2020년 07월 03일
// 추가 점수 기능 1: 책제목이 DB에 존재하는지 검색
// 추가 점수 기능 2: 리스트뷰 항목에 이미지 사용
// 추가 점수 기능 3: 메뉴의 '날짜별 책 추천'을 누르면 캘린더뷰를 사용해서 날짜별로 책을 추천해줌. 캘린더의 날짜를 누르면 랜덤으로 DB안에 있는 책 한권을 토스트로 추천해줌.
package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    ListView listView;
    MyAdapter myAdapter;
    ArrayList<MyData> myDataList = null;
    MyDBManager myDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.customListView);
        myDataList = new ArrayList<MyData>();
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, myDataList);
        listView.setAdapter(myAdapter);
        myDBManager = new MyDBManager(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyData data = myDataList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("data", data); // 시리얼라이즈드라서 담기가 가능
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("책 데이터 삭제")
                        .setMessage(myDataList.get(pos).getBook_title() + " 의 책 정보를 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (myDBManager.removeData(myDataList.get(pos).get_id())) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    myDataList.clear();
                                    myDataList.addAll(myDBManager.getAllData());
                                    myAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                return true;
            }
        });

    }

    protected void onResume() {
        super.onResume();
        myDataList.clear();
        myDataList.addAll(myDBManager.getAllData());
        myAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
            case R.id.title_search:
                Intent search = new Intent(this, SearchActivity.class);
                startActivity(search);
                break;
            case R.id.recommand:
                Intent rec = new Intent(this, RecActivity.class);
                startActivity(rec);
                break;
            case R.id.developer:
                Intent developer = new Intent(this, DeveloperActivity.class);
                startActivity(developer);
                break;
            case R.id.exit:
                AlertDialog.Builder builderExit = new AlertDialog.Builder(this);
                builderExit.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setNegativeButton("취소", null)
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String mydata = data.getStringExtra("mydata");
                    Toast.makeText(this, mydata + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "책 데이터 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        else if (requestCode == UPDATE_CODE) {
            switch(resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "책 데이터 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "책 데이터 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}