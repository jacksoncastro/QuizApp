PRAGMA foreign_keys = ON;

CREATE TABLE quiz (
    id INTEGER PRIMARY KEY,
    question TEXT NOT NULL,
    image_link TEXT NOT NULL
);

CREATE TABLE item (
    id INTEGER PRIMARY KEY,
    answer TEXT NOT NULL,
    quiz_id INTEGER NOT NULL,
    FOREIGN KEY(quiz_id) REFERENCES quiz(id)
);

CREATE TABLE right_answer (
    id INTEGER PRIMARY KEY,
    quiz_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    FOREIGN KEY(quiz_id) REFERENCES quiz(id),
    FOREIGN KEY(item_id) REFERENCES item(id)
);