package t1.openschool.sampleservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import t1.openschool.sampleservice.service.EtcService;

@RestController
@RequestMapping(path = "/etc")
@RequiredArgsConstructor
@Tag(name = "Controller with small demo functions")
public class EtcController {
    private final EtcService etcService;

    @GetMapping("/hi")
    @Operation(summary = "Return string 'Hi'")
    public String sayHi() {
        return "Hi";
    }

    @GetMapping("/hi/{userName}")
    @Operation(summary = "Return string 'Hi [userName]'")
    public String sayHi(@PathVariable String userName) {
        return "Hi " + userName;
    }

    @GetMapping("/random")
    @Operation(summary = "Return random int with or without delay")
    public int getRandomInt(@RequestParam(value = "useDelay", required = false) Boolean useDelay) {
        if (Boolean.TRUE.equals(useDelay)) {
            return etcService.getRandomIntWithDelay();
        }
        return etcService.getRandomValue();
    }
}
