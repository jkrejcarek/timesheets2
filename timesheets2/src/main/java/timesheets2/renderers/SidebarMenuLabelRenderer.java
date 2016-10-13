package timesheets2.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import timesheets2.components.SidebarMenuLabel;

@FacesRenderer(componentFamily = "Navigation", rendererType = "SidebarMenuLabel")
public class SidebarMenuLabelRenderer extends Renderer {
/* 		<li class="ts-label">Search</li>*/
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		SidebarMenuLabel label = (SidebarMenuLabel) component;

		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("li", label);
		writer.writeAttribute("class", "ts-label", "styleClass");

	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("li");
	}
}
