package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicServicel;

    /*@GetMapping("")
    public Header<List<ItemApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15) Pageable pageable) {
        log.info("{}", pageable);
        return itemApiLogicServicel.search(pageable);
    }*/

    @Override
    @PostMapping("")         // /api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicServicel.create(request);
    }

    @Override
    @GetMapping("{id}")      // /api/item/id
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiLogicServicel.read(id);
    }

    @Override
    @PutMapping("")          // api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicServicel.update(request);
    }

    @Override
    @DeleteMapping("{id}")   // /api/item/id
    public Header delete(@PathVariable Long id) {
        return itemApiLogicServicel.delete(id);
    }

}
