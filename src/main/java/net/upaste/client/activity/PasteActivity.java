package net.upaste.client.activity;

import java.util.List;

import net.upaste.client.ClientFactory;
import net.upaste.client.PasteService;
import net.upaste.client.PasteServiceAsync;
import net.upaste.client.place.PastePlace;
import net.upaste.client.view.PasteView;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PasteActivity extends AbstractUPasteActivity<PastePlace>
{
	private PasteServiceAsync service;
	private PasteView view;
	
	public PasteActivity(PastePlace place, ClientFactory clientFactory)
	{
		super(place, clientFactory);
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus)
	{
		service = GWT.create(PasteService.class);
		view = clientFactory.getPasteView();
		view.clearAllFields();

		service.loadPaste(place.getPasteId(), new AsyncCallback<Paste>() {
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage());
			}
			@Override
			public void onSuccess(Paste result) {
				initializeView(result);
				// Prettify the content
				prettyPrint();
				
			}
		});
		
		service.getRecentPastes(10, new AsyncCallback<List<Paste>>() {
			@Override
			public void onSuccess(List<Paste> result) {
				view.setRecentPasteList(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage());
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
	
	private static native void prettyPrint()/*-{
		$wnd.prettyPrint();
	}-*/;
}
