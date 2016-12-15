package ru.lephant.learning.spring.SomeFirmWebFlow.plan.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Plan;

import java.util.List;

/**
 * Created by lephant on 13.12.2016.
 */
public interface PlanService {

    public Plan getPlanById(long id);

    public List listPlan();

    public void deletePlan(long id);

    public void savePlan(Plan plan);

}
