package com.custom;

import com.custom.entity.Dummy;
import com.custom.external.ExternalBean;
import com.custom.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    @Qualifier("ext")
    private  ExternalBean externalBean;

    @PostMapping("/save")
    public void saveDummy(@RequestBody Dummy dummy) {
        dummyRepository.save(dummy);
    }

    @GetMapping("/all")
    public List<Dummy> getAllDummy() {
        return dummyRepository.findAll();
    }

    @GetMapping("/test")
    public String getTestValue() {
        return externalBean.getUrl();
    }
}
