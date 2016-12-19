package ru.lephant.learning.spring.SomeFirmWebFlow.tool.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ToolType;
import java.util.List;

public interface ToolDAO {

    public ToolType getToolByPressmark(long pressmark);

    public List listTool();

    public void saveTool(ToolType tool);

    public void deleteTool(long pressmark);

}
