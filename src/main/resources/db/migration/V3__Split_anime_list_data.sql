ALTER TABLE user_list_entries
    DROP COLUMN anime_title,
    DROP COLUMN anime_title_english,
    DROP COLUMN anime_title_japanese,
    DROP COLUMN anime_image,
    DROP COLUMN anime_start_date,
    DROP COLUMN anime_end_date,
    DROP COLUMN anime_synopsis,
    DROP COLUMN anime_average_score,
    DROP COLUMN anime_rank_by_score,
    DROP COLUMN anime_rank_by_popularity,
    DROP COLUMN anime_number_of_users,
    DROP COLUMN anime_number_of_scoring_users,
    DROP COLUMN anime_created_at_date_time,
    DROP COLUMN anime_updated_at_date_time,
    DROP COLUMN media_type,
    DROP COLUMN airing_status,
    DROP COLUMN anime_num_episodes,
    DROP COLUMN anime_source,
    DROP COLUMN anime_episode_duration_seconds,
    DROP COLUMN age_rating,
    DROP COLUMN anime_season
;

TRUNCATE TABLE user_list_entries;

UPDATE mal_users SET last_updated = NULL WHERE last_updated IS NOT NULL;

CREATE TABLE anime(
    id UUID PRIMARY KEY,
    anime_id INT UNIQUE,
    anime_title TEXT,
    anime_title_english TEXT,
    anime_title_japanese TEXT,
    anime_image TEXT,
    anime_start_date DATE,
    anime_end_date DATE,
    anime_synopsis TEXT,
    anime_average_score DECIMAL,
    anime_rank_by_score INT,
    anime_rank_by_popularity INT,
    anime_number_of_users INT,
    anime_number_of_scoring_users INT,
    anime_created_at_date_time TIMESTAMP,
    anime_updated_at_date_time TIMESTAMP,
    media_type TEXT,
    airing_status TEXT,
    anime_num_episodes INT,
    anime_source TEXT,
    anime_episode_duration_seconds INT,
    age_rating TEXT,
    anime_season TEXT
);

CREATE INDEX idx_anime_anime_id ON anime(anime_id);

ALTER TABLE user_list_entries ADD CONSTRAINT fk_user_list_entry_anime_id
    FOREIGN KEY (anime_id) REFERENCES anime(anime_id);

ALTER TABLE user_list_entries RENAME COLUMN list_entry_status TO watching_status;
