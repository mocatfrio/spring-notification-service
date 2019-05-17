package id.ac.its.notification_api.controller;

import id.ac.its.notification_api.dao.DeviceDao;
import id.ac.its.notification_api.model.Device;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/device")
public class DeviceController {
    private final DeviceDao dao;

    public DeviceController(DeviceDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public Device create(@Valid @RequestBody Device device) {
        return dao.save(device);
    }

    @GetMapping
    public List<Device> list(@ModelAttribute Device device) {
        return dao.findAll(Example.of(device));
    }

    @GetMapping("/{id}")
    public Device retrieve(@PathVariable UUID id) {
        return dao.findById(id).orElseThrow();
    }
}
