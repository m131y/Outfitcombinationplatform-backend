package com.my131.Outfitcombinationplatform_backend.domain.closet.controller;

import com.my131.Outfitcombinationplatform_backend.domain.closet.dto.CreateClosetDto;
import com.my131.Outfitcombinationplatform_backend.domain.closet.service.ClosetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/closet")
public class ClosetController {
    private final ClosetService closetService;

    @PostMapping
    public ResponseEntity<Void> createCloset() {
        closetService.createCloset();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{closetId}")
    public ResponseEntity<Void> deleteCloset(@PathVariable Long closetId) {
        closetService.deleteCloset(closetId);
        return ResponseEntity.noContent().build();
    }


}
