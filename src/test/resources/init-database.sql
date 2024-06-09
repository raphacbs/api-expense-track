CREATE TABLE IF NOT EXISTS Users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    google_account_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS category (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    color VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS payment_method (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    is_deleted BOOLEAN NOT NULL,
    user_id UUID REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS money_box (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bank VARCHAR(255) NOT NULL,
    goal VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL,
    target_date DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN NOT NULL,
    user_id UUID REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS budget (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL,
    user_id UUID REFERENCES Users(id),
    category_id UUID REFERENCES category(id),
    amount DECIMAL NOT NULL,
    notes VARCHAR(255),
    is_deleted BOOLEAN NOT NULL,
    parent_id UUID
);

CREATE TABLE IF NOT EXISTS transaction (
    id UUID PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    value DECIMAL NOT NULL,
    total_value DECIMAL NOT NULL,
    payment_date DATE,
    due_date DATE NOT NULL,
    is_deleted BOOLEAN NOT NULL,
    installments INTEGER NOT NULL,
    current_installments INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    parent_id UUID,
    is_recurring BOOLEAN,
    merchant VARCHAR(255),
    status VARCHAR(255),
    tags VARCHAR(255),
    user_id UUID REFERENCES Users(id),
    category_id UUID REFERENCES category(id),
    payment_method UUID REFERENCES payment_method(id),
    group_id UUID
);

CREATE TABLE IF NOT EXISTS recurring_transaction (
    id UUID PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    value DECIMAL NOT NULL,
    is_fixed_value BOOLEAN NOT NULL,
    frequency VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    created_at DATE NOT NULL,
    last_processing TIMESTAMP,
    is_deleted BOOLEAN NOT NULL,
    category_id UUID REFERENCES category(id),
    user_id UUID REFERENCES Users(id),
    group_id UUID,
    payment_method UUID REFERENCES payment_method(id),
    money_box_id UUID REFERENCES money_box(id),
    budget_id UUID REFERENCES budget(id),
    due_date DATE NOT NULL,
    last_due_date DATE NOT NULL,
    merchant VARCHAR(255),
    tags VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS recurring_budget (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES Users(id),
    category_id UUID REFERENCES category(id),
    amount DECIMAL NOT NULL,
    frequency VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    notes VARCHAR(255),
    is_deleted BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction_budget (
    id UUID PRIMARY KEY,
    transaction_id UUID REFERENCES transaction(id),
    budget_id UUID REFERENCES budget(id)
);

CREATE TABLE IF NOT EXISTS transaction_money_box (
    id UUID PRIMARY KEY,
    transaction_id UUID REFERENCES transaction(id),
    money_box_id UUID REFERENCES money_box(id)
);