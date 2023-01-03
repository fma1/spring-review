create table if not exists transactions
(
    id uuid default random_uuid() primary key,
    amount numeric,
    reference varchar(255),
    bank_slogan varchar(255),
    receiving_user varchar(255),
    `timestamp` timestamp with time zone not null,
    version int
);