package net.upaste.client.component;

import java.util.Date;

import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class RecentPasteLink extends Composite {

	private static RecentPasteLinkUiBinder uiBinder = GWT
			.create(RecentPasteLinkUiBinder.class);

	interface RecentPasteLinkUiBinder extends UiBinder<Widget, RecentPasteLink> {
	}

	@UiField
	Anchor anchor;
	@UiField
	HTML timeDelta;

	public RecentPasteLink() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPaste(Paste paste)
	{
		String title;
		String fullTitle = null;
		if(paste.getTitle() == null || paste.getTitle().trim().isEmpty()) {
			title = "Unknown";
			fullTitle = "No paste title was given.";
		} else if(paste.getTitle().length() > 20){
			fullTitle = SafeHtmlUtils.htmlEscape(paste.getTitle());
			title = SafeHtmlUtils.htmlEscape(paste.getTitle().substring(0, 20)) + "...";
		} else {
			title = SafeHtmlUtils.htmlEscape(paste.getTitle());
		}
		anchor.setHTML(SafeHtmlUtils.htmlEscape(title));
		anchor.setHref("#paste:" + paste.getID());
		
		if(fullTitle != null) {
			anchor.setTitle(fullTitle);
		}

		// TODO: This needs to reflect actual time delta between now and time of pasting
		timeDelta.setText(getNiceTime(paste));
	}

	public String getReadableAddedOn(Paste paste) {
		DateTimeFormat format = DateTimeFormat.getFormat("MMM d, yyyy h:m a");
		return format.format(paste.getAddedOn());
	}
	
	/**
	 * Get how long ago this paste was added in nice terms like 
	 * "1 min ago", "5 mins ago", "1 hour ago", "Yesterday"
	 */
	public String getNiceTime(Paste paste) {
		JsDate nowDate = JsDate.create();
		Date addedOn = paste.getAddedOn();
		DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
		String addedOnStr = format.format(addedOn); 
		String[] dateSplit = addedOnStr.substring(0, 10).split("-");
		
		// Is more than a year ago?
		if(nowDate.getUTCFullYear() > Integer.parseInt(dateSplit[0])) {
			return getReadableAddedOn(paste);
		}
		
		// Is more than a month ago
		if(nowDate.getUTCMonth() > Integer.parseInt(dateSplit[1])) {
			return getReadableAddedOn(paste);
		}
		
		// Is more than two days ago
		if(nowDate.getUTCDate() >= (Integer.parseInt(dateSplit[2]) + 2)) {
			return getReadableAddedOn(paste);
		}
		
		String[] hoursSplit = addedOnStr.substring(11).split(":");

		// Was yesterday
		if(nowDate.getUTCDate() == (Integer.parseInt(dateSplit[2]) + 1) && nowDate.getUTCHours() >= (Integer.parseInt(hoursSplit[0]) + 24)) {
			return "Yesterday";
		}
		
		// Was hours ago
		int hourDiff = nowDate.getUTCHours() - Integer.parseInt(hoursSplit[0]);
		if(hourDiff < 24) {
			if(hourDiff == 1) {
				return "1 hour ago";
			} else {
				return hourDiff + " hours ago";
			}
		}
		
		// Was minutes ago
		int minDiff = nowDate.getUTCMinutes() - Integer.parseInt(hoursSplit[1]);
		if(minDiff < 60) {
			if(minDiff == 1) {
				return "a minute ago";
			} else {
				return minDiff + " minutes ago";
			}
		}
		
		return "just now";
	}
}
