package sk.stuba.fei.uim.asos.assignment2.user.service;

import sk.stuba.fei.uim.asos.assignment2.user.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserService implements IUserService<User, Long> {

    private final AtomicLong idCounter;
    private final Map<Long, User> users;

    public UserService() {
        this.users = new HashMap<>();
        idCounter = new AtomicLong(0);
    }

    /**
     * Add new user.
     * Id for a user is generated before saved.
     *
     * @param user New user
     * @return Newly added user
     */
    @Override
    public User add(User user) {
        user.setId(this.idCounter.incrementAndGet());
        users.put(user.getId(), user);
        return user;
    }

    /**
     * Update existing user.
     *
     * @param user Updated user
     * @return Previously saved (overridden) user
     * @throws IllegalArgumentException Thrown if provided user's id was not found
     */
    @Override
    public User update(User user) {
        if (!users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User has not been found!");
        }
        return users.put(user.getId(), user);
    }

    /**
     * Get all saved users.
     *
     * @return List of users
     */
    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    /**
     * Find saved user by id.
     *
     * @param id User's id
     * @return Found user or null
     */
    @Override
    public User getOne(Long id) {
        return users.get(id);
    }
}
