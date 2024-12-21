//maria gresia plena br purba
//235314094
package org.example.revisi_uts2_094.Controller;

import org.example.revisi_uts2_094.Model.Return;
import org.example.revisi_uts2_094.Service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/return")
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @PostMapping
    public ResponseEntity<Return> returnBook(@RequestParam Long borrowingId) {
        return ResponseEntity.ok(returnService.processReturn(borrowingId));
    }

    @GetMapping
    public ResponseEntity<List<Return>> getAllReturns() {
        return ResponseEntity.ok(returnService.getAllReturns());
    }
}
