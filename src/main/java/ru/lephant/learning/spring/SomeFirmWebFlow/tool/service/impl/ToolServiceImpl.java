package ru.lephant.learning.spring.SomeFirmWebFlow.tool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ToolType;
import ru.lephant.learning.spring.SomeFirmWebFlow.tool.dao.ToolDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.tool.service.ToolService;

import java.util.List;

@Service(value = "toolService")
public class ToolServiceImpl implements ToolService {

    @Autowired
    ToolDAO toolDAO;

    @Transactional(readOnly = true)
    public ToolType getToolByPressmark(long pressmark) {
        return toolDAO.getToolByPressmark(pressmark);
    }

    @Transactional(readOnly = true)
    public List listTool() {
        return toolDAO.listTool();
    }

    @Transactional
    public void saveTool(ToolType tool) {
        toolDAO.saveTool(tool);
    }

    @Transactional
    public void deleteTool(long pressmark) {
        toolDAO.deleteTool(pressmark);
    }

}
