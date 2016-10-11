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
		System.out.println("Menu children count: " + childCount);
		if (childCount == 0)
			return;

		String viewId = context.getViewRoot().getViewId();
		System.out.println("View ID: " + viewId);

		ResponseWriter writer = context.getResponseWriter();
		List<UIComponent> children = menu.getChildren();
		writer.startElement("ul", menu);
		writer.writeAttribute("class", "ts-sidebar-menu", null);

		for (UIComponent c : children) {
			System.out.println("child " + c.getClass().getName());
			writer.startElement("li", menu);

			if (c instanceof NavLink) {
				NavLink navLink = (NavLink) c;
				String href = navLink.getHref();
				System.out.println("nav link with href " + href);
				
				if (viewId.equalsIgnoreCase("/" + href)) {
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
