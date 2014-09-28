CREATE TABLE notification (
  cleaning_user      VARCHAR2(100) CONSTRAINT pk_cleaning_notify PRIMARY KEY,
  notification_email VARCHAR2(255) CONSTRAINT notification_email_nn NOT NULL
);