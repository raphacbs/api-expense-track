package com.coelho.brasileiro.expensetrack.context;

import com.coelho.brasileiro.expensetrack.dto.ResponsePage;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Map;

public interface Context {
    <T extends Input> T getInput(String key, Class<T> clazz);

    void setInput(String key, Input input);

    <T extends Dto> T getDto(String key, Class<T> clazz);

    void setDto(String key, Dto dto);

    <T extends IEntity> T getEntity(String key, Class<T> clazz);

    void setEntity(String key, IEntity entity);

    <T extends Dto> List<T> getDtos(String key, Class<T> clazz);

    void setDtos(String key, List<? extends Dto> dtos);

    <T extends IEntity> List<T> getEntities(String key, Class<T> clazz);

    void setEntities(String key, List<? extends IEntity> entities);

    void setEntityNameCurrent(String nameEntity);
    String getEntityNameCurrent();

    Map<String, String> getParams();

    void setParams(Map<String, String> params);
    <T extends IEntity> Page<T> getPage();
    void setPage(Page<? extends IEntity> page);

    void addResponsePage(ResponsePage<?> responsePage);
    ResponsePage<?> getResponsePage();
}
