▎Практическая работа №1 по системному программированию

Руководитель: Луговой Филипп Сергеевич

---

▎Описание проекта

Данная практическая работа представляет собой консольное приложение для управления библиотекой. Она служит практическим примером применения принципов системного программирования, таких как работа с базами данных, создание репозиториев и управление пользовательским интерфейсом. В проекте реализованы некоторые компоненты, что было сделано с замыслом на дальнейшее расширение функционала. Это позволит легко добавлять новые возможности, такие как интеграция с внешними сервисами, улучшение пользовательского интерфейса или внедрение дополнительных бизнес-логик.
▎Структура проекта

Проект организован по следующей структуре:

• org/example/demo1/Main.java  
  Главный класс приложения, который запускает программу и инициирует работу всех компонентов.

▎Директории проекта

• book  
  Содержит сущности и репозитории, связанные с книгами.
  *Класс BookRowMapper преобразует строки базы данных в объекты Reader, обеспечивая удобное взаимодействие с данными.*


• reader  
  Аналогична директории book, но предназначена для работы с читателями.  


• config  

  • AppConfig.java  
    Класс для конфигурации приложения, включая настройки подключения к базе данных и другие параметры.

• utils  
  Содержит вспомогательные классы для работы с приложением.

  • DatabaseManager.java  
    Управляет соединением с базой данных, инкапсулируя логику открытия, закрытия и управления состоянием соединения.

  • PrintUtils.java  
    Утилитный класс для форматирования и вывода информации в консоль.

• sql  
  SQL-скрипт для инициализации базы данных, содержащий команды для создания таблиц и заполнения их начальными данными.

• ui.console  
  Содержит классы, отвечающие за пользовательский интерфейс консольного приложения.

• exceptions  
  Содержит классы пользовательских исключений для обработки ошибок в приложении:

  • DatabaseManagerException.java  
    Исключение, возникающее при ошибках управления базой данных.

  • RepositoryException.java  
    Исключение, возникающее при ошибках в репозиториях (например, при выполнении операций CRUD).

  • ServiceException.java  
    Исключение, возникающее в сервисном слое приложения при обработке бизнес-логики.

---

▎Описание команд

Приложение предоставляет следующие команды:

• 0: Выход из приложения.

• 1: Отображает список всех книг, находящихся в библиотеке.

• 2: Запрашивает у пользователя ввод названия и ищет совпадающие книги.

• 3: Позволяет пользователю добавить новую книгу в библиотеку.

• 4: Запрашивает у пользователя данные книги для удаления.

• 5: Отображает список всех зарегистрированных читателей.

• 6: Запрашивает у пользователя ввод электронного адреса и ищет совпадающих читателей.

• 7: Позволяет пользователю добавить нового читателя в систему.

• 8: Запрашивает у пользователя данные читателя для удаления.

---

▎Установка и запуск

1. Клонируйте репозиторий на свой компьютер.

2. Убедитесь, что у вас установлены необходимые зависимости (например, Java и JDBC).

3. Настройте подключение к базе данных в файле AppConfig.java.

4. Запустите главный класс Main.java.

---

