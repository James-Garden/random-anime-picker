CREATE TABLE mal_users (
    id              UUID PRIMARY KEY,
    username        TEXT NOT NULL,
    last_updated    TIMESTAMP
);

CREATE TABLE user_list_entries (
    id UUID PRIMARY KEY,
    mal_user_id UUID NOT NULL,
    status INT,
    score INT,
    tags INT,
    is_rewatching TEXT,
    num_episodes_watched INT,
    created_at_epoch INT,
    updated_at_epoch INT,
    anime_title TEXT,
    anime_title_eng TEXT,
    anime_num_episodes TEXT,
    anime_airing_status INT,
    anime_id INT,
    anime_studios INT,
    anime_licensors TEXT,
    anime_total_members TEXT,
    anime_total_scores INT,
    anime_score_val INT,
    has_episode_video DECIMAL,
    has_promotion_video BOOLEAN,
    has_video BOOLEAN,
    video_url BOOLEAN,
    genres TEXT,
    anime_url TEXT,
    anime_image_path TEXT,
    is_added_to_list TEXT,
    anime_media_type_string BOOLEAN,
    anime_mpaa_rating_string TEXT,
    start_date_string TEXT,
    finish_date_string TEXT,
    anime_start_date_string TEXT,
    anime_end_date_string TEXT,
    days_string TEXT,
    storage_string TEXT,
    priority_rating TEXT,
    notes TEXT,
    editable_notes TEXT,
    CONSTRAINT fk_user_list_item_user_id FOREIGN KEY (mal_user_id) REFERENCES mal_users(id)
);

CREATE INDEX ON user_list_entries(mal_user_id)
