CREATE TABLE irrigation_audit (
  id INT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  plot_id INT NOT NULL,
  device_id INT NOT NULL,
  slots_time INT NOT NULL,
  status VARCHAR (10) NOT NULL,
  alert_id INT NULL,
  message VARCHAR (2000) NULL,
  start_time DATE NOT NULL,
  end_time DATE NULL,
  CONSTRAINT audit_pkey PRIMARY KEY (id)
);
