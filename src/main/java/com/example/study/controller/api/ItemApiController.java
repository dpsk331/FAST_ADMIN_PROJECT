package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicServicel;

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
        return null;
    }

    @Override
    @DeleteMapping("{id}")   // /api/item/id
    public Header delete(@PathVariable Long id) {
        return null;
    }

}
