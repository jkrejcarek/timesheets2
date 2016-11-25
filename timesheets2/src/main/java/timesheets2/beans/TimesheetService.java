package timesheets2.beans;

import java.io.Serializable;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import timesheets2.model.Activity;
import timesheets2.model.Project;
import timesheets2.model.Timesheet;



/**
 * Jednotne misto pro praci s databazi. Nic jineho k databazi nepristupuje.
 * Trida je Singleton, je vytvorena jen jednou kontejnerem a spravovana podle potreby
 * @author Jan Krejcarek
 *
 */
@Singleton
public class TimesheetService implements Serializable {

    private static final long serialVersionUID = 6089364104209696364L;
	@PersistenceContext(unitName = "timesheets2")	//odkazuje na nazev definice Persistent Unit v souboru persistence.xml
    private EntityManager entityManager;

    /**
     * Prida do databaze novy vykaz
     * @param timesheet Vykaz, ktery bude ulozen do databaze
     */
    public void addTimesheet(Timesheet timesheet) {
    	entityManager.persist(timesheet);
    }
    
    /**
     * Ulozi novy projekt do databaze
     * @param project
     */
    public void add(Project project) {
    	entityManager.persist(project);
    }
    
    public void add(Activity activity) {
    	entityManager.persist(activity);
    }
    
    /**
     * Aktualizuje existujici vykaz v databazi
     * @param timesheet Vykaz pro aktualizaci, uz musel v databazi existovat
     */
    public void updateTimesheet(Timesheet timesheet) {
    	entityManager.merge(timesheet);
    }

    /**
     * @return Seznam vsech vykazu v databazi
     */
    public List<Timesheet> getAllTimesheets() {
        CriteriaQuery<Timesheet> cq = entityManager.getCriteriaBuilder().createQuery(Timesheet.class);
        cq.select(cq.from(Timesheet.class));
        return entityManager.createQuery(cq).getResultList();
    }
 
    /**
     * Seznam poslednich 15 vykazu serazenych sestupne podle data.
     * Vyuziva Criteria API pro sestaveni dotazu
     * @return Seznam vykazu
     */
    public List<Timesheet> getLastTimesheets() {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	
    	CriteriaQuery<Timesheet> cq = builder.createQuery(Timesheet.class);
    	Root<Timesheet> t = cq.from(Timesheet.class);
    	cq.select(t);
    	cq.orderBy(builder.desc(t.get("date")));
    	
    	TypedQuery<Timesheet> query = entityManager.createQuery(cq);
    	query.setMaxResults(15);
    	
    	return query.getResultList();
    }
    
    /**
     * @return Seznam vsech definovanych projektu (pro vyber projektu pri vlozeni vykazu)
     */
    public List<Project> getAllProjects() {
        CriteriaQuery<Project> cq = entityManager.getCriteriaBuilder().createQuery(Project.class);
        cq.select(cq.from(Project.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
    /**
     * @return Seznam vsech definovanych aktivit (pro vyber aktivity pri vlozeni vykazu)
     */
    public List<Activity> getAllActivities() {
    	CriteriaQuery<Activity> cq = entityManager.getCriteriaBuilder().createQuery(Activity.class);
    	cq.select(cq.from(Activity.class));
    	List<Activity> result = entityManager.createQuery(cq).getResultList();
    	
    	return result;
    }
    
    /**
     * Vraci vykaz s urcenym ID. Vyuzito pri editaci konkretniho vykazu
     * @param id ID vykazu
     * @return Vykaz s uvedenym ID
     */
    public Timesheet getTimesheet(int id) {		
		return entityManager.find(Timesheet.class, id);
	}
    
    public Project getProject(int id) {
    	return entityManager.find(Project.class, id);
    }
    public Activity getActivity(int id) {
    	return entityManager.find(Activity.class, id);
    }
    
    public void update(Project project) {
    	entityManager.merge(project);
    }
    
    public void update(Activity activity) {
    	entityManager.merge(activity);
    }
}
