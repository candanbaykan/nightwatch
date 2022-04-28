# Nightwatch

This project can automatically distribute a monthly shift according to the preferences and rules of the employees in the system. It enables managers to make changes to the shifts from their own applications after the distribution, and enables the employees to monitor which day they will be on duty from the mobile application.

## Build & Run

### Prerequisites
* Docker
* Docker-compose
* Python 3

After having the prerequisites, you can run the following commands in project directory.

- Build & Run
```shell
./up.py
``` 

- Show logs
```shell
./logs.py
``` 

- Terminate
```shell
./down.py
```

You can visit <http://localhost:8080/swagger-ui/index.html> to view API documentation while backend is running.

To build and run the mobile application, open the project in mobile-app folder with Android Studio and then press run button.

## Credits
Mobile Development: @mtaylanibrahim  
Backend Development: @candanbaykan
