package com.minhtv.businesscardmama;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {


int bookingNumber = 0;
String getnumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void Book(View view) {

        String book = createBookMessage();

        safe();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Lu-Vuong@yahoo.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.booking, getnumber ));
        intent.putExtra(Intent.EXTRA_TEXT, book);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void safe () {

        bookingNumber++;

        String book_string = String.valueOf(bookingNumber);

        final SharedPreferences save = this.getSharedPreferences("booking_number", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putString("book_key", book_string);
        editor.commit();


        Log.v("my_tag", "data being sent is: "+book_string);
        getnumber = save.getString("book_key", "");
        Log.v("my_tag", "data being sent is: "+getnumber );
    }

    public String createBookMessage() {
        String message = getString(R.string.add_name);
        message += "\n" + getString(R.string.add_date);
        message += "\n" + getString(R.string.add_time);
        message += "\n" + getString(R.string.add_phone);
        message += "\n" + getString(R.string.add_info);
        return message;

    }


}
