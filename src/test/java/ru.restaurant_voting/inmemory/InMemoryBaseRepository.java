package ru.restaurant_voting.inmemory;

import ru.restaurant_voting.model.AbstractBaseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class InMemoryBaseRepository<T extends AbstractBaseEntity> {

    private static AtomicInteger counter = new AtomicInteger(START_SEQ);

    Map<Integer, T> map = new ConcurrentHashMap<>();

    public T save(T entry) {
        Objects.requireNonNull(entry, "Entry must not be null");
        if (entry.isNew()) {
            entry.setId(counter.incrementAndGet());
            map.put(entry.getId(), entry);
            return entry;
        }
        return map.computeIfPresent(entry.getId(), (id, oldT) -> entry);
    }

    public boolean delete(int id) {
        return map.remove(id) != null;
    }

    public T get(int id) {
        return map.get(id);
    }

    Collection<T> getCollection() {
        return map.values();
    }
}