package com.daveslist.demo.repository;

import com.daveslist.demo.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room,Long>{
    Iterable<Room> findByRentedTrue();

}
