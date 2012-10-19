package net.upaste.view;

/**
 * Composite
 * @author Robert Bost <bostrt@gmail.com>
 */
public abstract class CompositeView<CHILD extends View> extends View {

	private CHILD child;
	
	public CompositeView(CHILD child) {
		this.child = child;
	}
	
	public CHILD getChild() {
		return this.child;
	}
}
