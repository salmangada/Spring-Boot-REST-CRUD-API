# REST CRUD API Using Spring Boot

# Guide

1. Download or clone the repository.
2. Create database 'rest_invoice' in MySQL workbench. you can choose database software of your own choice.
3. Update `appliaction.properties` file with your database credentials.
    ```
    #  DB Connection Properties
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/rest_invoice
   spring.datasource.username=root
   spring.datasource.password="Your database password"
    ```

4. Run the application.

# Testing Application
 Use 'POSTMAN' to test the application.

## save method
Open 'POSTMAN', Select method POST from dropdown, enter the given URL.
```
http://localhost:8080/invoice/rest/saveInvoice
```
From nav choose Body->Raw  and json format. Paste the following data in there. Send the request and success response is returned.
```
{
    "name": "aqua",
    "amount": 500,
    "number": "Inv023",
    "receivedDate": "01-12-2020",
    "type": "normal",
    "vendor": "Vend001",
    "comments": "on hold"
}
```
![tempsnip](https://user-images.githubusercontent.com/70872374/147855027-abdf4259-b8c3-4b5d-a054-7515b57be17b.png)

## Get-All Method

Select 'GET' method from dropdown, then enter below URL and send the request.
```
http://localhost:8080/invoice/rest/getAllInvoices
```

## Get-one Method
Select ‘GET’ method from dropdown, then enter below URL and send the request.
```
http://localhost:8080/invoice/rest/find/1 
```

## Update Method
Select ‘PUT’ method from dropdown, then enter below URL and send the request.
```
http://localhost:8080/invoice/rest/modify/2 
```

## Delete Method
Select ‘DELETE’ method from dropdown, then enter below URL and send the request.
``` 
http://localhost:8080/invoice/rest/remove/1
```

## Custom Method
Suppose you want to modify name field of an Invoice where id is 1.
Then use below pattern URL. Select method ‘PATCH’ from dropdown,
enter below URL, select ‘Body’ then click on ‘raw’, 
select ‘JSON” from dropdown. Then Enter data in JSON format to be modified.
Finally click on ‘Send’ button & check the successful message in lower box.
```
http://localhost:8080/invoice/rest/modify/1/Inv02345
```

