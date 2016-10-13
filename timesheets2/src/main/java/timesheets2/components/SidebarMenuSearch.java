package timesheets2.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

@FacesComponent("SidebarMenuSearch")
public class SidebarMenuSearch extends UIComponentBase {
	private String target;
	private String parameterName;
	
	@Override
	public String getFamily() {
		return "Navigation";
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	@Override
	public Object saveState(FacesContext context) {
		Object values[] = new Object[3];
	    values[0] = super.saveState(context);
	    values[1] = target;
	    values[2] = parameterName;
	    return (values);
	}

	@Override
	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
	    super.restoreState(context, values[0]);
	    target = (String) values[1];
	    parameterName = (String) values[2];
	}
	
	

}
