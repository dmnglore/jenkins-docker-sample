package com.demo.devops.jenkinsdockersample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shipments")
public class ShipmentController {
	
	private final ShipmentService shipmentService;
	
	/*public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }  -- you dont a explicit constructuctor for dependancy injection. spring handle automatically. */
	
	@PostMapping("/process")
	public ResponseEntity processShipment(@RequestBody ShipmentRequestDTO request) {
		
		ShipmentResponseDTO response = 
		shipmentService.processShipment(request);
		return ResponseEntity.ok(response);
		
	}
	
}
//s Recieves http request, delegates immediately to the service,no business logic ,returns response 