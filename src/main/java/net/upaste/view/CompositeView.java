package net.upaste.view;

import net.upaste.view.impl.NullLeaf;

/**
 * Composite
 * @author Robert Bost <bostrt@gmail.com>
 */
public abstract class CompositeView extends View {

	private View child;
	
	public CompositeView(View child) {
		this.child = child;
	}
	
	public View getChild() {
		if(this.child == null) {
			this.child = new NullLeaf();
		}
		return this.child;
	}
}
