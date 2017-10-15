# eBaithak 
## a simple video and group chat application made in JAVA SPRING using ajax and WebRTC

## FEATURES
1. User register/signin - ``done``
2. Create chat group (baithak) - ``done``
3. Add friends to the chat group - ``done``
4. Chat with friends  - ``done``
5. Leave/Delete Group - ``done``
6. Create a video call (using nextRTC) - ``ERROR - solving`` 
	``https://stackoverflow.com/questions/46753211/got-an-error-while-configuring-the-nextrtc-websocket-error-during-websocket-ha``

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
3. Change image upload path in ``UploadController`` as per your need


 # THANK YOU