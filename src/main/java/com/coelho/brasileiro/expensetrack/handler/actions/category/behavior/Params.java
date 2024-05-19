package com.coelho.brasileiro.expensetrack.handler.actions.category.behavior;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.Optional;

public interface Params {
    static Pageable getPageable(Map<String, String> params){
        String sortDir = Optional.ofNullable(params.get(com.coelho.brasileiro.expensetrack.constants.Params.SORT_DIR)).orElse("desc").toString();
        String sortBy = Optional.ofNullable(params.get(com.coelho.brasileiro.expensetrack.constants.Params.SORT_BY)).orElse(com.coelho.brasileiro.expensetrack.constants.Params.NAME).toString();
        int pageNo = Integer.parseInt(Optional.ofNullable(params.get(com.coelho.brasileiro.expensetrack.constants.Params.NO_PAGE)).orElse("0"));
        int pageSize = Integer.parseInt(Optional.ofNullable(params.get(com.coelho.brasileiro.expensetrack.constants.Params.PAGE_SIZE)).orElse("10"));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        return PageRequest.of((pageNo == 0 ? 0 : pageNo -1 ), pageSize, sort);
    }
}
