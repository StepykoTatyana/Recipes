package com.example.Recipes.JPATask.ValidateTask;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;


@RestController
@Validated
public class SpecialAgentController {

    @PostMapping("/agent")
    public ResponseEntity<String> validate(@Valid @RequestBody SpecialAgent agent) {

        return ResponseEntity.ok("Agent info is valid.");
    }

    /*
        @PostMapping("/agent")
    public ResponseEntity<String> validate(@RequestBody List<@Valid SpecialAgent> agents) {
        return ResponseEntity.ok("All agents have valid info.");
    }
     */

    @GetMapping("/agents/{id}")
    ResponseEntity<String> validateAgentPathVariable(@PathVariable("id") @Min(1) @Max(100) int id) {
        return ResponseEntity.ok("Agent id is valid.");
    }

    @GetMapping("/agents")
    ResponseEntity<String> validateAgentRequestParam(
            @RequestParam("code") @Pattern(regexp = "[0-9]{1,3}") String code) {
        return ResponseEntity.ok("Agent code is valid.");
    }
}
