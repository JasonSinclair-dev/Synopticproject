package me.jasonsinclair.musicarchivebackend.repository;

import me.jasonsinclair.musicarchivebackend.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song, String> {



}
