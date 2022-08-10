package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText title;
    EditText author;
    EditText publisher;

    MyDBManager MyDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = findViewById(R.id.update_book_title);
        author = findViewById(R.id.update_book_author);
        publisher = findViewById(R.id.update_book_publisher);

        MyDBManager = new MyDBManager(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addbutton:
                String add_title = title.getText().toString();
                String add_author = author.getText().toString();
                String add_publisher = publisher.getText().toString();

                if (add_title.length() > 0 && add_author.length() > 0 && add_publisher.length() > 0) {
                    boolean result = MyDBManager.addNewData(
                            new MyData(add_title, add_author, add_publisher));

                    if (result) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("mydata", title.getText().toString() );
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(this, "모두 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancelbutton:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
