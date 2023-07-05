package com.atcdilivery.spring.jwt.mongodb.serviceImpl;

import com.atcdilivery.spring.jwt.mongodb.entity.OrderDetails;
import com.atcdilivery.spring.jwt.mongodb.entity.OrderResponse;
import com.atcdilivery.spring.jwt.mongodb.entity.PackagesResponse;
import com.atcdilivery.spring.jwt.mongodb.entity.Warehouse;
import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.createorder.CreateOrder;
import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.createorder.PickupLocation;
import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.createorder.Shipment;
import com.atcdilivery.spring.jwt.mongodb.payload.directRequest.warehouse.CreateWarehouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.directResponse.createorder.OrderResponseObj;
import com.atcdilivery.spring.jwt.mongodb.payload.directResponse.trackorder.TrackOrderResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.directResponse.warehouse.WarehouseCreationResponse;
import com.atcdilivery.spring.jwt.mongodb.payload.request.EditWareHouseRequest;
import com.atcdilivery.spring.jwt.mongodb.payload.response.Response;
import com.atcdilivery.spring.jwt.mongodb.repository.OrderDetailsRepository;
import com.atcdilivery.spring.jwt.mongodb.repository.OrderResponseRepository;
import com.atcdilivery.spring.jwt.mongodb.repository.PackagesResponseRepository;
import com.atcdilivery.spring.jwt.mongodb.service.ApiService;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

@Service
public class ApiServiceImplementation implements ApiService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderResponseRepository orderResponseRepository;

    @Autowired
    PackagesResponseRepository packagesResponseRepository;
    /**
     * This is Sanbox URL
     */
    String orderCreateTESTUrl = "https://staging-express.delhivery.com/api/cmu/create.json";
    String createWareHouseTESTUrl = "https://staging-express.delhivery.com/api/backend/clientwarehouse/create/";

    String editWareHouseTESTUrl = "https://staging-express.delhivery.com/api/backend/clientwarehouse/edit/";
    /**
     * This is Production URL
     */
    String orderCreateProdUrl = "https://track.delhivery.com/api/cmu/create.json";
    String createWareHouseProdUrl = "https://track.delhivery.com/api/backend/clientwarehouse/create/";
    String editWareHouseProdUrl = "https://track.delhivery.com/api/backend/clientwarehouse/edit/";


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        /**
         * This is sandboxToken
         */
//        headers.set("Authorization", "Token bd318e209dbfdb51691498c51369a9d2b77cb194");
        headers.set("Authorization", "Token 36bfc245c28e50e40364f7cbf6dea17405a2123d");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    private HttpHeaders getSandBoxHeaders() {
        HttpHeaders headers = new HttpHeaders();
        /**
         * This is sandboxToken
         */
        headers.set("Authorization", "Token bd318e209dbfdb51691498c51369a9d2b77cb194");
        headers.set("Content-Type", "application/json");
        return headers;
    }

    @Override
    public Response createOrderDilivery(String orderId) throws JsonProcessingException {
        OrderDetails orderDetails = orderDetailsRepository.findById(orderId).get();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        List<Shipment> shipmentList = new ArrayList<Shipment>();
        PickupLocation pickupLocation = new PickupLocation();
        CreateOrder createOrder = new CreateOrder();
        Shipment shipment = new Shipment();
        shipment.setPin((int) orderDetails.getDropPincode());
        shipment.setState(orderDetails.getDropState());
        shipment.setCity(orderDetails.getDropCity());
        shipment.setAdd(orderDetails.getDropAddress());
        shipment.setName(orderDetails.getDropName());
        shipment.setPhone(Long.valueOf(orderDetails.getDropMobile()));
        shipment.setAlternate_phone(orderDetails.getDropAlternative_mobile());
        shipment.setOrder(orderDetails.getOrderId());
        shipment.setTotal_amount(orderDetails.getTotalAmount());
        shipment.setProducts_desc(orderDetails.getProductName());
//        Extra Prameters
        shipment.setCountry("India");
        shipment.setCountry_code("");

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String date = zdt.format(formatter);

        shipment.setOrder_date(date);
//        shipment.setClient("SDHHOUSESURFACE-B2C");
        shipment.setFragile_shipment(orderDetails.getFragile());
        shipment.setSource("clpanel");

        if (orderDetails.getPaymentMode().equals("COD")) {
            shipment.setPayment_mode("COD");
            shipment.setCod_amount(orderDetails.getTotalAmount());
        } else if (orderDetails.getPaymentMode().equals("Prepaid")) {
            shipment.setPayment_mode("prepaid");
            shipment.setCod_amount(0);
        }

        if (orderDetails.getActualWeight() >= orderDetails.getVolumentricWeight()) {
            shipment.setWeight(orderDetails.getActualWeight());
        } else {
            shipment.setWeight(orderDetails.getVolumentricWeight());
        }
        /**
         * FIXME
         *
         */
        shipment.setShipment_length("");
        shipment.setShipment_width("");
        shipment.setShipment_height("");

        shipment.setTax_value(orderDetails.getTotalTaxes());
        shipment.setCommodity_value(orderDetails.getProductPrice());
        shipment.setCategory_of_goods(orderDetails.getProductCategory());
        shipment.setSeller_name(orderDetails.getSellerName());
        shipment.setSeller_add(orderDetails.getSellerAddress());
        shipment.setSeller_inv_date(orderDetails.getInvoiceDate());

        shipmentList.add(shipment);

        pickupLocation.setName(orderDetails.getWarehouseName());
        pickupLocation.setCity(orderDetails.getPickupCity());
        pickupLocation.setPin((int) orderDetails.getPickupPincode());
        pickupLocation.setCountry("India");
        pickupLocation.setPhone(orderDetails.getPickupMobile());
        pickupLocation.setAdd(orderDetails.getPickupAddress());

        createOrder.setShipments((ArrayList<Shipment>) shipmentList);
        createOrder.setPickup_location(pickupLocation);

        ObjectMapper mapper = new ObjectMapper();

        HttpEntity<String> entity = new HttpEntity<>("format=json&data=" + mapper.writeValueAsString(createOrder), headers);
        System.out.println(entity.getHeaders());
        System.out.println(entity.getBody());
        OrderResponseObj response = restTemplate
                .postForEntity(orderCreateProdUrl, entity, OrderResponseObj.class)
                .getBody();
        System.out.println(response);

        Response methodResponse = new Response();
        if (response.isSuccess()) {
            OrderResponse liverOrder = new OrderResponse();
            List<String> packagesIds = new ArrayList<>();
            response.getPackages().forEach(x -> {
                PackagesResponse packagesResponse = new PackagesResponse();
                packagesResponse.setStatus(x.getStatus());
                packagesResponse.setClient(x.getClient());
                packagesResponse.setSort_code(x.getSort_code());
                packagesResponse.setRemarks(x.getRemarks());
                packagesResponse.setWaybill(x.getWaybill());
                packagesResponse.setCod_amount(x.getCod_amount());
                packagesResponse.setPayment(x.getPayment());
                packagesResponse.setServiceable(x.isServiceable());
                packagesResponse.setRefnum(x.getRefnum());
                PackagesResponse savedPackages = packagesResponseRepository.save(packagesResponse);
                orderDetails.setWaybill(x.getWaybill());
                packagesIds.add(savedPackages.getId());
            });

            liverOrder.setOrderId(orderDetails.getOrderId());
            liverOrder.setOrderDatabaseId(orderId);
            liverOrder.setCash_pickups(response.getCash_pickups());
            liverOrder.setPackage_count(response.getPackage_count());
            liverOrder.setUpload_wbn(response.getUpload_wbn());
            liverOrder.setReplacement_count(response.getReplacement_count());
            liverOrder.setRmk(response.getRmk());
            liverOrder.setPickups_count(response.getPickups_count());
            liverOrder.setCod_count(response.getCod_count());
            liverOrder.setSuccess(response.isSuccess());
            liverOrder.setPrepaid_count(response.getPrepaid_count());
            liverOrder.setCod_amount(response.getCod_amount());
            liverOrder.setPackagesIds(packagesIds);
            OrderResponse orderResponse = orderResponseRepository.save(liverOrder);

            orderDetails.setUploadWbn(orderResponse.getUpload_wbn());
            orderDetails.setOrderLive(true);
            orderDetails.setOrderResponseId(orderResponse.getId());
            orderDetailsRepository.save(orderDetails);
            methodResponse.setStatusCode(200);
            methodResponse.setMessage("Order created please refer to My-Orders for Details");
            return methodResponse;
        }
        methodResponse.setStatusCode(409);
        methodResponse.setMessage(response.getRmk());
        return methodResponse;
    }

    @Override
    public Response createWareHouse(Warehouse warehouse) throws JsonProcessingException {
        CreateWarehouseRequest request = new CreateWarehouseRequest();
        request.setName(warehouse.getName());
        request.setEmail(warehouse.getEmail());
        request.setPhone(warehouse.getPhone());
        request.setAddress(warehouse.getAddress());
        request.setCity(warehouse.getCity());
        request.setCountry("India");
        request.setPin(warehouse.getPin());
        request.setReturn_address(warehouse.getReturnAddress());
        request.setReturn_pin(warehouse.getReturnPin());
        request.setReturn_city(warehouse.getReturnCity());
        request.setReturn_state(warehouse.getReturnState());
        request.setReturn_country("India");
        ObjectMapper mapper = new ObjectMapper();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(request), headers);
        System.out.println(entity.getHeaders());
        System.out.println(entity.getBody());
        WarehouseCreationResponse response = restTemplate
                .postForEntity(createWareHouseProdUrl, entity, WarehouseCreationResponse.class)
                .getBody();
        System.out.println(response);

        Response finalResponse = new Response();
        if(response.isSuccess()){
            finalResponse.setStatusCode(200);
            finalResponse.setMessage("WareHouse Created Successfully");
            return finalResponse;
        }else {
            finalResponse.setStatusCode(409);
            finalResponse.setMessage(response.getError());
            return finalResponse;
        }
    }

    @Override
    public Response editWareHouse(EditWareHouseRequest editWareHouseRequest) throws JsonProcessingException {
        com.atcdilivery.spring.jwt.mongodb.payload.directRequest.warehouse.EditWareHouseRequest request = new com.atcdilivery.spring.jwt.mongodb.payload.directRequest.warehouse.EditWareHouseRequest();
        request.setName(editWareHouseRequest.getName());
        request.setPin(editWareHouseRequest.getPin());
        request.setAddress(editWareHouseRequest.getAddress());
        request.setPhone(editWareHouseRequest.getPhone());
        request.setRegistered_name(editWareHouseRequest.getName());

        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(request), headers);
        System.out.println(entity.getHeaders());
        System.out.println(entity.getBody());
        WarehouseCreationResponse response = restTemplate
                .postForEntity(editWareHouseProdUrl, entity, WarehouseCreationResponse.class)
                .getBody();
        System.out.println(response);
        Response finalResponse =  new Response();
        if (response.isSuccess()){
            finalResponse.setStatusCode(200);
            finalResponse.setMessage("Warehouse Edited Successfully");
            return finalResponse;
        }else {
            finalResponse.setStatusCode(409);
            finalResponse.setMessage(response.getError());
            return finalResponse;
        }
    }

    @Override
    public String getCityByPincode(int pincode) {
        String url = "https://api.postalpincode.in/pincode/" + pincode;

        try {
            URL apiURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONArray postOfficeArray = jsonObject.getJSONArray("PostOffice");
                    if (postOfficeArray.length() > 0) {
                        JSONObject postOfficeObject = postOfficeArray.getJSONObject(0);
                        String city = postOfficeObject.getString("District");
                        System.out.println("City: " + city);
                        return city;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double getDistance(int origin, int destination) {
        String apiKey = "AIzaSyCxrkEdvpRDZ7gIZIvOKUB1oAVP_nY15Lg";
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="
                + origin + "&destinations=" + destination + "&key=" + apiKey;

        try {
            URL apiURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray rowsArray = jsonObject.getJSONArray("rows");
                if (rowsArray.length() > 0) {
                    JSONObject rowObject = rowsArray.getJSONObject(0);
                    JSONArray elementsArray = rowObject.getJSONArray("elements");
                    if (elementsArray.length() > 0) {
                        JSONObject elementObject = elementsArray.getJSONObject(0);
                        JSONObject distanceObject = elementObject.getJSONObject("distance");
                        int distance = distanceObject.getInt("value");
                        double distanceInKm = distance / 1000.0;
                        System.out.println("Distance between " + origin + " and " + destination + ": " + distance + " meters");
                        return distanceInKm;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> trackMyOrder(String waybillNo){
        String apiUrl = "http://staging-express.delhivery.com/api/v1/packages/json/?waybill=5209910000405";
        String authorizationToken = "Token bd318e209dbfdb51691498c51369a9d2b77cb194";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorizationToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println(responseBody);
        } else {
            System.out.println("API request failed with response code: " + response.getStatusCodeValue());
        }
        return null;
    }

    //code ajitesh
    @Override
    public TrackOrderResponse trackOrderResponse(String waybillNo){
        String apiUrl = "http://staging-express.delhivery.com/api/v1/packages/json/?waybill="+waybillNo;
        String authorizationToken = "Token bd318e209dbfdb51691498c51369a9d2b77cb194";
        ObjectMapper mapper = new ObjectMapper();
        TrackOrderResponse trackOrderResponse = new TrackOrderResponse();
        HttpResponse<String> response=null;
        try {
            Unirest.setTimeouts(0, 0);
            response = Unirest.get(apiUrl)
                    .header("Content-Type", "application/json")
                    .header("Authorization", authorizationToken)
                    .asString();
            String resp = response.getBody();
            trackOrderResponse= mapper.readValue(resp, TrackOrderResponse.class);
            return trackOrderResponse;
        }catch(Exception e) {

        }
        return null;
    }
}
