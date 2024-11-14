package miu.asd.adsmanagement.controller;

import lombok.RequiredArgsConstructor;
import miu.asd.adsmanagement.dto.request.AddressResquestDto;
import miu.asd.adsmanagement.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adsweb/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("list")
    public ResponseEntity<?> getAddresses() {
        return ResponseEntity.ok(addressService.getAddresses());
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressResquestDto addressResquestDto) {
        addressService.createAddress(addressResquestDto);
        return ResponseEntity.ok("Address created successfully.");
    }
}
