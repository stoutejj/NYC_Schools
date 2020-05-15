package com.example.codingchallengenycschools.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingchallengenycschools.R
import com.example.codingchallengenycschools.model.data.School
import com.example.codingchallengenycschools.model.data.Scores
import com.example.codingchallengenycschools.viewmodel.SchoolViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_school_info.*

class MainActivity : AppCompatActivity(), SchoolInfoClickListener {


    private var filterList = emptyList<School>()
    private val schoolViewModel = SchoolViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SchoolAdapter(this)
        school_recycler_view.layoutManager = LinearLayoutManager(this)
        school_recycler_view.adapter = adapter

        schoolViewModel.fetchSchools()
        schoolViewModel.fetchScores()

        schoolViewModel.schoolList.observe(this, Observer {
            adapter.setSchools(it)
        })

        schoolViewModel.error.observe(this, Observer{
            Snackbar.make(school_recycler_view,"Unable to Fetch Data...", Snackbar.LENGTH_INDEFINITE)
                .setAction("REDO",View.OnClickListener {
                    schoolViewModel.fetchSchools()
                    schoolViewModel.fetchScores()
                })
                .show()
        })

        btn_clear_search.setOnClickListener {
            search_bar.text.clear()
        }

        //Creates a search bar for the schools
        search_bar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                println(s.toString())

                schoolViewModel.schoolList.observe(this@MainActivity, Observer {
                    adapter.setSchools(it.filter { school ->
                        school.school_name.contains(
                            s.toString(),
                            true
                        )
                    })
                })

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }


    /*
    When SCHOOL INFO BUTTON is clicked on
    passes that SCHOOL Object into an intent along with
    SCORES class corresponding with that SCHOOL object
     */
    override fun onClick(school: School) {

        lateinit var score : Scores

        schoolViewModel.scoresList.observe(this, Observer {

            /*
            Runs a search on the list of all Schools' SAT Scores
            if a matching school ID a.k.a "score.dbn" is set var score to that SCORE object
            else set it "N/A" on the SAT scores
             */
            score = it.find { score -> score.dbn == school.dbn } ?:
                    Scores("null",
                        "null",
                        "N/A",
                        "N/A",
                        "N/A")
        })

        val intent =
            Intent(this,
                SchoolInfo::class.java)
                .putExtra("schoolInfo", school)
                .putExtra("schoolScores", score)

        startActivity(intent)
    }
}
