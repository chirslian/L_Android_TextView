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
        
        //设置第一个TextView要显示的文本
        String html = "<font color = 'red'>Hello Chirs</font><br>";
        html += "<font color = '#0000ff'><big>Hello Chirs</big></font><p>";
        html += "<font color = '@"+android.R.color.black+"'><tt><b><big>Hello Chirs</big></b></tt></font><p>";
        html += "<big><a href = 'https://www.github.com'>GitHub</a></big>";
        //将带预定义的标签转换成CharSequence对象
        CharSequence charSequence = Html.fromHtml(html);
        textView1.setText(charSequence);
        //下面语句非常重要，没有该语句，无法单机链接调用浏览器显示网页
        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        
        //设置第二个textView 控件要显示的文本
        String text = "My URL: http://www.hao123.com\n";
        text += "My Email: chirslian@163.com\n";
        text += "My Number:18350210052\n";
        textView2.setText(text);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        
        //设置第三个textView 
        String text3 = "One<img src='f001'/>";
        text3 +="Four<img src='f004'/><p>";
        text3 += "<a href = 'https://www.github.com'>Five<img src='f005'/></a>";
         
        textView3.setTextColor(Color.BLUE);
        textView3.setBackgroundColor(Color.GREEN);
        textView3.setTextSize(20);
        // 使用Html.fromHtml方法将含有Html标签的文件转换成CharSequence对象
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
        
        //设置第四个textView
        String text4 = "Second Activity";
        //将文本转化成 SpannableString对象
        SpannableString sstring = new SpannableString(text4);
        //将text4中的所有文本设置成clickableSpan对象，并实现了onClick方法
        sstring.setSpan(new ClickableSpan() {

        	//在onclick 方法中可以编写单击链接时要执行的动作
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
	 * 由于无法直接使用文件名来引用res/drawable中的图像资源，因此我们使用了一个技巧，
	 * 利用反射技术充R.drawable类中通过图像资源文件名获得了相应的图像资源ID，
	 * 实现的原理是R.drawable类中的乡音的资源ID变量名就是图像文件名，
	 * 所以可以利用反射技术获得R.drawable类中的制定字段的值
	 */    
    public int getResourceId(String name)
    {
    	Field field;
		try {
			/**
			 * 根据资源ID的变量名，也就是图像资源的文件名 获得Field对象
			 */
			field = R.drawable.class.getField(name);
			try {
				/**
				 * 取得并返回资源ID字段(静态变量）的值
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
