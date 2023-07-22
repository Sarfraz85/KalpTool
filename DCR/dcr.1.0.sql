-- Create the admin schema and set it as the default schema
CREATE SCHEMA IF NOT EXISTS admin;
SET search_path TO admin;

-- Create the users table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL -- 'admin' or 'user'
);

-- Create the clients table
CREATE TABLE clients (
    client_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255),
    admin_id INT REFERENCES admin.users(user_id) ON DELETE CASCADE
);

-- Create the tasks schema and set it as the default schema
CREATE SCHEMA IF NOT EXISTS tasks;
SET search_path TO tasks;

-- Create the tasks table
CREATE TABLE tasks (
    task_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    client_id INT REFERENCES admin.clients(client_id) ON DELETE CASCADE,
    assigned_to INT REFERENCES admin.users(user_id) ON DELETE SET NULL,
    status VARCHAR(20) NOT NULL -- e.g., 'Pending', 'In Progress', 'Completed', etc.
);

-- Create the sub_tasks table
CREATE TABLE sub_tasks (
    sub_task_id SERIAL PRIMARY KEY,
    task_id INT REFERENCES tasks.tasks(task_id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL -- e.g., 'Pending', 'In Progress', 'Completed', etc.
);

-- Create the bills table
CREATE TABLE bills (
    bill_id SERIAL PRIMARY KEY,
    client_id INT REFERENCES admin.clients(client_id) ON DELETE CASCADE,
    total_amount NUMERIC(10, 2) NOT NULL,
    payment_status VARCHAR(20) NOT NULL -- e.g., 'Paid', 'Pending', 'Overdue', etc.
);
