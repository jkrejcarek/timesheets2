package timesheets2.beans;

import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value="securityBean")
@RequestScoped
public class SecurityBean {

	private HttpServletRequest getRequest() {
		 return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public String getUserName() {
		HttpServletRequest req = getRequest();
		Principal principal = req.getUserPrincipal() ;
		if (principal != null) {
			return principal.getName();
		} else {
			return "";
		}
	}

	public boolean isLoggedIn() {
		return getRequest().getUserPrincipal() != null;
	}
	
}
