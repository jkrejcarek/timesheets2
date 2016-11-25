package timesheets2.beans;

import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Poskytuje informace o prihlasenem uzivateli. Lze pouzit pro snadne skryvani/zobrazeni
 * prvku UI, kdyz je uzivatel prihlasen nebo ma urcitou roli.
 * V JSF strankach k dispozici jako #{securityBean}
 * @author Jan Krejcarek
 *
 */
@Named(value="securityBean")
@RequestScoped
public class SecurityBean {

	/**
	 * @return aktualni request
	 */
	private HttpServletRequest getRequest() {
		 return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * @return Jmeno uzivatele, ktery je prihlasen nebo prazdny retezec, pokud neni
	 */
	public String getUserName() {
		HttpServletRequest req = getRequest();
		Principal principal = req.getUserPrincipal();
		if (principal != null) {
			return principal.getName();
		} else {
			return "";
		}
	}

	/**
	 * @return True, pokud je identita uzivatele overena, jinak false
	 */
	public boolean isLoggedIn() {
		return getRequest().getUserPrincipal() != null;
	}
	
}
