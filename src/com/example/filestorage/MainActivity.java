package com.example.filestorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	EditText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edittext = (EditText) findViewById(R.id.etEnterName);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void saveInternalCache(View view) {
		String data = edittext.getText().toString();
		File folder = getCacheDir();
		File myFile = new File(folder, "mydata1.txt");
		writeData(myFile, data);
	}

	public void saveExternalCache(View view) {
		String data = edittext.getText().toString();
		File folder = getExternalCacheDir();
		File myFile = new File(folder, "mydata2.txt");
		writeData(myFile, data);
	}

	public void savePrivateExternalFile(View view) {
		String data = edittext.getText().toString();
		File folder = getExternalFilesDir("SampleDataStorage");
		File myFile = new File(folder, "mydata3.txt");
		writeData(myFile, data);
	}

	public void savePublicExternalFile(View view) {
		String data = edittext.getText().toString();
		File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		File myFile = new File(folder, "mydata4.txt");
		writeData(myFile, data);
	}

	private void writeData(File myFile, String data) {
		FileOutputStream fileoutputstream = null;
		try {
			fileoutputstream = new FileOutputStream(myFile);
			fileoutputstream.write(data.getBytes());
			message(data + " was writen successfully at "
					+ myFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileoutputstream != null) {
				try {
					fileoutputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void next(View view) {
		Intent i = new Intent(MainActivity.this, SecondActivity.class);
		startActivity(i);
	}

	private void message(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
	}

}
