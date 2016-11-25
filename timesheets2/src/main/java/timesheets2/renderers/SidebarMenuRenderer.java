package timesheets2.renderers;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import net.bootsfaces.component.navLink.NavLink;
import timesheets2.components.SidebarMenu;

@FacesRenderer(componentFamily = "Navigation", rendererType = "SidebarMenuNavigation")
public class SidebarMenuRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		SidebarMenu menu = (SidebarMenu) component;

		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("nav", component);
		writer.writeAttribute("class", "ts-sidebar", "styleClass");

	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("nav");
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		SidebarMenu menu = (SidebarMenu) component;
		int childCount = menu.getChildCount();
		if (childCount == 0) {
			return;
		}

		String appPath = context.getExternalContext().getApplicationContextPath();
		String viewId = context.getViewRoot().getViewId();
		String path = appPath + viewId;	//complete page path

		ResponseWriter writer = context.getResponseWriter();
		
		List<UIComponent> children = menu.getChildren();
		writer.startElement("ul", menu);
		writer.writeAttribute("class", "ts-sidebar-menu", null);

		for (UIComponent c : children) {
			writer.startElement("li", menu);

			if (c instanceof NavLink) {
				NavLink navLink = (NavLink) c;
				String href = navLink.getHref().toLowerCase();
				
				if (path.equalsIgnoreCase(href)) {
					writer.writeAttribute("class", "open", null);
				}
			}
			
			c.encodeBegin(context);
			c.encodeChildren(context);
			c.encodeEnd(context);

			writer.endElement("li");
		}

		writer.endElement("ul");
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

}
