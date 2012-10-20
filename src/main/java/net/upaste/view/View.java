package net.upaste.view;

import java.lang.reflect.Method;

import net.upaste.view.annotation.Template;
import net.upaste.view.annotation.TemplateMethod;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;

/**
 * Component
 * @author Robert Bost <bostrt@gmail.com> 
 */
public abstract class View
{
	private STGroup stgroup = null;
	
	public String render() {
		STGroup g = new STGroupDir("stg", '$', '$');
		ST st = g.getInstanceOf("base");
		st.add("head", head());
		st.add("body", body());
		return st.render();
	}
	
	
	public String head() {
		return head(getHeadST());
	}
	
	public String body() {
		return body(getBodyST());
	}
	
	/**
	 * Read the {@link TemplateMethod} annotation from {@link View#head(ST)} method.
	 * Using the parameter to {@link TemplateMethod} or just use "head" if no param is given, 
	 * pull the template from the STGroup from {@link View#getSTGroup()}.
	 * 
	 * @return {@link ST}
	 */
	public ST getHeadST() {
		Class<? extends View> clazz = this.getClass();
		try {
			Method headMethod = clazz.getMethod("head", ST.class);
			TemplateMethod headSTAnno = headMethod.getAnnotation(TemplateMethod.class);
			// Default template group name is "head"
			String templateHead = "head";
			if(headSTAnno.getName() != null && !headSTAnno.getName().isEmpty()) {
				// If a parameter is given to HeadST annotation, pull it out.
				templateHead = headSTAnno.getName();
			}
			
			STGroup stg = getSTGroup();
			return stg.getInstanceOf(templateHead);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Read the {@link TemplateMethod} annotation from {@link View#body(ST)} method.
	 * Using the parameter to {@link TemplateMethod} or just use "body" if no param is given, 
	 * pull the template from the STGroup from {@link View#getSTGroup()}.
	 * 
	 * @return {@link ST}
	 */
	public ST getBodyST() {
		Class<? extends View> clazz = this.getClass();
		try {
			Method bodyMethod = clazz.getMethod("body", ST.class);
			TemplateMethod bodySTAnno = bodyMethod.getAnnotation(TemplateMethod.class);
			// Default template group name is "body"
			String templateHead = "body";
			if(bodySTAnno.getName() != null && !bodySTAnno.getName().isEmpty()) {
				// If a parameter is given to HeadST annotation, pull it out.
				templateHead = bodySTAnno.getName();
			}
			
			STGroup stg = getSTGroup();
			if(stg == null) {
				return null;
			}
			return stg.getInstanceOf(templateHead);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Read the {@link Template} annotation. Build STGroup.
	 * @return {@link STGroup}
	 */
	public STGroup getSTGroup() {
		if(stgroup != null) {
			return stgroup;
		}
		
		Class<? extends View> clazz = this.getClass();
		Template templateAnno = clazz.getAnnotation(Template.class);
		// Must have Template definition
		assert(templateAnno != null);
		
		String fileName = templateAnno.value();
		// Must have Template parameter
		assert(fileName != null && fileName.isEmpty());
		
		try {
			stgroup = new STGroupFile("stg/"+fileName, '$', '$');
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return stgroup;
	}
	
	public abstract String head(ST headST);
	public abstract String body(ST bodyST);

}
