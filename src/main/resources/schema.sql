-- Таблица тренировок
CREATE TABLE workouts (
id SERIAL PRIMARY KEY,
workout_date DATE NOT NULL,
name VARCHAR(100) NOT NULL
);

-- Упражнения: каждое принадлежит тренировке
CREATE TABLE exercises (
id SERIAL PRIMARY KEY,
workout_id INTEGER NOT NULL REFERENCES workouts (id) ON DELETE CASCADE,
name VARCHAR(100) NOT NULL,
description VARCHAR(255) NOT NULL
);

-- Подходы: каждый принадлежит упражнению
CREATE TABLE exercise_sets (
id SERIAL PRIMARY KEY,
exercise_id INTEGER NOT NULL REFERENCES exercises (id) ON DELETE CASCADE,
weight DOUBLE PRECISION NOT NULL CHECK (weight >= 0),
reps INTEGER NOT NULL CHECK (reps > 0),
difficulty INTEGER NOT NULL CHECK (difficulty BETWEEN 1 AND 10),
comment VARCHAR(255)
);