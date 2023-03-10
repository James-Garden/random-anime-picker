DROP TABLE user_list_entries;

CREATE TABLE user_list_entries(
    id UUID PRIMARY KEY,
    mal_user_id UUID NOT NULL,
    anime_id INT,
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
    anime_genres TEXT,
    anime_num_episodes INT,
    anime_source TEXT,
    anime_episode_duration_seconds INT,
    age_rating TEXT,
    anime_season TEXT,
    list_entry_status TEXT,
    user_anime_score INT,
    user_anime_num_episodes_watched INT,
    user_anime_is_rewatching Boolean,
    user_anime_updated_at_date_time TIMESTAMP,
    user_anime_start_date DATE,
    user_anime_finish_date DATE,
    CONSTRAINT fk_list_entry_user_id
    FOREIGN KEY (mal_user_id) REFERENCES mal_users(id)
);

CREATE INDEX idx_list_entry_user_id ON user_list_entries(mal_user_id);
