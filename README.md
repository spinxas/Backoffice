Backoffice
==========

SpringMVC and AngularJS combined

To compile the project, you will have to use the maven plugin for tomcat. To do that you will have to:

1. Right click the project
2. Select Run as -> Run configurations...
3. Create a new maven build
4. Add to Goals the following: clean install tomcat7:run
5. Run

If you have tomcat installed in your system, you will need to go to the installation folder and conf folder you will have to adjust context.xml file to following:
<?xml version='1.0' encoding='utf-8'?>
<Context>
	<WatchedResource>WEB-INF/web.xml</WatchedResource>

    <Resource
            factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
            name="jdbc/tomcatDataSource"
            auth="Container"
            type="javax.sql.DataSource"
            initialSize="1"
            maxActive="20"
            maxIdle="3"
            minIdle="1"
            maxWait="5000"
            username="root"
            password="password"
            driverClassName="com.mysql.jdbc.Driver"
            validationQuery="SELECT 'OK'"
            testWhileIdle="true"
            testOnBorrow="true"
            numTestsPerEvictionRun="5"
            timeBetweenEvictionRunsMillis="30000"
            minEvictableIdleTimeMillis="60000"
            url="jdbc:mysql://localhost:3306/uaicontacts" />

</Context>
