package my.app.sms_gateway;
import java.util.ArrayList;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import my.app.sms_gateway.SMS;

@SuppressLint("ParserError")
public class ShowPopup extends Activity implements OnClickListener{
	
	public static ShowPopup Active_Instance;
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Active = true;
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Active = false;
		SmsReciver.Smsinfo.clear();
		SmsReciver.smsCounter=0;
		super.onStop();
	}

	public static ListView smsList;
	public static boolean Active ;
	//ArrayList<SMS> SMSclctn = new ArrayList<SMS>();
	public static TextToSpeech tts;
	String from;
	String msg;
	NotificationManager nm ;
	
	@SuppressLint({ "ParserError", "ParserError", "ParserError" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Active_Instance= this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		smsList =(ListView)findViewById(R.id.smslist);
		nm	= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(SmsReciver.nKey);
		ArrayList<SMS> SMSclctn = new ArrayList<SMS>(); 
				SMSclctn.addAll(SmsReciver.Smsinfo);
	
			smsList.setAdapter(new SMSAdapter(SMSclctn, getApplicationContext()));
		
		
		
	/*	TextView frm = (TextView) findViewById(R.id.frm);
		TextView msgbody = (TextView) findViewById(R.id.msg);
		Button yes = (Button)findViewById(R.id.yes);
		
		yes.setOnClickListener(this);
		
		
		
		tts = new TextToSpeech(this,new OnInitListener(){
			public void onInit(int status){
				if(status != TextToSpeech.ERROR){
					tts.setLanguage(Locale.US);
				}
			}
			
		});*/
	}

	/*@SuppressLint({ "ParserError", "ParserError" })
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.yes){
			tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null);
		}
			
	}*/

	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressLint({ "ParserError", "ParserError" })
	public static void showDialog(String mFrm,String mBody){
		Dialog show_sms = new Dialog(Active_Instance);
		//show_sms.requestWindowFeature(Window.FEATURE_NO_TITLE);
		show_sms.setTitle("SMS_Reader");
		show_sms.setContentView(R.layout.dialog);
		show_sms.setCancelable(true);

		final TextView smsFrom = (TextView)show_sms.findViewById(R.id.dfrm);
		final TextView smsBody = (TextView)show_sms.findViewById(R.id.dmsg);
		Button Read = (Button)show_sms.findViewById(R.id.dread);
		Button Reply = (Button)show_sms.findViewById(R.id.dreply);
		Button Cancel = (Button)show_sms.findViewById(R.id.dcancel);
		
		smsFrom.setText("FROM: "+mFrm);
		smsBody.setText(mBody);

		
		 tts = new TextToSpeech(Active_Instance,new OnInitListener(){
			public void onInit(int status){
				if(status != TextToSpeech.ERROR){
					tts.setLanguage(Locale.US);
				}
			}
			
		});
		Read.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tts.speak(smsBody.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
			}
		});
		
		

		Reply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
			
		
		
		Cancel.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			
			}
		});
	
		
		show_sms.show();
	}
	
}
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public static ArrayList<String> msgBody= new ArrayList<String>();
	public static ArrayList<String> Sender= new ArrayList<String>();
	public static String num,msg;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle gotB = getIntent().getExtras();
		msgBody= gotB.getStringArrayList("msg");
		Sender = gotB.getStringArrayList("sender");
		
		int lenmsg= msgBody.toArray().length;
		int lensender = Sender.toArray().length;
		int big = (lenmsg>lensender)?lenmsg:lensender;
		
		//showDialog(getApplicationContext());
		
		
		
	}
	
	
	
	public void showDialog(Context context){
		final Dialog readMsg   = new Dialog(context);
		//View v=LayoutInflater.from(context).inflate(my.com.smsreader.R.layout.dialog, null);
		 readMsg.setContentView(R.layout.dialog);
		 	TextView Frm = (TextView) readMsg.findViewById(my.com.smsreader.R.id.frm);
		 	Frm.setText("Hi there this is sms reader");
			TextView Msg = (TextView)  readMsg.findViewById(my.com.smsreader.R.id.frm);
			Msg.setText("this is msg body");
			Button yes = (Button)  readMsg.findViewById(my.com.smsreader.R.id.yes);
			Button no = (Button)  readMsg.findViewById(my.com.smsreader.R.id.no);
			no.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 readMsg.dismiss();
				
				}
			});
//		 .setContentView(v);
		// readMsg.show();
	}*/

