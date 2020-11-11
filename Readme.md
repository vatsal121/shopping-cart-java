**** CREDENTIALS TO LOGIN ARE AT THE EOF ****

In our application, we have two users - Admin and Customer. 

They can login through username and password, after user has been authenticated, routing will take place and takes the customer to appropriate page based on their role.
- If customer logs in: 

- Customer can view products, add product to cart, remove product from cart, view cart totals and check out (purchase all products in cart).
- On user side whenever the user adds a product to cart, only one qty of the product will be added to the cart by default.
- Upon adding the same product again its quantity will be updated in the cart rather than creating new entry.

Finally, when a user is logged in, they can log out.

- New User Can SignUp.
1. New User Can signup via the Signup window from the login page.
	Validations in place:
		a). Username cannot be repeated.(server side validation)
		b). Password and confirm password should match.(using javascript)



** Additional Uses **
---------------------------------------------------------------------------------------------------------------
- Database Config in code is placed under com.isi.service package insided file named "DBConnector.java"
- For password hashing a new utility class is created which will generate the salt and hash.
- Passwords are NOT stored in plain text in DB.
---------------------------------------------------------------------------------------------------------------

** STEPS TO RESTORE DATABASE ** 

1. Open MySQL Workbench.
2. Open a new query window.
3. Open the .sql file named "shoppingdb_vmp.sql" from the project folder.
4. Execute the script by pressing execute button.
5. Upon executing it will create a database named "shoppingdb_vmp".
6. All the data will be inside the database as the script has both schema and data of the database.


**********************************
Testing credentials:
**********************************
No 	ID 	PASS	 Role
1) 	admin 	admin	 Admin
2) 	vatsal 	vatsal	 Customer
3) 	meet 	meet	 Customer
**********************************

