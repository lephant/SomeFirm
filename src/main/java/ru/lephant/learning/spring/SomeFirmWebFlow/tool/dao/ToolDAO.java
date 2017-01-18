package ru.lephant.learning.spring.SomeFirmWebFlow.tool.dao;

import org.hibernate.exception.ConstraintViolationException;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ToolType;

import java.util.List;

public interface ToolDAO {

    public ToolType getToolByPressmark(long pressmark);

    public List listTool();

    public void createTool(ToolType tool)
            throws ConstraintViolationException;

    public void updateTool(ToolType tool)
            throws ConstraintViolationException;

    public void deleteTool(long pressmark)
            throws ConstraintViolationException;

}
