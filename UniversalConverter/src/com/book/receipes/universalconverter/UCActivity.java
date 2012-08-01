package com.book.receipes.universalconverter;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class UCActivity extends Activity {
	private int position = 0;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Getting the layout using the inflater
		LinearLayout layout= (LinearLayout)getLayoutInflater().inflate(R.layout.activity_uc,null);
		
		//Create a new Button View
		Button btnAbout = new Button(this);
		//Setting the text on the Btn
		btnAbout.setText("About");
		
		
		
		//adding the Btn to the Layout 
		layout.addView(btnAbout,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		
		setContentView(layout);

		final EditText etUnits = (EditText) findViewById(R.id.units);
		final Spinner spnConversions = (Spinner) findViewById(R.id.conversions);
		ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.conversions,android.R.layout.simple_spinner_item);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnConversions.setAdapter(aa);
		AdapterView.OnItemSelectedListener oisl = new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				UCActivity.this.position = position;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				System.out.println("nothing");
			}
		};
		spnConversions.setOnItemSelectedListener(oisl);
		final Button btnClear = (Button) findViewById(R.id.clear);
		AdapterView.OnClickListener ocl = new AdapterView.OnClickListener() {
			@Override
			public void onClick(View v) {
				etUnits.setText("");
			}

			
		};
		btnClear.setOnClickListener(ocl);
		btnClear.setEnabled(false);
		final Button btnConvert = (Button) findViewById(R.id.convert);
		ocl = new AdapterView.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = etUnits.getText().toString();
				double input = Double.parseDouble(text);
				double result = 0;
				if (position == 3)
					result = input * 9.0 / 5.0 + 32; // Celsius to Fahrenheit
				else if (position == 4)
					result = (input - 32) * 5.0 / 9.0; // Fahrenheit to Celsius
				else{
					String[] convFactors = getResources().getStringArray(R.array.multipliers); 
					result = input * Double.parseDouble(convFactors[position]);
				}
					
				etUnits.setText("" + result);
			}
		};
		btnConvert.setOnClickListener(ocl);
		btnConvert.setEnabled(false);
		Button btnClose = (Button) findViewById(R.id.close);
		ocl = new AdapterView.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		};
		btnClose.setOnClickListener(ocl);
		TextWatcher tw = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (etUnits.getText().length() == 0) {
					btnClear.setEnabled(false);
					btnConvert.setEnabled(false);
				} else {
					btnClear.setEnabled(true);
					btnConvert.setEnabled(true);
				}
			}

			
		};
		etUnits.addTextChangedListener(tw);
	}
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.activity_uc, menu);
	// return true;
	// }
}
