CREATE TABLE IF NOT EXISTS Taco_Order (
    id SERIAL PRIMARY KEY,
    delivery_Name VARCHAR(50) NOT NULL,
    delivery_Street VARCHAR(50) NOT NULL,
    delivery_City VARCHAR(50) NOT NULL,
    delivery_State VARCHAR(2) NOT NULL,
    delivery_Zip VARCHAR(10) NOT NULL,
    cc_number VARCHAR(16) NOT NULL,
    cc_expiration VARCHAR(5) NOT NULL,
    cc_cvv VARCHAR(3) NOT NULL,
    placed_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    taco_order BIGINT NOT NULL,
    taco_order_key BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (taco_order) REFERENCES Taco_Order(id)
);

CREATE TABLE IF NOT EXISTS Ingredient_Ref (
    ingredient VARCHAR(4) NOT NULL,
    taco BIGINT NOT NULL,
    taco_key BIGINT NOT NULL,
    FOREIGN KEY (ingredient) REFERENCES Ingredient(id)
);

CREATE TABLE IF NOT EXISTS Ingredient (
    id VARCHAR(4) PRIMARY KEY NOT NULL,
    name VARCHAR(25) NOT NULL,
    type smallint NOT NULL
);
alter table Taco
add foreign key (taco_order) references Taco_Order(id);
alter table Ingredient_Ref
add foreign key (ingredient) references Ingredient(id);