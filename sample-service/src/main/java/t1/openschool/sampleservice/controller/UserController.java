package t1.openschool.sampleservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import t1.openschool.sampleservice.dto.UserNewDto;
import t1.openschool.sampleservice.dto.UserPatchDto;
import t1.openschool.sampleservice.dto.UserResponseDto;
import t1.openschool.sampleservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Tag(name = "CRUD for User entity")
public class UserController {
    private final UserService userService;

    @GetMapping()
    @Operation(summary = "Read all")
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read by id")
    public UserResponseDto getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Valid @RequestBody UserNewDto dto) {
        return userService.create(dto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto patch(@PathVariable long id, @RequestBody UserPatchDto dto) {
        return userService.patch(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
