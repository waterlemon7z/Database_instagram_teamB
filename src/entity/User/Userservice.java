package service;

import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> users = new ArrayList<>();

    // 사용자 추가
    public void addUser(User user) {
        users.add(user);
    }

    // 사용자 조회
    public Optional<User> getUserById(int id) {
        return users.stream()
                    .filter(user -> user.getId() == id)
                    .findFirst();
    }

    // 사용자 삭제
    public boolean deleteUser(int id) {
        return users.removeIf(user -> user.getId() == id);
    }

    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
