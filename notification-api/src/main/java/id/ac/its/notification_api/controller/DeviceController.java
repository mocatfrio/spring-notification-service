package id.ac.its.notification_api.controller;

import id.ac.its.notification_api.entity.Device;
import id.ac.its.notification_api.repository.DeviceRepository;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/device", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {
    private final DeviceRepository dao;

    public DeviceController(DeviceRepository dao) {
        this.dao = dao;
    }

    @PostMapping
    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataTypeClass = String.class)
    public Device create(@Valid @RequestBody Device device) {
        return dao.save(device);
    }

    @GetMapping
    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataTypeClass = String.class)
    public List<Device> list(@ModelAttribute Device device) {
        return dao.findAll(Example.of(device));
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataTypeClass = String.class)
    public Device retrieve(@PathVariable UUID id) {
        return dao.findById(id).orElseThrow();
    }
}
