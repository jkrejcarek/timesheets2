package timesheets2.model;

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



@Singleton
public class TimesheetService implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6089364104209696364L;
	@PersistenceContext(unitName = "timesheets2")
    private EntityManager entityManager;

    public void addTimesheet(Timesheet timesheet) {
    	System.out.println("Service adding timesheet with id " + timesheet.getId());
      entityManager.persist(timesheet);
    }
    
    public void updateTimesheet(Timesheet timesheet) {
    	entityManager.merge(timesheet);
    }

    public List<Timesheet> getAllTimesheets() {
        CriteriaQuery<Timesheet> cq = entityManager.getCriteriaBuilder().createQuery(Timesheet.class);
        cq.select(cq.from(Timesheet.class));
        return entityManager.createQuery(cq).getResultList();
    }
 
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
    
    public List<Project> getAllProjects() {
        CriteriaQuery<Project> cq = entityManager.getCriteriaBuilder().createQuery(Project.class);
        cq.select(cq.from(Project.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
    public List<Activity> getAllActivities() {
    	CriteriaQuery<Activity> cq = entityManager.getCriteriaBuilder().createQuery(Activity.class);
    	cq.select(cq.from(Activity.class));
    	List<Activity> result = entityManager.createQuery(cq).getResultList();
    	
    	return result;
    }
    
    public Timesheet getTimesheet(int id) {		
		return entityManager.find(Timesheet.class, id);
	}
    
}
