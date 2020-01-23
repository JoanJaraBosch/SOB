<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Force database installation</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database initialization in progress</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "sob_grup_04";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname + ";create=true", "sob", "sob");
            Statement stmt = con.createStatement();
            
            /* drop tables if they exist */
            String tablenames[] = new String[] {
                "ROOM",
                "TENANT",
                "RENTER",
                /*"DISCOUNT_CODE",
                "MICRO_MARKET",*/
            };
            for (String tablename : tablenames) {
                try { 
                    stmt.executeUpdate("DROP TABLE " + tablename); 
                    out.println("<pre> -> DROP TABLE " + tablename + "<pre>");
                } catch (SQLException e) { 
                    // table didn't exist; it is the first time
                }
            }
            
            /* creating tables */
            String tables[] = new String[] {
                "CREATE TABLE TENANT (ID INTEGER NOT NULL, SMOKER BOOLEAN, PET BOOLEAN,NAME VARCHAR(20) NOT NULL, SURNAME VARCHAR(20) NOT NULL, SEX VARCHAR(20) NOT NULL, AGE INTEGER NOT NULL,EMAIL VARCHAR(20) NOT NULL, PRIMARY KEY (ID))",
                "CREATE TABLE RENTER (ID INTEGER NOT NULL, SEX VARCHAR(10) NOT NULL,NAME VARCHAR(10) NOT NULL, SURNAME VARCHAR(10) NOT NULL, ADREÇA VARCHAR(400) NOT NULL, CITY VARCHAR(20) NOT NULL, EMAIL VARCHAR(30) NOT NULL, SMOKER BOOLEAN, PET BOOLEAN, AGE_MAX INTEGER NOT NULL, AGE_MIN INTEGER NOT NULL,ZIP INTEGER NOT NULL,PRIMARY KEY (ID))",
                "CREATE TABLE ROOM (ROOM_ID INTEGER NOT NULL, DESCRIPTION VARCHAR(400) NOT NULL, ADREÇA VARCHAR(400) NOT NULL,CITY VARCHAR(25), PRICE DECIMAL(8, 4),SIMPLE_ROOM BOOLEAN, INDOOR BOOLEAN, FURNISHED BOOLEAN, ZIPCODE INTEGER NOT NULL,RENTER_ID INT, IMATGE VARCHAR(100) NOT NULL, TENANT_ID INT , FOREIGN KEY (TENANT_ID) REFERENCES TENANT(ID), FOREIGN KEY (RENTER_ID) REFERENCES RENTER(ID),PRIMARY KEY (ROOM_ID))",
                /*"CREATE TABLE DISCOUNT_CODE (DISCOUNT_CODE CHAR(1) NOT NULL, RATE DECIMAL(4, 2), PRIMARY KEY (DISCOUNT_CODE))",
                "CREATE TABLE MICRO_MARKET (ZIP_CODE VARCHAR(10) NOT NULL, RADIUS DOUBLE, AREA_LENGTH DOUBLE, AREA_WIDTH DOUBLE, PRIMARY KEY (ZIP_CODE))",
            */
                };
            for (String table : tables) {
                try {
                    stmt.execute(table);
                } catch(SQLException e) {
                    out.println("<span class='error'>Error creating table: " + table + "</span>");
                    out.println(e.getCause());
                    return;
                }
                out.println("<pre> -> " + table + "<pre>");
            }
          
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String data[] = new String[]{
                "INSERT INTO TENANT VALUES(1, TRUE, TRUE,'sob', 'sob','unisex', 20, 'sob@gmail.com')",
                "INSERT INTO TENANT VALUES(2, FALSE, TRUE,'Joan', 'Jara','home', 23, 'joan.jara@gmail.com')",
                "INSERT INTO TENANT VALUES(3, TRUE, FALSE,'Aleix', 'Iglesias','dona', 50, 'maricarmen@gmail.com')",
                "INSERT INTO TENANT VALUES(4, FALSE, FALSE,'Marc', 'Ferrer','home', 70, 'marcferrer@gmail.com')",
                "INSERT INTO TENANT VALUES(5, TRUE, TRUE,'Antonio', 'Giro','unisex', 20, 'antonio@gmail.com')",
          
                "INSERT INTO RENTER VALUES(1, 'unisex','Aleix', 'Mariner', 'Cambrils, carrer tenenant', 'Cambrils', 'aleix.mariner@gmail.com', TRUE, TRUE, 50, 18,45900)",
                "INSERT INTO RENTER VALUES(2, 'dona','Mario', 'Bros', 'Carrer dels morts', 'Reus', 'fals1@gmail.com', TRUE, FALSE, 50, 23,34567)",
                "INSERT INTO RENTER VALUES(3, 'home','Luigi', 'Bros', 'Carrer pedrastela', 'Reus', 'fals2@gmail.com', FALSE, TRUE, 80, 18,43209)",
                "INSERT INTO RENTER VALUES(4, 'home','Esvalda', 'Antoniet', 'Avinguda diagonal', 'Dubai', 'fals3@gmail.com', FALSE, FALSE, 50, 50,50034)",
                
                "INSERT INTO ROOM VALUES (1, 'Habitacio gran i neta al centre de Reus.', 'Carrer monestir de poblet num28','Reus', 900.00, TRUE, FALSE, TRUE, 43205,1,'https://okdiario.com/img/viajes/2016/11/19/convertir-el-dormitorio-en-una-suite.jpg' ,null)",
                "INSERT INTO ROOM VALUES (2, 'Habitacio ambientada en del estil japones.', 'Carrer ripoll a les afores de Reus','Reus', 400.00, FALSE, FALSE, TRUE, 43206,2, 'https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2017/11/03/15097220522736.jpg',null)",
                "INSERT INTO ROOM VALUES (3, 'Habitacio en un gratacels de dubai on es veu la posta de sol', 'Avinguda de dubai falsa','Dubai', 1000.66, FALSE, FALSE, FALSE, 90205,3, 'https://d1vp8nomjxwyf1.cloudfront.net/wp-content/uploads/sites/156/2017/09/11143942/C2_-Superior-room.jpg',null)",
                "INSERT INTO ROOM VALUES (4, 'Habitacio petita de 40m2 a cambrils.', 'Riera de cambrils','Cambrils', 200.45, TRUE, TRUE, TRUE, 43205,4,'https://d1vp8nomjxwyf1.cloudfront.net/wp-content/uploads/sites/156/2017/09/11143942/C2_-Superior-room.jpg' ,null)",
                /*"INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (2, 'M', '95035', 'New Enterprises', '9754 Main Street', 'P.O. Box 567', 'Miami', 'FL', '305-555-0148', '305-555-0149', 'www.new.example.com', 50000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (25, 'M', '85638', 'Wren Computers', '8989 Red Albatross Drive', 'Suite 9897', 'Houston', 'TX', '214-555-0133', '214-555-0134', 'www.wrencomp.example.com', 25000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (3, 'L', '12347', 'Small Bill Company', '8585 South Upper Murray Drive', 'P.O. Box 456', 'Alanta', 'GA', '555-555-0175', '555-555-0176', 'www.smallbill.example.com', 90000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (36, 'H', '94401', 'Bob Hosting Corp.', '65653 Lake Road', 'Suite 2323', 'San Mateo', 'CA', '650-555-0160', '650-555-0161', 'www.bobhostcorp.example.com', 65000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (106, 'L', '95035', 'Early CentralComp', '829 E Flex Drive', 'Suite 853', 'San Jose', 'CA', '408-555-0157', '408-555-0150', 'www.centralcomp.example.com', 26500)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (149, 'L', '95117', 'John Valley Computers', '4381 Kelly Valley Ave', 'Suite 77', 'Santa Clara', 'CA', '408-555-0169', '408-555-0167', 'www.johnvalley.example.com', 70000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (863, 'N', '94401', 'Big Network Systems', '456 444th Street', 'Suite 45', 'Redwood City', 'CA', '650-555-0181', '650-555-0180', 'www.bignet.example.com', 25000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (777, 'L', '48128', 'West Valley Inc.', '88 Northsouth Drive', 'Building C', 'Dearborn', 'MI', '313-555-0172', '313-555-0171', 'www.westv.example.com', 100000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (753, 'H', '48128', 'Zed Motor Co', '2267 NE Michigan Ave', 'Building 21', 'Dearborn', 'MI', '313-555-0151', '313-555-0152', 'www.parts@ford.example.com', 5000000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (722, 'N', '48124', 'Big Car Parts', '52963 Notouter Dr', 'Suite 35', 'Detroit', 'MI', '313-555-0144', '313-555-0145', 'www.bparts.example.com', 50000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (409, 'L', '10095', 'Old Media Productions', '4400 527th Street', 'Suite 562', 'New York', 'NY', '212-555-0110', '212-555-0111', 'www.oldmedia.example.com', 10000)",
                "INSERT INTO " + dbname + ".CUSTOMER (CUSTOMER_ID, DISCOUNT_CODE, ZIP, \"NAME\", ADDRESSLINE1, ADDRESSLINE2, CITY, \"STATE\", PHONE, FAX, EMAIL, CREDIT_LIMIT) VALUES (410, 'M', '10096', 'Yankee Computer Repair Ltd', '9653 211th Ave', 'Floor 4', 'New York', 'NY', '212-555-0191', '212-555-0197', 'www.nycompltd@repair.example.com', 25000)",
                
                "INSERT INTO " + dbname + ".DISCOUNT_CODE (DISCOUNT_CODE, RATE) VALUES ('H', 16.00)",
                "INSERT INTO " + dbname + ".DISCOUNT_CODE (DISCOUNT_CODE, RATE) VALUES ('M', 11.00)",
                "INSERT INTO " + dbname + ".DISCOUNT_CODE (DISCOUNT_CODE, RATE) VALUES ('L', 7.00)",
                "INSERT INTO " + dbname + ".DISCOUNT_CODE (DISCOUNT_CODE, RATE) VALUES ('N', 0.00)",

                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('95051', 255.59, 689.856, 478.479)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('94043', 157.869, 385.821, 147.538)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('85638', 758.648, 328.963, 482.164)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('12347', 475.965, 385.849, 146.937)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('94401', 368.386, 285.848, 173.794)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('95035', 683.396, 472.859, 379.757)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('95117', 755.778, 547.967, 468.858)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('48128', 684.675, 475.854, 408.074)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('48124', 753.765, 487.664, 456.632)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('10095', 1987.854, 975.875, 865.681)",
                "INSERT INTO " + dbname + ".MICRO_MARKET (ZIP_CODE, RADIUS, AREA_LENGTH, AREA_WIDTH) VALUES ('10096', 1876.766, 955.666, 923.556)",
*/          
};
            for (String datum : data) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
