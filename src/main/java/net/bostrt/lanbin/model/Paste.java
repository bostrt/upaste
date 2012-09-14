package net.bostrt.lanbin.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringEscapeUtils;

@Entity
@Table(name = "Paste")
@NamedQueries({
	@NamedQuery(name = "RecentPastes", query = "SELECT p FROM Paste p WHERE p.isPrivate = false ORDER BY p.addedOn DESC"),
	@NamedQuery(name = "ByUUID", query = "SELECT p FROM Paste p WHERE p.uuid = :uuid"),
})
public class Paste extends BaseModel {

	@Column(name = "Title")
	private String title;
	
	@Column(name = "Content", length=8086)
	private String content;
	
	@Column(name = "Private")
	private boolean isPrivate = false;
	
	@Column(name = "UUID")
	private String uuid;
	
	@Column(name = "AddedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn = Calendar.getInstance().getTime();
	
	@Column(name = "HighlightType")
	private String highlightType;
	
	@Column(name = "Email")
	private String email;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	public void setHighlightType(String highlightType) {
		this.highlightType = highlightType;
	}
	public String getHighlightType() {
		return this.highlightType;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	
	public String getSafeContent() {
		return StringEscapeUtils.escapeHtml(this.content);
	}
	
	/**
	 * Get how long ago this paste was added in nice terms like 
	 * "1 min ago", "5 mins ago", "1 hour ago", "Yesterday"
	 */
	public String getNiceTime() {
		Calendar nowCal = Calendar.getInstance();
		Date now = nowCal.getTime();
		
		long difference = (now.getTime() - this.addedOn.getTime())/1000;
		SimpleDateFormat format;
		// Was this paste more than or exactly an hour ago?
		if(isSameDay(this.addedOn) && difference < 86400) {
			double minDiff = difference / 60;
			if(minDiff <= .5) {
				return "just now";
			}
			if(minDiff < 60) {
				// Difference is only minutes.
				return (int)minDiff + " min" + (minDiff == 1 ? "" : "s");
			}
			// Difference is an hour or more
			double hourDiff = minDiff / 60;
			return (int)hourDiff + " hour" + ((int)hourDiff == 1 ? "" : "s");
		}

		// Was this paste before yesterday?
		if(isBeforeYesterday(this.addedOn)) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(this.addedOn);
		}

		// Was this paste on yesterday?
		if(isYesterday(this.addedOn)) {
			return "Yesterday";
		}

		format = new SimpleDateFormat("yyyy-M-d");
		return format.format(this.addedOn);
	}

	/**
	 * Is the given {@link Date} before yesterday?<br/>
	 * Example:<br/>
	 * If today is Wednesday then this method will return
	 * true if the given date is Monday. This method will return
	 * true if the given date is Tuesday!
	 * 
	 * @param date
	 * @return boolean True if the given date is before yesterday.
	 * 				False otherwise.
	 */
	private boolean isBeforeYesterday(Date date) {
		Calendar cal = Calendar.getInstance();
		Calendar paramCal = Calendar.getInstance();
		paramCal.setTime(date);

		// Is last year?
		int thisYear = cal.get(Calendar.YEAR);
		int paramYear = paramCal.get(Calendar.YEAR);
		if(thisYear > paramYear) {
			return true;
		}

		// Is last month?
		int thisMonth = cal.get(Calendar.MONTH);
		int paramMonth = paramCal.get(Calendar.MONTH);
		if(thisMonth > paramMonth) {
			return true;
		}
		
		// Is 2 days ago?
		int thisDay = cal.get(Calendar.DAY_OF_MONTH);
		int paramDay = paramCal.get(Calendar.DAY_OF_MONTH);
		if(thisDay > (paramDay + 1)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Return true if the given date was yesterday.
	 * @param date
	 * @return boolean True if the given date is yesterday.
	 */
	private boolean isYesterday(Date date) {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		Calendar paramCal = Calendar.getInstance();
		paramCal.setTime(date);
		
		int yesterdayDay = yesterday.get(Calendar.DAY_OF_MONTH);
		int paramDay = paramCal.get(Calendar.DAY_OF_MONTH);
		
		if(yesterdayDay == paramDay) {
			return true;
		}
		
		return false;
	}

	/**
	 * Is the given date on the same day of the month as today? 
	 * This is not affected by year! Only DAY_OF_MONTH
	 * @param date
	 * @return True if the given day of month as today.
	 */
	private boolean isSameDay(Date date) {
		Calendar cal = Calendar.getInstance();
		Calendar paramCal = Calendar.getInstance();
		paramCal.setTime(date);
		
		int todayDay = cal.get(Calendar.DAY_OF_MONTH);
		int paramDay = paramCal.get(Calendar.DAY_OF_MONTH);
		if(todayDay == paramDay) {
			return true;
		}
		
		return false;
	}
}
