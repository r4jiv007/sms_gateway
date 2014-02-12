package my.app.sms_gateway;

import java.util.ArrayList;
import java.util.Random;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import my.app.service.MyService;

import my.app.sms_gateway.SMS;
public class SmsReciver extends BroadcastReceiver {
	 static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

		
	 	public static ArrayList<SMS> Smsinfo = new ArrayList<SMS>();
		Intent in;
		public static Bundle bundle = new Bundle();
		public static String frm="";
		public static String msg="";
		public final static int nKey=0;
		public static int smsCounter=0;
		
	public void onReceive(Context context, Intent intent) {
				
		// TODO Auto-generated method stub
				
	if(intent.getAction().equals(ACTION)){			
		Bundle bundle=intent.getExtras();
		smsCounter++;
		Object[] messages=(Object[])bundle.get("pdus");
		
		SmsMessage[] sms=new SmsMessage[messages.length];

		for(int n=0;n<messages.length;n++){
			sms[n]=SmsMessage.createFromPdu((byte[]) messages[n]);
		}
			
		for(SmsMessage x:sms){
				 msg = msg + (x.getDisplayMessageBody());
				frm=x.getDisplayOriginatingAddress();
				}
		
		displayNotification(context, frm, msg);
       	msg="";
		frm="";
	}
	
}
	public void displayNotification(Context context,String frm,String msg){
		NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		if(ShowPopup.Active==false){
			Intent i = new Intent(context,MyService.class);
            Bundle bd = new Bundle();
            bd.putCharSequence("fmr",frm);
            bd.putCharSequence("msg",msg);
            i.putExtra("sms",bd);
			PendingIntent pi = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
			Smsinfo.add(new SMS(frm, msg));
			
			
			if(smsCounter<2){
				Notification nf = new Notification(R.drawable.ic_launcher,frm+"\n"+msg,System.currentTimeMillis());
				nf.setLatestEventInfo(context, frm, msg,pi);
				nf.vibrate = new long[]{100,250,100,500};
				nm.notify(nKey,nf);
				
			}else if(smsCounter >= 2){
				Notification nf = new Notification(R.drawable.ic_launcher,frm+"\n"+msg,System.currentTimeMillis());
				nf.setLatestEventInfo(context, "New messages", String.valueOf(smsCounter)+" unread messages.",pi);
				nf.vibrate = new long[]{100,250,100,500};
				nm.notify(nKey,nf);
        			
			}
    
		}else if(ShowPopup.Active==true){
			Notification nf = new Notification(R.drawable.ic_launcher,frm+"\n"+msg,System.currentTimeMillis());
			Intent i = new Intent(context,ShowPopup.class);
			PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
			
			nf.setLatestEventInfo(context, frm, msg,pi);
			nf.flags |= Notification.FLAG_AUTO_CANCEL;
			nf.vibrate = new long[]{100,250,100,500};
			nm.notify(nKey,nf);
			Smsinfo.add(new SMS(frm, msg));
			Toast.makeText(context, "New message", Toast.LENGTH_LONG).show();
			ShowPopup.smsList.setAdapter(new SMSAdapter(Smsinfo,context));
			//nm.cancel(nKey);
		}
	}
		
}

