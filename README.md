# Workout Diary

Дневник тренировок — учебный pet-проект на Java.

Цель — пройти путь от консольного приложения на чистой Java
до полноценного REST API на Spring Boot + PostgreSQL,
покрытого тестами и упакованного в Docker.

## Текущий этап

**Этап 1 — ядро языка Java** (главы 6–7 Шилдта: классы и методы).

Реализована начальная domain model из трёх классов:
- `Workout` — тренировка (дата, название, список упражнений)
- `Exercise` — упражнение (название, описание, список подходов)
- `ExerciseSet` — один подход (вес, повторения, сложность, комментарий)

Все три сущности связаны через композицию: тренировка содержит
упражнения, упражнение содержит подходы.

## Запуск
javac src/*.java
java -cp src Main
Или открыть в IntelliJ IDEA и запустить `Main.main()`.

## Стек (план)

- **Сейчас:** Java 25
- **Этап 2–3:** Collections Framework, Stream API, многопоточность
- **Этап 5:** Maven, Git
- **Этап 6:** PostgreSQL, JDBC
- **Этап 7:** Spring Boot, Spring Data JPA, Spring Security
- **Этап 8:** JUnit 5, Mockito, Docker

## Прогресс

- [x] Domain model (Workout, Exercise, ExerciseSet)
- [x] Метод `toString()` с правильным `@Override`
- [x] Дата как `LocalDate`, а не как строка
- [ ] Геттеры и валидация в конструкторе
- [ ] Переход на `ArrayList` вместо массивов
- [ ] Сохранение в файл
- [ ] PostgreSQL и JDBC
- [ ] REST API на Spring Boot
- [ ] Тесты на JUnit
- [ ] Docker
