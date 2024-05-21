package t1.openschool.sampleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t1.openschool.sampleservice.dto.UserNewDto;
import t1.openschool.sampleservice.dto.UserPatchDto;
import t1.openschool.sampleservice.dto.UserResponseDto;
import t1.openschool.sampleservice.model.User;
import t1.openschool.sampleservice.repository.UserRepository;
import t1.openschool.sampleservice.util.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto getById(long id) {
        return toDto(findUserById(id));
    }

    public UserResponseDto create(UserNewDto dto) {
        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setAge(dto.getAge());
        return toDto(userRepository.save(newUser));
    }

    public UserResponseDto patch(long id, UserPatchDto dto) {
        User patchUser = findUserById(id);
        if (dto.getName() != null && !dto.getName().isBlank()) {
            patchUser.setName(dto.getName());
        }
        if (dto.getAge() != null) {
            patchUser.setAge(dto.getAge());
        }
        return toDto(userRepository.save(patchUser));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    private User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id=" + id + " not found"));
    }

    private UserResponseDto toDto(User entity) {
        return UserResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .build();
    }
}
