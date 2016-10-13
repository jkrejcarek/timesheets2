package timesheets2.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import timesheets2.components.SidebarMenuLabel;
import timesheets2.components.SidebarMenuSearch;

@FacesRenderer(componentFamily = "Navigation", rendererType = "SidebarMenuSearch")
public class SidebarMenuSearchRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		SidebarMenuSearch search = (SidebarMenuSearch) component;
		if (search.getChildCount() > 0) {
			for (UIComponent uiComponent : search.getChildren()) {
				if (uiComponent instanceof SidebarMenuLabel) {
					uiComponent.encodeAll(context);
				}
			}
		}
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		SidebarMenuSearch search = (SidebarMenuSearch) component;
		ResponseWriter writer = context.getResponseWriter();
		
		String cid = component.getClientId(context)+"_search"; // $NON-NLS-1$

		writer.startElement("li", search);
		writer.startElement("input", search);
		writer.writeAttribute("id",cid,null);
		writer.writeAttribute("class", "ts-sidebar-search", "styleClass");
		writer.writeAttribute("placeholder", "Search...", null);

		String submitSearch = "_tsAppSearchSubmit_" + cid; // $NON-NLS-1$
        writer.writeAttribute("onkeypress","javascript:var kc=event.keyCode?event.keyCode:event.which;if(kc==13){"+submitSearch+"(); return false}",null); // $NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
        writer.endElement("input");
		
		writer.endElement("li");

		String path = search.getTarget();
		if (path == null || path.length() == 0) {
			path = "search.xhtml";
		}
		
		writer.startElement("script",component);
		writer.writeAttribute("type", "text/javascript", null);
		StringBuilder sb = new StringBuilder();
		sb.append("function ");
		sb.append(submitSearch);
		sb.append("(){");
		sb.append("var val= $(\"#" + cid + "\")[0].value"); //$NON-NLS-1$
		sb.append(";"); // $NON-NLS-1$
		
		sb.append("if(val){var loc=\""); // $NON-NLS-1$
		String loc;
		String queryParam = search.getParameterName();
		if (queryParam == null || queryParam.length() == 0) {
			queryParam = "q";
		}
		
		loc = path + "?"+queryParam+"=";
		sb.append(loc);
		sb.append("\" + encodeURIComponent(val)"); // $NON-NLS-1$
		sb.append(";");

        sb.append("window.location.href=loc;}}"); // $NON-NLS-1$
        writer.writeText(sb.toString(),null);
        
		writer.endElement("script");
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	
	
}
