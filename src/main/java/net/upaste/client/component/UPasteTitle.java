package net.upaste.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UPasteTitle extends Composite {

	private static UPasteTitleUiBinder uiBinder = GWT
			.create(UPasteTitleUiBinder.class);

	interface UPasteTitleUiBinder extends UiBinder<Widget, UPasteTitle> {
	}

	public UPasteTitle() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
