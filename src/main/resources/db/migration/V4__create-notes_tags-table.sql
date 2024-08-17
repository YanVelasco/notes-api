CREATE TABLE IF NOT EXISTS notes_tags
(
    note_id VARCHAR(255) NOT NULL,
    tag_id  VARCHAR(255) NOT NULL,
    PRIMARY KEY (note_id, tag_id),
    FOREIGN KEY (note_id) REFERENCES notes (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE
);