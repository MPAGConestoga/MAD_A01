/*
 *	FILE			: TaskViewActivity.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 02 - 05
 *	DESCRIPTION		: Activity that allows the display of a task info
 *
 */

package com.github.mpagconestoga.mad_a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.adapters.SubtaskAdapter;
import com.github.mpagconestoga.mad_a01.adapters.TaskListAdapter;
import com.github.mpagconestoga.mad_a01.adapters.ViewSubtaskAdapter;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.viewmodel.TaskViewModel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TaskViewActivity extends AppCompatActivity {
    private static final String TAG = "TaskViewActivity";

    private RecyclerView subtaskRecyclerView;
    private ViewSubtaskAdapter subtaskAdapter;
    private RecyclerView.LayoutManager subtaskLayoutManager;
    private TextView header;
    private ProgressBar progressBar;

    private TaskViewModel viewModel;

    private View backgroundView;
    String imageURL = "https://neighborscape.ca/wp-content/uploads/2018/06/Thumbnail-02-256x256.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        // Grab viewModel
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()).create(TaskViewModel.class);
        int taskId = getIntent().getIntExtra("taskid", -1);

        backgroundView = findViewById(R.id.layout);

        // Get task
        viewModel.setTaskById(taskId);

        // Set Task Header info
        progressBar = findViewById(R.id.task_progress);
        header = findViewById(R.id.task_title);
        header.setText(String.format("%s: %s", "Task", viewModel.getTask().getName()));

        // Set subtask recycler list
        subtaskRecyclerView = findViewById(R.id.viewsubtask_list);
        subtaskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        subtaskRecyclerView.setHasFixedSize(true);

        // Subtask Adapter
        subtaskAdapter = new ViewSubtaskAdapter(this);
        subtaskRecyclerView.setAdapter(subtaskAdapter);
        subtaskAdapter.setData(viewModel.getSubtasks());

        DownloadTask downloadTask = new DownloadTask(); // logic for saving and loading background image
        downloadTask.execute(imageURL);
    }

    class DownloadTask extends AsyncTask<String, Integer, String> {   //Integer and second String can be changed back to Void later

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(TaskViewActivity.this);
            progressDialog.setTitle("Download in Progress...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params){                      //change String back to void
            String path = params[0];                                             // assigns the url to the path variable
            int fileLength = 0;

            try {
                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                fileLength = urlConnection.getContentLength();                   //assigns length of file to variable
                File newFolder = getApplicationContext().getExternalFilesDir(null);         //assigns to folder for storage
                if (newFolder.exists())
                {
                    newFolder.mkdirs();                                           //creates folder should it not already exist
                }

                Log.d("filepath", getApplicationContext().getExternalFilesDir(null).toString());

                File inputFile = new File(newFolder, "downloaded_image.jpg");    // might need to change second string
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192); //opens an inputstream with an 8kb buffer
                byte[] data = new byte[1024];                                        // reads the info in at 1kb
                int total = 0;
                int count = 0;
                OutputStream outputStream = new FileOutputStream(inputFile);
                while((count=inputStream.read(data))!=-1){                           //I think this is all for the progress bar and can be removed
                    total += count;
                    outputStream.write(data, 0, count);
                    int progress = total * 100/fileLength;                           //for the progress bar; can remove
                    publishProgress(progress);
                }
                inputStream.close();
                outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download Complete...";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {                        // consider changing from Integer back to void
            progressDialog.setProgress(values[0]);
        }


        @Override
        protected void onPostExecute(String result) {                                  //change parameters back to Void aVoid
            progressDialog.hide();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            SetBackground setBackground = new SetBackground();
            setBackground.execute();

        }
    }

    class SetBackground extends AsyncTask<Void, Void, BitmapDrawable> {

        @Override
        protected BitmapDrawable doInBackground(Void... voids) {

            String path = getApplicationContext().getExternalFilesDir(null) + File.separator + "downloaded_image.jpg";
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getApplicationContext().getResources(), bitmap);

            return bitmapDrawable;
        }

        @Override
        protected void onPostExecute(BitmapDrawable bitmapDrawable) {
            backgroundView.setBackground(bitmapDrawable);
            bitmapDrawable.setAlpha(50);
        }
    }
}
