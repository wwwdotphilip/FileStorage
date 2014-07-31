package com.example.filestorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class SecondActivity extends ActionBarActivity {
	EditText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		edittext = (EditText) findViewById(R.id.etLoaderContent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.second, menu);
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

	public void loadInternalCache(View view) {
		File folder = getCacheDir();
		File myFile = new File(folder, "mydata1.txt");
		String data = readData(myFile);
		if (data!=null) {
			edittext.setText(data);
		}else{
			edittext.setText("No data was found");
		}
	}

	public void loadExternalCache(View view) {
		File folder = getExternalCacheDir();
		File myFile = new File(folder, "mydata2.txt");
		String data = readData(myFile);
		if (data!=null) {
			edittext.setText(data);
		}else{
			edittext.setText("No data was found");
		}
	}

	public void loadPrivateExternalFile(View view) {
		File folder = getExternalFilesDir("SampleDataStorage");
		File myFile = new File(folder, "mydata3.txt");
		String data = readData(myFile);
		if (data!=null) {
			edittext.setText(data);
		}else{
			edittext.setText("No data was found");
		}
	}

	public void loadPublicExternalFile(View view) {
		File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		File myFile = new File(folder, "mydata4.txt");
		String data = readData(myFile);
		if (data!=null) {
			edittext.setText(data);
		}else{
			edittext.setText("No data was found");
		}
	}

	private String readData(File myFile) {
		FileInputStream fileinputstream = null;
		try {
			fileinputstream = new FileInputStream(myFile);
			int read = 1;
			StringBuffer stringbuffer = new StringBuffer();
			while ((read = fileinputstream.read()) != -1) {
				stringbuffer.append((char) read);
			}
			return stringbuffer.toString();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileinputstream != null) {
				try {
					fileinputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public void previous(View view) {
		finish();
	}

	private void message(String string) {
		// TODO Auto-generated method stub
		Toast.makeText(SecondActivity.this, string, Toast.LENGTH_SHORT).show();
	}

}
