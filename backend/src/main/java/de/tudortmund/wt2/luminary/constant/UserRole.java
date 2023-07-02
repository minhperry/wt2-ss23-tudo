package de.tudortmund.wt2.luminary.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum UserRole {
    ADMIN(1, "admin"),
    USER(2, "user");

    private final int index;
    private final String type;

    private static final HashMap<Integer, UserRole> fromIntegerMap = new HashMap<>();

    static {
        Arrays.stream(UserRole.values()).forEach(value -> {
            fromIntegerMap.put(value.index, value);
        });
    }

    public static Optional<UserRole> fromIndex(int index){
        return Optional.ofNullable(fromIntegerMap.get(index));
    }
}