package com.d_andaman;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Slidedown extends Activity {

	private LinearLayout mLinearLayout;
    private LinearLayout mLinearLayoutHeader;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swip);

        mLinearLayout = (LinearLayout) findViewById(R.id.expandable);
        //set visibility to GONE
        mLinearLayout.setVisibility(View.GONE);
        mLinearLayoutHeader = (LinearLayout) findViewById(R.id.header);
 
        mLinearLayoutHeader.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mLinearLayout.getVisibility()==View.GONE){
                    expand();
                }else{
                    collapse();
                }
            }
        });
}
	
    
    private void expand() {
        //set Visible
        mLinearLayout.setVisibility(View.VISIBLE);
        
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mLinearLayout.measure(widthSpec, heightSpec);
        
        ValueAnimator mAnimator = slideAnimator(0, mLinearLayout.getMeasuredHeight());
        mAnimator.start();
   }
    
    private void collapse() {
        int finalHeight = mLinearLayout.getHeight();

        ValueAnimator mAnimator = slideAnimator(finalHeight, 0);
     
        mAnimator.addListener(new Animator.AnimatorListener() {
             
        	
        	@Override
             public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                 mLinearLayout.setVisibility(View.GONE);
             }

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
            
        });
        
        mAnimator.start();
   }
   

   private ValueAnimator slideAnimator(int start, int end) {
	   
	    ValueAnimator animator = ValueAnimator.ofInt(start, end);
	  
	    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
	         @Override
	         public void onAnimationUpdate(ValueAnimator valueAnimator) {
	            //Update Height
	            int value = (Integer) valueAnimator.getAnimatedValue();
	            ViewGroup.LayoutParams layoutParams = mLinearLayout.getLayoutParams();
	            layoutParams.height = value;
	            mLinearLayout.setLayoutParams(layoutParams);
	         }
	    });
	    return animator;
	}
    
    
    
}
