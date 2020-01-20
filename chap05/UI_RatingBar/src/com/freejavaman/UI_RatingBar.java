package com.freejavaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class UI_RatingBar extends Activity {
	
	TextView txt;
	RatingBar rtbar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
        
     //���o�������
     txt = (TextView)this.findViewById(R.id.txt);
     rtbar = (RatingBar)this.findViewById(R.id.rtbar);
        
     //�i��e�����u�@
     rtbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
	  public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
	   txt.setText("���ơG" + rating + "/" + ratingBar.getNumStars());	  
	  }
	 });   
    }
}