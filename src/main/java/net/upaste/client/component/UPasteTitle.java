package net.upaste.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class UPasteTitle extends Composite implements HasClickHandlers 
{

	private static UPasteTitleUiBinder uiBinder = GWT
			.create(UPasteTitleUiBinder.class);

	interface UPasteTitleUiBinder extends UiBinder<Widget, UPasteTitle> {
	}

	@UiField
	FocusPanel panel;
	
	public UPasteTitle() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler arg0) {
		return panel.addClickHandler(arg0);
	}
}
