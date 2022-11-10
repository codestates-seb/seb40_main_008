package main008.BED.warning.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.warning.mapper.WarningMapper;
import main008.BED.warning.service.WarningService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WarningController {

    private final WarningService warningService;
    private final WarningMapper warningMapper;
}
