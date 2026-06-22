# Workout Diary

Дневник тренировок — учебный pet-проект на Java.

Цель — пройти путь от консольного приложения на чистой Java
до полноценного REST API на Spring Boot + PostgreSQL,
покрытого тестами и упакованного в Docker.

## Текущий этап

**Этап 2 — стандартная библиотека Java** (Collections Framework, глава 20 Шилдта).

Domain model из трёх классов, связанных композицией:
- `Workout` — тренировка (дата, название, список упражнений)
- `Exercise` — упражнение (название, описание, список подходов)
- `ExerciseSet` — один подход (вес, повторения, сложность, комментарий)

Реализовано:
- хранение вложенных сущностей через `List<>` (ArrayList)
- справочник упражнений на `Map<String, Exercise>` — поиск по названию
- валидация входных данных в конструкторах (`IllegalArgumentException`)
- обработка ошибок через `try-catch` без падения программы

## Запуск
javac src/*.java
java -cp src Main
Или открыть в IntelliJ IDEA и запустить `Main.main()`.

Или открыть в IntelliJ IDEA и запустить `Main.main()`.

## Стек (план)

- **Сейчас:** Java 25, Collections Framework
- **Этап 2–3:** Stream API, многопоточность
- **Этап 5:** Maven, Git
- **Этап 6:** PostgreSQL, JDBC
- **Этап 7:** Spring Boot, Spring Data JPA, Spring Security
- **Этап 8:** JUnit 5, Mockito, Docker

## Прогресс

- [x] Domain model (Workout, Exercise, ExerciseSet)
- [x] Метод `toString()` с правильным `@Override`
- [x] Дата как `LocalDate`, а не как строка
- [x] Геттеры и валидация в конструкторе
- [x] Обработка исключений (try-catch)
- [x] Переход на `ArrayList` вместо массивов
- [x] Справочник упражнений на `Map`
- [ ] Stream API для статистики тренировок
- [ ] Сохранение в файл
- [ ] PostgreSQL и JDBC
- [ ] REST API на Spring Boot
- [ ] Тесты на JUnit
- [ ] Docker
