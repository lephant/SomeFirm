package ru.lephant.learning.spring.SomeFirmWebFlow.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Plan;
import ru.lephant.learning.spring.SomeFirmWebFlow.plan.dao.PlanDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.plan.service.PlanService;

import java.io.Serializable;
import java.util.List;

@Service("planService")
public class PlanServiceImpl implements PlanService, Serializable {

    @Autowired
    PlanDAO planDAO;

    @Transactional(readOnly = true)
    public Plan getPlanById(long id) {
        return planDAO.getPlanById(id);
    }

    @Transactional(readOnly = true)
    public List listPlan() {
        return planDAO.listPlan();
    }

    @Transactional
    public void deletePlan(long id) {
        planDAO.deletePlan(id);
    }

    @Transactional
    public void savePlan(Plan plan) {
        planDAO.savePlan(plan);
    }
}
