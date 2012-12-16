package net.upaste.client.view;

import java.util.List;

import net.upaste.shared.data.model.Paste;

public interface HasRecentPastes {
	void setRecentPasteList(List<Paste> pastes);
}
