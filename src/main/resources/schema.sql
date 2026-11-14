-- Таблица тренировок
CREATE TABLE workouts (
                          id           SERIAL PRIMARY KEY,      -- номер строки, база сама присвоит 1, 2, 3...
                          workout_date DATE         NOT NULL,   -- дата, обязательна
                          name         VARCHAR(100) NOT NULL    -- текст до 100 символов, обязателен
);