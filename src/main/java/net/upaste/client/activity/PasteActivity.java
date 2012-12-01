package net.upaste.client.activity;

import net.upaste.client.ClientFactory;
import net.upaste.client.PasteService;
import net.upaste.client.PasteServiceAsync;
import net.upaste.client.place.PastePlace;
import net.upaste.client.view.PasteView;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PasteActivity extends AbstractActivity
{
	private ClientFactory clientFactory;
	private PasteServiceAsync service;
	private PastePlace place;
	private PasteView view;
	
	public PasteActivity(PastePlace place, ClientFactory clientFactory)
	{
		this.clientFactory = clientFactory;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus)
	{
		service = GWT.create(PasteService.class);
		view = clientFactory.getPasteView();
		
		service.loadPaste(place.getPasteId(), new AsyncCallback<Paste>() {
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage());
			}
			@Override
			public void onSuccess(Paste result) {
				initializeView(result);
			}
		});
		
		panel.setWidget(view);
	}
	
	private void initializeView(Paste paste)
	{
		if(paste.getEmail() != null)
			view.setEmail(paste.getEmail());
		if(paste.getContent() != null)
			view.setContent(paste.getContent());
		if(paste.getTitle() != null)
			view.setTitle(paste.getTitle());
		view.setIsPrivate(paste.isPrivate());
	}
}
