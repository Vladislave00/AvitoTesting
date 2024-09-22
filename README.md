## Инструкция

1. **Склонируйте репозиторий**  
   Выполните команду в терминале для клонирования проекта тестового задания:
   ```bash
   git clone https://github.com/Vladislave00/AvitoTesting
   ```
   Или скачайте ZIP архив по [ссылке](https://github.com/Vladislave00/AvitoTesting/archive/refs/heads/master.zip) и распакуйте его.

2. Откройте проект в любой IDE с поддержкой Maven (рекомендуется использовать IntelliJ IDEA).

3. Чтобы все зависимости Maven подгрузились, нажмите **Reload project**.

4. Убедитесь, что у вас установлен браузер Chrome и соответствующий ему ChromeDriver.

5. **Запустите тесты**  
   Запустите тесты в терминале Maven с помощью команды:
   ```bash
   mvn clean test
   ```