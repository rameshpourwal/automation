# automation
Prerequisite:
1.	Install the scoop and allure 

Install Scoop:
	Open the powershell and hit the below command 
      iex (new-object net.webclient).downloadstring('https://get.scoop.sh') 

Install Allure
	Open the power-shell and hit the below command 
  scoop install allure


2.	Install the elastic search and kibana

Install Elastic Search:

	Download the elastic search from the https://www.elastic.co/start 
	Install the installer as services.

3.	Install the java 
Follow article: https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html

4.	Install Maven 
Follow article: https://www.mkyong.com/maven/how-to-install-maven-in-windows/

Maintain the TestCase.xls File
-All the created test cases added into the TestCase.xls file and Set the flag as yes/No based on the test run.

 




Run the Project
Setting the following maven opts:
1.	browserName= chrome ,firefox   browser name
2.	parallel =True/false  for parallel execution 
3.	executionType= api/web  test platform like API , web application 

Setting maven command:
 mvn clean test 



 


Features 
1.	Allure reporting :
1.1	Detailed logs for each test case.
1.2	Integrated screen shot at failures step.
1.3	Report generate for parallel processing
2.	Log4j
2.1	customize logs
3.	Elastic search 
3.1	Every test case execution detailed send to elastic search with parameter class-name, result, time of execution and description of test case.
4.	Kibana :
4.1	Create dashboard for test execution status.
5.	Parallel execution 
5.1	Framework is able to execute same test case on different browsers in parallel fashion.
6.	Web/API/Mobile:
6.1	Framework is capable to perform web and API application
7.	REST API
7.1	Rest API integration.
8.	Sonar-Lint 
8.1 Sonar lint integrated for coding standard management.

