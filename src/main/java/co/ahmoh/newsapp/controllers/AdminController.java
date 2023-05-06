package co.ahmoh.newsapp.controllers;
import co.ahmoh.newsapp.entities.Admin;
import co.ahmoh.newsapp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> adminOptional = adminService.getAdminById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.created(URI.create("/admin/" + savedAdmin.getId())).body(savedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable Long id) {
        adminService.deleteAdminById(id);
        return ResponseEntity.noContent().build();
    }

}
