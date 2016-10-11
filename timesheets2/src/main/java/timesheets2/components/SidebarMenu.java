package timesheets2.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

@FacesComponent("SidebarMenu")
public class SidebarMenu extends UIComponentBase {

	@Override
	public String getFamily() {
		return "Navigation";
	}

}
