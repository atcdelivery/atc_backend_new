package com.atcdilivery.spring.jwt.mongodb.serviceImpl;

import com.atcdilivery.spring.jwt.mongodb.entity.Warehouse;
import com.atcdilivery.spring.jwt.mongodb.models.User;
import com.atcdilivery.spring.jwt.mongodb.payload.request.EditWareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.request.WareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.repository.UserRepository;
import com.atcdilivery.spring.jwt.mongodb.repository.WareHouseRepository;
import com.atcdilivery.spring.jwt.mongodb.service.ApiService;
import com.atcdilivery.spring.jwt.mongodb.service.WarehouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class WareHouseImplementation implements WarehouseService {

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    ApiService apiService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Response createWareHouse(WareHouseRequest wareHouseRequest) throws JsonProcessingException {
        Response response = new Response();
        Warehouse warehouse = new Warehouse();
        User user = userRepository.findById(wareHouseRequest.getUserId()).get();
        warehouse.setName(user.getUserId()+wareHouseRequest.getName());
        Warehouse existingWarehouse = wareHouseRepository.findByName(warehouse.getName());
        if (ObjectUtils.isEmpty(existingWarehouse)){
            warehouse.setUserId(wareHouseRequest.getUserId());
            warehouse.setAddress(wareHouseRequest.getAddress());
            warehouse.setPin(wareHouseRequest.getPin());
            warehouse.setPhone(wareHouseRequest.getPhone());
            warehouse.setCity(wareHouseRequest.getCity());
            warehouse.setState(wareHouseRequest.getState());
            warehouse.setEmail(wareHouseRequest.getEmail());
            if (wareHouseRequest.isReturnSame()){
                warehouse.setReturnAddress(wareHouseRequest.getAddress());
                warehouse.setReturnCity(wareHouseRequest.getCity());
                warehouse.setReturnState(wareHouseRequest.getState());
                warehouse.setReturnPin(wareHouseRequest.getPin());
            }else {
                warehouse.setReturnAddress(wareHouseRequest.getReturnAddress());
                warehouse.setReturnCity(wareHouseRequest.getReturnCity());
                warehouse.setReturnState(wareHouseRequest.getReturnState());
                warehouse.setReturnPin(wareHouseRequest.getReturnPin());
            }

            Response apiResponse = apiService.createWareHouse(warehouse);
            if (apiResponse.getStatusCode() == 200){
                wareHouseRepository.save(warehouse);
            }
            return apiResponse;
        }
        response.setStatusCode(409);
        response.setMessage("Warehouse already exist!");
        return response;
    }

    @Override
    public Response editWareHouse(EditWareHouseRequest editWareHouseRequest) throws JsonProcessingException {
        Warehouse warehouse = wareHouseRepository.findByName(editWareHouseRequest.getName());
        Response response1 = new Response();
        if (ObjectUtils.isEmpty(warehouse)){
            response1.setStatusCode(409);
            response1.setMessage("Warehouse doesn't exist");
            return response1;
        }else {
            warehouse.setPin(editWareHouseRequest.getPin());
            warehouse.setAddress(editWareHouseRequest.getAddress());
            warehouse.setPhone(editWareHouseRequest.getPhone());
            Response response = apiService.editWareHouse(editWareHouseRequest);
            if (response.getStatusCode() == 200){
                wareHouseRepository.save(warehouse);
                return response;
            }else {
                return response;
            }
        }
    }
}
