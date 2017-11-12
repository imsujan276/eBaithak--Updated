# eBaithak 
## The video and group text chat application made in JAVA SPRING using ajax and WebRTC

## FEATURES
1. User register/signin 
2. Create chat group (baithak) 
3. Add friends to the chat group 
4. Chat with friends 
5. Leave/Delete Group 
6. Create a video call (using WebRTC) 

## Feel free to contribute in this project

## NOTE:
``Import the application in the Spring Tools Suite (STS) ``
1. create a database : ``ebaithak``
2. change in webapp/WEB-INF/spring/appServlet/servlet-context.xml.
	        ``<property name="hibernateProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">create</prop> // to create fresh tables
                AND
                <prop key="hibernate.hbm2ddl.auto">Validate</prop> // after creating the tables in database
            </props>
        </property>``
3. Change image upload path in ``UploadController`` as per your system
4. Import the jar files ( included in the xtra folder ) to the project path

 # THANK YOU