CREATE TABLE user (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    phoneNumber VARCHAR(15),
    email VARCHAR(50),
    dob DATE,
    password VARCHAR(15),
    role VARCHAR(15)
);

CREATE TABLE customer (
    userId INT PRIMARY KEY,
    mailingStreet VARCHAR(50),
    mailingCity VARCHAR(50),
    mailingState VARCHAR(2),
    mailingZip INT,
    FOREIGN KEY (userId) REFERENCES user(userId)
);

CREATE TABLE dentist (
    userId INT PRIMARY KEY,
    specialization VARCHAR(100),
    FOREIGN KEY (userId) REFERENCES user(userId)
);

CREATE TABLE surgery_office (
    officeId INT PRIMARY KEY AUTO_INCREMENT,
    officeName VARCHAR(50),
    address VARCHAR(100),
    phoneNumber VARCHAR(15)
);

CREATE TABLE appointment (
    appointmentId INT PRIMARY KEY AUTO_INCREMENT,
    customerId INT,
    dentistId INT,
    appDateTime DATETIME,
	officeId INT,
    notes VARCHAR(255),
    status VARCHAR(10),
    FOREIGN KEY (customerId) REFERENCES user(userId),
    FOREIGN KEY (dentistId) REFERENCES user(userId),
	FOREIGN KEY (officeId) REFERENCES surgery_office(officeId)
);

CREATE TABLE invoice (
    invoiceId INT PRIMARY KEY AUTO_INCREMENT,
    appointmentId INT,
    issuedDate DATETIME,
    totalAmount DOUBLE,
    paidDate DATETIME,
    status VARCHAR(10),
    FOREIGN KEY (appointmentId) REFERENCES appointment(appointmentId)
);

CREATE TABLE service (
    serviceId INT PRIMARY KEY AUTO_INCREMENT,
    serviceName VARCHAR(100),
    price DOUBLE
);

CREATE TABLE invoice_item (
    invoiceId INT,
    serviceId INT,
    PRIMARY KEY (invoiceId, serviceId),
    FOREIGN KEY (invoiceId) REFERENCES invoice(invoiceId),
    FOREIGN KEY (serviceId) REFERENCES service(serviceId)
);

-- create dummy data
-- Insert data into user table
INSERT INTO user (firstName, lastName, phoneNumber, email, dob, password, role) VALUES
('John', 'Doe', '123-456-7890', 'johndoe@example.com', '1985-06-15', 'password123', 'customer'),
('Jane', 'Smith', '234-567-8901', 'janesmith@example.com', '1990-08-22', 'pass1234', 'customer'),
('Alice', 'Johnson', '345-678-9012', 'alicejohnson@example.com', '1975-12-05', 'alicepass', 'dentist'),
('Bob', 'Brown', '456-789-0123', 'bobbrown@example.com', '1982-02-17', 'bobpassword', 'dentist'),
('Charlie', 'Davis', '567-890-1234', 'charliedavis@example.com', '2000-04-30', 'charlie123', 'customer');

-- Insert data into customer table
INSERT INTO customer (userId, mailingStreet, mailingCity, mailingState, mailingZip) VALUES
(1, '123 Maple St', 'Springfield', 'IL', 62704),
(2, '456 Oak Ave', 'Madison', 'WI', 53703),
(5, '789 Pine Blvd', 'Austin', 'TX', 73301);

-- Insert data into dentist table
INSERT INTO dentist (userId, specialization) VALUES
(3, 'Orthodontics'),
(4, 'Cosmetic Dentistry');

-- Insert data into surgery_office table
INSERT INTO surgery_office (officeName, address, phoneNumber) VALUES
('Downtown Clinic', '123 Main St, Springfield', '123-555-7890'),
('Madison Dental Center', '456 Elm St, Madison', '234-555-6789'),
('Austin Smile Care', '789 Cedar Ave, Austin', '345-555-5678');

-- Insert data into appointment table
INSERT INTO appointment (customerId, dentistId, appDateTime, officeId, notes, status) VALUES
(1, 3, '2024-11-10 10:00:00', 1, 'Routine check-up', 'Scheduled'),
(2, 4, '2024-11-15 14:00:00', 2, 'Teeth whitening', 'Scheduled'),
(5, 4, '2024-11-20 09:30:00', 3, 'Consultation for braces', 'Scheduled');

-- Insert data into invoice table
INSERT INTO invoice (appointmentId, issuedDate, totalAmount, paidDate, status) VALUES
(1, '2024-11-10 11:00:00', 150.00, '2024-11-10 11:30:00', 'Paid'),
(2, '2024-11-15 15:00:00', 200.00, NULL, 'Unpaid'),
(3, '2024-11-20 10:00:00', 120.00, '2024-11-20 10:30:00', 'Paid');

-- Insert data into service table
INSERT INTO service (serviceName, price) VALUES
('Teeth Cleaning', 100.00),
('Teeth Whitening', 200.00),
('Consultation', 50.00),
('Braces Consultation', 120.00);

-- Insert data into invoice_item table
INSERT INTO invoice_item (invoiceId, serviceId) VALUES
(1, 1),  -- Invoice 1 for Teeth Cleaning
(2, 2),  -- Invoice 2 for Teeth Whitening
(3, 4);  -- Invoice 3 for Braces Consultation



--1. Display the list of ALL Dentists registered in the system, sorted in ascending order of their lastNames
SELECT u.userId, u.firstName, u.lastName, u.phoneNumber, u.email, d.specialization
FROM user u
JOIN dentist d ON u.userId = d.userId
WHERE role='dentist'
ORDER BY u.lastName ASC;

--2. Display the list of ALL Appointments for a given Dentist by their dentist_Id number. Include in the result, the Patient information.
SELECT a.appointmentId, a.appDateTime, a.notes, a.status,
       u.userId AS patientId, u.firstName AS patientFirstName, u.lastName AS patientLastName,
       u.phoneNumber AS patientPhoneNumber, u.email AS patientEmail
FROM appointment a
JOIN user u ON a.customerId = u.userId
JOIN dentist d ON a.dentistId = d.userId
WHERE a.dentistId = 4;

--3. Display the list of ALL Appointments that have been scheduled at a Surgery Location
SELECT 
    a.appointmentId,
    a.appDateTime,
    a.notes,
    a.status,
    u.firstName AS customerFirstName,
    u.lastName AS customerLastName,
    d.firstName AS dentistFirstName,
    d.lastName AS dentistLastName,
    s.officeName,
    s.address AS officeAddress,
    s.phoneNumber AS officePhoneNumber
FROM 
    appointment a
JOIN 
    surgery_office s ON a.officeId = s.officeId
JOIN 
    user u ON a.customerId = u.userId
JOIN 
    user d ON a.dentistId = d.userId
ORDER BY 
    a.appDateTime;
	
--4. Display the list of the Appointments booked for a given Patient on a given Date.
SELECT 
    a.appointmentId,
    a.appDateTime,
    a.notes,
    a.status,
    d.firstName AS dentistFirstName,
    d.lastName AS dentistLastName,
    s.officeName,
    s.address AS officeAddress
FROM 
    appointment a
JOIN 
    user u ON a.customerId = u.userId
JOIN 
    user d ON a.dentistId = d.userId
JOIN 
    surgery_office s ON a.officeId = s.officeId
WHERE 
    a.customerId = 1 
    AND DATE(a.appDateTime) = '2024-11-10';