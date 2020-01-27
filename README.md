## Demo Challenge


#### Project Setup
1. Clone this project.
2. Setup the project in your IDE.
3. Open the index.html file from src/test/resource/files in a browser
4. Copy the url from the browser and update the url value in src/test/resource/config.properties to be the copied url.
5. In src/test/resources update the config.properties file platform for your OS.
6. From command line run mvn clean install -U -DskipTests
7. Make sure you can run the DemoTest and chrome launches.  You may need to update the chromedriver in /src/test/resources/chromedriver/ to the version that works with your browser
   https://chromedriver.chromium.org/

#### Expectations
We will be evaluating
1. Quality of test cases
2. Variety  of testing types (examples: boundary, happy path, negative, etc)
3. Code structure and organization
4. Naming conventions
5. Code readability
6. Code modularity

#### Exercise
1. Use the site at the index.html
2. There are helper locators provided for you in the src/test/resource/files/locators.txt file.
3. In the Read me file, write up all of the test cases you think are necessary to test the sample page.  Please note any defects or issues observed in the Read me file.
4. Code up a few examples of 
  - At least one happy path case placing an order
  - At least one error case
5. When complete please check your code into a public git repo

#### Test Cases

========================================================================

TestCase #   Description				Expected result.				Actual result													                                                  				 
--------------------------------------------------------------------------------------------------------------------------------
Success flows:
---------------------------

1. Select pizza = "Small pizza with no toppings"		
and set "quantity"=1 enter a valid "name", "email", 
"phone number" and place order button.  			:A success text box should pop 					Pass.								up indicating the type of pizza ordered
								along with correct cost calculated.						
1.1Repeat above steps with pizza type selected as 
"Large - no toppings", quantity 1,3 and 5																			:A success text box should pop up indicating		Pass.	 	  							 the type of pizza ordered along with correct 
								cost calculated.						
2.Select pizza = "Medium pizza with 2 toppings
" and set "quantity"=1
 enter a valid "toppings1,"toppings2,"name", "email", 
"phone number" and place order button.  																			:A success text box should pop up indicating 		Pass.
								the type of pizza ordered along with correct
								cost calculated.						
2.1Repeat aboves steps with pizza type selected as 
"Large with 2 toppings", quantity 1,3 and 5											 								 :A success text box should pop up indicating the  	Pass.									type of pizza ordered along with correct cost
								calculated.

3. Select pizza = "Small pizza with 1 toppings" 
and set "quantity"=3 enter a valid "name", 
"email", "phone number" and place order button.  		: A success text box should pop up indicating 		Pass.								        the type of pizza ordered along with correct
								cost calculated.

4. Select pizza = "Small pizza with 1 toppings"
and set "quantity"=4enter a valid "name", 			:All the entered fields should be Reset.		Fail
enter a valid "name", "email", "phone number" and Reset button							(see observation 															4.)		
						 			
Failure flows:
---------------------------

1. Select pizza = "small pizza with no topping" 
and set "quantity"= -1       					:An error message has to be displayed or 	Fail(see 									GUI should not allow to input -ve numbers	Observ 3.)																				
2. Boundary value test- 
    Select pizza = "small pizza with no topping" 
    and set "quantity"= 6  and 0         			:An error message is to be displayed to the	Fail(see -
								user indicating maximum quantity allowed is      Requirements)
								5 and minimum is 1		 										   					
3.Select pizza = "Large pizza with no topping" 
and set "quantity"=1,enter a valid "phone num" 
and an "empty name"						:An error message alert should pop up  		Pass.
								indicating"Name is missing "					

4. Select pizza = "Medium pizza with 2 toppings"
and set "quantity"=2,						:An error message alert should pop up		Pass.
enter a valid "name" and an 					indicating "Phone is missing "
"empty phone num" 												
    
5. Select pizza = "Large pizza with 2 toppings" 
and set "quantity"=3, enter a valid "name"
and an "invalid phone num"(non-digit input)			:An error message has to be displayed or	Fail(see 					 				GUI should not allow to input 	           observation 6.)	
								alphanumeric characters
   								

6. Select pizza = "small pizza with no topping"
and set "quantity"=1,enter an "empty name"
and an "empty phone num".					:An error message alert should pop up		Pass.
								indicating "Name is missing "					
    								and "Phone is missing "

7. Select pizza = "Small pizza with no toppings" 
  and set "quantity"=1 enter a valid "name", "email", 		:An error message has to be displayed or 	Fail (see 
 "phone number",select both cash and credit card 		GUI should not allow to select both		observation 2)	
 	payment and place order button.				the payment options
 													     
8. Select pizza = "Large pizza with 2 toppings"
and set "quantity"=3,enter a valid "name","phone",
invalid email (email without domain and @ symbol 		:An error message has to be displayed 		Fail (see 									or GUI should not allow to input		observation 5)
								invalid email	
    									
    	

9. Enter an "Empty Pizza", "quantity"=1,a 
valid "name","phone",payment information
and place order button 						:An error message alert should pop up		Fail (see
								indicating "Pizza is missing" 			 observation 7)
   

10. Select pizza = "Medium pizza with 2 toppings"		:An error message alert should 			Fail (see 									pop up indicating														"Payment Information is missing" 																		observation 7)
and set "quantity"=1,enter a valid "name","phone",		
"empty payment information"						
	
11.Select pizza = "Small pizza with 1 toppings",set		:An error message has to be displayed or	Fail (see 
"quantity"=1,"toppings1"=Olives, 				GUI should not allow to select Toppings2	observation 8)
"toppings2"=Mushrooms,enter valid "name","phone" 											
11.1Repeat steps(11) with pizza type selected as 		:An error message has to be displayed or	Fail (see 	"Large - no toppings"						GUIshould not allow to select both Toppings1	observation 8)										and Toppings 2 						11.2Repeat steps(10) with pizza type selected as		:An error message has to be displayed or	Fail (see
"Large - 2 toppings", and set both "toppings1"			GUI should not allow to select same topping	observation 1)
and "toppings2" to Olives					for both Toppings1 and Toppings2



Requirements:
-------------------------------- 
 
Maximum Quantity: There are currently no limits set, but for boundary testing let’s assume 5.
Max length of field names: Name,Email and Phone is currently set at 100.

Observations:
-------------------------------- 

1. App lets the user select same topping for both Toppings1 and Toppings2. 
2. Payment Information - Its a radio button and only one option should be selectable. I am able to place an order with both options selected
3. Quantity field is allowing negative quantity which seems to be incorrect 
4. Clicking the Reset button does not reset fields Toppings 1 and Toppings 2
5. Email field does not check for valid domain name, and accepts an email address without @ symbol/
6. Phone number field accepts alphanumeric characters
7. Fields Pizza and Payment Information should be mandatory. If either of the fields is missing the order can still be placed
8. If a guest selects a Pizza with 1 Topping, only one of Toppings drop down should be enabled


