/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package timesheets2.model;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;


@Stateless
public class TimesheetService implements Serializable {

    @PersistenceContext(unitName = "timesheets2")
    private EntityManager entityManager;

    public void addTimesheet(Timesheet timesheet) {
      entityManager.persist(timesheet);
    }

    public List<Timesheet> getAllTimesheets() {
        CriteriaQuery<Timesheet> cq = entityManager.getCriteriaBuilder().createQuery(Timesheet.class);
        cq.select(cq.from(Timesheet.class));
        return entityManager.createQuery(cq).getResultList();
    }
    

}
