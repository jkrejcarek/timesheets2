package timesheets2.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;


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

    public List<Timesheet> getAllTimesheets() {
        CriteriaQuery<Timesheet> cq = entityManager.getCriteriaBuilder().createQuery(Timesheet.class);
        cq.select(cq.from(Timesheet.class));
        return entityManager.createQuery(cq).getResultList();
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
    	
    	System.out.println("Found activities: " + result.toString());
    	
    	return result;
    }
    
}
