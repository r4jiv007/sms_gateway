package com.example.sms_gateway;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import my.app.sms_gateway.SMS;

public class SMSAdapter extends BaseAdapter{

	ArrayList<SMS> smsclt;
	Context mycontext;
	public SMSAdapter(ArrayList<SMS> smses,Context context){
		smsclt=smses;
		mycontext=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return smsclt.toArray().length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v=LayoutInflater.from(mycontext).inflate(R.layout.listelement, null);
		LinearLayout vLayout =(LinearLayout) v.findViewById(R.id.ll);
		
		final String From = smsclt.get(position).getSender();
		final String Msg = smsclt.get(position).getMsg();
		TextView from =(TextView)v.findViewById(R.id.frm);
		TextView msg = (TextView)v.findViewById(R.id.msg);
		from.setText(From);
		msg.setText(Msg);
		
		vLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowPopup.showDialog(From, Msg);
				
			}
		});
			return v;
	}
	
}

