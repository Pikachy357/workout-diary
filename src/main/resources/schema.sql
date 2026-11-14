-- Таблица тренировок
CREATE TABLE workouts (
id           SERIAL PRIMARY KEY,
workout_date DATE         NOT NULL,
name         VARCHAR(100) NOT NULL
);