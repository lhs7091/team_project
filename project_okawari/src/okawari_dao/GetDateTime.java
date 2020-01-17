package okawari_dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GetDateTime {

	public String getDate() {
		Date now = new Date();
		SimpleDateFormat sdf4 = new SimpleDateFormat("yy/MM/dd");
		sdf4.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

		return sdf4.format(now);
	}

	public String getTime() {
		Date now = new Date();
		SimpleDateFormat sdf4 = new SimpleDateFormat("HH:mm:ss");
		sdf4.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

		return sdf4.format(now);
	}
	
	// 밀리세컨드 들어오면 날짜 스트링 타입으로 바꿔줘요..
	public String getDateString(long time) {
		SimpleDateFormat sdf4 = new SimpleDateFormat("yy/MM/dd");
		String date = sdf4.format(time);
		return date;
	}
	
	// 날짜 스트링 타입을 넣으면 밀리세컨드로 바꿔줘요..
	public long getMilisecond(String date) {
		Date now = new Date();
		SimpleDateFormat sdf4 = new SimpleDateFormat("yy/MM/dd");
		try {
			now = sdf4.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return now.getTime();
	}
	
	// 하루에 해당하는 밀리세컨드 계산
	public long getOneDay() {
		return 1000*24*60*60;
	}

}
