package com.chirslian.l_android_textview;

import java.lang.reflect.Field;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	public TextView textView1;
	public TextView textView2;
	public TextView textView3;
	public TextView textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        
        //���õ�һ��TextViewҪ��ʾ���ı�
        String html = "<font color = 'red'>Hello Chirs</font><br>";
        html += "<font color = '#0000ff'><big>Hello Chirs</big></font><p>";
        html += "<font color = '@"+android.R.color.black+"'><tt><b><big>Hello Chirs</big></b></tt></font><p>";
        html += "<big><a href = 'https://www.github.com'>GitHub</a></big>";
        //����Ԥ����ı�ǩת����CharSequence����
        CharSequence charSequence = Html.fromHtml(html);
        textView1.setText(charSequence);
        //�������ǳ���Ҫ��û�и���䣬�޷��������ӵ����������ʾ��ҳ
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        
        //���õڶ���textView �ؼ�Ҫ��ʾ���ı�
        String text = "My URL: http://www.hao123.com\n";
        text += "My Email: chirslian@163.com\n";
        text += "My Number:18350210052\n";
        textView2.setText(text);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        
        //���õ�����textView 
        String text3 = "One<img src='f001'/>";
        text3 +="Four<img src='f004'/><p>";
        text3 += "<a href = 'https://www.github.com'>Five<img src='f005'/></a>";
         
        textView3.setTextColor(Color.BLUE);
        textView3.setBackgroundColor(Color.GREEN);
        textView3.setTextSize(20);
        // ʹ��Html.fromHtml����������Html��ǩ���ļ�ת����CharSequence����
        CharSequence charSequence2 = Html.fromHtml(text3, new ImageGetter() {
			@Override
			public Drawable getDrawable(String source) {
				Drawable drawable = getResources().getDrawable(getResourceId(source));
				if(source.equals("f005"))
					drawable.setBounds(0, 0, 2*drawable.getIntrinsicWidth(), 2*drawable.getIntrinsicHeight());
				else
					drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
				return drawable;
			}
		}, null);
        textView3.setText(charSequence2);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
        
        //���õ��ĸ�textView
        String text4 = "Second Activity";
        //���ı�ת���� SpannableString����
        SpannableString sstring = new SpannableString(text4);
        //��text4�е������ı����ó�clickableSpan���󣬲�ʵ����onClick����
        sstring.setSpan(new ClickableSpan() {

        	//��onclick �����п��Ա�д��������ʱҪִ�еĶ���
			@Override
			public void onClick(View widget) {
				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
				startActivity(intent);
			}
		}, 0, text4.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView4.setText(sstring);
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
    }
    
    /**
	 * �����޷�ֱ��ʹ���ļ���������res/drawable�е�ͼ����Դ���������ʹ����һ�����ɣ�
	 * ���÷��似����R.drawable����ͨ��ͼ����Դ�ļ����������Ӧ��ͼ����ԴID��
	 * ʵ�ֵ�ԭ����R.drawable���е���������ԴID����������ͼ���ļ�����
	 * ���Կ������÷��似�����R.drawable���е��ƶ��ֶε�ֵ
	 */    
    public int getResourceId(String name)
    {
    	Field field;
		try {
			/**
			 * ������ԴID�ı�������Ҳ����ͼ����Դ���ļ��� ���Field����
			 */
			field = R.drawable.class.getField(name);
			try {
				/**
				 * ȡ�ò�������ԴID�ֶ�(��̬��������ֵ
				 */
				return Integer.parseInt(field.get(null).toString());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
