package com.example.webservicestwo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    val TAG = "Android"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createProgressDialog()

        btnGetDetails.setOnClickListener {
            val getIdToFetch = etId.text
            Log.i(TAG,"$getIdToFetch")

            if (getIdToFetch.isNullOrEmpty() || getIdToFetch.toString().toInt() > 10 || getIdToFetch.toString().toInt() < 1
            ) {
                etId.setError("Enter digit between 1 to 10")
            } else {
                progressDialog.show()
                getMyData(getIdToFetch.toString().toInt())
            }


        }




    }

    private fun createProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCancelable(false)
    }

    private fun getMyData(id:Int) {


        val retrofitData = APIClient.getClient.getData(id)
        //Ctrl+shift+space to get the callbacks
        retrofitData.enqueue(object : Callback<List<DataModel>?> {
            override fun onResponse(
                call: Call<List<DataModel>?>,
                response: Response<List<DataModel>?>
            ) {
                progressDialog.dismiss()
                Log.i("MainActivity", "Data is ${response.body()}")
                for (data in response.body()!!) {
                    //println("Data is ${data.postID}, ${data.postTitle}, ${data.postBody}")
                    tvId.text = "Post Id: ${data.id.toString()}"
                    tvTitle.text = "Title: ${data.title}"
                    tvBody.text = "Body: ${data.body}"
                }
            }

            override fun onFailure(call: Call<List<DataModel>?>, t: Throwable) {
                Log.d("MainActivity","onFailure:" + t.message)
            }
        })
    }
}