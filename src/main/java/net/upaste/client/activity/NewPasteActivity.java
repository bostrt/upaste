package net.upaste.client.activity;

import java.util.List;

import net.upaste.client.ClientFactory;
import net.upaste.client.PasteService;
import net.upaste.client.PasteServiceAsync;
import net.upaste.client.view.NewPasteView;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

public class NewPasteActivity extends AbstractActivity implements NewPasteView.Presenter
{
	public ClientFactory clientFactory;
	private NewPasteView view;
	private PasteServiceAsync service;
	
	public NewPasteActivity(ClientFactory clientFactory)
	{
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus)
	{
		view = clientFactory.getNewPasteView();
		service = GWT.create(PasteService.class);

		service.getRecentPastes(10, new AsyncCallback<List<Paste>>() {
			@Override
			public void onSuccess(List<Paste> result) {
				view.setRecentPastes(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage());
			}
		});
		
		view.addSubmitHandler(new SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				Paste p = new Paste();
				p.setTitle(view.getTitle());
				p.setEmail(view.getEmail());
				p.setContent(view.getContent());
				p.setPrivate(view.getIsPrivate());
				service.addPaste(p, new AsyncCallback<Paste>() {
					@Override
					public void onSuccess(Paste result) {
						GWT.log("SUCCESS!");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						GWT.log(caught.getMessage());
					}
				});
				event.cancel();
			}
		});

		// Put view on screen
		panel.setWidget(view);
	}

	@Override
	public void goTo(Place place)
	{
		clientFactory.getPlaceController().goTo(place);
	}
}
