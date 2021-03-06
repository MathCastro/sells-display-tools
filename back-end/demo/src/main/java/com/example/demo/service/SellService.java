package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.model.SellBO;
import com.example.demo.repository.SellRepository;

@Service
public class SellService
{
    @Autowired
    SellRepository repository;
     
    public Page<SellBO> getAllSells(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<SellBO> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }
    
    public Page<SellBO> getAllSellsFiltered(Integer pageNo, Integer pageSize, String sortBy, String value)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<SellBO> pagedResult = repository.filterSells(value, paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }
}