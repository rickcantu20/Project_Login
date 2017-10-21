package com.example.rendevu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class Register extends Activity{
	Intent i=null;
	ImageView im=null;
	EditText tv1,tv2,tv3,tv4,tv5;
	DatePickerDialog datePickerDialog;
	boolean flag=false;
	SQLiteDatabase db=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		im=(ImageView)findViewById(R.id.show_hide);
		tv1=(EditText)findViewById(R.id.name);
		tv2=(EditText)findViewById(R.id.email_id);
		tv3=(EditText)findViewById(R.id.phone);
		tv4=(EditText)findViewById(R.id.password);
		tv5=(EditText)findViewById(R.id.dateOfBirth);
		db=openOrCreateDatabase("mydb", MODE_PRIVATE, null);
		db.execSQL("create table if not exists login(name varchar,mobile_no varchar," +
                "email_id varchar,password varchar,flag varchar)");

		tv5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// calender class's instance and get current date , month and year from calender
				final Calendar c = Calendar.getInstance();
				int mYear = c.get(Calendar.YEAR); // current year
				int mMonth = c.get(Calendar.MONTH); // current month
				int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
				// date picker dialog
				datePickerDialog = new DatePickerDialog(Register.this, //android.R.style.Theme_Holo_Dialog,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
												  int monthOfYear, int dayOfMonth) {
								// set day of month , month and year value in the edit text
								tv5.setText((monthOfYear + 1) + "/"
										+ dayOfMonth + "/" + year);

                                 //Set max limit for no future dates.

							}
						}, mYear, mMonth, mDay);  //Use month/day/year to calculate age
				datePickerDialog.show();
			}
		});

		
		im.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
			
				if(flag==false)
				{
					im.setImageResource(R.drawable.hide);
					tv4.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
					flag=true;
				}
				else
				{
					im.setImageResource(R.drawable.show);
					tv4.setInputType(129);
					flag=false;
					
				}
			}
		});
	}
	
	public void action(View v)
	{
	switch(v.getId())
	{
	case R.id.login: 
		i=new Intent(this,Login.class);
		startActivityForResult(i, 500);
		overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom); 
		finish();
		break;
	case R.id.register:
		String name=tv1.getText().toString();
		String email_id=tv2.getText().toString();
		String mobile_no=tv3.getText().toString();
		String password=tv4.getText().toString();
		if(name==null||name==""||name.length()<3)
		{
			show("Please Enter Correct Name.");
		}
		else if(mobile_no==null||mobile_no==""||mobile_no.length()<10)
		{
			show("Please Enter Correct mobile number.");
		}
		else if(email_id==null||email_id==""||email_id.length()<10)
		{
			show("Please Enter Correct Email id.");
		}
		else if(password==null||password==""||password.length()<6)
		{
			show("Please Enter Strong Password.");
		}
		else
		{
			db.execSQL("insert into login values('"+name+"','"+mobile_no+"','"+email_id+"','"+password+"','nothing')");
			i=new Intent(this,Main2Activity.class);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); 
			db.close();
			finish();
		}
		break;
	}
  }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	} 
	
	public void show(String str)
	{
	Toast.makeText(this, str, Toast.LENGTH_LONG).show();	
	}
}
