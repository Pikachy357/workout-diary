-- Тестовые данные для разработки.

INSERT INTO workouts (workout_date, name) VALUES
('2026-11-09', 'День ног'),
('2026-11-11', 'Верх'),
('2026-11-13', 'День ног');

INSERT INTO exercises (workout_id, name, description) VALUES
(1, 'Приседания', 'Со штангой'),
(1, 'Жим ногами', 'В тренажёре'),
(2, 'Жим', 'Лёжа'),
(3, 'Приседания', 'Со штангой');

INSERT INTO exercise_sets (exercise_id, weight, reps, difficulty, comment) VALUES
(1, 80, 10, 5, 'разминка'),
(1, 100, 8, 7, 'рабочий'),
(2, 150, 12, 6, 'рабочий'),
(3, 80, 8, 6, 'рабочий'),
(3, 90, 5, 8, 'тяжело'),
(4, 105, 6, 8, 'рекорд');