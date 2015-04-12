package com.chirslian.l_android_textview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.widget.TextView;

public class SecondActivity extends Activity{

	public TextView textView1;
	public TextView textView2;
	public TextView textView3;
	public TextView textView4;
	public TextView textView5;
	public TextView textView6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		textView1 = (TextView)findViewById(R.id.textV1);
		textView2 = (TextView)findViewById(R.id.textV2);
		textView3 = (TextView)findViewById(R.id.textV3);
		textView4 = (TextView)findViewById(R.id.textV4);
		textView5 = (TextView)findViewById(R.id.textV5);
		textView6 = (TextView)findViewById(R.id.textV6);
		
		//���õ�һ��textView
		String text = "<NONE><Yellow>\n\n<BLUE BACKGROUND��RED FONT>";
		SpannableString spannableString = new SpannableString(text);
		int start = 6;
		int end = 13;
		BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.YELLOW);
		spannableString.setSpan(backgroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		start = 16;
		ColorSpan colorSpan = new ColorSpan(Color.RED, Color.BLUE);
		spannableString.setSpan(colorSpan, start, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		textView1.setText(spannableString);
		
		//���õڶ���textView
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
		
		//���õ�����textView
		
		//���õ��ĸ�textView
		textView4.setText(textView3.getText());
		textView4.setEllipsize(TruncateAt.END);
		textView4.setSingleLine(true);
		
		//���õ����textview
		textView5.setText(textView3.getText());
		
		//���õ�����textView
		
		
	}
	/**
	 * �ܹ�ͬʱ���� �ı�����ɫ�Լ��ı�������ɫ
	 * @author xiaobin
	 *
	 */
	public class ColorSpan extends CharacterStyle{
		private int mTextColor;
		private int bgColor;
		
		public ColorSpan(final int textColor,final int backgroundColor){
			mTextColor = textColor;
			bgColor  = backgroundColor;
		}
		@Override
		public void updateDrawState(TextPaint tp) {
			tp.bgColor = bgColor;
			tp.setColor(mTextColor);
		}
	}
	
}
