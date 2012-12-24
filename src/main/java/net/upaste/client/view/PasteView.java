package net.upaste.client.view;


public interface PasteView extends UPasteView, HasRecentPastes
{
	void setContent(String content);
	void setTitle(String title);
	void setEmail(String email);
	void setIsPrivate(boolean isPrivate);
	void clearAllFields();
}
