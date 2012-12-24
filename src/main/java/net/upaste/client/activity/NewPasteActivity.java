package net.upaste.client.activity;

import java.util.List;

import net.upaste.client.ClientFactory;
import net.upaste.client.PasteService;
import net.upaste.client.PasteServiceAsync;
import net.upaste.client.place.NewPastePlace;
import net.upaste.client.place.PastePlace;
import net.upaste.client.view.NewPasteView;
import net.upaste.shared.data.model.Paste;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

public class NewPasteActivity extends AbstractUPasteActivity<NewPastePlace> implements NewPasteView.Presenter
{
	private NewPasteView view;
	private PasteServiceAsync service;
	
	public NewPasteActivity(NewPastePlace place, ClientFactory clientFactory)
	{
		super(place, clientFactory);
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus)
	{
		view = clientFactory.getNewPasteView();
		service = GWT.create(PasteService.class);
		view.clearAllFields();

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
						PastePlace place = new PastePlace(result.getID());
						goTo(place);
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
}
