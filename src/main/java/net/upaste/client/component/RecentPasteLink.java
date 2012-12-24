package net.upaste.client.component;

import net.upaste.client.place.PastePlace;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.client.GWT;
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
		timeDelta.setText("1 hour ago");
	}
}
