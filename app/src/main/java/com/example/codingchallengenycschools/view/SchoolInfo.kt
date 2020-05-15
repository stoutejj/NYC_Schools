package com.example.codingchallengenycschools.view

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.example.codingchallengenycschools.R
import com.example.codingchallengenycschools.model.data.School
import com.example.codingchallengenycschools.model.data.Scores
import kotlinx.android.synthetic.main.activity_school_info.*


class SchoolInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_info)

        val school = intent.extras?.getParcelable<School>("schoolInfo")
        val scores = intent.extras?.getParcelable<Scores>("schoolScores")

        tv_school_name.text = school?.school_name
        tv_reading_score.text = "SAT Average Critical Reading Score:  ${scores?.sat_critical_reading_avg_score}"
        tv_math_score.text = "SAT Average Math Score:  ${scores?.sat_math_avg_score}"
        tv_writing_score.text = "SAT Average Writing Score:  ${scores?.sat_writing_avg_score}"
        tv_overview_paragraph.text = school?.overview_paragraph
        tv_fax_number.text = "Fax: ${school?.fax_number}"

        val schoolAddr = SpannableString("Address: ${school?.location}")
        val phoneNumber = SpannableString("Tel: ${school?.phone_number}")
        val emailAdrr = SpannableString("Email: ${school?.school_email ?: let { tv_email.visibility = View.GONE }}")
        val website = SpannableString("Website: ${school?.website}")

        //When the SCHOOL ADDRESS is clicked redirects to GOOGLE MAPS
        val clickableSpanForAddr = object : ClickableSpan(){

            override fun onClick(widget: View) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:${school?.latitude},${school?.longitude}?q=${school?.location}")
                )
                    .setPackage("com.google.android.apps.maps")
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLACK
                ds.isUnderlineText = true
            }
        }

        //When the PHONE NUMBER is clicked redirects to the DIALER
        val clickableSpanForPhone = object : ClickableSpan(){

            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:${school?.phone_number}")
                )
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLACK
                ds.isUnderlineText = true
            }
        }

        //When the EMAIL ADDRESS BUTTON is clicked on redirects to send an email
        val clickableSpanForEmail = object : ClickableSpan(){

            override fun onClick(widget: View) {
                val intent = Intent(
                    Intent.ACTION_SENDTO,
                    Uri.parse("mailto:${school?.school_email}")
                )
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLACK
                ds.isUnderlineText = true
            }
        }

        //When the WEBSITE LINK is clicked
        val clickableSpanForWebsite = object : ClickableSpan(){

            override fun onClick(widget: View) {
                if (!school?.website?.startsWith("http")!!){
                    school.website = "http://${school.website}"
                }

                /*
                Creates a prompt
                Gives the user the choice whether to open the app using
                an implicit/explicit intent

                1) WebBrowser
                2) WebView
                3) Cancel
                 */
                AlertDialog.Builder(this@SchoolInfo)
                    .setTitle("Navigate to School Website")
                    .setMessage(
                        "How would you like to proceed \n\n" +
                                "1) Web View -> Inside of the app\n\n" +
                                "2) Using the Web Browser")

                    // WEBVIEW
                    .setNeutralButton("WebView"){dialog, which ->

                        val intent = Intent(widget.context,
                            SchoolWebActivity::class.java)
                            .putExtra("schoolUrl", school?.website)
                        startActivity(intent)}

                    // WEB BROWSER
                    .setNegativeButton("Web Browser") { dialog, which ->
                        val intent = Intent(Intent.ACTION_VIEW,
                            Uri.parse(school?.website))
                        startActivity(intent) }

                    // CANCEL
                    .setPositiveButton("Cancel") { dialog, which -> dialog.dismiss()}
                    .create()
                    .show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLACK
                ds.isUnderlineText = true
            }
        }

        //Defines what's clickable in the text
        schoolAddr.setSpan(clickableSpanForAddr,9,schoolAddr.length-1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        phoneNumber.setSpan(clickableSpanForPhone,5,phoneNumber.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        emailAdrr.setSpan(clickableSpanForEmail,7,emailAdrr.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        website.setSpan(clickableSpanForWebsite,9,website.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        //Links the TextView with it's Click Action
        tv_school_addr.text = schoolAddr
        tv_school_addr.movementMethod = LinkMovementMethod.getInstance()

        tv_phone_number.text = phoneNumber
        tv_phone_number.movementMethod = LinkMovementMethod.getInstance()

        tv_email.text = emailAdrr
        tv_email.movementMethod = LinkMovementMethod.getInstance()


        tv_website.text = website
        tv_website.movementMethod = LinkMovementMethod.getInstance()
    }
}
