package com.coelho.brasileiro.expensetrack.context;


import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.dto.ResponsePage;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.data.domain.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class BaseContext implements Context {
    private final Map<String, Input> inputs = new HashMap<>();
    private final Map<String, Dto> dtos = new HashMap<>();
    private final Map<String, IEntity> entities = new HashMap<>();
    private final Map<String, List<? extends Dto>> dtosList = new HashMap<>();
    private final Map<String, List<? extends IEntity>> entitiesList = new HashMap<>();

    private String entityCurrent;

    private Map<String, String> params; //<key, value>

    private Page<?> page;

    private ResponsePage<?> responsePage;


    @Override
    public <T extends Input> T getInput(String key, Class<T> clazz) {
        Input input = inputs.get(key);
        if (!clazz.isInstance(input)) {
//            throw new IllegalArgumentException("Invalid input type for key: " + key);
            return null;
        }
        return clazz.cast(input);
    }

    @Override
    public void setInput(String key, Input input) {
        inputs.put(key, input);
    }

    @Override
    public <T extends Dto> T getDto(String key, Class<T> clazz) {
        Dto dto = dtos.get(key);
        if (!clazz.isInstance(dto)) {
//            throw new IllegalArgumentException("Invalid dto type for key: " + key);
            return null;
        }

        return clazz.cast(dto);
    }

    @Override
    public void setDto(String key, Dto dto) {
        dtos.put(key, dto);
    }

    @Override
    public <T extends IEntity> T getEntity(String key, Class<T> clazz) {
        IEntity entity = entities.get(key);
        if (!clazz.isInstance(entity)) {
//            throw new IllegalArgumentException("Invalid entity type for key: " + key);
            return null;
        }
        return clazz.cast(entity);
    }

    @Override
    public void setEntity(String key, IEntity entity) {
        entities.put(key, entity);
    }

    @Override
    public <T extends Dto> List<T> getDtos(String key, Class<T> clazz) {
        List<? extends Dto> dtos = dtosList.get(key);
        if (dtos == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (Dto dto : dtos) {
            if (clazz.isInstance(dto)) {
                result.add(clazz.cast(dto));
            }
        }
        return result;
    }


    @Override
    public void setDtos(String key, List<? extends Dto> dtos) {
        dtosList.put(key, dtos);
    }


    @Override
    public <T extends IEntity> List<T> getEntities(String key, Class<T> clazz) {
        List<? extends IEntity> entityList = entitiesList.get(key);
        if (entityList == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (IEntity entity : entityList) {
            if (clazz.isInstance(entity)) {
                result.add(clazz.cast(entity));
            }
        }
        return result;
    }


    @Override
    public void setEntities(String key, List<? extends IEntity> entities) {
        entitiesList.put(key, entities);
    }

    @Override
    public void setEntityNameCurrent(String nameEntity) {
        this.entityCurrent = nameEntity;
    }

    @Override
    public String getEntityNameCurrent() {
        return this.entityCurrent;
    }

    @Override
    public Map<String, String> getParams() {
        return this.params;
    }

    @Override
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Page<?> getPage() {
        return page;
    }

    @Override
    public void setPage(Page<? extends IEntity> page) {
        if (this.page == null || page.isEmpty()) {
            this.page = page;
        } else {
            // 1. Obtenha as duas listas de objetos a serem mescladas.
            List<? extends IEntity> existingContent = (List<? extends IEntity>) this.page.getContent();
            List<? extends IEntity> newContent = page.getContent();

            // 2. Mescle as duas listas, evitando objetos com o mesmo ID.
            Set<UUID> existingIds = existingContent.stream()
                    .map(IEntity::getId)
                    .collect(Collectors.toSet());

            List<IEntity> mergedContent = Stream.concat(
                    existingContent.stream(),
                    newContent.stream()
                            .filter(entity -> !existingIds.contains(entity.getId()))
            ).collect(Collectors.toList());

            // 3. Crie um objeto Sort com os campos de ordenação.
            Sort sort = Sort.by(Sort.Order.asc("yourSortField"));

            // 4. Crie um objeto Pageable com informações de paginação.
            int pageSize = page.getSize(); // Tamanho da página
            int pageNumber = page.getNumber(); // Número da página
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

            // 5. Ordene a lista mergedContent usando o Sort.
            mergedContent.sort((e1, e2) -> {
                // Ordene os objetos com base em 'yourSortField'
                Comparable field1 = e1.getId();
                Comparable field2 = e2.getId();
                return field1.compareTo(field2);
            });

            // 6. Pagine a lista ordenada usando o objeto Pageable.
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), mergedContent.size());

            List<IEntity> pagedContent = mergedContent.subList(start, end);

            this.page = new PageImpl<>(pagedContent, pageable, mergedContent.size());
        }
    }



    @Override
    public void addResponsePage(ResponsePage<?> responsePage) {
        this.responsePage = responsePage;
    }

    @Override
    public ResponsePage<?> getResponsePage() {
        return responsePage;
    }
}
