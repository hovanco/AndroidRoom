package com.example.androidroom;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    AppDatabase db;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.uid = 11;
                user.firstName = "John Cena";
                db.userDao().insertAll(user);
                return null;
            }
        }.execute();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.uid = 17;
                user.firstName = "John Cena";
                db.userDao().insertAll(user);
                return null;
            }
        }.execute();

        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsers();
            }
        });
    }

    private void getUsers() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                final List<User> users = db.userDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "size " + users.size(), Toast.LENGTH_SHORT).show();
                    }
                });

                return null;
            }
        }.execute();

    }


}
