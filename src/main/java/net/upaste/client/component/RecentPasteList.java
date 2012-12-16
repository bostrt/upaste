package net.upaste.client.component;

import java.util.List;

import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RecentPasteList extends Composite {

	private static RecentPasteListUiBinder uiBinder = GWT
			.create(RecentPasteListUiBinder.class);

	interface RecentPasteListUiBinder extends UiBinder<Widget, RecentPasteList> {
	}

	@UiField
	VerticalPanel recentPastePanel;
	@UiField
	Anchor moreAnchor;
	
	private List<Paste> recentPasteList;

	public RecentPasteList() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setRecentPasteList(List<Paste> recentPasteList)
	{
		this.recentPasteList = recentPasteList;
		
		recentPastePanel.clear();
		RecentPasteLink link;
		for(Paste p : recentPasteList)
		{
			link = new RecentPasteLink();
			link.setPaste(p);
			recentPastePanel.add(link);
		}
	}
}
