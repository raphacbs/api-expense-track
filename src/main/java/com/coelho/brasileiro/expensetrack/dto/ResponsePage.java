package com.coelho.brasileiro.expensetrack.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResponsePage<T>{
    private List<T> items;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

//    public void addItems(List<?> items){
//        this.items.addAll((Collection<? extends T>) items);
//        this.totalElements = items.size();
//        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
//        this.last = (pageNo == totalPages);
//    }
}
