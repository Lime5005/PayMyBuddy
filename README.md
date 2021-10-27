# PayMyBuddy
Openclassrooms Project6
## Livrables:
- Le diagramme de classe UML:   

  <img width="901" alt="uml_p6" src="https://user-images.githubusercontent.com/65612959/138292268-e52f9821-903c-4e82-ad90-6904a054dbdb.png">
  
- Le modèle physique de données:   

  <img width="883" alt="modele_p6" src="https://user-images.githubusercontent.com/65612959/138292655-e71c6c53-4ba5-4758-8f59-ea7f3b42351f.png">

### Interface web:
- Login:
  
  <img width="424" alt="icon" src="https://user-images.githubusercontent.com/65612959/138293033-2d091f37-3bf8-4fdd-9f28-f91c07e4ca38.png">
  
- Home:
  
  <img width="1236" alt="home" src="https://user-images.githubusercontent.com/65612959/139069938-70d5e3d6-baf1-4072-8435-4d301efabce9.png">

  

#### Note: 
- Une fois un compte a été créé, il faut mettre au moins 1€ dedans pour activer le compte.  

- Pour démarrer l'application, ajouter `application.properties` sous `resources`:
  ```properties
  spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
  spring.datasource.url=jdbc:mysql://localhost:3306/pay_my_buddy?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&characterEncoding=utf8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  spring.jpa.open-in-view=false
  server.error.whitelabel.enabled=false
  ```
  
#### Jacoco test couverture sur la couche du service:   
  
  <img width="1035" alt="jacoco_p6" src="https://user-images.githubusercontent.com/65612959/139065464-314d74c7-1de9-48da-bac9-161729bd1ef0.png">

