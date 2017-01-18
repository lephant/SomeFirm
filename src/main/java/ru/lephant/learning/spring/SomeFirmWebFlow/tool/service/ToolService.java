package ru.lephant.learning.spring.SomeFirmWebFlow.tool.service;

import org.springframework.binding.message.MessageContext;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ToolType;

import java.util.List;

public interface ToolService {

    public ToolType getToolByPressmark(long pressmark);

    public List listTool();

    public boolean saveTool(ToolType tool, MessageContext messageContext, boolean isNew);

    public void deleteTool(long pressmark, MessageContext messageContext);

}
