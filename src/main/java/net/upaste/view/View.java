package net.upaste.view;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;

/**
 * Component
 * @author Robert Bost <bostrt@gmail.com> 
 */
public abstract class View {
	
	public String render() {
		STGroup g = new STGroupDir("stg", '$', '$');
		ST st = g.getInstanceOf("basebase");
		st.add("head", head());
		st.add("body", body());
		return st.render();
	}
	
	public abstract String head();
	public abstract String body();
}
