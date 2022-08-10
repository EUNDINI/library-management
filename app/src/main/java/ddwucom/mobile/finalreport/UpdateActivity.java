package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    MyData mydata;

    EditText title;
    EditText author;
    EditText publisher;
    EditText price;
    EditText pages;

    MyDBManager myDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mydata = (MyData) getIntent().getSerializableExtra("data");

        title = findViewById(R.id.update_book_title);
        author = findViewById(R.id.update_book_author);
        publisher = findViewById(R.id.update_book_publisher);
        price = findViewById(R.id.update_book_price);
        pages = findViewById(R.id.update_book_pages);

        title.setHint(mydata.getBook_title());
        author.setHint(mydata.getAuthor());
        publisher.setHint(mydata.getPublisher());
        price.setHint(Integer.toString(mydata.getPrice()));
        pages.setHint(Integer.toString(mydata.getNumber_of_pages()));

        myDBManager = new MyDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.update_button:
                String update_title = title.getText().toString();
                String update_author = author.getText().toString();
                String update_publisher = publisher.getText().toString();
                String update_price = price.getText().toString();
                String update_pages = pages.getText().toString();

                if (update_title.length() > 0 && update_author.length() > 0 && update_publisher.length() > 0 && update_price.length() > 0 && update_pages.length() > 0 ) {
                    mydata.setBook_title(update_title);
                    mydata.setAuthor(update_author);
                    mydata.setPublisher(update_publisher);
                    mydata.setPrice(Integer.parseInt(update_price));
                    mydata.setNumber_of_pages(Integer.parseInt(update_pages));
                    boolean result = myDBManager.modifyData(mydata);

                    if (result) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("title", mydata);
                        setResult(RESULT_OK, resultIntent);
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                }
                else {
                    Toast.makeText(this, "모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.update_cancel_button:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
